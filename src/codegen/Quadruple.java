/*
package project5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quadruple {
    int curRow = -1;
    List<List<String>> quadruple = new ArrayList<>();

    public void emit(String op, String arg1, String arg2, String res){
        List<String> tempRow = new ArrayList<>(Arrays.asList(op,arg1,arg2,res));
        this.quadruple.add(tempRow);
    }
    public void emit(String op, String arg1, String res){
        List<String> tempRow = new ArrayList<>(Arrays.asList(op,arg1,null,res));
        this.quadruple.add(tempRow);
    }    
    public void emit(String op,String res){
        List<String> tempRow = new ArrayList<>(Arrays.asList(op,null,null,res));
        this.quadruple.add(tempRow);
    }              
    public List<String> get3ACLine(){
        curRow++;
        return this.quadruple.get(curRow);
    }
    @Override
    public String toString(){
        String outString = "";
        for(int i = 0; i < this.quadruple.size();i++){
            for(int k = 0; k < this.quadruple.get(i).size(); k++){
                outString += this.quadruple.get(i).get(k).toString() + " ";                    
            }
            outString += "\n";
        }      
        return outString;
    }    
}
*/