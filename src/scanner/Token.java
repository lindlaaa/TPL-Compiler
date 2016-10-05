package src.scanner;
/**
 *  This is an interface used to classify all the classes ending in
 *  "Token". Token only enforces the toString() method.
 *  This interface is especially useful when creating
 *  an array of mixed token types.
 */
public interface Token{

    @Override
    public String toString();

    public int getline();
    
    //public <T> T getVal();
    
    public int getTerminalType();
}
