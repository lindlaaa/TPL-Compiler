
/**
 *The class constructor takes a string as input. Input is expected
 *to be either “true” or “false”. It implements the Token interface,
 *requiring a toString() method.
 */
public class BoolToken implements Token{
    String outputString = "Boolean ";
    public BoolToken(String inputString){
        outputString += inputString;
    }

    /**
     *  When the token’s toString()
     *  is called, it will return a concatenation of the string
     *  “Boolean “ with the string input.
     *
     *  @return String A concatenation of the string
     *                 “Boolean “ with the string input.
     */
    public String toString(){
        return outputString;
    }
}
