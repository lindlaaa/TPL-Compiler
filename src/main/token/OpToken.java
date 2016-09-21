

public class OpToken implements Token{
    String outputString = "Operator ";
    public OpToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){
        return outputString;
    }
}
