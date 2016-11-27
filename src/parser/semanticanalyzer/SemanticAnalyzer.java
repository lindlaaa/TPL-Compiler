package src.parser.semanticanalyzer;

import java.util.List;

import src.scanner.Token;
import src.scanner.IdentifierToken;

import src.parser.*;
import src.parser.nodes.ProgramNode;
import src.parser.symboltable.*;

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
    symbolTable.printTable();
  }


  public void populatePairs(){

    //System.out.println("\n------ Identifier references and locations: ---"); //FIXME
    for(Token each : tokenArray){
      if(each instanceof IdentifierToken){
        IdentifierToken var = (IdentifierToken)each;
        //System.out.println(var.avery() + " -- " + var.getLexicalPair()); // FIXME

        String varName = var.avery();
        symbolTable.put(varName);
        symbolTable.addPair(varName, var.getLexicalPair());
        //symbolTable.printPairs(varName); // FIXME
      } // end if
    } // end for
  }
}
