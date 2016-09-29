package src.scanner;
/**
 *The class constructor takes a string as input. Input is expected
 *to be either "true" or "false". It implements the Token interface,
 *requiring a toString() method.
 */
public class BoolToken implements Token{

  String outputString = "Boolean ";
	boolean boolValue;
  int line;

  public BoolToken(String inputString, int c){
    if(inputString == "true"){
      boolValue = true;
    }else{
      boolValue = false;
    }
    outputString += inputString;
    line = c;
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
