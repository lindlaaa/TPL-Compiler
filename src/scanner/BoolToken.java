package src.scanner;
/**
 *The class constructor takes a string as input. Input is expected
 *to be either "true" or "false". It implements the Token interface,
 *requiring a toString() method.
 */
public class BoolToken implements Token{
    String outputString = "Boolean ";
    boolean boolVal;
        
    public BoolToken(String inputString){
	boolVal = Boolean.parseBoolean(inputString);
        outputString += inputString;
    }
	
    public boolean getVal(){
	return boolVal;
    }
    /**
     *  When the token's toString()
     *  is called, it will return a concatenation of the string
     *  “Boolean “ with the string input.
     *
     *  @return String A concatenation of the string
     *                 “Boolean “ with the string input.
     */
    @Override
    public String toString(){
        return outputString;
    }
}
