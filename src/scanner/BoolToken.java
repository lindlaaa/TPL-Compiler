package src.scanner;
import src.parser.*;

/**
 *The class constructor takes a string as input. Input is expected
 *to be either "true" or "false". It implements the Token interface,
 *requiring a toString() method.
 */
public class BoolToken implements Token{

  private String outputString = "Boolean ";
  private boolean boolValue;
  private int line;
  private int col;

  public BoolToken(String inputString, int r, int c){
    col = c - inputString.length() - 1;
    line = r;
    if(inputString == "true"){
      boolValue = true;
    }else{
      boolValue = false;
    }
    outputString += inputString;
  }

  public BoolToken(){
    this("false",0,0);
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
    if(boolValue){
      return 17;
    }else{
      return 18;
    }
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
