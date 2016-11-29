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
  public SemanticAnalyzer(ProgramNode p, List tokens) throws ParseException {

    this.root = p;
    this.tokenArray = tokens;

    this.symbolTable = new SymbolTable();
    populatePairs();
    //symbolTable.printTable(); //FIXME
    getBasicTypes();
    setFunctionParamsAmt(root);
    rebalanceTree();
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
    for(int i = node.getChildren().size()-1; i > -1; i--){
      SemanticNode each = node.getChild(i);
      getBasicTypes(each);
    }
  }



  /**
   *  Finds the number of variables each function needs.
   *  Makes that number an attribute of the Symbol in the symboltable.
   */
  public void setFunctionParamsAmt(){
    for (int i = root.getChildren().size()-1; i > -1; i--) {
      SemanticNode each = root.getChild(i);
      setFunctionParamsAmt(each);
    }
  }
  /* Used by the function above */
  public int setFunctionParamsAmt(SemanticNode node){
    if(node instanceof DefNode){
      int amt=node.getChild(2).getChildren().size();//Getting arg amt
      String key = node.getChild(3).getID();        //Getting key
      BranchType type = node.getChild(1).getType(); //Getting type
      
      symbolTable.get(key).setType(type);           //Set type to key
      symbolTable.get(key).setIsFunction(true);     //Set to a function
      symbolTable.get(key).setNumOfArgs(amt);       //Set num of args
      //System.out.println("@key -"+key+" @Type -"+type+" @amt: "+amt); //FIXME
      return 0;
    }
    for(int i = node.getChildren().size()-1; i > -1; i--){
      SemanticNode each = node.getChild(i);
      setFunctionParamsAmt(each);
    }
    return 1;
  }



  public void rebalanceTree() throws ParseException {
    for (int i = root.getChildren().size()-1; i > -1; i--) {
      SemanticNode each = root.getChild(i);
      rebalanceTree(each);
    }
  }
  public void rebalanceTree(SemanticNode node) throws ParseException {
    SemanticNode argNode, childBelow;

    if(node instanceof IdentifierNode && !(node.getParent() instanceof DefNode)){
      if(symbolTable.isSet(node.getID())){
        if(symbolTable.get(node.getID()).getIsFunction()){

          int pos = node.getParent().getChildren().indexOf(node);
          int amt = symbolTable.get(node.getID()).getNumOfArgs();

          for (int i = amt; i > 0; i--){ // Amount of args to move
            try{
              argNode = node.getParent().getChild(pos-1);
              node.addChild(argNode, node);
              node.getParent().getChildren().remove(argNode);
              pos--;
            }catch(Exception e){
              throw new ParseException("--Semantic analysis shows you used the wrong number of args.--");
            }
          }//END FOR

        }
      }
    }
    for (int i = node.getChildren().size()-1; i > -1; i--){
      rebalanceTree(node.getChild(i));
    }
  }
}
