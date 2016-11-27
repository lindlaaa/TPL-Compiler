package src.parser.symboltable;

import java.util.ArrayList;

import src.parser.semanticanalyzer.BranchType;

/**
 *  This object will be held in the symbol table
 *    and represent the variables in the program holding all
 *    of their information.
 */
@SuppressWarnings("unchecked")
public class Symbol{

  /* holds list of reference locations */
  private ArrayList<LexicalPair> pairsList = new ArrayList();

  /* string showing the name */
  private String name;

  /* represents if this key is a function or a variable */
  private BranchType type;

  /* true if we are a function, false if we are a variable */
  private boolean isFunction;

  /* string showing the name of the function it was declared in */
  //What if 'n' is a parameter in multiple functions? FIXME TODO
  //Should callerFunction be a list of all callers? FIXME TODO
  private String callerFunction;



  /* Constructor */
  public Symbol(String n, String c){
    name = n;
    callerFunction = c;
  }



  /**
   *  Sets the type of this symbol in the symboltable.
   *  @param t BranchType representing the type of the Symbol.
   */
  public void setType(BranchType t){
      if(this.type == null){    // If type is not set
        this.type = t;
      }else if(this.type != t){ // If types are mixed
        this.type = BranchType.EITHER;
      }else if(this.type == t){ // If they are the same
        //Do nothing
      }
  }


  /**
	 *  Returns the BranchType of the symbol.
	 *  @return     BranchType representing the type of the symbol.
	 */
  public BranchType getType(){
    return this.type;
  }



  /**
   *  Prints the LexicalPairs of the key to stdout.
   */
  public void printPair(){
    System.out.println(name + " -- " + pairsList);
  }



  /**
   *  Sets the attribute 'callerFunction'.
   *  @param n String representing the new name to be set.
   */
  public void setCallerName(String n){
    this.callerFunction = n;
  }



  /**
   *  Adds a new LexicalPair to the Symbol.
   *  @param pair LexicalPair pair that represents the new pair to add.
   */
  public void addPair(LexicalPair pair){
    this.pairsList.add(pair);
  }


  /**
   *  Sets the isFunction attribute.
   *  @param i boolean representing if this symbol is a function.
   */
  public void setIsFunction(boolean i){
    this.isFunction = i;
  }

}
