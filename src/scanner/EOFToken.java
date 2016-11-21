package src.scanner;


import src.parser.semanticanalyzer.*;
import src.parser.symboltable.*;
import src.parser.*;
/**
 * The class constructor takes no input
 * It is used to represent the end of the file stream
 * It implements the Token interface, requiring a toString() method.
 */
public class EOFToken implements Token{

  private String outputString = "EOFToken ";
  private int line;

  public EOFToken(int c){
    outputString += '$';
    line = c;
  }

  public EOFToken(){
    this(0);
  }
  /**
   *  Returns the EOF symbol
   *
   *  @return '$' to represent EOF
   */
  public char getVal(){
      return '$';
  }


  public int getTerminalType(){
    return 30;
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
    return 0;
  }

  /**
   *  When the token’s toString() is called, it will return a
   *  the char EOF
   *
   *  @return EOF symbol
   */
  @Override
  public String toString(){
      return outputString;
  }
}
