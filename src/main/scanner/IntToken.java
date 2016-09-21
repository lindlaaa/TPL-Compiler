
public class IntToken implements Token{
    
    private final double MAX = Math.pow(2, 32) - 1;
    String outputString = "Integer ";
    double intValue;
    
    public IntToken(String inputInt){
        intValue = (Double.parseDouble(inputInt)) % MAX;
        outputString += intValue;
    }
    public double getIntValue(){
        return intValue;
    }
    @Override
    public String toString(){
        return outputString;
    }
}
