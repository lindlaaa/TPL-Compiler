package src.parser;

import java.util.Stack;
import src.scanner.*;

public class TableDrivenParser extends Parser{

  private Parsetable flairTable;
  private Stack stack = new Stack();

  public TableDrivenParser(Scanner source) throws ScanException,
                                                  Exception{

    super(source);
    flairTable = makeParsingTable();
  }


  @SuppressWarnings("unchecked")
  public boolean parseProgram() throws ParseException{

    stack.push(new EOFToken(1));    //push EOF onto stack
    stack.push(NonTerminal.Program);//push program onto stack

    do{ //Main loop
      if(stack.peek() instanceof Token) //Terminal
      {
        if(stack.peek() == curToken){
          stack.pop();
          consumeToken();
        }else
        {
          //ERROR
        }
      }else if(stack.peek() instanceof NonTerminal){ //NonTerminal
        if(flairTable.lookup((NonTerminal)stack.peek(),curToken) != null)
        {
          stack.push(flairTable.lookup((NonTerminal)stack.peek(),curToken));
        }
      }else //Parse Error
      {
        System.out.println("No rule is here");
      }
    }while((stack.peek() instanceof EOFToken) == false);

    return true;
  }


  private Parsetable makeParsingTable() throws ScanException,
                                                Exception{

    Parsetable tempTable = new Parsetable();

    //Make the parse table on google sheets
    //Declare and Add the rules to the table here
    ParseRule ProgramRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("program", 0)),
                        new PushNonTerminal(  NonTerminal.Identifier),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new TerminatorToken(';',0)),
                        new PushNonTerminal(  NonTerminal.Definitions),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken('.', 0))
                      } );

    ParseRule DefinitonsRule01 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule DefinitionsRule02 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Def),
                        new PushNonTerminal(  NonTerminal.Definitions)
                        } );

    ParseRule DefRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("function", 0)),
                        new PushNonTerminal(  NonTerminal.Identifier),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new PunctuationToken(':', 0)),
                        new PushNonTerminal(  NonTerminal.Type),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken(';', 0))
                        } );

    ParseRule FormalsRule01 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule FormalsRule02 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule NonEmptyFormalsRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Formal),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormalsPrime),
                        } );

    ParseRule NonEmptyFormalsPrimeRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',',0)),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule NonEmptyFormalsPrimeRule02 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule FormalRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Identifier),
                        new PushTerminal(     new PunctuationToken(':',0)),
                        new PushNonTerminal(  NonTerminal.Type)
                        } );

    ParseRule BodyRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("begin", 0)),
                        new PushNonTerminal(  NonTerminal.StatementList),
                        new PushTerminal(     new KeywordToken("end", 0))
                        } );

    ParseRule StatementListRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.PrintStatement),
                        new PushNonTerminal(  NonTerminal.StatementList)
                        } );

    ParseRule StatementListRule02 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("return", 0)),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule TypeRule01 = new PushRule(
      new ParseRule[] { new PushTerminal( new KeywordToken("integer", 0))
                        } );

    ParseRule TypeRule02 = new PushRule(
      new ParseRule[] { new PushTerminal( new KeywordToken("boolean", 0))
                        } );

    ParseRule ExprRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.SimpleExpr),
                        new PushNonTerminal(  NonTerminal.ExprPrime)
                        } );

    ParseRule ExprPrimeRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('<', 0)),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule ExprPrimeRule02 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('=', 0)),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule ExprPrimeRule03 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule SimpleExprRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Term),
                        new PushNonTerminal(  NonTerminal.SimpleExprPrime)
                        } );

    ParseRule SimpleExprRulePrime01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("or", 0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule SimpleExprRulePrime02 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('+',0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule SimpleExprRulePrime03 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-',0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule SimpleExprRulePrime04 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule TermRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Factor),
                        new PushNonTerminal(  NonTerminal.TermPrime),
                        } );

    ParseRule TermPrimeRule01 = new PushRule(
       new ParseRule[] { new PushTerminal(      new KeywordToken("and",0)),
                          new PushNonTerminal(  NonTerminal.Factor)
                          } );

    ParseRule TermPrimeRule02 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('*',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule TermPrimeRule03 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('/',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule TermPrimeRule04 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule FactorRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("if",0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("then",0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("else",0)),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule FactorRule02 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("not",0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule FactorRule03 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Identifier),
                        new PushNonTerminal(  NonTerminal.IdentifierPrime)
                        } );

    ParseRule FactorRule04 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Literal)
                        } );

    ParseRule FactorRule05 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule FactorRule06 = new PushRule(
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')', 0))
                        } );

    ParseRule IdentifierPrimeRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Actuals),
                        new PushTerminal(     new PunctuationToken(')', 0))
                        } );

    ParseRule IdentifierPrimeRule02 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule ActualsRule01 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule ActualsRule02 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyActuals)
                        } );

    ParseRule NonEmptyActualsRule01 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule NonEmptyActualsPrimeRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',',0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule NonEmptyActualsRulePrime02 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule LiteralRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(new IntToken("0", 0, 0))
                        } );

    ParseRule LiteralRule02 = new PushRule(
      new ParseRule[] { new PushTerminal(new BoolToken("false", 0))
                        } );

    ParseRule PrintStatementRule01 = new PushRule(
      new ParseRule[] { new PushTerminal(  new KeywordToken("print", 0)),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new TerminatorToken(';',0))
                        } );

    tempTable.add(NonTerminal.Program, new KeywordToken("program", 0), ProgramRule01);

    //return the table to use in parseProgram()
    return tempTable;
  }
}
