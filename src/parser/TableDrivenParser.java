package src.parser;

import java.util.Stack;

import src.scanner.*;
import src.parser.*;
import src.parser.nodes.*;
import src.parser.semanticanalyzer.*;

@SuppressWarnings("unchecked")
public class TableDrivenParser extends Parser{

  private       Parsetable flairTable;
  public static Stack parseStack = new Stack();
  public static Stack semanticStack = new Stack();
  public static Stack semanticBuffer = new Stack();
  public static ProgramNode ast;

  public TableDrivenParser(Scanner source) throws ScanException,
                                                  Exception{
    super(source);
    flairTable = makeParsingTable();
  }

  public ProgramNode getAST() throws ParseException{
    if(ast == null){
      throw new ParseException("--Tried to get the AST before it was created or assigned.--");
    }else{
      return ast;
    }
  }




  /**
   *  TODO
   */
  public void consumeToken(){
    if(  (curToken instanceof IntToken)
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




  public void consumeSemanticAction() throws ParseException{
	  SemanticAction tempAction = (SemanticAction)parseStack.pop();
    SemanticNode tempNode = NodeFactory.createNewNode(tempAction);
    tempNode.setChildren();
    semanticStack.push(tempNode);
    //System.out.println("Semantic Stack-> " + semanticStack);
  }



  private void balanceTree(SemanticNode node){
    SemanticNode child;
    SemanticNode childBelow;

    /*
     *  balances expressions but not functions
     */
    if(node instanceof IdentifierNode  ||
       node instanceof IntTypeNode     ||
       node instanceof BoolTypeNode){
         //do nothing
         //System.out.println("leaf");
    }else{ //node to be checked
      for (int i = node.getChildren().size()-1; i > -1; i--){
        child = node.getChild(i);
        balanceTree(child);
        if(child instanceof SimpleExprPrimePlusNode  || //PLUS
           child instanceof SimpleExprPrimeMinusNode || //MINUS
           child instanceof SimpleExprPrimeOrNode    || //OR
           child instanceof ExprPrimeLTNode          || //LT
           child instanceof ExprPrimeExprNode        || //==
           child instanceof TermPrimeAndNode         || //AND
           child instanceof TermPrimeTimesNode       || //*
           child instanceof TermPrimeDivideNode){       ///
          try{
            childBelow = node.getChild(i+1);

            //System.out.println("Child = "+child+"\nchild Below = "+childBelow+"\n");
            child.addChild(childBelow, child);
            node.getChildren().remove(childBelow);
          }catch(Exception e){
            //System.out.println("errors occured\n");
          }
        }//END IF
      }//END FOR
    }
  }



  @SuppressWarnings("unchecked")
  public boolean parseProgram(boolean showTree, String fileName) throws ParseException{

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
          parseStack.pop();
          consumeToken();
        }else // Token mismatch
        {
          //FIXME
          //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
          throw new ParseException("--Token mismatch @ "+curToken.getline()+":"+curToken.getCol()+"--");
        }
      //NonTerminal
      }else if(parseStack.peek() instanceof NonTerminal){
        try{
          flairTable.lookup((NonTerminal)parseStack.pop(), curToken).execute(parseStack);
        }catch(ParseException e){
          //FIXME
          //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
          throw e;
        }
      //SemanticAction
      }else if(parseStack.peek() instanceof SemanticAction){
        consumeSemanticAction();
      }
      //Parse Error
      else{
        //FIXME
        //System.out.println("\nAt error:\nparseStack:"+parseStack+"\nCURTOKEN:"+curToken+" @ line: "+curToken.getline()+" @ col: "+curToken.getCol()+"\n");
        throw new ParseException("--Top of parseStack is not terminal or nonterminal--");
      }
      //System.out.println("\nAt end of loop:\n"+parseStack+"\n"+curToken);//FIXME
      //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");//FIXME

    }while((parseStack.peek() instanceof EOFToken) == false);

    if(parseStack.peek() instanceof EOFToken && curToken instanceof EOFToken){

      ast = (ProgramNode)semanticStack.peek();
      //System.out.println("\n------ Diagram of function contents: ---"); //FIXME
      balanceTree(ast);
      SemanticAnalyzer semAn = new SemanticAnalyzer(ast, tokenArray);

      //-t
      if(showTree){
        try{
          WriteString writer = new WriteString();
          ast.printTree(ast, "");
          writer.writeTree(ast.graphTree(ast), fileName);

        }catch(Exception e){}
      }

      return true;
    }
    else{
      throw new ParseException("--Parser found tokens after program end.--");
    }
  }


  private Parsetable makeParsingTable() throws ScanException,
                                                Exception{
    Parsetable tempTable = new Parsetable();

    ParseRule rule00 = new PushRule(
      new ParseRule[] { new PushNull() } );

    ParseRule rule01 = new PushRule( //ProgramRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("program")),
                        new PushTerminal(     new IdentifierToken()),
						            new PushSemantic(     SemanticAction.Identifier),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';')),
                        new PushNonTerminal(  NonTerminal.Definitions),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken('.')),
						            new PushSemantic(     SemanticAction.Program)
                      } );

    ParseRule rule02 = new PushRule( // DefinitionsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Def),
                        new PushNonTerminal(  NonTerminal.Definitions),
						            new PushSemantic(     SemanticAction.Definitions)
                        } );

    ParseRule rule03 = new PushRule( // DefRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("function")),
                        new PushTerminal(     new IdentifierToken()),
						            new PushSemantic(     SemanticAction.Identifier),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Formals),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type),
                        new PushNonTerminal(  NonTerminal.Body),
                        new PushTerminal(     new TerminatorToken(';')),
						            new PushSemantic(     SemanticAction.Def)
                        } );

    ParseRule rule04 = new PushRule( // FormalsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyFormals),
						            new PushSemantic(     SemanticAction.Formals)
                        } );

    ParseRule rule05 = new PushRule( // NonEmptyFormalsRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Formal),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormalsPrime),
						new PushSemantic(     SemanticAction.NonEmptyFormals)
                        } );

    ParseRule rule06 = new PushRule( // NonEmptyFormalsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.NonEmptyFormals),
						            new PushSemantic(     SemanticAction.NonEmptyFormalsPrime)
                        } );

    ParseRule rule07 = new PushRule( // FormalRule01
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
						            new PushSemantic(     SemanticAction.Identifier),
                        new PushTerminal(     new PunctuationToken(':')),
                        new PushNonTerminal(  NonTerminal.Type),
						            new PushSemantic(     SemanticAction.Formal)
                        } );

    ParseRule rule08 = new PushRule( // BodyRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("begin")),
                        new PushNonTerminal(  NonTerminal.StatementList),
                        new PushTerminal(     new KeywordToken("end")),
						            new PushSemantic(     SemanticAction.Body)
                        } );

    ParseRule rule09 = new PushRule( // StatementListRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.PrintStatement),
                        new PushNonTerminal(  NonTerminal.StatementList),
						            new PushSemantic(     SemanticAction.StatementListPS),
						            new PushSemantic(     SemanticAction.StatementList)

                        } );

    ParseRule rule10 = new PushRule( // StatementListRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("return")),
                        new PushNonTerminal(  NonTerminal.Expr),
						            new PushSemantic(     SemanticAction.StatementListReturn),
						            new PushSemantic(     SemanticAction.StatementList)
                        } );

    ParseRule rule11 = new PushRule( // TypeRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("integer")),
                        new PushSemantic(     SemanticAction.IntTypeKeyword)
                        } );

    ParseRule rule12 = new PushRule(
      new ParseRule[] { new PushTerminal(    new KeywordToken("boolean")),
                        new PushSemantic(     SemanticAction.BoolTypeKeyword)
                        } );

    ParseRule rule13 = new PushRule( // ExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.SimpleExpr),
                        new PushNonTerminal(  NonTerminal.ExprPrime),
						            new PushSemantic(     SemanticAction.Expr)
                        } );

    ParseRule rule14 = new PushRule( // ExprPrimeRule01
      new ParseRule[] { new PushTerminal(     new OpToken('<')),
                        new PushNonTerminal(  NonTerminal.Expr),
						new PushSemantic(     SemanticAction.ExprPrimeLT)
                        } );

    ParseRule rule15 = new PushRule( // ExprPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('=')),
                        new PushNonTerminal(  NonTerminal.Expr),
						new PushSemantic(     SemanticAction.ExprPrimeExpr)
                        } );

    ParseRule rule16 = new PushRule( // SimpleExprRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Term),
                        new PushNonTerminal(  NonTerminal.SimpleExprPrime),
						            new PushSemantic(     SemanticAction.SimpleExpr)
                        } );

    ParseRule rule17 = new PushRule( // SimpleExprRulePrime01
      new ParseRule[] { new PushTerminal(     new KeywordToken("or")),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						new PushSemantic(     SemanticAction.SimpleExprPrimeOr)
                        } );

    ParseRule rule18 = new PushRule( // SimpleExprRulePrime02
      new ParseRule[] { new PushTerminal(     new OpToken('+')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						new PushSemantic(     SemanticAction.SimpleExprPrimePlus)
                        } );

    ParseRule rule19 = new PushRule( // SimpleExprRulePrime03
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.SimpleExpr),
						new PushSemantic(     SemanticAction.SimpleExprPrimeMinus)
                        } );

    ParseRule rule20 = new PushRule( // TermRule01
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Factor),
                        new PushNonTerminal(  NonTerminal.TermPrime),
						            new PushSemantic(     SemanticAction.Term)
                        } );

    ParseRule rule21 = new PushRule( // TermPrimeRule01
       new ParseRule[] { new PushTerminal(    new KeywordToken("and")),
                         new PushNonTerminal( NonTerminal.Term),
						             new PushSemantic(     SemanticAction.TermPrimeAnd),
						             new PushSemantic(     SemanticAction.TermPrime)
                          } );

    ParseRule rule22 = new PushRule( // TermPrimeRule02
      new ParseRule[] { new PushTerminal(     new OpToken('*')),
                        new PushNonTerminal(  NonTerminal.Term),
            						new PushSemantic(     SemanticAction.TermPrimeTimes),
                        new PushSemantic(     SemanticAction.TermPrime)
                        } );

    ParseRule rule23 = new PushRule( // TermPrimeRule03
      new ParseRule[] { new PushTerminal(     new OpToken('/')),
                        new PushNonTerminal(  NonTerminal.Term),
            						new PushSemantic(     SemanticAction.TermPrimeDivide),
                        new PushSemantic(     SemanticAction.TermPrime)
                        } );

    ParseRule rule24 = new PushRule( // FactorRule01
      new ParseRule[] { new PushTerminal(     new KeywordToken("if")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("then")),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new KeywordToken("else")),
                        new PushNonTerminal(  NonTerminal.Expr),
						            new PushSemantic(     SemanticAction.FactorIf),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule25 = new PushRule( // FactorRule02
      new ParseRule[] { new PushTerminal(     new KeywordToken("not")),
                        new PushNonTerminal(  NonTerminal.Factor),
						            new PushSemantic(     SemanticAction.FactorNot),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule26 = new PushRule( // FactorRule03
      new ParseRule[] { new PushTerminal(     new IdentifierToken()),
						            new PushSemantic(     SemanticAction.Identifier),
                        new PushNonTerminal(  NonTerminal.IdentifierPrime),
						            new PushSemantic(     SemanticAction.FactorID),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule27 = new PushRule(
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Literal),
						            new PushSemantic(     SemanticAction.Literal),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule28 = new PushRule(
      new ParseRule[] { new PushTerminal(     new OpToken('-')),
                        new PushNonTerminal(  NonTerminal.Factor),
						            new PushSemantic(     SemanticAction.FactorMinus),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule29 = new PushRule( // FactorRule06
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')')),
						            new PushSemantic(     SemanticAction.FactorExpr),
                        new PushSemantic(     SemanticAction.Factor)
                        } );

    ParseRule rule30 = new PushRule( // IdentifierPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Actuals),
                        new PushTerminal(     new PunctuationToken(')')),
						new PushSemantic(     SemanticAction.IdentifierPrimeLP)
                        } );

    ParseRule rule31 = new PushRule( // ActualsRule02
      new ParseRule[] { new PushNonTerminal(  NonTerminal.NonEmptyActuals),
						            new PushSemantic(     SemanticAction.Actuals)
                        } );

    ParseRule rule32 = new PushRule( // NEA rule
      new ParseRule[] { new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime),
						            new PushSemantic(     SemanticAction.NonEmptyActuals)
                        } );

    ParseRule rule33 = new PushRule( // NonEmptyActualsPrimeRule01
      new ParseRule[] { new PushTerminal(     new PunctuationToken(',')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushNonTerminal(  NonTerminal.NonEmptyActualsPrime),
						            new PushSemantic(     SemanticAction.NonEmptyActualsPrime)
                        } );

    ParseRule rule34 = new PushRule(
      new ParseRule[] { new PushTerminal(     new IntToken()),
	  					          new PushSemantic(     SemanticAction.LiteralNumber)
                        } );

    ParseRule rule35 = new PushRule(
      new ParseRule[] { new PushTerminal(     new BoolToken()),
						            new PushSemantic(     SemanticAction.LiteralBoolean)
                        } );

    ParseRule rule36 = new PushRule(
      new ParseRule[] { new PushTerminal(     new KeywordToken("print")),
                        new PushTerminal(     new PunctuationToken('(')),
                        new PushNonTerminal(  NonTerminal.Expr),
                        new PushTerminal(     new PunctuationToken(')')),
                        new PushTerminal(     new TerminatorToken(';')),
						            new PushSemantic(     SemanticAction.PrintStatement)
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

    tempTable.add(NonTerminal.SimpleExpr,           new PunctuationToken('('), rule16);//FIXME Whats wrong?
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
