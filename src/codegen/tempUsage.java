package src.codegen;

public class tempUsage {
    long value = Integer.MIN_VALUE;
    long assignedTo = -1;
    boolean inUse = false;

    public long getIntVal(){
        return this.value;
    }
    public void setIntVal(long newVal){
        this.value = newVal;
    }
    public long getReg(){
        return this.assignedTo;
    }
    public void setReg(long newReg){
        this.assignedTo = newReg;
    }
    public boolean inUse(){
        return this.inUse;
    }
    public void setInUse(){
        this.inUse = true;
    }

	  public String getUsage(){
		  return this.value + " " + this.assignedTo + "\n";
	}
}
