

public class KeywordToken implements Token{
    String outputString = "Keyword ";
    public KeywordToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}
