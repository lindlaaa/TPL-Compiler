package src.scanner;
/**
 * The class constructor takes a character as input.
 * Input is expected to be either a period or a semicolon.
 * It implements the Token interface, requiring a toString() method.
 */
public class TerminatorToken implements Token{

  private String outputString = "Terminator ";
  private char termVal;
  private int line;

  public TerminatorToken(char inputChar, int c){
    outputString += inputChar;
    termVal = inputChar;
    line = c;
  }
  public TerminatorToken(char inputChar){
    this(inputChar,0);
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

  public int getTerminalType(){
    switch (termVal) {
      case ';':
        return 0;
      case '.':
        return 1;
      default:
        System.out.println("ERROR IN THE TERM TERMTYPE");
        return 0;

    }
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
