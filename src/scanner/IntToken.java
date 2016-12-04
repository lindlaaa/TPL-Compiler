package src.scanner;

import src.parser.semanticanalyzer.*;
import src.parser.symboltable.*;
import src.parser.*;
/**
 *The class constructor takes a string as input.
 *All characters are expected to be digits.
 *Once the value is stored, it will be parsed to a long and a
 *maximum value will be enforced.
 *If the long is larger than the max, ScanException will be thrown.
 *It implements the Token interface, requiring a toString() method.
 */
public class IntToken implements Token{

  private final long MAX = 4294967295L;
  private String outputString = "Integer ";
  private long intValue;
  private int line;
  private int col;

  public IntToken(String inputInt, int r, int c) throws ScanException{
    intValue = Long.parseLong(inputInt);
    if( intValue > MAX ){
      col = c - inputInt.length() - 1;
      throw new ScanException("--Int too long |"
                              +inputInt.substring(0, 6)+
                              "...| @ ln:"+r+"col:"+col+"--\n" +
        "                                           ^");
    }
    outputString += inputInt;
    line = r;
  }
  public IntToken() throws ScanException{
    this("0",0,0);
  }
  /**
   * When called it returns the integer value of the token
   *
   * @return long representing the int value of this token
   */
  public long getVal(){
      return intValue;
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


  public LexicalPair getLexicalPair(){
	  return new LexicalPair(this.line, this.col);
  }


  public int getTerminalType(){
    return 28;
  }
  /**
   * When the token’s toString() is called, it will return a
   * concatenation of the string “Integer “ with the string
   * input after it has been parsed to a long.
   *
   * @return String a concatenation
   *                of the string “Integer “ with the string input
   *                after it has been parsed to a long.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
