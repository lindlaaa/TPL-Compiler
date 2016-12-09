package src.codegen;

public class tempUsage {
    int value = Integer.MIN_VALUE;
    int assignedTo = -1;
    boolean inUse = false;

    public int getIntVal(){
        return this.value;
    }
    public void setIntVal(int newVal){
        this.value = newVal;
    }
    public int getReg(){
        return this.assignedTo;
    }
    public void setReg(int newReg){
        this.assignedTo = newReg;
    }
    public boolean inUse(){
        return this.inUse;
    }
    public void setInUse(){
        this.inUse = true;
    }

}
