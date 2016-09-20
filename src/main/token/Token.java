package src.main.token;
import src.main.exception;


/*
  Token datatype definition
  Last Modified: Avery 09.20
*/

public interface Token{
    public String toString();
}


//FIXME do not need
/*
public class DecToken implements Token{
    String outputString = "Declaration ";
    public DecToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}
*/
