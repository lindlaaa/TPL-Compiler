package src.scanner;
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
  String outputString = "Integer ";
  long intValue;
  int column;

  public IntToken(String inputInt, int c) throws ScanException{
    intValue = Long.parseLong(inputInt);
    if( intValue > MAX ){
      throw new ScanException("--Integer value too large: "+inputInt+
                              " at line:" +c+ "--\n");
    }
    outputString += inputInt;
    column = c;
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
   *  Returns the column number this Token is in
   *
   *  @return int Representing the column location of this Token
   */
  public int getColumn(){
    return column;
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
