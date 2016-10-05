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

  public Parser(Scanner source) throws ScanException{

    this.source = source;
    tokenArray = this.source.takeAllTokens();
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
       ParseAction DefRule01 = new PushSequence(//function <IDENTIFIER> ( <FORMALS> ) : <TYPE> <BODY> ;
                 new ParseAction[] { new PushTerminal(new KeywordToken("function", 0), new PushNonTerminal(NonTerminal.Identifier),
                                new PushTerminal(new PunctuationToken('(', 0)), new PushNonTerminal(NonTerminal.Formals),
								new PushTerminal(new PunctuationToken(')', 0)), new PushTerminal(new PunctuationToken(':', 0)),
								new PushNonTerminal(NonTerminal.Type), new PushNonTerminal(NonTerminal.Body), 
								new PushTerminal(new TerminatorToken(';', 0)							
                      } );
       ParseAction rule05 = new PushSequence(
                 new ParseAction[] { new PushNonTerminal(NonTerminal.Statement),
                                new PushNonTerminal(NonTerminal.Statements)
                      } );
       ParseAction rule06 = new PushSequence(
                 new ParseAction[] { new PushTerminal(print),
                                new PushTerminal(identifier)
                      } );
       ParseAction rule07 = new PushSequence(
                 new ParseAction[] { new PushTerminal(identifier),
                                new PushTerminal(assignment),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule08 = new PushSequence(
                 new ParseAction[] { new PushTerminal(plus),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule09 = new PushSequence(
                 new ParseAction[] { new PushTerminal(minus),
                                new PushNonTerminal(NonTerminal.Value),
                                new PushNonTerminal(NonTerminal.ExpressionTail)
                      } );
       ParseAction rule10 = new PushTerminal(identifier);
       ParseAction rule11 = new PushTerminal(floatValue);
       ParseAction rule12 = new PushTerminal(integerValue);

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
