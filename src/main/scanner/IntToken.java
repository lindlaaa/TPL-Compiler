

public class IntToken implements Token{
    String outputString = "Integer ";
    public IntToken(String inputInt){
        outputString += inputInt;
    }
    public String toString(){
        return outputString;
    }
}
