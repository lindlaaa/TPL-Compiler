package src.codegen;

import src.parser.symboltable;

public class Project5 {

    public static void main(String[] args) {
        RunTimeStack dMemory = new RunTimeStack();
        int[] inputArgs = {1, 1, 1};
        int[] inputRegisters = {9, 9, 9, 9};
        int[] tempObjects = {8, 8, 8};
        dMemory.pushFrame("Test",inputArgs,inputRegisters);
        dMemory.setTempObjects(tempObjects);
        dMemory.printIndividualStackFrame();
//comment out the above and uncomment below to print more than an individual frame
//The commented out print version isn't formmated well, yet       
//        int[] inputArgs2 = {2, 2, 2};
//        int[] inputRegisters2 = {7, 7, 7, 7};
//        dMemory.pushFrame("Test",inputArgs2,inputRegisters2);        
//        dMemory.printStack();
    }
    
}
