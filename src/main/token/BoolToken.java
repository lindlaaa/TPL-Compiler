

public class BoolToken implements Token{
    String outputString = "Boolean ";
    public BoolToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}
