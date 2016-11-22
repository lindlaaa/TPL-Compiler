package src.parser.symboltable;

import java.util.*;
import src.parser.nodes.*;
import src.parser.*;
import java.util.HashMap;

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
}
