package src.parser;

import java.util.Stack;
import src.scanner.*;

@SuppressWarnings("unchecked")
public class TableDrivenParser extends Parser{

  private Parsetable flairTable;
  private Stack parseStack = new Stack();
  private Stack semanticStack = new Stack();
  private Stack semanticBuffer = new Stack();

  public TableDrivenParser(Scanner source) throws ScanException,
                                                  Exception{

    super(source);
    flairTable = makeParsingTable();
  }


  public void consumeToken(){
    if((curToken instanceof IntToken)
       || (curToken instanceof BoolToken)
       || (curToken instanceof IdentifierToken))
    {
      semanticBuffer.push(curToken);
    }
    curPos++;
    curToken = tokenArray.get(curPos);
    try{
      peekToken = tokenArray.get(curPos+1);
    }catch(Exception e){
      //do nothing
    }
  }


  public void consumeSemanticAction(){
	//1. The semanticAction is popped of the parseStack
	//2. The semanticAction calls the creation of a semanticNode, corresponding to a specific make-rule.
	SemAction tempAction = parseStack.pop();
	SemNode tempNode = new SemNode(tempAction);
		
	//3. we POP a predetermined number of nodes off of the semanticStack and add them to the NEW NODE OBJECT as its children. 
        switch (tempAction ){
          case ActionIdentifier: case ActionNumber: case ActionBoolean:
            tempNode.addElement(semanticBuffer.pop());
            break;
          default:
	    //a. This number of pops will be known by the newly created 'node' that is expecting a certain number of children.
	    for (int i = 0; i < tempNode.numberOfElementsToPop(); i++){		  		  		  
              tempNode.addElement(semanticStack.pop());
            }
            break;
          }
	//4. We then add this new parent node, containing its children, onto the semantic stack.
        semanticStack.push(tempNode);		
  }

  @SuppressWarnings("unchecked")
  public boolean parseProgram() throws ParseException{

    parseStack.push(new EOFToken(1));    //push EOF onto parseStack
    parseStack.push(NonTerminal.Program);//push program onto parseStack

    do{ //Main loop
      if(curToken instanceof CommentToken){ //comment
        consumeToken();
      }
      //Terminal
      if(parseStack.peek() instanceof Token)
      {
        if(parseStack.peek().getClass().equals(curToken.getClass())){
          //System.out.println("Item popped from parseStack: \n"+parseStack.peek()+"\n");//FIXME
          //System.out.println("Token Consumed: \n"+curToken+"\n");//FIXME
          //TODO Will need to add primitive values to the semanticBuffer
          //if they are popped from here!!
          parseStack.pop();
          consumeToken();
        }else // Token mismatch
        {
          //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
          throw new ParseException("--Token mismatch--");
        }
      //NonTerminal
      }else if((NonTerminal)parseStack.peek() instanceof NonTerminal){
        try{
          flairTable.lookup((NonTerminal)parseStack.pop(), curToken).execute(parseStack);
        }catch(ParseException e){
          //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
          throw e;
        }
      //SemanticAction
      }else if(parseStack.peek() instanceof SemanticAction){
        consumeSemanticAction();
        System.out.println(parseStack.pop());
      }
      //Parse Error
      else{
        //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
        throw new ParseException("--Top of parseStack is not terminal or nonterminal--");
      }
      //System.out.println("\nAt end of loop:\n"+parseStack+"\n"+curToken);//FIXME
      //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");//FIXME
    }while((parseStack.peek() instanceof EOFToken) == false);

    if(parseStack.peek() instanceof EOFToken && curToken instanceof EOFToken){
      return true;
    }
    else{
      throw new ParseException("--Parser found tokens after program end.--");
    }
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
						//new PushSemantic(     SemAction.ActionIdentifier),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';')),
                        new PushNonTerminal(  NonTerminal.Definitions),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken('.')),
						//new PushSemantic(     SemAction.Action01)
                      } );

    ParseRule rule02 = new PushRule( // DefinitionsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Def),
                        new PushNonTerminal(  NonTerminal.Definitions),
						//new PushSemantic(     SemAction.Action02)
                        } );

    ParseRule rule03 = new PushRule( // DefRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("function")),
                        new PushTerminal(     new IdentifierToken()),
						//new PushSemantic(     SemAction.ActionIdentifier),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken(';')),
						//new PushSemantic(     SemAction.Action03)
                        } );

    ParseRule rule04 = new PushRule( // FormalsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyFormals),
						//new PushSemantic(     SemAction.Action04)
                        } );

    ParseRule rule05 = new PushRule( // NonEmptyFormalsRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Formal),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormalsPrime),
						//new PushSemantic(     SemAction.Action05)
                        } );

    ParseRule rule06 = new PushRule( // NonEmptyFormalsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormals),
						//new PushSemantic(     SemAction.Action06)
                        } );

    ParseRule rule07 = new PushRule( // FormalRule01
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
						//new PushSemantic(     SemAction.ActionIdentifier),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type),
						//new PushSemantic(     SemAction.Action07)
                        } );

    ParseRule rule08 = new PushRule( // BodyRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("begin")),
                        new PushNonTerminal(  NonTerminal.StatementList),
                        new PushTerminal(     new KeywordToken("end")),
						//new PushSemantic(     SemAction.Action08)
                        } );

    ParseRule rule09 = new PushRule( // StatementListRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.PrintStatement),
                        new PushNonTerminal(  NonTerminal.StatementList),
						//new PushSemantic(     SemAction.Action09)
                        } );

    ParseRule rule10 = new PushRule( // StatementListRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("return")),
                        new PushNonTerminal(  NonTerminal.Expr),
						//new PushSemantic(     SemAction.Action10)
                        } );

    ParseRule rule11 = new PushRule( // TypeRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("integer")),
						//new PushSemantic(     SemAction.Action11)
                        } );

    ParseRule rule12 = new PushRule(
      new ParseRule[] { new PushTerminal(    new KeywordToken("boolean")),
						//new PushSemantic(     SemAction.Action12)
                        } );

    ParseRule rule13 = new PushRule( // ExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.SimpleExpr),
                        new PushNonTerminal(  NonTerminal.ExprPrime),
						//new PushSemantic(     SemAction.Action13)
                        } );

    ParseRule rule14 = new PushRule( // ExprPrimeRule01
      new ParseRule[] { new PushTerminal(     new OpToken('<')),
                        new PushNonTerminal(  NonTerminal.Expr),
						//new PushSemantic(     SemAction.Action14)
                        } );

    ParseRule rule15 = new PushRule( // ExprPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('=')),
                        new PushNonTerminal(  NonTerminal.Expr),
						//new PushSemantic(     SemAction.Action15)
                        } );

    ParseRule rule16 = new PushRule( // SimpleExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Term),
                        new PushNonTerminal(  NonTerminal.SimpleExprPrime),
						//new PushSemantic(     SemAction.Action16)
                        } );

    ParseRule rule17 = new PushRule( // SimpleExprRulePrime01
      new ParseRule[] { new PushTerminal(     new KeywordToken("or")),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action17)
                        } );

    ParseRule rule18 = new PushRule( // SimpleExprRulePrime02
      new ParseRule[] { new PushTerminal(     new OpToken('+')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action18)
                        } );

    ParseRule rule19 = new PushRule( // SimpleExprRulePrime03
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action19)
                        } );

    ParseRule rule20 = new PushRule( // TermRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Factor),
                        new PushNonTerminal(  NonTerminal.TermPrime),
						//new PushSemantic(     SemAction.Action20)
                        } );

    ParseRule rule21 = new PushRule( // TermPrimeRule01
       new ParseRule[] { new PushTerminal(    new KeywordToken("and")),
                         new PushNonTerminal( NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action21)
                          } );

    ParseRule rule22 = new PushRule( // TermPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('*')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action22)
                        } );

    ParseRule rule23 = new PushRule( // TermPrimeRule03
      new ParseRule[] { new PushTerminal(     new OpToken('/')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						//new PushSemantic(     SemAction.Action23)
                        } );

    ParseRule rule24 = new PushRule( // FactorRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("if")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("then")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("else")),
                        new PushNonTerminal(  NonTerminal.Expr),
						//new PushSemantic(     SemAction.Action24)
                        } );

    ParseRule rule25 = new PushRule( // FactorRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("not")),
                        new PushNonTerminal(  NonTerminal.Factor),
						//new PushSemantic(     SemAction.Action25)
                        } );

    ParseRule rule26 = new PushRule( // FactorRule03
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
						//new PushSemantic(     SemAction.ActionIdentifier),
                        new PushNonTerminal(  NonTerminal.IdentifierPrime),
						//new PushSemantic(     SemAction.Action26)
                        } );

    ParseRule rule27 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Literal),
						//new PushSemantic(     SemAction.Action27)
                        } );

    ParseRule rule28 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.Factor),
						//new PushSemantic(     SemAction.Action28)
                        } );

    ParseRule rule29 = new PushRule( // FactorRule06
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')')),
						//new PushSemantic(     SemAction.Action29)
                        } );

    ParseRule rule30 = new PushRule( // IdentifierPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Actuals),
                        new PushTerminal(     new PunctuationToken(')')),
						//new PushSemantic(     SemAction.Action30)
                        } );

    ParseRule rule31 = new PushRule( // ActualsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyActuals),
						//new PushSemantic(     SemAction.Action31)
                        } );

    ParseRule rule32 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime),
						//new PushSemantic(     SemAction.Action32)
                        } );

    ParseRule rule33 = new PushRule( // NonEmptyActualsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime),
						//new PushSemantic(     SemAction.Action33)
                        } );

    ParseRule rule34 = new PushRule(
      new ParseRule[] { new PushTerminal(     new IntToken()),
	  					//new PushSemantic(     SemAction.ActionNumber),
						//new PushSemantic(     SemAction.Action34)
                        } );

    ParseRule rule35 = new PushRule(
      new ParseRule[] { new PushTerminal(     new BoolToken()),
						//new PushSemantic(     SemAction.ActionBoolean),
						//new PushSemantic(     SemAction.Action35)
                        } );

    ParseRule rule36 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("print")),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';')),
						//new PushSemantic(     SemAction.Action36)
                        } );

    tempTable.add(NonTerminal.Program,              new KeywordToken("program"), rule01);

    tempTable.add(NonTerminal.Definitions,          new KeywordToken("function"), rule02);
    tempTable.add(NonTerminal.Definitions,          new KeywordToken("begin"), rule00);
    tempTable.add(NonTerminal.Definitions,          new EOFToken(), rule00);

    tempTable.add(NonTerminal.Def,                  new KeywordToken("function"), rule03);

    tempTable.add(NonTerminal.Formals,              new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.Formals,              new IdentifierToken(), rule04);
    tempTable.add(NonTerminal.Formals,              new EOFToken(), rule00);

    tempTable.add(NonTerminal.NonEmptyFormals,      new IdentifierToken(), rule05);

    tempTable.add(NonTerminal.NonEmptyFormalsPrime, new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.NonEmptyFormalsPrime, new PunctuationToken(','), rule06);
    tempTable.add(NonTerminal.NonEmptyFormalsPrime, new EOFToken(), rule00);

    tempTable.add(NonTerminal.Formal,               new IdentifierToken(), rule07);

    tempTable.add(NonTerminal.Body,                 new KeywordToken("begin"), rule08);

    tempTable.add(NonTerminal.StatementList,        new KeywordToken("print"), rule09);
    tempTable.add(NonTerminal.StatementList,        new KeywordToken("return"), rule10);

    tempTable.add(NonTerminal.Type,                 new KeywordToken("integer"), rule11);
    tempTable.add(NonTerminal.Type,                 new KeywordToken("boolean"), rule12);

    tempTable.add(NonTerminal.Expr,                 new PunctuationToken('('), rule13);
    tempTable.add(NonTerminal.Expr,                 new OpToken('-'), rule13);
    tempTable.add(NonTerminal.Expr,                 new KeywordToken("if"), rule13);
    tempTable.add(NonTerminal.Expr,                 new KeywordToken("not"), rule13);
    tempTable.add(NonTerminal.Expr,                 new IntToken(), rule13);
    tempTable.add(NonTerminal.Expr,                 new BoolToken(), rule13);
    tempTable.add(NonTerminal.Expr,                 new IdentifierToken(), rule13);

    tempTable.add(NonTerminal.ExprPrime,            new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new PunctuationToken(','), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new KeywordToken("end"), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('+'), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('-'), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new KeywordToken("and"), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('*'), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('/'), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new KeywordToken("then"), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new KeywordToken("else"), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new EOFToken(), rule00);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('<'), rule14);
    tempTable.add(NonTerminal.ExprPrime,            new OpToken('='), rule15);

    tempTable.add(NonTerminal.SimpleExpr,           new PunctuationToken('('), rule16);//FIXME
    tempTable.add(NonTerminal.SimpleExpr,           new PunctuationToken(')'), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new OpToken('-'), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new KeywordToken("if"), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new KeywordToken("not"), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new IntToken(), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new BoolToken(), rule16);
    tempTable.add(NonTerminal.SimpleExpr,           new IdentifierToken(), rule16);

    tempTable.add(NonTerminal.SimpleExprPrime,      new PunctuationToken(','), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new KeywordToken("end"), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new KeywordToken("and"), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('*'), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('/'), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new KeywordToken("then"), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new KeywordToken("else"), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('<'), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('='), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new EOFToken(), rule00);
    tempTable.add(NonTerminal.SimpleExprPrime,      new KeywordToken("or"), rule17);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('+'), rule18);
    tempTable.add(NonTerminal.SimpleExprPrime,      new OpToken('-'), rule19);

    tempTable.add(NonTerminal.Term,                 new PunctuationToken('('), rule20);
    tempTable.add(NonTerminal.Term,                 new OpToken('-'), rule20);
    tempTable.add(NonTerminal.Term,                 new KeywordToken("if"), rule20);
    tempTable.add(NonTerminal.Term,                 new KeywordToken("not"), rule20);
    tempTable.add(NonTerminal.Term,                 new IntToken(), rule20);
    tempTable.add(NonTerminal.Term,                 new BoolToken(), rule20);
    tempTable.add(NonTerminal.Term,                 new IdentifierToken(), rule20);

  	tempTable.add(NonTerminal.TermPrime,            new PunctuationToken(')'), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new PunctuationToken(','), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new KeywordToken("end"), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new KeywordToken("or"), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('+'), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('-'), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new KeywordToken("and"), rule21);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('*'), rule22);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('/'), rule23);
  	tempTable.add(NonTerminal.TermPrime,            new KeywordToken("then"), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new KeywordToken("else"), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('='), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new OpToken('<'), rule00);
  	tempTable.add(NonTerminal.TermPrime,            new EOFToken(), rule00);

    tempTable.add(NonTerminal.Factor,               new PunctuationToken('('), rule29);
  	tempTable.add(NonTerminal.Factor,               new OpToken('-'), rule28);
  	tempTable.add(NonTerminal.Factor,               new KeywordToken("if"), rule24);
  	tempTable.add(NonTerminal.Factor,               new KeywordToken("not"), rule25);
  	tempTable.add(NonTerminal.Factor,               new IntToken(), rule27);
  	tempTable.add(NonTerminal.Factor,               new BoolToken(), rule27);
  	tempTable.add(NonTerminal.Factor,               new IdentifierToken(), rule26);

    tempTable.add(NonTerminal.IdentifierPrime,      new PunctuationToken('('), rule30);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('-'), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("if"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("not"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new IntToken(), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new BoolToken(), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new IdentifierToken(), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new EOFToken(), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new PunctuationToken(','), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("end"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("or"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('+'), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("and"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('*'), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('/'), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("then"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new KeywordToken("else"), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('='), rule00);
    tempTable.add(NonTerminal.IdentifierPrime,      new OpToken('<'), rule00);

    tempTable.add(NonTerminal.Actuals,              new PunctuationToken('('), rule31);
    tempTable.add(NonTerminal.Actuals,              new PunctuationToken(')'), rule00);
    tempTable.add(NonTerminal.Actuals,              new OpToken('-'), rule31);
    tempTable.add(NonTerminal.Actuals,              new KeywordToken("if"), rule31);
    tempTable.add(NonTerminal.Actuals,              new KeywordToken("not"), rule31);
    tempTable.add(NonTerminal.Actuals,              new IntToken(), rule31);
    tempTable.add(NonTerminal.Actuals,              new BoolToken(), rule31);
    tempTable.add(NonTerminal.Actuals,              new IdentifierToken(), rule31);

  	tempTable.add(NonTerminal.NonEmptyActuals,      new PunctuationToken('('), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals,      new OpToken('-'), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals,      new KeywordToken("if"), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals,      new IntToken(), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals,      new BoolToken(), rule32);
  	tempTable.add(NonTerminal.NonEmptyActuals,      new IdentifierToken(), rule32);

  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(')'), rule00);
  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new PunctuationToken(','), rule33);
  	tempTable.add(NonTerminal.NonEmptyActualsPrime, new EOFToken(), rule00);

    tempTable.add(NonTerminal.Literal,              new IntToken(), rule34);
    tempTable.add(NonTerminal.Literal,              new BoolToken(), rule35);

    tempTable.add(NonTerminal.PrintStatement, new KeywordToken("print"), rule36);

    return tempTable;
  }
}
