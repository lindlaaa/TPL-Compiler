package src.codegen;
//import src.parser.symboltable;

import java.util.*;

public class RunTimeStack {
  //these will be implemented when we create iMem
  public int numberOfArgs = 0;//will be able to find with the symbol table
  public int numberOfTempObjects = 0;  
  public int r5Bottom = 0;
  public int r6Top = 0;
  public int r7PC = 0;//won't be able to get until later on
  
  public static MemoryTable dMem = new MemoryTable();
  int[] mainArgumentList; 
  
  public RunTimeStack(){
  } 
  //write the main functions arguments to dMem
  public RunTimeStack(int[] inputArgs){	  
	RunTimeStack.dMem.setArguments(inputArgs);
	this.r5Bottom = RunTimeStack.dMem.getTopIndex();
	this.r6Top = RunTimeStack.dMem.getTopIndex();
  }
  public void printStack(){
    for(int k = 0; k < RunTimeStack.dMem.getTopIndex();k++){
        System.out.println("--------------------");     
        System.out.println("|         " + 
                RunTimeStack.dMem.getItemAt(k) +
                "|");
    }
    System.out.println("--------------------");  
    
//    this.printReturnValue();
//    this.printArguments();
//    this.printStatus();
//    this.printRegisters();
//    this.printTempObjects();
  }
  //This is only temporary until I can get multiple frames working
  public void printIndividualStackFrame(){
    this.printReturnValue();
    this.printArguments();
    this.printStatus();
    this.printRegisters();
    this.printTempObjects();       
  }
  
  private void printReturnValue(){
    System.out.println("--------------------");  
    System.out.println("|Return value: " + this.getDMemReturnValue() + "   |");
  }
  private void printArguments(){
    for(int k = 0; k < this.numberOfArgs;k++){
        int[] argumentArray = this.getDMemArguments();      
        System.out.println("--------------------");  
        System.out.println("|Argument" + k + ": " + argumentArray[k] + "       |");       
    }
  }
  private void printStatus(){
    System.out.println("--------------------");  
    System.out.println("|Status Link: " + this.getDmemStatus() + "    |");
  }
  private void printRegisters(){
    for(int l = 0; l < 5; l++){
        int[] registerArray = this.getDmemRegisters();      
        System.out.println("--------------------");  
        System.out.println("|Register" + (l + 1) + ": " + registerArray[l] + "       |");       
    }
  }
  private void printTempObjects(){
    for(int k = 0; k < this.numberOfTempObjects;k++){
        List<Integer> tempObjectArray = this.getTempObjects();
        System.out.println("--------------------");  
        System.out.println("|Temp Object" + k + ": " + tempObjectArray.get(k) + "    |");       
    }
    System.out.println("--------------------");
  }    
  public void popFrame(){
    this.r6Top = this.r5Bottom - 1;
    this.r5Bottom = this.getDmemRegisterAt(5);	
  }
  public void pushFrame(String inputName,int[] inputArgs,int[] inputRegisters){
    //numberOfArgs =lookup inputName in the symbol table to get number of args
    this.r5Bottom = this.r6Top;
    this.numberOfArgs = inputArgs.length;
    RunTimeStack.dMem.writeMemoryItem();//return value not assigned yet, so null
    RunTimeStack.dMem.setArguments(inputArgs);//arguments
    RunTimeStack.dMem.writeMemoryItem();//status/return address, also null
    RunTimeStack.dMem.setArguments(inputRegisters);//registers 1-4
    RunTimeStack.dMem.writeMemoryItem(this.r5Bottom);//register r5Bottom
    this.r6Top = RunTimeStack.dMem.getTopIndex();
    //will setTempObjects() when we evaluate function	
  }  
  public int getDMemReturnValue(){
	//bottom is the return address	
    return RunTimeStack.dMem.getItemAt(this.r5Bottom);
  } 
  public void setDmemReturnValue(int inputReturnValue){
	//bottom is the return address	
    RunTimeStack.dMem.setItemAt(this.r5Bottom, inputReturnValue);
  }
  
  public int[] getDMemArguments(){
    int[] returnArray = new int[this.numberOfArgs];
    int argPointer = this.r5Bottom + 1;  
    for(int i = 0; i < this.numberOfArgs; i++){
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
    return RunTimeStack.dMem.getItemAt(RunTimeStack.dMem.getTopIndex() - this.numberOfTempObjects - 5 - registerNumber);
  }
  public void setDmemRegisterAt(int registerNumber, int inputVal){
    RunTimeStack.dMem.setItemAt(this.r5Bottom + this.numberOfArgs + 1 + registerNumber, inputVal);
  }   
  public int[] getDmemRegisters(){
    int[] returnArray = new int[5]; //registers 1-4 and r5Bottom
    int rPointer = (RunTimeStack.dMem.getTopIndex() - this.numberOfTempObjects - 5);  
    for(int i = 0; i < 5; i++){
        returnArray[i] = RunTimeStack.dMem.getItemAt(rPointer);
        rPointer++;
    }
    return returnArray;
  }
  public void setDmemRegisters(int[] argv){
    int rPointer = this.r5Bottom + this.numberOfArgs + 2;  
    for(int i = 0; i < 5; i++){
        RunTimeStack.dMem.setItemAt(rPointer, argv[i]);
        rPointer++;
    }
  }
  
  public List<Integer> getTempObjects(){
    //calculate based on size from bottom up
    List<Integer> returnArray = new ArrayList<>();
    int rPointer = this.r5Bottom + this.numberOfArgs + 7;  
    for(int i = rPointer; i < (RunTimeStack.dMem.getTopIndex() + 1); i++){
        returnArray.add(RunTimeStack.dMem.getItemAt(i));
    }
    return returnArray;	
  }   
  public void setTempObjects(int[] inputValues){
    this.numberOfTempObjects = inputValues.length;
    RunTimeStack.dMem.setArguments(inputValues);
    this.r6Top = RunTimeStack.dMem.getTopIndex();
  }
}

