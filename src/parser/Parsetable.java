package src.parser;

import src.scanner.*;

/*
Contains some data-structure that represents our Parse table,
holding objects [functions?] that act when its block [location]
is called.


 */

public class Parsetable{

  private ParseRule[][] table;

  public Parsetable(){
    table = new ParseRule[24][31];
  }

  public ParseRule lookup(NonTerminal symbol,
                          Token token)
                          throws ParseException
  {

    int row = convertNonTerminal(symbol);
    int column = convertTerminal(token);

    if(table[row][column] == null){
      /*throw new ParseException("--Tried to reach block that is undefined--\n"+
                                "Stack: "+symbol+" - Row#:"+row+"\n"+
                                "Stream:"+token+" - column#:"+column+"\n");*/
      throw new ParseException("--Tried to reach block that is undefined--\n");
    }

    return table[row][column];
  }


  public void add( NonTerminal symbol, Token token, ParseRule action )
              throws Exception
  {
    int row = convertNonTerminal( symbol );
    int column = convertTerminal( token  );

    table[row][column] = action;
  }

  public String toString()
  {
    return table.toString();
  }

  private int convertNonTerminal( NonTerminal symbol )
  {
    if (symbol.equals( NonTerminal.Program )              ) return 0;
    if (symbol.equals( NonTerminal.Definitions )          ) return 1;
    if (symbol.equals( NonTerminal.Def )                  ) return 2;
    if (symbol.equals( NonTerminal.Formals )              ) return 3;
    if (symbol.equals( NonTerminal.Formal )               ) return 4;
    if (symbol.equals( NonTerminal.NonEmptyFormals )      ) return 5;
    if (symbol.equals( NonTerminal.NonEmptyFormalsPrime)  ) return 6;
    if (symbol.equals( NonTerminal.Body )                 ) return 7;
    if (symbol.equals( NonTerminal.StatementList )        ) return 8;
    if (symbol.equals( NonTerminal.Type )                 ) return 9;
    if (symbol.equals( NonTerminal.Expr )                 ) return 10;
    if (symbol.equals( NonTerminal.ExprPrime )            ) return 11;
    if (symbol.equals( NonTerminal.SimpleExpr )           ) return 12;
    if (symbol.equals( NonTerminal.SimpleExprPrime)       ) return 13;
    if (symbol.equals( NonTerminal.Term )                 ) return 14;
    if (symbol.equals( NonTerminal.TermPrime )            ) return 15;
    if (symbol.equals( NonTerminal.Factor )               ) return 16;
    if (symbol.equals( NonTerminal.IdentifierPrime )      ) return 17;
    if (symbol.equals( NonTerminal.Actuals )              ) return 18;
    if (symbol.equals( NonTerminal.NonEmptyActuals )      ) return 19;
    if (symbol.equals( NonTerminal.NonEmptyActualsPrime ) ) return 20;
    if (symbol.equals( NonTerminal.Literal )              ) return 21;
    if (symbol.equals( NonTerminal.PrintStatement )       ) return 22;

    return 0;
  }

    private int convertTerminal( Token token )
    {
      return token.getTerminalType();
    }
}










/*
//TODO FIXME The following is what wallingford made


import java.util.Stack;

public class ParseRule
{
    private ParseRule[][] table;

    public ParsingTable()
    {
         table = new ParseRule[22][31];
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

    private int convertToken( Token token )
    {
        return token.type();
    }
}
*/
