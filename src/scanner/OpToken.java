package src.scanner;
/**
 * The class constructor takes a character as input.
 * Input is expected to be a mathematical operator.
 * It implements the Token interface, requiring a toString() method.
 */
public class OpToken implements Token{

  private String outputString = "Operator ";
	private char opVal;
  private int line;

  public OpToken(char inputChar, int c){
    outputString += inputChar;
    line = c;
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
   *  Returns the op represented by this Token
   *
   *  @return String  Represents the op represented by this Token
   */
	public char getVal(){
		return opVal;
	}

  /**
   *  When the token’s toString() is called,
   *  it will return a concatenation of the string “Operator “
   *  with the character input.
   *
   *  @return String A concatenation of the string “Operator “
   *                 with the character input.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
