package src.scanner;
/**
 *The class constructor a string as input. It is expected to be a
 *reserved keyword for the language. It implements the Token interface,
 *requiring a toString() method.
 */
public class KeywordToken implements Token{
    String outputString = "Keyword ";
	String keyString;
    public KeywordToken(String inputString){
        outputString += inputString;
    }
	
	public String getVal(){
		return keyString;
	}
    /**
     *  When the token’s toString() is called,
     *  it will return a concatenation of the string “Keyword “
     *  with the string input.
     *
     *  @return String a concatenation of the string “Keyword “
     *                 with the string input.
     */
    @Override
    public String toString(){
        return outputString;
    }
}
