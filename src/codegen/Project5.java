package src.codegen;

import src.parser.symboltable;

public class Project5 {

    public static void main(String[] args) {
        RunTimeStack dMemory = new RunTimeStack();
        //test frame 1
        int[] inputArgs = {1};
        int[] inputRegisters = {9, 9, 9, 9};
        dMemory.pushFrame("Print",inputArgs,inputRegisters);        
        //test frame 2
        int[] inputArgs2 = {2};
        int[] inputRegisters2 = {8, 8, 8, 8};
        dMemory.pushFrame("Test",inputArgs2,inputRegisters2);        
        //test frame 3
        int[] inputArgs3 = {3};
        int[] inputRegisters3 = {7, 7, 7, 7};
        dMemory.pushFrame("Test",inputArgs3,inputRegisters3);  
        
        dMemory.printStack();
    }
    
}
