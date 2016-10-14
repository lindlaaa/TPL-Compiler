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
  private int col;

  public OpToken(char inputChar, int r, int c){
    col = c;
    line = r;
    outputString += inputChar;
    opVal = inputChar;
  }

  public OpToken(char inputChar){
    this(inputChar,0,0);
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
   *  Returns the column number this token BEGINS at.
   *
   *  @return int Representing the column position if the token.
   */
  public int getCol(){
    return col;
  }


  //    case '+': case '-': case '*':
  //    case '/': case '<': case '=':
  public int getTerminalType(){
    switch (opVal) {
      case '+':
        return 6;
      case '-':
        return 7;
      case '*':
        return 8;
      case '/':
        return 9;
      case '<':
        return 10;
      case '=':
        return 11;
      default:
        System.out.println("ERROR IN THE OP TERMTYPE\n" +
                            "opVal = " +opVal + "\n");
        return 0;
    }
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
