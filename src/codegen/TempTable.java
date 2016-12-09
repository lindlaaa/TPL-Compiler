package src.codegen;

import java.util.*;

@SuppressWarnings("unchecked")
public class TempTable {

    Map<String, tempUsage> tempTable = new HashMap();

    public long getVal(String varName)throws Exception{
      try{
        if(this.tempTable.containsKey(varName)){
            return this.tempTable.get(varName).getIntVal();
        }else{
            throw new Exception("getVal() Error: there is no value for the " + varName);
        }
      }catch(Exception e){return 0;}
    }

    public void setVal(String varName, long inputVal){
        tempUsage usage = new tempUsage();
        usage.setIntVal(inputVal);
        this.tempTable.put(varName,usage);
    }

    public long getLastRegister(String varName)throws Exception{
        if(this.tempTable.containsKey(varName)){
            return this.tempTable.get(varName).getReg();
        }else{
            throw new Exception("getVal() Error: there is no value for the " + varName);
        }
    }
    public void setLastRegister(String varName, long inputReg){
        tempUsage usage = new tempUsage();
        usage.setReg(inputReg);
        this.tempTable.put(varName,usage);
    }

    public boolean isUsed(String varName)throws Exception{
        if(this.tempTable.containsKey(varName)){
            return this.tempTable.get(varName).inUse();
        }else{
            throw new Exception("getVal() Error: there is no value for the " + varName);
        }
    }
    public void setLastRegister(String varName){
        tempUsage usage = new tempUsage();
        usage.setInUse();
        this.tempTable.put(varName,usage);
    }

	@Override
	 public String toString(){
		String output = "";
		for (Map.Entry<String, tempUsage> entry : tempTable.entrySet()) {
			output += entry.getKey().toString() + " | " + entry.getValue().getUsage();
		}
		return output;
	}
}
