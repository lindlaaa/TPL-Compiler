package src.main.token;

public class IdentifierToken implements Token{
    String outputString = "Identifier ";
    public IdentifierToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}
