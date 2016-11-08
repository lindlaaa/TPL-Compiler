package src.scanner;
import src.parser.*;
/**
 *The class constructor takes a string as input.
 *Input is expected to start with a letter while all other
 *characters are alphanumeric. It implements the Token interface,
 *requiring a toString() method.
 */
public class IdentifierToken implements Token{

  private String outputString = "Identifier ";
  private String idVal;
  private int line;
  private int col;

  public IdentifierToken(String inputString,int r, int c) throws ScanException{
    col = c - inputString.length() - 1;
    if(inputString.length() > 256){
      throw new ScanException("--ID too long |"
                              +inputString.substring(0, 12)+
                              "...| @ ln:"+r+"col:"+col+"--\n" +
        "                                          ^");
    }
    idVal = inputString;
    outputString += inputString;
    line = r;
  }
  public IdentifierToken() throws ScanException{
    this("X",0,0);
  }

  /**
   *  Returns the name represented by this Token
   *
   *  @return String  Represents the name represented by this Token
   */
  public String getVal(){
    return idVal;
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


  public int getTerminalType(){
    return 29;
  }

  /**
   *  When the token’s toString() is
   *  called, it will return a concatenation of the
   *  string “Identifier “ with the character input.
   *
   *  @return String A concatenation of the
   *                 string “Identifier “ with the character input.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
