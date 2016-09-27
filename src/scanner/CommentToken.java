package src.scanner;


/**
*The class constructor takes a string as input.
*Input is expected to start with a letter while all other
*characters are alphanumeric. It implements the Token interface,
*requiring a toString() method.
*/
public class CommentToken implements Token{
   String outputString = "Comment ";
   public CommentToken(String inputString){
       outputString += inputString;
   }

   /**
    *  When the token’s toString() is
    *  called, it will return a concatenation of the
    *  string "Comment “ with the comment input.
    *
    *  @return String A concatenation of the
    *                 string “Comment “ with the String input.
    */
   public String toString(){
       return outputString;
   }
}
