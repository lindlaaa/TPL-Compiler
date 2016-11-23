package src.parser.semanticanalyzer;

import src.parser.symboltable.*;

import src.scanner.Token;
import java.util.HashMap;
import java.util.List;
import src.scanner.IdentifierToken;
import java.util.ArrayList;

import src.parser.*;
import src.parser.nodes.ProgramNode;

@SuppressWarnings("unchecked")
public class SemanticAnalyzer{

  private ProgramNode program;
  private SymbolTable symbolTable;
  private List<Token>   tokenArray;

  public SemanticAnalyzer(ProgramNode p, List tokens){

    this.program = p;
    this.tokenArray = tokens;

    this.symbolTable = new SymbolTable();
    populatePairs();
  }


  public void populatePairs(){

    System.out.println("\n\n\nIdentifier Reference Locations:");//FIXME
    for(Token each : tokenArray){
      if(each instanceof IdentifierToken){
        System.out.println(each);
      } // end if
    } // end for
  }
}
