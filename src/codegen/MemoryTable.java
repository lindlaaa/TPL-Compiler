package src.codegen;

public class MemoryTable {
  int[] memoryBlock = new int[1023];
  int topIndex = 0;
  
  public MemoryTable(){
  }	    
  public void setArguments(int[] argv){
    for(int j = 0; j < argv.length; j++){
        memoryBlock[topIndex] = argv[j];
	topIndex++;
    }
  } 
  public void setArguments(int argc){
    for(int i = 0; i < argc; argc++){
        memoryBlock[topIndex] = -1;
	topIndex++;
    }
  }   
  public void writeMemoryItem(){
    memoryBlock[topIndex] = -1;
    topIndex++;
  }  
  public void writeMemoryItem(int inputInt){
    memoryBlock[topIndex] = inputInt;
    topIndex++;
  }  
  public int getItemAt(int inputIndex){
    return this.memoryBlock[inputIndex];
  }
  public void setItemAt(int inputIndex, int inputValue){
    this.memoryBlock[inputIndex] = inputValue;
  }    
}
