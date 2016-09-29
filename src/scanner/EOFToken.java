package src.scanner;
/**
 * The class constructor takes no input
 * It is used to represent the end of the file stream
 * It implements the Token interface, requiring a toString() method.
 */
public class EOFToken implements Token{

  String outputString = "EOFToken ";
    int column;

  public EOFToken(){
    outputString += '$';
  }

  /**
   *  Returns the EOF symbol
   *
   *  @return '$' to represent EOF
   */
    public char getVal(){
        return '$';
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
