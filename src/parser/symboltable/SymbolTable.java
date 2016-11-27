package src.parser.symboltable;

import java.util.*;
import src.parser.nodes.*;
import src.parser.*;
import java.util.HashMap;
import src.parser.symboltable.VariableSymbol;

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

	public int tableSize(){
		System.out.println("\nSymbol Table size:"); // FIXME
		System.out.println(table.size()+"\n"); // FIXME
		return table.size();
	}

	public boolean isSet(String tempKey){
		if(table.containsKey(tempKey)){
			return true;
		}
		return false;
	}

	public boolean put(String tempKey){
		if(isSet(tempKey)){
			System.out.println("Already Exists.\n"); // FIXME
			return false;
			// add lexical pair and other info to existing symbol
		}else{
			VariableSymbol tempSymbol = new VariableSymbol(tempKey, "-Parent Name-"); //FIXME
			table.put(tempKey, tempSymbol);
			System.out.println("added new pair.\n"); // FIXME
			// create new symbol and populate information
			// assign new symbol to a new key/val pair in table
			return true;
		}
	}

	public void setName(String old, String n){
		table.get(old).setName(n);
	}

	public void addPair(String name, LexicalPair pair){
    table.get(name).addPair(pair);
  }

}
