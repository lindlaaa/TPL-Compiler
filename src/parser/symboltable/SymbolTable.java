package src.parser.symboltable;

import java.util.*;
import src.parser.nodes.*;
import src.parser.*;
import java.util.HashMap;

public class SymbolTable{

	private ProgramNode program;
	private SymbolTableBuilder tableMaker;
	private HashMap table;

	public SymbolTable(ProgramNode p, Parser parser){

		this.program = p;

		this.tableMaker = new SymbolTableBuilder(parser);
		tableMaker.buildTable(program);
		tableMaker.populatePairs();
		this.table = tableMaker.getTable();
		tableMaker.printMap();
	}

	public HashMap getTable(){
		return this.table;
	}
}
