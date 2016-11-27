package src.parser.semanticanalyzer;

import java.util.List;

import src.scanner.Token;
import src.scanner.IdentifierToken;

import src.parser.*;
import src.parser.nodes.*;
import src.parser.symboltable.*;

@SuppressWarnings("unchecked")
public class SemanticAnalyzer{

  private ProgramNode root;
  private SymbolTable symbolTable;
  private List<Token>   tokenArray;

  /* Constructor */
  public SemanticAnalyzer(ProgramNode p, List tokens){

    this.root = p;
    this.tokenArray = tokens;

    this.symbolTable = new SymbolTable();
    populatePairs();
    symbolTable.printTable();
    getBasicTypes();
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
    for (SemanticNode each : root.getChildren()) {
      getBasicTypes(each);
    }
  }
  /* Used by the function above */
  public void getBasicTypes(SemanticNode node){
    if(node instanceof FormalNode){
      String key = node.getChild(1).getID();        //Getting key
      BranchType type = node.getChild(0).getType(); //Getting type
      symbolTable.get(key).setType(type);           //Set type to key

      //Set this symbol to be a variable type and not a function type.
      symbolTable.get(key).setIsFunction(false);
    }
    for(SemanticNode each : node.getChildren()){
      getBasicTypes(each);
    }
  }



  /**
   *  Finds the number of variables each function needs.
   *  Makes that number an attribute of the Symbol in the symboltable.
   */
  public void setFunctionParams(){

    //TODO
  }
}
