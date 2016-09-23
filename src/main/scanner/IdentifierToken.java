package src.main.scanner;
/**
 *
 */
public class IdentifierToken implements Token{
    String outputString = "Identifier ";
    public IdentifierToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}
