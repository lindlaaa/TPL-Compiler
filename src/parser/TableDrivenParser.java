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
    ParseRule rule00 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule rule01 = new PushRule( //ProgramRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("program", 0)),
                        new PushTerminal(     new IdentifierToken("X", 0, 0)),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new TerminatorToken(';',0)),
                        new PushNonTerminal(  NonTerminal.Definitions),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken('.', 0))
                      } );

    ParseRule rule02 = new PushRule( // DefinitionsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Def),
                        new PushNonTerminal(  NonTerminal.Definitions)
                        } );

    ParseRule rule03 = new PushRule( // DefRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("function", 0)),
                        new PushTerminal(     new IdentifierToken("X", 0,0)),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new PunctuationToken(':', 0)),
                        new PushNonTerminal(  NonTerminal.Type),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken(';', 0))
                        } );

    ParseRule rule04 = new PushRule( // FormalsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule rule05 = new PushRule( // NonEmptyFormalsRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Formal),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormalsPrime),
                        } );

    ParseRule rule06 = new PushRule( // NonEmptyFormalsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',',0)),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule rule07 = new PushRule( // FormalRule01
      new ParseRule[] { new PushTerminal(     new IdentifierToken("X", 0, 0)),
                        new PushTerminal(     new PunctuationToken(':',0)),
                        new PushNonTerminal(  NonTerminal.Type)
                        } );

    ParseRule rule08 = new PushRule( // BodyRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("begin", 0)),
                        new PushNonTerminal(  NonTerminal.StatementList),
                        new PushTerminal(     new KeywordToken("end", 0))
                        } );

    ParseRule rule09 = new PushRule( // StatementListRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.PrintStatement),
                        new PushNonTerminal(  NonTerminal.StatementList)
                        } );

    ParseRule rule10 = new PushRule( // StatementListRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("return", 0)),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule rule11 = new PushRule( // TypeRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("integer", 0))
                        } );

    ParseRule rule12 = new PushRule(
      new ParseRule[] { new PushTerminal(    new KeywordToken("boolean", 0))
                        } );

    ParseRule rule13 = new PushRule( // ExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.SimpleExpr),
                        new PushNonTerminal(  NonTerminal.ExprPrime)
                        } );

    ParseRule rule14 = new PushRule( // ExprPrimeRule01
      new ParseRule[] { new PushTerminal(     new OpToken('<', 0)),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule rule15 = new PushRule( // ExprPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('=', 0)),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule rule16 = new PushRule( // SimpleExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Term),
                        new PushNonTerminal(  NonTerminal.SimpleExprPrime)
                        } );

    ParseRule rule17 = new PushRule( // SimpleExprRulePrime01
      new ParseRule[] { new PushTerminal(     new KeywordToken("or", 0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule18 = new PushRule( // SimpleExprRulePrime02
      new ParseRule[] { new PushTerminal(     new OpToken('+',0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule19 = new PushRule( // SimpleExprRulePrime03
      new ParseRule[] { new PushTerminal(     new OpToken('-',0)),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule20 = new PushRule( // TermRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Factor),
                        new PushNonTerminal(  NonTerminal.TermPrime),
                        } );

    ParseRule rule21 = new PushRule( // TermPrimeRule01
       new ParseRule[] { new PushTerminal(    new KeywordToken("and",0)),
                         new PushNonTerminal( NonTerminal.Factor)
                          } );

    ParseRule rule22 = new PushRule( // TermPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('*',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule23 = new PushRule( // TermPrimeRule03
      new ParseRule[] { new PushTerminal(     new OpToken('/',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule24 = new PushRule( // FactorRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("if",0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("then",0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("else",0)),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule rule25 = new PushRule( // FactorRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("not",0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule26 = new PushRule( // FactorRule03
      new ParseRule[] { new PushTerminal(     new IdentifierToken("X", 0, 0)),
                        new PushNonTerminal(  NonTerminal.IdentifierPrime)
                        } );

    ParseRule rule27 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Literal)
                        } );

    ParseRule rule28 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-',0)),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule29 = new PushRule( // FactorRule06
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')', 0))
                        } );

    ParseRule rule30 = new PushRule( // IdentifierPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Actuals),
                        new PushTerminal(     new PunctuationToken(')', 0))
                        } );

    ParseRule rule31 = new PushRule( // ActualsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyActuals)
                        } );

    ParseRule rule32 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule rule33 = new PushRule( // NonEmptyActualsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',',0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule rule34 = new PushRule(
      new ParseRule[] { new PushTerminal(     new IntToken("0", 0, 0))
                        } );

    ParseRule rule35 = new PushRule(
      new ParseRule[] { new PushTerminal(     new BoolToken("false", 0))
                        } );

    ParseRule rule36 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("print", 0)),
                        new PushTerminal(     new PunctuationToken('(', 0)),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')', 0)),
                        new PushTerminal(     new TerminatorToken(';',0))
                        } );

    //Make symbols '}' and string "a"
    tempTable.add(NonTerminal.Program, new KeywordToken("program", 0), rule01);

    tempTable.add(NonTerminal.Definitions, new KeywordToken("function", 0), rule02);
    tempTable.add(NonTerminal.Definitions, new KeywordToken("begin", 0), rule00);
    tempTable.add(NonTerminal.Definitions, new EOFToken(0), rule00);
    tempTable.add(NonTerminal.Body,        new KeywordToken("body", 0), rule08);
                                                  
//grants	

  tempTable.add(NonTerminal.StatementList, new KeywordToken("print", 0), rule09);
  tempTable.add(NonTerminal.StatementList, new KeywordToken("return", 0), rule10);
  
  tempTable.add(NonTerminal.Type,          new KeywordToken("integer", 0), rule11);
  tempTable.add(NonTerminal.Type,          new KeywordToken("boolean", 0), rule12);
  
  tempTable.add(NonTerminal.Expr,          new PunctuationToken('(', 0), rule13);
  tempTable.add(NonTerminal.Expr,          new OpToken('-', 0), rule13);
  tempTable.add(NonTerminal.Expr,          new KeywordToken("if", 0), rule13);
  tempTable.add(NonTerminal.Expr,          new KeywordToken("not", 0), rule13);
  tempTable.add(NonTerminal.Expr,          new IntToken("0", 0, 0), rule13);
  tempTable.add(NonTerminal.Expr,          new BoolToken("0", 0), rule13);
  tempTable.add(NonTerminal.Expr,          new IdentifierToken("0", 0, 0), rule13);
  
  tempTable.add(NonTerminal.ExprPrime,     new PunctuationToken(')', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new PunctuationToken(',', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("end", 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('+', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('-', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("and", 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('*', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('/', 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("then", 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("else", 0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new EOFToken(0), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('<', 0), rule14);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('=', 0), rule15);
  
  tempTable.add(NonTerminal.SimpleExpr,     new PunctuationToken(')', 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new OpToken('-', 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new KeywordToken("if", 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new KeywordToken("not", 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new IntToken("0", 0, 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new BoolToken("0", 0), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new IdentifierToken("0", 0, 0), rule16);
  
  tempTable.add(NonTerminal.SimpleExprPrime, new PunctuationToken(')', 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("end", 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("and", 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('*', 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('/', 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("then", 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("else", 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('<', 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('=', 0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new EOFToken(0), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("or", 0), rule17);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('+', 0), rule18);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('-', 0), rule19);

  tempTable.add(NonTerminal.Term, new OpToken('(', 0), rule20);
  tempTable.add(NonTerminal.Term, new OpToken('-', 0), rule20);
  tempTable.add(NonTerminal.Term, new KeywordToken("if", 0), rule20);
  tempTable.add(NonTerminal.Term, new KeywordToken("not", 0), rule20);
  tempTable.add(NonTerminal.Term, new IntToken("0", 0, 0), rule20);
  tempTable.add(NonTerminal.Term, new BoolToken("0", 0), rule20);
  tempTable.add(NonTerminal.Term, new IdentifierToken("0", 0, 0), rule20);
	
	tempTable.add(NonTerminal.TermPrime, new PunctuationToken(')', 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new PunctuationToken(',', 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("end", 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("or", 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new OpToken('+', 0), rule00);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('-', 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("and", 0), rule21);
	tempTable.add(NonTerminal.TermPrime, new OpToken('*', 0), rule22);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('/', 0), rule23);		
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("then", 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("else", 0), rule00);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('=', 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new OpToken('<', 0), rule00);
	tempTable.add(NonTerminal.TermPrime, new EOFToken(0), rule00);		
	
    tempTable.add(NonTerminal.Factor, new PunctuationToken(')', 0), rule29);
	tempTable.add(NonTerminal.Factor, new OpToken('-', 0), rule28);	
	tempTable.add(NonTerminal.Factor, new KeywordToken("if", 0), rule24);	
	tempTable.add(NonTerminal.Factor, new KeywordToken("not", 0), rule25);
	tempTable.add(NonTerminal.Factor, new IntToken(0,0,0), rule27);
	tempTable.add(NonTerminal.Factor, new BoolToken("false",0), rule27);	
	tempTable.add(NonTerminal.Factor, new IdentifierToken('X',0,0), rule26);	
	
    tempTable.add(NonTerminal.IdentifierPrime, new PunctuationToken('(', 0), rule30);
    tempTable.add(NonTerminal.IdentifierPrime, new OpToken('-', 0), rule00);		
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("if", 0), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("not", 0), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new IntToken(0,0,0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new BoolToken("false",0), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new IdentifierToken('X',0,0), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new EOFToken(0), rule00);	
	
    tempTable.add(NonTerminal.Actuals, new PunctuationToken('(', 0), rule31);
    tempTable.add(NonTerminal.Actuals, new PunctuationToken(')', 0), rule00);	
    tempTable.add(NonTerminal.Actuals, new OpToken('-', 0), rule31);
    tempTable.add(NonTerminal.Actuals, new KeywordToken("if", 0), rule31);	
    tempTable.add(NonTerminal.Actuals, new KeywordToken("not", 0), rule31);	
    tempTable.add(NonTerminal.Actuals, new IntToken(0,0,0), rule31);
    tempTable.add(NonTerminal.Actuals, new BoolToken("false",0), rule31);	
    tempTable.add(NonTerminal.Actuals, new IdentifierToken('X',0,0), rule31);	
	
	tempTable.add(NonTerminal.NonEmptyActuals, new PunctuationToken('(', 0), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new OpToken('-', 0), rule32);	
	tempTable.add(NonTerminal.NonEmptyActuals, new KeywordToken("if", 0), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new IntToken(0,0,0), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new BoolToken("false",0), rule32);	
	tempTable.add(NonTerminal.NonEmptyActuals, new IdentifierToken('X',0,0), rule32);		
		
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(')', 0), rule00);
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(',', 0), rule33);	
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new EOFToken(0), rule00);	

    tempTable.add(NonTerminal.Literal, new IntToken(0,0,0), rule34);
    tempTable.add(NonTerminal.Literal, new BoolToken("false",0), rule35);	

    tempTable.add(NonTerminal.PrintStatement, new KeywordToken("print", 0), rule36);	

    //return the table to use in parseProgram()
    return tempTable;
  }
}
