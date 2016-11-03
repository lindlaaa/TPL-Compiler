package src.parser.semanticanalyzer;

import java.util.*;
import src.parser.nodes.*;
import java.util.HashMap;

public class SymbolTable{

	private ProgramNode program;
	private SymbolTableBuilder tableMaker;
	private HashMap table;

	public SymbolTable(ProgramNode p){

		this.program = p;

		this.tableMaker = new SymbolTableBuilder();
		tableMaker.buildTable(program);
		this.table = tableMaker.getTable();
		tableMaker.printMap();
	}

	public HashMap getTable(){
		return this.table;
	}
	/*
	private HashMap symbolTable;

	public SymbolTable(){
		this.symbolTable = new HashMap();
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

	public LexicalPair getLexicalAddress(char inputID, int lexicalPair) throws ParseException{
		try{
			return (LexicalPair) this.symbolTable.get(inputID);//.get(lexicalPair);FIXME
		}catch(Exception e){ //FIXME
			throw new ParseException("The Lexical address was not found within the symbol table");
		}
	}

	public void addKeyValue(char inputID, BranchType inputType) throws ParseException{
		if (isIDUsed(inputID)){
			throw new ParseException("Identifier " +inputID+" is already within the symbol table");
		}
		ArrayList<Object> tempList = new ArrayList<>();
		tempList.add(inputType);
		this.symbolTable.put(inputID, tempList);
	}

	public void addLexicalPair(char inputID, LexicalPair inputPair) throws ParseException{
		if(isIDUsed(inputID)){
			this.symbolTable.get(inputID);//.add(inputPair);FIXME
		}else{
			throw new ParseException("Identifier "+inputID+" is not found within the symbol table");
	  }
  }

	public String toString(){
		return this.symbolTable.toString();
	}*/
}
