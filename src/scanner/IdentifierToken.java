package src.scanner;
/**
 *The class constructor takes a string as input.
 *Input is expected to start with a letter while all other
 *characters are alphanumeric. It implements the Token interface,
 *requiring a toString() method.
 */
public class IdentifierToken implements Token{

  String outputString = "Identifier ";
	String idVal;
  int column;

  public IdentifierToken(String inputString, int c){
    idVal = inputString;
    outputString += inputString;
    column = c;
  }

  /**
   *  Returns the name represented by this Token
   *
   *  @return String  Represents the name represented by this Token
   */
  public String getVal(){
    return idVal;
  }

  /**
   *  Returns the column number this Token is in
   *
   *  @return int Representing the column location of this Token
   */
  public int getColumn(){
    return column;
  }

  /**
   *  When the token’s toString() is
   *  called, it will return a concatenation of the
   *  string “Identifier “ with the character input.
   *
   *  @return String A concatenation of the
   *                 string “Identifier “ with the character input.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
