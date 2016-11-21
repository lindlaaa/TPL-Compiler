package src.scanner;

import src.parser.semanticanalyzer.*;
import src.parser.symboltable.*;
import src.parser.*;
/**
 *The class constructor a string as input. It is expected to be a
 *reserved keyword for the language. It implements the Token interface,
 *requiring a toString() method.
 */
public class KeywordToken implements Token{

  private String outputString = "Keyword ";
  private String keyString;
  private int line;
  private int col;

  public KeywordToken(String inputString, int r, int c){
    col = c - inputString.length() - 1;
    line = r;
    outputString += inputString;
    keyString = inputString;
  }

  public KeywordToken(String inputString){
    this(inputString,0,0);
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
   *  Returns the column number this token BEGINS at.
   *
   *  @return int Representing the column position if the token.
   */
  public int getCol(){
    return col;
  }
  /*
  {"if", "then", "else", "integer",
    "boolean", "true", "false", "not", "or", "and", "print", "program",
    "function", "return", "begin", "end"};
  */
  public int getTerminalType(){
    switch (keyString) {
      case "if":
        return 12;
      case "then":
        return 13;
      case "else":
        return 14;
      case "integer":
        return 15;
      case "boolean":
        return 16;
      case "not":
        return 19;
      case "or":
        return 20;
      case "and":
        return 21;
      case "print":
        return 22;
      case "program":
        return 23;
      case "function":
        return 24;
      case "return":
        return 25;
      case "begin":
        return 26;
      case "end":
        return 27;
      default:
        System.out.println("ERROR IN THE KEYWORD TERMTYPE\n"+
                "keyString = "+keyString+"\n");
        return 0;
    }
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
