//public class SymbolTable{
	private HashMap symbolTable;
	
	public SymbolTable(){
		this.symbolTable = new Hashmap(); 
	}
	
	public boolean isIDUsed(char inputID){
		return this.symbolTable.containsKey(inputID);
	}

    public BranchType lookupIDType(char inputID) throws SemanticException{
		if(isIDUsed(inputID)){
			//if we make the value an arraylist, then index 0 will be the type
			return (BranchType) this.symbolTable.get(inputID).get(0); 
		}else{
			throw new SemanticException("Identifier "+inputID+" is not found within the symbol table");
		}
	}
	
	public Pair getLexicalAddress(int lexicalPair){
		try{
			return (Pair) this.symbolTable.get(inputID).get(lexicalPair);
		}catch{
			//lexical pair does not exist at this index
		}
	}

	public void addKeyValue(char inputID, BranchType inputType) throws SemanticException{
		if (isIDUsed(inputID)){
			throw new SemanticException("Identifier " +inputID+" is already within the symbol table");
		}
		ArrayList<Object> tempList = new ArrayList<>();
		tempList.add(inputType);
		this.symbolTable.put(inputID, tempList);
	}
	
	public void addLexicalPair(char inputID, Pair inputPair){
		if(isIDUsed(inputID)){
			this.symbolTable.get(inputID).add(inputPair);	
		}else{
			throw new SemanticException("Identifier "+inputID+" is not found within the symbol table");	
	}
	
	public String toString(){
		return this.symbolTable.toString();
	}
}