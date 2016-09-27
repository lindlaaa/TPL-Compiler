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
   
    public CommentToken(String inputString){
	commentVal = inputString;
        outputString += inputString;
    }
    
    public String getVal(){
	return commentVal;
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
