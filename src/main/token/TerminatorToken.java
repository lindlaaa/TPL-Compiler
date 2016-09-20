package src.main.token;

public class TerminatorToken implements Token{
    String outputString = "Terminator ";
    public TerminatorToken(){
    }
    public String toString(){
        return outputString + ";";
    }
}
