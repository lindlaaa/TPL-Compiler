package src.parser.semanticanalyzer;

import src.parser.*;
import src.parser.nodes.ProgramNode;

public class SemanticAnalyzer{

  private ProgramNode program;
  private SymbolTable symbolTable;

  public SemanticAnalyzer(ProgramNode p, Parser parser){

    this.program = p;

    this.symbolTable = new SymbolTable(program, parser);
  }
}
