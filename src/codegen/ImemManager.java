package src.codegen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImemManager {

    TempTable tempTable;
    public List<Boolean> registers = new ArrayList<>(Arrays.asList(null,true,true,true,true));

    //values should already be within the inpuTable
    public ImemManager(TempTable inputTable){
        tempTable = inputTable;
    }

    public String tmString = "";
    long curLine = 3;
    public void createTemplate(List<String> topRow) throws Exception{
        String op = topRow.get(0);
        switch (op) {
            case "assign": assignmentTemplate(topRow);
                break;
            case "plus": additionTemplate(topRow);
                break;
            case "minus": subtractionTemplate(topRow);
                break;
            case "multiply": multiplicationTemplate(topRow);
                break;
            case "divide": divisionTemplate(topRow);
                break;
            case "print": printTemplate(topRow);
                break;
            //case "Return": returnTemplate(topRow);
            //    break;
            case "<": lessThanTemplate(topRow);
                break;
            default: System.out.println("");
                break;
            //add more cases
        }
    }
    private long nextOpenRegister(){
        //probably should be more complicated
        long returnIndex = this.registers.indexOf(true);
        this.registers.set((int)returnIndex,false);
        return returnIndex;
    }
    private void openRegister(long freeIndex){
        //probably should be more complicated
        this.registers.set((int)freeIndex,true);
    }
    public void openAllRegisters(){
        for(long i = 1; i < this.registers.size();i++){
            this.openRegister(i);
        }
    }
    private void returnTemplate(List<String> inputRow) throws Exception{
        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Return Template----\n";

        long arg1 = this.tempTable.getVal(inputRow.get(3));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(3));
        }
        this.tmString += String.format("%d: ADD 5, 1(0)\n%d: LD 7, 1(0)\n",
                                        curLine++,curLIne++);
    }

    private void assignmentTemplate(List<String> inputRow) throws Exception{
        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += "----Assignment Template----\n";
        String flairVar= inputRow.get(1);
        long arg1;
        long r1 = 0;
        try{
            arg1 = Integer.parseInt(flairVar);
            r1 = arg1;
        }catch(Exception e){
            arg1 = this.tempTable.getVal(flairVar);
            if(this.tempTable.getLastRegister(flairVar) != -1){
                r1 = this.tempTable.getLastRegister(flairVar);
            }else{
                r1 = this.nextOpenRegister();
                this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r1,arg1);
            }
        }
        String flairVar2= inputRow.get(3);
        long arg2;
        long r2 = 0;
        try{
            arg2 = Integer.parseInt(flairVar2);
            r2 = arg2;
        }catch(Exception l){
            arg2 = this.tempTable.getVal(flairVar2);
            if(arg2 == Integer.MIN_VALUE){
                arg2 = 0;
            }
            if(this.tempTable.getLastRegister(flairVar2) != -1){
                r2 = this.tempTable.getLastRegister(flairVar2);
            }else{
                r2 = this.nextOpenRegister();
                this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r2,arg2);
            }
        }

        this.tmString += String.format("%d: LDC %d, %d (0)\n",
            curLine++,r2,r1);

        this.openRegister(r1);
    }
    private void printTemplate(List<String> inputRow) throws Exception{
        long arg1 = this.tempTable.getVal(inputRow.get(3));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(3));
        }

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Print Template----\n";

        //TODO: should probably use a buffered reader
        this.tmString += String.format("%d: OUT %d, 0, 0\n",
            curLine++,arg1);


    }

    private void additionTemplate(List<String> inputRow) throws Exception{
        long r1;
        long r2;
        boolean isR1 = false;
        boolean isR2 = false;
        long arg1 = this.tempTable.getVal(inputRow.get(1));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(1));
            isR1 = true;
            r1 = arg1;
        }else{
            r1 = this.nextOpenRegister();
        }
        long arg2 = this.tempTable.getVal(inputRow.get(2));
        if(arg2 == Integer.MIN_VALUE){
            arg2 = this.tempTable.getLastRegister(inputRow.get(2));
            isR2 = true;
            r2 = arg2;
        }else{
            r2 = this.nextOpenRegister();
        }

        if(this.tempTable.getVal(inputRow.get(3)) == Integer.MIN_VALUE){
            this.tempTable.setLastRegister(inputRow.get(3),r1);
        }

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Addition Template----\n";

        //TODO: should probably use a buffered reader
        if(!isR1){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                curLine++,r1,arg1);
        }
        if(!isR2){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r2,arg2);
        }
        this.tmString += String.format("%d: ADD %d, %d, %d\n",
                curLine++,r1,r1,r2);

        this.openRegister(r2);
    }
    private void subtractionTemplate(List<String> inputRow) throws Exception{
        long r1;
        long r2;
        boolean isR1 = false;
        boolean isR2 = false;
        long arg1 = this.tempTable.getVal(inputRow.get(1));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(1));
            isR1 = true;
            r1 = arg1;
        }else{
            r1 = this.nextOpenRegister();
        }
        long arg2 = this.tempTable.getVal(inputRow.get(2));
        if(arg2 == Integer.MIN_VALUE){
            arg2 = this.tempTable.getLastRegister(inputRow.get(2));
            isR2 = true;
            r2 = arg2;
        }else{
            r2 = this.nextOpenRegister();
        }

        if(this.tempTable.getVal(inputRow.get(3)) == Integer.MIN_VALUE){
            this.tempTable.setLastRegister(inputRow.get(3),r1);
        }

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Subtraction Template----\n";

        //TODO: should probably use a buffered reader
        if(!isR1){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                curLine++,r1,arg1);
        }
        if(!isR2){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r2,arg2);
        }
        this.tmString += String.format("%d: SUB %d, %d, %d\n",
                curLine++,r1,r1,r2);

        this.openRegister(r2);
    }
    private void multiplicationTemplate(List<String> inputRow) throws Exception{
        long r1;
        long r2;
        boolean isR1 = false;
        boolean isR2 = false;
        long arg1 = this.tempTable.getVal(inputRow.get(1));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(1));
            isR1 = true;
            r1 = arg1;
        }else{
            r1 = this.nextOpenRegister();
        }
        long arg2 = this.tempTable.getVal(inputRow.get(2));
        if(arg2 == Integer.MIN_VALUE){
            arg2 = this.tempTable.getLastRegister(inputRow.get(2));
            isR2 = true;
            r2 = arg2;
        }else{
            r2 = this.nextOpenRegister();
        }

        if(this.tempTable.getVal(inputRow.get(3)) == Integer.MIN_VALUE){
            this.tempTable.setLastRegister(inputRow.get(3),r1);
        }

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Multiplication Template----\n";

        //TODO: should probably use a buffered reader
        if(!isR1){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                curLine++,r1,arg1);
        }
        if(!isR2){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r2,arg2);
        }
        this.tmString += String.format("%d: MULT %d, %d, %d\n",
                curLine++,r1,r1,r2);

        this.openRegister(r2);
    }
    private void divisionTemplate(List<String> inputRow) throws Exception{
        long r1;
        long r2;
        boolean isR1 = false;
        boolean isR2 = false;
        long arg1 = this.tempTable.getVal(inputRow.get(1));
        if(arg1 == Integer.MIN_VALUE){
            arg1 = this.tempTable.getLastRegister(inputRow.get(1));
            isR1 = true;
            r1 = arg1;
        }else{
            r1 = this.nextOpenRegister();
        }
        long arg2 = this.tempTable.getVal(inputRow.get(2));
        if(arg2 == Integer.MIN_VALUE){
            arg2 = this.tempTable.getLastRegister(inputRow.get(2));
            isR2 = true;
            r2 = arg2;
        }else{
            r2 = this.nextOpenRegister();
        }

        if(this.tempTable.getVal(inputRow.get(3)) == Integer.MIN_VALUE){
            this.tempTable.setLastRegister(inputRow.get(3),r1);
        }

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Division Template----\n";

        //TODO: should probably use a buffered reader
        if(!isR1){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                curLine++,r1,arg1);
        }
        if(!isR2){
            this.tmString += String.format("%d: LDC %d, %d (0)\n",
                    curLine++,r2,arg2);
        }
        this.tmString += String.format("%d: DIV %d, %d, %d\n",
                curLine++,r1,r1,r2);

        this.openRegister(r2);
    }


    private void lessThanTemplate(List<String> inputRow) throws Exception{
        long arg1 = this.tempTable.getVal(inputRow.get(1));
        long arg2 = this.tempTable.getVal(inputRow.get(2));
        //will need three open registers
        long r1 = this.nextOpenRegister();
        long r2 = this.nextOpenRegister();

        //TODO: REMOVE: this a test string that should be removed later
        this.tmString += ";;----Less Than Template----\n";
        //WILL REQUIRE BACKPATCHINHG AND A TEMPORARY PLACEHOLDER
        this.tmString += String.format("%d: LDC r%d, %d (0)\n",
                curLine++,r1,arg1) +
                String.format("%d: LDC %d, %d (0)\n",
                curLine++,r2,arg2) +
                String.format("%d: SUB %d, %d, %d\n",
                curLine++,r1,r1,r2) +
                String.format("%d: JLT %d, (then) (7)\n",
                curLine++,r1);
        //open needed registers
    }


    //add more templates
}
