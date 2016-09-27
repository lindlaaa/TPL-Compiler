package src.scanner;
/**
 *The class constructor takes a string as input.
 *Input is expected to start with a letter while all other
 *characters are alphanumeric. It implements the Token interface,
 *requiring a toString() method.
 */
public class IdentifierToken implements Token{
    String outputString = "Identifier ";
    public IdentifierToken(String inputString){
        outputString += inputString;
    }

    /**
     *  When the token’s toString() is
     *  called, it will return a concatenation of the
     *  string “Identifier “ with the character input.
     *
     *  @return String A concatenation of the
     *                 string “Identifier “ with the character input.
     */
    public String toString(){
        return outputString;
    }
}
