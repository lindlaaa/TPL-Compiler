package src.scanner;

import src.parser.*;
import src.parser.symboltable.*;
import src.parser.semanticanalyzer.*;
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

    public int getCol();

    public int getTerminalType();

    public LexicalPair getLexicalPair();
}
