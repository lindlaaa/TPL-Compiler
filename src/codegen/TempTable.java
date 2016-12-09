package src.codegen;

import java.util.*;

public class TempTable {

    Map<String, tempUsage> tempTable = new HashMap();

    public int getVal(String varName)throws Exception{
        if(this.tempTable.containsKey(varName)){
            return this.tempTable.get(varName).getIntVal();
        }else{
            throw new Exception("getVal() Error: there is no value for the " + varName);
        }
    }

    public void setVal(String varName, int inputVal){
        tempUsage usage = new tempUsage();
        usage.setIntVal(inputVal);
        this.tempTable.put(varName,usage);
    }

    public int getLastRegister(String varName)throws Exception{
        if(this.tempTable.containsKey(varName)){
            return this.tempTable.get(varName).getReg();
        }else{
            throw new Exception("getVal() Error: there is no value for the " + varName);
        }
    }
    public void setLastRegister(String varName, int inputReg){
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

}
