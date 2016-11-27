package src.parser.symboltable;

import java.util.List;

/**
 *  This object will be held in the symbol table
 *    and represent the variables in the program holding all
 *    of their information.
 */
@SuppressWarnings("unchecked")
public class VariableSymbol implements Symbol{

  /* holds list of reference locations */
  private List<LexicalPair> pairs;

  /* string showing the name */
  private String name;

  /* string showing the name of the function it was declared in */
  //What is 'n' is a parameter in multiple functions? FIXME TODO
  private String callerFunction;

  public VariableSymbol(String n, String c){
    name = n;
    callerFunction = c;
  }

  public void setName(String n){
    this.name = n;
  }

  public void addPair(LexicalPair pair){
    this.parts.add(pair);
  }

}
