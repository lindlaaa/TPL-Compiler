

/**
 *The class constructor takes a character as input.
 *Input is expected to be a symbol understood by the language.
 *It implements the Token interface, requiring a toString() method.
 */
public class PunctuationToken implements Token{
    String outputString = "Punctuation ";
    public PunctuationToken(char inputChar){
        outputString += inputChar;
    }

    /**
     *  When the token’s toString() is called, it will return a
     *  concatenation of the string “Punctuation “ with the
     *  character input.
     *
     *  @return String A concatenation of the string “Punctuation “
     *                 with the character input.
     */
    public String toString(){
        return outputString;
    }
}
