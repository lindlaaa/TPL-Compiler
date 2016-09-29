package src.scanner;
/**
 * The class constructor takes a character as input.
 * Input is expected to be either a period or a semicolon.
 * It implements the Token interface, requiring a toString() method.
 */
public class TerminatorToken implements Token{

  String outputString = "Terminator ";
	char termVal;
  int line;

  public TerminatorToken(char inputChar, int c){
    outputString += inputChar;
    line = c;
  }

  /**
   *  Returns the op represented by this Token
   *
   *  @return String  Represents the op represented by this Token
   */
	public char getVal(){
		return termVal;
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
   *  When the token’s toString() is called, it will return a
   *  concatenation of the string “Terminator “ with the
   *  character input.
   *
   *  @return String A concatenation of the string “Terminator “
   *                 with the character input.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
