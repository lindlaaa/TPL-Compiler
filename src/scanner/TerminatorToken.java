package src.scanner;
/**
 * The class constructor takes a character as input.
 * Input is expected to be either a period or a semicolon.
 * It implements the Token interface, requiring a toString() method.
 */
public class TerminatorToken implements Token{
    String outputString = "Terminator ";
	char termVal;
    public TerminatorToken(char inputChar){
        outputString += inputChar;
    }
	public char getVal(){
		return termVal;
	}
    /**
     *  When the token’s toString() is called, it will return a
     *  concatenation of the string “Terminator “ with the
     *  character input.
     *
     *  @return String A concatenation of the string “Terminator “
     *                 with the character input.
     */
    @Override
    public String toString(){
        return outputString;
    }
}
