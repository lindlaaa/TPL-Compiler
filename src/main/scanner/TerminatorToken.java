package src.main.scanner;
/**
 *
 */
public class TerminatorToken implements Token{
    String outputString = "Terminator ";
    public TerminatorToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){
        return outputString;
    }
}
