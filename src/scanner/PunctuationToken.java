package src.scanner;
/**
 *The class constructor takes a character as input.
 *Input is expected to be a symbol understood by the language.
 *It implements the Token interface, requiring a toString() method.
 */
public class PunctuationToken implements Token{

  private String outputString = "Punctuation ";
  private char punctVal;
  private int line;

  public PunctuationToken(char inputChar, int c){
    outputString += inputChar;
    punctVal = inputChar;
    line = c;
  }

  /**
   *  Returns the char represented by this Token
   *
   *  @return String  Represents the char represented by this Token
   */
  public char getVal(){
    return punctVal;
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
    switch (punctVal) {
      case '(':  
        return 2;
      case ')':
        return 3;
      case ',':  
        return 4;
      case ':':
        return 5;
  }  
  /**
   *  When the token’s toString() is called, it will return a
   *  concatenation of the string “Punctuation “ with the
   *  character input.
   *
   *  @return String A concatenation of the string “Punctuation “
   *                 with the character input.
   */
  @Override
  public String toString(){
    return outputString ;
  }
}
