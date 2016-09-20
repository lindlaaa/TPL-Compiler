package src.main.token;
import src.main.exception;


/*
  Token datatype definition
  Last Modified: Avery 09.11
*/

public interface Token{
    public String toString();
}


//FIXME do not need
public class TerminatorToken implements Token{
    String outputString = "Terminator ";
    public TerminatorToken(){
    }
    public String toString(){
        return outputString + ";";
    }
}

//FIXME do not need
public class DecToken implements Token{
    String outputString = "Declaration ";
    public DecToken(String inputString){
        outputString += inputString;
    }
    public String toString(){
        return outputString;
    }
}

//FIXME do not need
public class OpToken implements Token{
    String outputString = "Operator ";
    public OpToken(char inputChar){
        outputString += inputChar;
    }
    public String toString(){
        return outputString;
    }
}
