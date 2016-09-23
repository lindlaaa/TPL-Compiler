
/**
 *
 */
public class IntToken implements Token{

    private final double MAX = Math.pow(2, 32) - 1;
    String outputString = "Integer ";
    double intValue;

    public IntToken(String inputInt) throws ScanException{
        intValue = (Double.parseDouble(inputInt));
        if( intValue > MAX ){
          throw new ScanException( " --INTEGER VALUE TOO LARGE-- " );
        }
        outputString += intValue;
    }

    /**
     * [getIntValue description]
     * @return [description]
     */
    public double getIntValue(){
        return intValue;
    }

    /**
     * [toString description]
     * @return [description]
     */
    @Override
    public String toString(){
        return outputString;
    }
}
