package src.codegen;

import src.parser.symboltable.*;
import java.util.*;

  public int numberOfArgs = 0;
  public int numberOfTempObjects = 0;  
  public int r5Bottom = 0;
  public int r6Top = -1;
  public int r7PC = 0;//won't be able to get until later on
  
  public static MemoryTable dMem = new MemoryTable();
  int[] mainArgumentList; 
  
  public RunTimeStack(){
  } 
  //write the main functions arguments to dMem
  public RunTimeStack(int[] inputArgs){	  
	RunTimeStack.dMem.setArguments(inputArgs);
  }
  
  //This is only temporary until I can get multiple frames working
  public void printStack(){
    
//    for(int k = 0; k < this.r6Top + 1; k++){
//        System.out.println(k + ": " + RunTimeStack.dMem.getItemAt(k));
//    }  
//    System.out.println("--------------------");  
//    System.out.println("--------------------");  
//    System.out.println("--------------------");
//   
    boolean atBottom = (this.r5Bottom == 0);
    if(atBottom){
        printFrame();
    }
    while(!atBottom){
        printFrame();
        atBottom = this.popFrame();
        if(atBottom){
            printFrame();
        }        
    }
  }
  
  private void printFrame(){
    this.printReturnValue();
    this.printArguments();
    this.printStatus();
    this.printRegisters();
    //this.printTempObjects();      
  }
  
  private void printReturnValue(){
    System.out.println("--------------------");  
    System.out.println("|Return value: " + this.getDMemReturnValue());
  }
  private void printArguments(){
    int[] argumentArray = this.getDMemArguments();      
    for(int k = 0; k < getNumberOfArgs();k++){
        if(argumentArray[k] != -1){
        System.out.println("--------------------");  
        System.out.println("|Argument" + k + ": " + argumentArray[k]);
        }       
    }
  }
  private void printStatus(){
    System.out.println("--------------------");  
    System.out.println("|Status Link: " + this.getDmemStatus());
  }
  private void printRegisters(){
    int[] registerArray = this.getDmemRegisters();       
    for(int l = 0; l < 7; l++){
        //if(registerArray[l] != -1){
        System.out.println("--------------------");  
        System.out.println("|Register" + (l + 1) + ": " + registerArray[l]);
        //}
    }
  }
  private void printTempObjects(){      
    List<Integer> tempObjectArray = this.getTempObjects();      
    for(int k = 0; k < this.numberOfTempObjects;k++){
        System.out.println("--------------------");  
        System.out.println("|Temp Object" + k + ": " + tempObjectArray.get(k));       
    }
    System.out.println("--------------------");
  }    
  public boolean popFrame(){
    //need to test
    if(this.r5Bottom != 0){
        this.r6Top = this.r5Bottom - 1;
    }
    this.r5Bottom = this.getDmemRegisterAt(5);
    return(this.r5Bottom == 0);
  }
  public void pushFrame(String inputName,int[] inputArgs,int[] inputRegisters){
    //should be able to get from symbol table  
    this.numberOfArgs = inputArgs.length;
    if(this.r6Top != 0){
        this.r5Bottom = this.r6Top + 1; 
    }
    this.numberOfArgs = inputArgs.length;
    RunTimeStack.dMem.writeMemoryItem();//return value not assigned yet, -1
    RunTimeStack.dMem.setArguments(inputArgs);//arguments
    RunTimeStack.dMem.writeMemoryItem();//status/return address, -1
    RunTimeStack.dMem.setArguments(inputRegisters);//registers 1-4
    RunTimeStack.dMem.writeMemoryItem(this.r5Bottom);//register r5Bottom
    int tempTop = 9 + this.numberOfArgs + this.r6Top;
    RunTimeStack.dMem.writeMemoryItem(tempTop);
    this.r6Top = tempTop;   
    RunTimeStack.dMem.writeMemoryItem(this.r7PC);
    
    //will setTempObjects() when we evaluate function	
  }
  public int getNumberOfArgs(){
      return this.r6Top - this.r5Bottom - 8;
  }
  public int getDMemReturnValue(){
    //bottom is the return address
    return RunTimeStack.dMem.getItemAt(getDmemRegisterAt(5));
  } 
  public void setDmemReturnValue(int inputReturnValue){
	//bottom is the return address	
    RunTimeStack.dMem.setItemAt(this.r5Bottom, inputReturnValue);
  }
  
  public int[] getDMemArguments(){
    int argPointer = getDmemRegisterAt(5) + 1;  
    int[] returnArray = new int[getNumberOfArgs()];
    for(int i = 0; i < getNumberOfArgs(); i++){
        returnArray[i] = RunTimeStack.dMem.getItemAt(argPointer);
	argPointer++;
    }
    return returnArray;
  }
  public void setDMemArguments(int[] argv){
    int argPointer = this.r5Bottom + 1;  
    for(int i = 0; i < this.numberOfArgs; i++){
        RunTimeStack.dMem.setItemAt(argPointer, argv[i]);
	argPointer++;
    }
  }
  
  public int getDmemStatus(){
    return RunTimeStack.dMem.getItemAt(this.r5Bottom + this.numberOfArgs + 1);
  }
  public void setDmemStatus(int inputVal){
    RunTimeStack.dMem.setItemAt(this.r5Bottom + this.numberOfArgs + 1, inputVal);
  }    
 
  public int getDmemRegisterAt(int registerNumber){
    return RunTimeStack.dMem.getItemAt(this.r6Top - this.numberOfTempObjects - 7 + registerNumber);
  }
  public void setDmemRegisterAt(int registerNumber, int inputVal){
    RunTimeStack.dMem.setItemAt(this.r5Bottom + this.numberOfArgs + 1 + registerNumber, inputVal);
  }   
  public int[] getDmemRegisters(){
    //TODO: needs to be able to get the number of args from the 
    int[] returnArray = new int[7]; //registers 1-4 and r5Bottom
    int rPointer = this.r6Top - 6;  
    for(int i = 0; i < 7; i++){
        returnArray[i] = RunTimeStack.dMem.getItemAt(rPointer);
        rPointer++;
    }
    return returnArray;
  }
  public void setDmemRegisters(int[] argv){
    int rPointer = this.r5Bottom + this.numberOfArgs + 2;  
    for(int i = 0; i < 7; i++){
        RunTimeStack.dMem.setItemAt(rPointer, argv[i]);
        rPointer++;
    }
  }
  
  public List<Integer> getTempObjects(){
    //calculate based on size from bottom up
    List<Integer> returnArray = new ArrayList<>();
    int rPointer = this.r5Bottom + this.numberOfArgs + 9;  
    for(int i = rPointer; i < (rPointer + numberOfTempObjects); i++){
        returnArray.add(RunTimeStack.dMem.getItemAt(i));
    }
    return returnArray;	
  }   
  public void setTempObjects(int[] inputValues){
    this.numberOfTempObjects = inputValues.length;
    RunTimeStack.dMem.setArguments(inputValues);
    this.r6Top += inputValues.length;
    int tempTop = 9 + this.numberOfArgs + this.r6Top;
    setDmemRegisterAt(6 + this.r6Top, tempTop);
    this.r6Top = tempTop;   
  }
}

