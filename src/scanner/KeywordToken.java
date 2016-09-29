package src.scanner;
/**
 *The class constructor a string as input. It is expected to be a
 *reserved keyword for the language. It implements the Token interface,
 *requiring a toString() method.
 */
public class KeywordToken implements Token{

  String outputString = "Keyword ";
	String keyString;
  int line;

  public KeywordToken(String inputString, int c){
    outputString += inputString;
    line = c;
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
   *  Returns the line number this Token is in
   *
   *  @return int Representing the line location of this Token
   */
  public int getline(){
    return line;
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
