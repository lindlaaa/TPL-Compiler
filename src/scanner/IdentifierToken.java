package src.scanner;
/**
 *The class constructor takes a string as input.
 *Input is expected to start with a letter while all other
 *characters are alphanumeric. It implements the Token interface,
 *requiring a toString() method.
 */
public class IdentifierToken implements Token{
    String outputString = "Identifier ";
    String idVal;
    
    public IdentifierToken(String inputString){
        idVal = inputString;
        outputString += inputString;
    }
    
    public String getVal(){
            return idVal;
    }

    /**
     *  When the token’s toString() is
     *  called, it will return a concatenation of the
     *  string “Identifier “ with the character input.
     *
     *  @return String A concatenation of the
     *                 string “Identifier “ with the character input.
     */
    @Override
    public String toString(){
        return outputString;
    }
}
