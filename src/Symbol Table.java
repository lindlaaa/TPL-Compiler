package src.parser;

import java.util.Hashmap;

//in some locations, Eugene had "new Character(inputId)" and didn't see why that was necessary

public class SymbolTable{
	private HashMap symbolTable;
	
	public SymbolTable(){
		this.symbolTable = new Hashmap(23, 1); //why did eugene have 23?
		//do we want a Hashmap with a list as the values, in order to account for all occurences?
	}
	
	public boolean isIDUsed(char inputID){
		//return this.symbolTable.containsKey(inputID);
		return this.symbolTable.containsKey(new Character(inputID));
	}
	
    //should this be the generic Token type?
	//public <T> T lookupID(char inputID) throws SemanticException{
    public BranchType lookupID(char inputID) throws SemanticException{}
		if(isIDUsed(inputID){
			//return (T) this.symbolTable.get(inputID);
			return (BranchType) this.symbolTable.get(new Character (inputID));
		}else{
			throw new SemanticException("Identifier "+inputID+" is not found within the symbol table");
		}
	}
	
	//Eugene uses "BranchType" rather than T
	public void addKeyValue(char inputID, T inputType) throws SemanticException{
		if (isIDUsed(inputID)){
			throw new SemanticException("Identifier " +inputID+" is already within the symbol table");
		}
		//this.symbolTable.put(inputID, inputType);
		this.symbolTable.put(new Character(inputID), inputType);
	}
	
	public String toString(){
		return this.symbolTable.toString();
	}
}