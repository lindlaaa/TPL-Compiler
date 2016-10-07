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

    System.out.println(curToken);
    System.out.println(stack.peek());

    do{ //Main loop
      System.out.println("Top of stack is terminal: "+(stack.peek() instanceof NonTerminal)+"\n");
      System.out.println("Before first if");
      if(stack.peek() instanceof Token) //Terminal
      {
        System.out.println("before first ifs if");
        if(stack.peek() == curToken){
          System.out.println("after first ifs if");
          stack.pop();
          consumeToken();
          System.out.println("after first ifs ifs consumeToken");
        }else
        {
          //ERROR
        }
        System.out.println("Before second if");
      }else if(stack.peek() instanceof NonTerminal){ //NonTerminal
        System.out.println("before second ifs if");
        if(flairTable.lookup(stack.peek(), curToken) != null)
        {
          System.out.println("after second ifs if");
          stack.push(flairTable.lookup(stack.pop(),curToken));
        }
      }else //Parse Error
      {
        throw new ParseException("--There is no rule here--");
      }
      System.out.println("before restarting the while loop");
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
      new ParseRule[] { new PushTerminal(     new KeywordToken("program")),
                        new PushTerminal(     new IdentifierToken()),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';')),
                        new PushNonTerminal(  NonTerminal.Definitions),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken('.'))
                      } );

    ParseRule rule02 = new PushRule( // DefinitionsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Def),
                        new PushNonTerminal(  NonTerminal.Definitions)
                        } );

    ParseRule rule03 = new PushRule( // DefRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("function")),
                        new PushTerminal(     new IdentifierToken()),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken(';'))
                        } );

    ParseRule rule04 = new PushRule( // FormalsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule rule05 = new PushRule( // NonEmptyFormalsRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Formal),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormalsPrime),
                        } );

    ParseRule rule06 = new PushRule( // NonEmptyFormalsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormals)
                        } );

    ParseRule rule07 = new PushRule( // FormalRule01
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type)
                        } );

    ParseRule rule08 = new PushRule( // BodyRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("begin")),
                        new PushNonTerminal(  NonTerminal.StatementList),
                        new PushTerminal(     new KeywordToken("end"))
                        } );

    ParseRule rule09 = new PushRule( // StatementListRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.PrintStatement),
                        new PushNonTerminal(  NonTerminal.StatementList)
                        } );

    ParseRule rule10 = new PushRule( // StatementListRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("return")),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule rule11 = new PushRule( // TypeRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("integer"))
                        } );

    ParseRule rule12 = new PushRule(
      new ParseRule[] { new PushTerminal(    new KeywordToken("boolean"))
                        } );

    ParseRule rule13 = new PushRule( // ExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.SimpleExpr),
                        new PushNonTerminal(  NonTerminal.ExprPrime)
                        } );

    ParseRule rule14 = new PushRule( // ExprPrimeRule01
      new ParseRule[] { new PushTerminal(     new OpToken('<')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule rule15 = new PushRule( // ExprPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('=')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr)
                        } );

    ParseRule rule16 = new PushRule( // SimpleExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Term),
                        new PushNonTerminal(  NonTerminal.SimpleExprPrime)
                        } );

    ParseRule rule17 = new PushRule( // SimpleExprRulePrime01
      new ParseRule[] { new PushTerminal(     new KeywordToken("or")),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule18 = new PushRule( // SimpleExprRulePrime02
      new ParseRule[] { new PushTerminal(     new OpToken('+')),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule19 = new PushRule( // SimpleExprRulePrime03
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.Term)
                        } );

    ParseRule rule20 = new PushRule( // TermRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Factor),
                        new PushNonTerminal(  NonTerminal.TermPrime),
                        } );

    ParseRule rule21 = new PushRule( // TermPrimeRule01
       new ParseRule[] { new PushTerminal(    new KeywordToken("and")),
                         new PushNonTerminal( NonTerminal.Factor)
                          } );

    ParseRule rule22 = new PushRule( // TermPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('*')),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule23 = new PushRule( // TermPrimeRule03
      new ParseRule[] { new PushTerminal(     new OpToken('/')),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule24 = new PushRule( // FactorRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("if")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("then")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("else")),
                        new PushNonTerminal(  NonTerminal.Expr)
                        } );

    ParseRule rule25 = new PushRule( // FactorRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("not")),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule26 = new PushRule( // FactorRule03
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
                        new PushNonTerminal(  NonTerminal.IdentifierPrime)
                        } );

    ParseRule rule27 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Literal)
                        } );

    ParseRule rule28 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.Factor)
                        } );

    ParseRule rule29 = new PushRule( // FactorRule06
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')'))
                        } );

    ParseRule rule30 = new PushRule( // IdentifierPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Actuals),
                        new PushTerminal(     new PunctuationToken(')'))
                        } );

    ParseRule rule31 = new PushRule( // ActualsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyActuals)
                        } );

    ParseRule rule32 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule rule33 = new PushRule( // NonEmptyActualsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime)
                        } );

    ParseRule rule34 = new PushRule(
      new ParseRule[] { new PushTerminal(     new IntToken())
                        } );

    ParseRule rule35 = new PushRule(
      new ParseRule[] { new PushTerminal(     new BoolToken())
                        } );

    ParseRule rule36 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("print")),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';'))
                        } );

    //Make symbols '}' and string "a"
<<<<<<< HEAD
    tempTable.add(NonTerminal.Program,         new KeywordToken("program", 0), rule01);

    tempTable.add(NonTerminal.Definitions,     new KeywordToken("function", 0), rule02);
    tempTable.add(NonTerminal.Definitions,     new KeywordToken("begin", 0), rule00);
    tempTable.add(NonTerminal.Definitions,     new EOFToken(0), rule00);

    tempTable.add(NonTerminal.Body,            new KeywordToken("begin", 0), rule08);

    tempTable.add(NonTerminal.StatementList,   new KeywordToken("print", 0), rule09);
    tempTable.add(NonTerminal.StatementList,   new KeywordToken("return", 0), rule10);

    tempTable.add(NonTerminal.Type,            new KeywordToken("integer", 0), rule11);
    tempTable.add(NonTerminal.Type,            new KeywordToken("boolean", 0), rule12);

    tempTable.add(NonTerminal.Expr,            new PunctuationToken('(', 0), rule13);
    tempTable.add(NonTerminal.Expr,            new OpToken('-', 0), rule13);
    tempTable.add(NonTerminal.Expr,            new KeywordToken("if", 0), rule13);
    tempTable.add(NonTerminal.Expr,            new KeywordToken("not", 0), rule13);
    tempTable.add(NonTerminal.Expr,            new IntToken("0", 0, 0), rule13);
    tempTable.add(NonTerminal.Expr,            new BoolToken("0", 0), rule13);
    tempTable.add(NonTerminal.Expr,            new IdentifierToken("0", 0, 0), rule13);

    tempTable.add(NonTerminal.ExprPrime,       new PunctuationToken(')', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new PunctuationToken(',', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new KeywordToken("end", 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('+', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('-', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new KeywordToken("and", 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('*', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('/', 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new KeywordToken("then", 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new KeywordToken("else", 0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new EOFToken(0), rule00);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('<', 0), rule14);
    tempTable.add(NonTerminal.ExprPrime,       new OpToken('=', 0), rule15);

    tempTable.add(NonTerminal.SimpleExpr,      new PunctuationToken(')', 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new OpToken('-', 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new KeywordToken("if", 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new KeywordToken("not", 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new IntToken("0", 0, 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new BoolToken("false", 0), rule16);
    tempTable.add(NonTerminal.SimpleExpr,      new IdentifierToken("0", 0, 0), rule16);

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

    tempTable.add(NonTerminal.Term,            new PunctuationToken('(', 0), rule20);
    tempTable.add(NonTerminal.Term,            new OpToken('-', 0), rule20);
    tempTable.add(NonTerminal.Term,            new KeywordToken("if", 0), rule20);
    tempTable.add(NonTerminal.Term,            new KeywordToken("not", 0), rule20);
    tempTable.add(NonTerminal.Term,            new IntToken("0", 0, 0), rule20);
    tempTable.add(NonTerminal.Term,            new BoolToken("false", 0), rule20);
    tempTable.add(NonTerminal.Term,            new IdentifierToken("0", 0, 0), rule20);

  	tempTable.add(NonTerminal.TermPrime,       new PunctuationToken(')', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new PunctuationToken(',', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new KeywordToken("end", 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new KeywordToken("or", 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('+', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('-', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new KeywordToken("and", 0), rule21);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('*', 0), rule22);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('/', 0), rule23);
  	tempTable.add(NonTerminal.TermPrime,       new KeywordToken("then", 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new KeywordToken("else", 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('=', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new OpToken('<', 0), rule00);
  	tempTable.add(NonTerminal.TermPrime,       new EOFToken(0), rule00);

    tempTable.add(NonTerminal.Factor,          new PunctuationToken(')', 0), rule29);
  	tempTable.add(NonTerminal.Factor,          new OpToken('-', 0), rule28);
  	tempTable.add(NonTerminal.Factor,          new KeywordToken("if", 0), rule24);
  	tempTable.add(NonTerminal.Factor,          new KeywordToken("not", 0), rule25);
  	tempTable.add(NonTerminal.Factor,          new IntToken("0",0,0), rule27);
  	tempTable.add(NonTerminal.Factor,          new BoolToken("false",0), rule27);
  	tempTable.add(NonTerminal.Factor,          new IdentifierToken("0",0,0), rule26);

    tempTable.add(NonTerminal.IdentifierPrime, new PunctuationToken('(', 0), rule30);
    tempTable.add(NonTerminal.IdentifierPrime, new OpToken('-', 0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("if", 0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("not", 0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new IntToken("0",0,0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new BoolToken("false",0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new IdentifierToken("X",0,0), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new EOFToken(0), rule00);

    tempTable.add(NonTerminal.Actuals,         new PunctuationToken('(', 0), rule31);
    tempTable.add(NonTerminal.Actuals,         new PunctuationToken(')', 0), rule00);
    tempTable.add(NonTerminal.Actuals,         new OpToken('-', 0), rule31);
    tempTable.add(NonTerminal.Actuals,         new KeywordToken("if", 0), rule31);
    tempTable.add(NonTerminal.Actuals,         new KeywordToken("not", 0), rule31);
    tempTable.add(NonTerminal.Actuals,         new IntToken("0",0,0), rule31);
    tempTable.add(NonTerminal.Actuals,         new BoolToken("false",0), rule31);
    tempTable.add(NonTerminal.Actuals,         new IdentifierToken("X",0,0), rule31);

  	tempTable.add(NonTerminal.NonEmptyActuals, new PunctuationToken('(', 0), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals, new OpToken('-', 0), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals, new KeywordToken("if", 0), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals, new IntToken("0",0,0), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals, new BoolToken("false",0), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals, new IdentifierToken("X",0,0), rule32);

  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(')', 0), rule00);
  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(',', 0), rule33);
  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new EOFToken(0), rule00);

    tempTable.add(NonTerminal.Literal,         new IntToken("0",0,0), rule34);
    tempTable.add(NonTerminal.Literal,         new BoolToken("false",0), rule35);

    tempTable.add(NonTerminal.PrintStatement,  new KeywordToken("print", 0), rule36);
=======
    tempTable.add(NonTerminal.Program, new KeywordToken("program"), rule01);

    tempTable.add(NonTerminal.Definitions, new KeywordToken("function"), rule02);
    tempTable.add(NonTerminal.Definitions, new KeywordToken("begin"), rule00);
    tempTable.add(NonTerminal.Definitions, new EOFToken(), rule00);
    tempTable.add(NonTerminal.Body,        new KeywordToken("body"), rule08);
                                                  
//grants	

  tempTable.add(NonTerminal.StatementList, new KeywordToken("print"), rule09);
  tempTable.add(NonTerminal.StatementList, new KeywordToken("return"), rule10);
  
  tempTable.add(NonTerminal.Type,          new KeywordToken("integer"), rule11);
  tempTable.add(NonTerminal.Type,          new KeywordToken("boolean"), rule12);
  
  tempTable.add(NonTerminal.Expr,          new PunctuationToken('('), rule13);
  tempTable.add(NonTerminal.Expr,          new OpToken('-'), rule13);
  tempTable.add(NonTerminal.Expr,          new KeywordToken("if"), rule13);
  tempTable.add(NonTerminal.Expr,          new KeywordToken("not"), rule13);
  tempTable.add(NonTerminal.Expr,          new IntToken(), rule13);
  tempTable.add(NonTerminal.Expr,          new BoolToken(), rule13);
  tempTable.add(NonTerminal.Expr,          new IdentifierToken(), rule13);
  
  tempTable.add(NonTerminal.ExprPrime,     new PunctuationToken(')'), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new PunctuationToken(','), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("end"), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('+'), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('-'), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("and"), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('*'), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('/'), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("then"), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new KeywordToken("else"), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new EOFToken(), rule00);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('<'), rule14);
  tempTable.add(NonTerminal.ExprPrime,     new OpToken('='), rule15);
  
  tempTable.add(NonTerminal.SimpleExpr,     new PunctuationToken(')'), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new OpToken('-'), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new KeywordToken("if"), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new KeywordToken("not"), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new IntToken(), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new BoolToken(), rule16);
  tempTable.add(NonTerminal.SimpleExpr,     new IdentifierToken(), rule16);
  
  tempTable.add(NonTerminal.SimpleExprPrime, new PunctuationToken(')'), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("end"), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("and"), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('*'), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('/'), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("then"), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("else"), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('<'), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('='), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new EOFToken(), rule00);
  tempTable.add(NonTerminal.SimpleExprPrime, new KeywordToken("or"), rule17);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('+'), rule18);
  tempTable.add(NonTerminal.SimpleExprPrime, new OpToken('-'), rule19);

  tempTable.add(NonTerminal.Term, new OpToken('('), rule20);
  tempTable.add(NonTerminal.Term, new OpToken('-'), rule20);
  tempTable.add(NonTerminal.Term, new KeywordToken("if"), rule20);
  tempTable.add(NonTerminal.Term, new KeywordToken("not"), rule20);
  tempTable.add(NonTerminal.Term, new IntToken(), rule20);
  tempTable.add(NonTerminal.Term, new BoolToken(), rule20);
  tempTable.add(NonTerminal.Term, new IdentifierToken(), rule20);
	
	tempTable.add(NonTerminal.TermPrime, new PunctuationToken(')'), rule00);
	tempTable.add(NonTerminal.TermPrime, new PunctuationToken(','), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("end"), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("or"), rule00);
	tempTable.add(NonTerminal.TermPrime, new OpToken('+', rule00);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('-'), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("and"), rule21);
	tempTable.add(NonTerminal.TermPrime, new OpToken('*'), rule22);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('/'), rule23);		
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("then"), rule00);
	tempTable.add(NonTerminal.TermPrime, new KeywordToken("else"), rule00);	
	tempTable.add(NonTerminal.TermPrime, new OpToken('='), rule00);
	tempTable.add(NonTerminal.TermPrime, new OpToken('<'), rule00);
	tempTable.add(NonTerminal.TermPrime, new EOFToken(), rule00);		
	
    tempTable.add(NonTerminal.Factor, new PunctuationToken(')'), rule29);
	tempTable.add(NonTerminal.Factor, new OpToken('-'), rule28);	
	tempTable.add(NonTerminal.Factor, new KeywordToken("if"), rule24);	
	tempTable.add(NonTerminal.Factor, new KeywordToken("not"), rule25);
	tempTable.add(NonTerminal.Factor, new IntToken(), rule27);
	tempTable.add(NonTerminal.Factor, new BoolToken(), rule27);	
	tempTable.add(NonTerminal.Factor, new IdentifierToken(), rule26);	
	
    tempTable.add(NonTerminal.IdentifierPrime, new PunctuationToken('('), rule30);
    tempTable.add(NonTerminal.IdentifierPrime, new OpToken('-'), rule00);		
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("if"), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new KeywordToken("not"), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new IntToken(), rule00);
    tempTable.add(NonTerminal.IdentifierPrime, new BoolToken(), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new IdentifierToken(), rule00);	
    tempTable.add(NonTerminal.IdentifierPrime, new EOFToken(), rule00);	
	
    tempTable.add(NonTerminal.Actuals, new PunctuationToken('('), rule31);
    tempTable.add(NonTerminal.Actuals, new PunctuationToken(')'), rule00);	
    tempTable.add(NonTerminal.Actuals, new OpToken('-'), rule31);
    tempTable.add(NonTerminal.Actuals, new KeywordToken("if"), rule31);	
    tempTable.add(NonTerminal.Actuals, new KeywordToken("not"), rule31);	
    tempTable.add(NonTerminal.Actuals, new IntToken(), rule31);
    tempTable.add(NonTerminal.Actuals, new BoolToken(), rule31);	
    tempTable.add(NonTerminal.Actuals, new IdentifierToken(), rule31);	
	
	tempTable.add(NonTerminal.NonEmptyActuals, new PunctuationToken('('), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new OpToken('-'), rule32);	
	tempTable.add(NonTerminal.NonEmptyActuals, new KeywordToken("if"), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new IntToken(), rule32);
	tempTable.add(NonTerminal.NonEmptyActuals, new BoolToken(), rule32);	
	tempTable.add(NonTerminal.NonEmptyActuals, new IdentifierToken(), rule32);		
		
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(')'), rule00);
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(','), rule33);	
	tempTable.add(NonTerminal.NonEmptyActualsPrime, new EOFToken(), rule00);	

    tempTable.add(NonTerminal.Literal, new IntToken(), rule34);
    tempTable.add(NonTerminal.Literal, new BoolToken(), rule35);	

    tempTable.add(NonTerminal.PrintStatement, new KeywordToken("print"), rule36);	
>>>>>>> origin/master

    //return the table to use in parseProgram()
    return tempTable;
  }
}
