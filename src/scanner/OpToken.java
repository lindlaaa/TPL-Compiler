package src.scanner;
/**
 * The class constructor takes a character as input.
 * Input is expected to be a mathematical operator.
 * It implements the Token interface, requiring a toString() method.
 */
public class OpToken implements Token{
    String outputString = "Operator ";
    public OpToken(char inputChar){
        outputString += inputChar;
    }

    /**
     *  When the token’s toString() is called,
     *  it will return a concatenation of the string “Operator “
     *  with the character input.
     *
     *  @return String A concatenation of the string “Operator “
     *                 with the character input.
     */
    public String toString(){
        return outputString;
    }
}
