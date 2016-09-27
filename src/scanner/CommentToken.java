package src.scanner;
/**
*The class constructor takes a string as input.
*Input is expected to start with a letter while all other
*characters are alphanumeric. It implements the Token interface,
*requiring a toString() method.
*/
public class CommentToken implements Token{

  String outputString = "Comment ";
  String commentVal;
  int column;

  public CommentToken(String inputString, int c){
	  commentVal = inputString;
    outputString += inputString;
    column = c;
  }

  /**
   *  Returns the comment represented by this Token
   *
   *  @return String  Represents the comment represented by this Token
   */
  public String getVal(){
	   return commentVal;
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
   *  When the token’s toString() is
   *  called, it will return a concatenation of the
   *  string "Comment “ with the comment input.
   *
   *  @return String A concatenation of the
   *                 string “Comment “ with the String input.
   */
  @Override
  public String toString(){
    return outputString;
  }
}
