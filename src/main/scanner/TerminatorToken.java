
/**
 * The class constructor takes a character as input.
 * Input is expected to be either a period or a semicolon.
 * It implements the Token interface, requiring a toString() method.
 */
public class TerminatorToken implements Token{
    String outputString = "Terminator ";
    public TerminatorToken(char inputChar){
        outputString += inputChar;
    }

    /**
     *  When the token’s toString() is called, it will return a
     *  concatenation of the string “Terminator “ with the
     *  character input.
     *
     *  @return String A concatenation of the string “Terminator “
     *                 with the character input.
     */
    public String toString(){
        return outputString;
    }
}
