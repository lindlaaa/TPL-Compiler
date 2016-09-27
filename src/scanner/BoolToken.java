package src.scanner;
/**
 *The class constructor takes a string as input. Input is expected
 *to be either "true" or "false". It implements the Token interface,
 *requiring a toString() method.
 */
public class BoolToken implements Token{

  String outputString = "Boolean ";
	String boolValue;
  int column;

  public BoolToken(String inputString, int c){
	  boolValue = inputString;
    outputString += inputString;
    column = c;
  }

  /**
   *  Returns the java boolean value this token represents
   *
   *  @return boolean Represents the java boolean value of this Token
   */
  public boolean getVal(){
	  return boolValue;
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
   *  When the token's toString()
   *  is called, it will return a concatenation of the string
   *  “Boolean “ with the string input.
   *
   *  @return String A concatenation of the string
   *                 “Boolean “ with the string input.
   */
  @Override
  public String toString(){
    return outputString;
  }
}
