package src.scanner;
/**
 *The class constructor a string as input. It is expected to be a
 *reserved keyword for the language. It implements the Token interface,
 *requiring a toString() method.
 */
public class KeywordToken implements Token{

  String outputString = "Keyword ";
	String keyString;
  int column;

  public KeywordToken(String inputString, int c){
    outputString += inputString;
    column = c;
  }

  /**
   *  Returns the comment represented by this Token
   *
   *  @return String  Represents the comment represented by this Token
   */
	public String getVal(){
		return keyString;
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
   *  When the token’s toString() is called,
   *  it will return a concatenation of the string “Keyword “
   *  with the string input.
   *
   *  @return String a concatenation of the string “Keyword “
   *                 with the string input.
   */
  @Override
  public String toString(){
    return outputString;
  }
}
