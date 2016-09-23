

/**
 *
 */
public class PunctuationToken implements Token{
    String outputString = "Punctuation ";
    public PunctuationToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){
        return outputString;
    }
}
