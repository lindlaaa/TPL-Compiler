package src.parser;

import java.util.Hashmap;

public class SymbolTable{
	private HashMap symbolTable;
	
	public SymbolTable(){
		this.symbolTable = new Hashmap(); 
	}
	
	public boolean isIDUsed(char inputID){
		return this.symbolTable.containsKey(inputID);
	}

    public BranchType lookupID(char inputID) throws SemanticException{
		if(isIDUsed(inputID)){
			/*
			if we make the value an arraylist, then index 0 will be the type
			return (BranchType) this.symbolTable.get(inputID).get(0);
			*/
			return (BranchType) this.symbolTable.get(inputID); 
		}else{
			throw new SemanticException("Identifier "+inputID+" is not found within the symbol table");
		}
	}

	public void addKeyValue(char inputID, BranchType inputType) throws SemanticException{
		if (isIDUsed(inputID)){
			throw new SemanticException("Identifier " +inputID+" is already within the symbol table");
		}
		/*
		might want a generic arraylist for the value to keep track of type and lexical addresses
		List<Object> inputList = new ArrayList<>(); 
		inputList.add(inputType);
		this.symbolTable.put(inputID, inputList);
		*/
		this.symbolTable.put(inputID, inputType);
	}
	
	public String toString(){
		return this.symbolTable.toString();
	}
}