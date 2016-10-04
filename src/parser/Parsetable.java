package src.parser;

import java.util.HashMap;
/*
Contains some data-structure that represents our Parse table,
holding objects [functions?] that act when its block [location]
is called.


 */

public class Parsetable{

  private HashMap table = new HashMap();
}











//TODO FIXME The following is what wallingford made


import java.util.Stack;

public class ParsingTable
{
    private ParseAction[][] table;

    public ParsingTable()
    {
         table = new ParseAction[7][10];
    }

    public ParseAction lookup( NonTerminal symbol, Token token )
    {
        int row = convertNonTerminal( symbol );
        int col = convertToken( token  );

        return table[row][col];
    }

    // ------------------------------------------------------------------

    public void add( NonTerminal symbol, Token token, ParseAction action )
                throws Exception
    {
        int row = convertNonTerminal( symbol );
        int col = convertToken( token  );

        table[row][col] = action;
    }

    public String toString()
    {
        return table.toString();
    }

    // ------------------------------------------------------------------

    private int convertNonTerminal( NonTerminal symbol )
    {
        if (symbol.equals( NonTerminal.Program )       ) return 0;
        if (symbol.equals( NonTerminal.Declarations )  ) return 1;
        if (symbol.equals( NonTerminal.Declaration )   ) return 2;
        if (symbol.equals( NonTerminal.Statements )    ) return 3;
        if (symbol.equals( NonTerminal.Statement )     ) return 4;
        if (symbol.equals( NonTerminal.ExpressionTail )) return 5;
        if (symbol.equals( NonTerminal.Value )         ) return 6;

        return 0;
    }

    private int convertToken( Token token )
    {
        return token.type();
    }
}
