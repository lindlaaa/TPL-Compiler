package src.scanner;
/**
 *The class constructor takes a string as input.
 *All characters are expected to be digits.
 *Once the value is stored, it will be parsed to a double and a
 *maximum value will be enforced.
 *If the double is larger than the max, ScanException will be thrown.
 *It implements the Token interface, requiring a toString() method.
 */
public class IntToken implements Token{

  private final double MAX = Math.pow(2, 32) - 1;
  String outputString = "Integer ";
  double intValue;
  int column;

  public IntToken(String inputInt, int c) throws ScanException{
    intValue = (Double.parseDouble(inputInt));
    if( intValue > MAX ){
      throw new ScanException(" --INTEGER VALUE TOO LARGE "+c+"-- ");
    }
    outputString += inputInt;
    column = c;
  }

  /**
   * When called it returns the integer value of the token
   *
   * @return double representing the int value of this token
   */
  public double getVal(){
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
   * input after it has been parsed to a double.
   *
   * @return String a concatenation
   *                of the string “Integer “ with the string input
   *                after it has been parsed to a double.
   */
  @Override
  public String toString(){
      return outputString;
  }
}
