package src.parser.symboltable;

import java.util.*;
import src.parser.nodes.*;
import src.parser.*;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class SymbolTable{

	private ProgramNode program;
	private HashMap table;

	public SymbolTable(){
		//this.tableMaker = new SymbolTableBuilder(parser);
		//tableMaker.buildTable(program);
		//tableMaker.populatePairs();
		//this.table = tableMaker.getTable();
		//tableMaker.printMap();
		this.table = new HashMap();
	}

	public HashMap getTable(){
		return this.table;
	}

	public void printTable(){
		System.out.println("\n------ Symbol table contents: ---"); //FIXME
		System.out.println(table.keySet());
	}

	public void tableSize(){
		System.out.println("\nSymbol Table size:");
		System.out.println(table.size()+"\n");
	}

	public boolean isSet(String tempKey){
		if(table.containsKey(tempKey)){
			return true;
		}
		return false;
	}

	public void put(String tempKey){
		if(isSet(tempKey)){
			System.out.println("Already Exists.\n");
			// add lexical pair and other info to existing symbol
		}else{
			VariableSymbol tempSymbol = new VariableSymbol(tempKey, "---"); //FIXME
			table.put(tempKey, tempSymbol);
			System.out.println("added new pair.\n");
			// create new symbol and populate information
			// assign new symbol to a new key/val pair in table
		}
	}

}
