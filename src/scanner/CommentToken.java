package src.scanner;
/**
*The class constructor takes a string as input.
*Input is expected to start with a letter while all other
*characters are alphanumeric. It implements the Token interface,
*requiring a toString() method.
*/
public class CommentToken implements Token{

  private String outputString = "Comment ";
  private String commentVal;
  private int line;

  public CommentToken(String inputString, int c){
	  commentVal = inputString;
    outputString += inputString;
    line = c;
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
   *  Returns the line number this Token is in
   *
   *  @return int Representing the line location of this Token
   */
  public int getline(){
    return line;
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
