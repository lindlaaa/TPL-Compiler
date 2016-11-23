package src.parser.symboltable;

/**
 *  This object will be held in the symbol table
 *    and represent the variables in the program holding all
 *    of their information.
 */
public class VariableSymbol implements Symbol{

  /* holds list of reference locations */
  private List<LexicalPair> pairs = null;

  /* string showing the name */
  private String name;

  /* string showing the name of the function it was declared in */
  //What is 'n' is a parameter in multiple functions? FIXME TODO
  private String callerFunction;

  public VariableSymbol(String n, String c){
    name = n;
    callerFunction = c;
  }

}
