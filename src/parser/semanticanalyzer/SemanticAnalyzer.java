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

  /* Constructor */
  public SemanticAnalyzer(ProgramNode p, List tokens){

    this.program = p;
    this.tokenArray = tokens;

    this.symbolTable = new SymbolTable();
    populatePairs();
    symbolTable.printTable();
  }



  /**
   *  Finds all identifiers in the program.
   *  Sets the LexicalPairs of each.
   */
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



  /**
   *  Sets all of the Types of the variables as they are declared.
   *  Utilized the BranchType Constants.
   */
  public void getBasicTypes(){

    //TODO
  }



  /**
   *  Finds the number of variables each function needs.
   *  Makes that number an attribute of the Symbol in the symboltable.
   */
  public void setFunctionParams(){

    //TODO
  }
}
