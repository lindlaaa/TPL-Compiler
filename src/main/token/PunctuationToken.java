package src.main.token;

public class PunctToken implements Token{
    String outputString = "Punctuation ";
    public PunctToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){
        return outputString;
    }
}
