package src.parser;

public class SemanticAction
{
  public static final SemanticAction Program = new SemanticAction(1);
  public static final SemanticAction Definitions = new SemanticAction(2);
  public static final SemanticAction Def= new SemanticAction(3);
  public static final SemanticAction Formals = new SemanticAction(4);
  public static final SemanticAction NonEmptyFormals = new SemanticAction(5);
  public static final SemanticAction NonEmptyFormalsPrime = new SemanticAction(6);
  public static final SemanticAction Formal = new SemanticAction(7);
  public static final SemanticAction Body = new SemanticAction(8);
  public static final SemanticAction StatementListPS = new SemanticAction(9);
  public static final SemanticAction StatementListReturn = new SemanticAction(10);
  public static final SemanticAction Expr = new SemanticAction(13);
  public static final SemanticAction ExprPrimeLT = new SemanticAction(14);
  public static final SemanticAction ExprPrimeExpr = new SemanticAction(15);
  public static final SemanticAction SimpleExpr = new SemanticAction(16);
  public static final SemanticAction SimpleExprPrimeOr = new SemanticAction(17);
  public static final SemanticAction SimpleExprPrimePlus = new SemanticAction(18);
  public static final SemanticAction SimpleExprPrimeMinus = new SemanticAction(19);
  public static final SemanticAction Term = new SemanticAction(20);
  public static final SemanticAction TermPrimeAnd = new SemanticAction(21);
  public static final SemanticAction TermPrimeTimes = new SemanticAction(22);
  public static final SemanticAction TermPrimeDivide = new SemanticAction(23);
  public static final SemanticAction FactorIf = new SemanticAction(24);
  public static final SemanticAction FactorNot = new SemanticAction(25);
  public static final SemanticAction FactorID = new SemanticAction(26);
  //public static final SemanticAction IfIntLiteral = new SemanticAction(27);
  //public static final SemanticAction IfIntMinus = new SemanticAction(28);
  //public static final SemanticAction IfIntLP = new SemanticAction(29);
  public static final SemanticAction IdentifierPrimeLP = new SemanticAction(30);
  public static final SemanticAction Actuals = new SemanticAction(31);
  public static final SemanticAction NonEmptyActuals = new SemanticAction(32);
  public static final SemanticAction NonEmptyActualsPrime = new SemanticAction(33);
  public static final SemanticAction LiteralNumber = new SemanticAction(34);
  public static final SemanticAction LiteralBoolean = new SemanticAction(35);
  public static final SemanticAction PrintStatement = new SemanticAction(36);
  public static final SemanticAction Identifier = new SemanticAction(37);


  //NEW
  public static final SemanticAction FactorExpr = new SemanticAction(38);
  public static final SemanticAction FactorMinus = new SemanticAction(39);
  public static final SemanticAction FactorLiteral = new SemanticAction(40);
  public static final SemanticAction Factor = new SemanticAction(41);
  public static final SemanticAction TermPrime = new SemanticAction(42);
  public static final SemanticAction Literal = new SemanticAction(43);
  public static final SemanticAction StatementList = new SemanticAction(44);
  public static final SemanticAction BoolTypeKeyword = new SemanticAction(45);
  public static final SemanticAction IntTypeKeyword = new SemanticAction(46);





  private int typeNumber;

  public SemanticAction( int semanticActionNumber)
  {
    this.typeNumber = semanticActionNumber;
  }

  public int getSemanticActionNumber(){
	return typeNumber;
  }


  @Override
  public String toString()
  {
    return Integer.toString(typeNumber);
  }
}
