package src.parser;

import src.scanner.*;
import java.util.ArrayList;
import java.util.List;
/*
Main container of our Parser
 */
public class Parser{

  private Scanner source;
  private List<Token> tokenArray = new ArrayList<>();
  int curPos;
  Token curToken;
  Token peekToken;

  public Parser(Scanner source) throws ScanException{

    this.source = source;
    tokenArray = this.source.takeAllTokens();
    curPos = 0;
    curToken = tokenArray.get(curPos);
    peekToken = tokenArray.get(curPos+1);
  }

  public void consumeToken(){
    curPos++;
    curToken = tokenArray.get(curPos);
    try{
      peekToken = tokenArray.get(curPos+1);
    }catch(Exception e){
      //TODO
    }
  }
}





/*-----------------------------------------------------------------------------------------
       ParseRule rule00 = new PushNothing();
       ParseRule ProgramRule01 = new PushRule(
                 new ParseRule[] { new PushTerminal(new keyWordToken("program")),
                                new PushNonTerminal(NonTerminal.Identifier), new PushTerminal(new PunctuationToken('(', 0)),
								new PushTerminal(new TerminatorToken(';',0)), new PushNonTerminal(NonTerminal.Formals),
								new PushTerminal(new PunctuationToken(')', 0)), new PushNonTerminal(NonTerminal.Definitions),
								new PushNonTerminal(NonTerminal.Body), new PushTerminal(new TerminatorToken('.', 0));
                      } );
       ParseAction DefinitonsRule01 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction DefinitionsRule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Def), new PushNonTerminal(NonTerminal.Definitions)
                      } );
       ParseAction DefRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("function", 0), new PushNonTerminal(NonTerminal.Identifier),
                                new PushTerminal(new PunctuationToken('(', 0)), new PushNonTerminal(NonTerminal.Formals),
								new PushTerminal(new PunctuationToken(')', 0)), new PushTerminal(new PunctuationToken(':', 0)),
								new PushNonTerminal(NonTerminal.Type), new PushNonTerminal(NonTerminal.Body),
								new PushTerminal(new TerminatorToken(';', 0)
                      } );
       ParseAction FormalsRule01 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction FormalsRule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.NonEmptyFormals)
                      } );
       ParseAction NonEmptyFormalsRule01 = new PushSequence(<NONEMPTYFORMALS> ::= <FORMAL> , <NONEMPTYFORMALS*>
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Formal), new PushTerminal(new PunctuationToken(',',0)),
                                new PushNonTerminal(NonTerminal.NonEmptyFormalsPrime),
                      } );
       ParseAction NonEmptyFormalsPrimeRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new PunctuationToken(',',0)), new PushNonTerminal(NonTerminal.NonEmptyFormals)
                      } );
       ParseAction NonEmptyFormalsPrimeRule02 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction FormalRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Identifier), new PushTerminal(new PunctuationToken(':',0)),
				 new PushNonTerminal(NonTerminal.Type)
                      } );
       ParseAction FormalRule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.NonEmptyFormals)
                      } );
       ParseAction BodyRule01 = new PushSequence(<NONEMPTYFORMALS> ::= <FORMAL> , <NONEMPTYFORMALS*>
                 new ParseAction[] { new PushTerminal(new KeywordToken("begin", 0)), new PushNonTerminal(NonTerminal.StatementList),
                                new PushTerminal(new KeywordToken("end", 0))
                      } );
       ParseAction StatementListRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.PrintStatement), new PushNonTerminal(NonTerminal.StatementList)
                      } );
       ParseAction StatementListRule02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("return"), new PushNonTerminal(NonTerminal.Expr)
                      } );
       ParseAction TypeRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("integer", 0)
                      } );
       ParseAction TypeRule02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("boolean", 0)
                      } );
       ParseAction ExprRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.SimpleExpr), new PushNonTerminal(NonTerminal.ExprPrime)
                      } );
       ParseAction ExprPrimeRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('<', 0)),new PushNonTerminal(NonTerminal.SimplExpr)
                      } );
       ParseAction ExprPrimeRule02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('=', 0)),new PushNonTerminal(NonTerminal.SimplExpr)
                      } );
       ParseAction ExprPrimeRule03 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction SimpleExprRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Term), new PushNonTerminal(NonTerminal.SimpleExprPrime)
                      } );
       ParseAction SimpleExprRulePrime01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("or", 0)), new PushNonTerminal(NonTerminal.Term)
                      } );
       ParseAction SimpleExprRulePrime02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('+',0)), new PushNonTerminal(NonTerminal.Term)
                      } );
       ParseAction SimpleExprRulePrime03 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('-',0)), new PushNonTerminal(NonTerminal.Term)
                      } );
       ParseAction SimpleExprRulePrime04 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction TermRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Factor), new PushNonTerminal(NonTerminal.TermPrime),
                      } );
       ParseAction TermPrimeRule01 = new PushSequence(
                    new ParseAction[] { new PushTerminal(new KeywordToken("and",0)), new PushNonTerminal(NonTerminal.Factor)
                      } );
       ParseAction TermPrimeRule02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('*',0)), new PushNonTerminal(NonTerminal.Factor)
                      } );
       ParseAction TermPrimeRule03 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('/',0)), new PushNonTerminal(NonTerminal.Factor)
                      } );
       ParseAction TermPrimeRule04 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction FactorRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("if",0)),new PushNonTerminal(NonTerminal.Expr),
                                new PushTerminal(new KeywordToken("then",0)),new PushNonTerminal(NonTerminal.Expr),
								new PushTerminal(new KeywordToken("else",0)),new PushNonTerminal(NonTerminal.Expr)
                      } );
       ParseAction FactorRule02 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new KeywordToken("not",0)),new PushNonTerminal(NonTerminal.Factor)
                      } );
       ParseAction FactorRule03 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Identifier), new PushNonTerminal(NonTerminal.FactorPrime)
                      } );
       ParseAction FactorRule04 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Literal)
                      } );
       ParseAction FactorRule05 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new OpToken('-',0)), new PushNonTerminal(NonTerminal.Factor)
                      } );
       ParseAction FactorRule06 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new PunctuationToken('(', 0)), new PushNonTerminal(NonTerminal.Expr),
								new PushTerminal(new PunctuationToken(')', 0))
                      } );
       ParseAction FactorPrimeRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new PunctuationToken('(', 0)), new PushNonTerminal(NonTerminal.Actuals),
								new PushTerminal(new PunctuationToken(')', 0))
                      } );
       ParseAction FactorPrimeRule02 = new PushSequence(
                 new ParseAction[] { new PushNull()
				      } );
       ParseAction ActualsRule01 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction ActualsRule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.NonEmptyActuals)
                      } );
       ParseAction NonEmptyActualsRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Expr), new PushNonTerminal(NonTerminal.NonEmptyActualsPrime)
                      } );
       ParseAction NonEmptyActualsPrimeRule01 = new PushSequence(
                 new ParseAction[] { new PushTerminal(new PunctuationToken(',',0)),new PushNonTerminal(NonTerminal.Expr),
				 new PushNonTerminal(NonTerminal.NonEmptyActualsPrime)
                      } );
       ParseAction NonEmptyActualsRulePrime02 = new PushSequence(
                 new ParseAction[] { new PushNull()
                      } );
       ParseAction LiteralRule01 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Number)
                      } );
       ParseAction LiteralRule02 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Boolean)
                      } );
       ParseAction PrintStatementRule01 = new PushSequence(//print ( <EXPR> ) ;
                 new ParseAction[] { new PushNonTerminal(new KeywordToken("print",0)),new PushTerminal(new PunctuationToken('(', 0)),
				 new PushNonTerminal(NonTerminal.Expr), new PushTerminal(new PunctuationToken(')', 0)),
				 new PushTerminal(new TerminatorToken(';',0))
                      } );


       table.add( NonTerminal.Program, floatDeclaration, rule01 );
       table.add( NonTerminal.Program, intDeclaration,   rule01 );
       table.add( NonTerminal.Program, print,            rule01 );
       table.add( NonTerminal.Program, identifier,       rule01 );
       table.add( NonTerminal.Program, endOfStream,      rule01 );

       table.add( NonTerminal.Declarations, floatDeclaration, rule02 );
       table.add( NonTerminal.Declarations, intDeclaration,   rule02 );
       table.add( NonTerminal.Declarations, print,            rule00 );
       table.add( NonTerminal.Declarations, identifier,       rule00 );
       table.add( NonTerminal.Declarations, endOfStream,      rule00 );

       table.add( NonTerminal.Declaration, floatDeclaration, rule03 );
       table.add( NonTerminal.Declaration, intDeclaration,   rule04 );

       table.add( NonTerminal.Statements, print,       rule05 );
       table.add( NonTerminal.Statements, identifier,  rule05 );
       table.add( NonTerminal.Statements, endOfStream, rule00 );

       table.add( NonTerminal.Statement, print,      rule06 );
       table.add( NonTerminal.Statement, identifier, rule07 );

       table.add( NonTerminal.ExpressionTail, print,       rule00 );
       table.add( NonTerminal.ExpressionTail, plus,        rule08 );
       table.add( NonTerminal.ExpressionTail, minus,       rule09 );
       table.add( NonTerminal.ExpressionTail, identifier,  rule00 );
       table.add( NonTerminal.ExpressionTail, endOfStream, rule00 );

       table.add( NonTerminal.Value, identifier,   rule10 );
       table.add( NonTerminal.Value, floatValue,   rule11 );
       table.add( NonTerminal.Value, integerValue, rule12 );

       } catch (Exception e) {
           System.out.println( "Trouble building parse table.  No entries." );
       }

       return table;
    }
}*/
