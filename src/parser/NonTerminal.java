package src.parser;

public class NonTerminal
{
    public static final NonTerminal Program =
                        new NonTerminal("PROGRAM");
    public static final NonTerminal Definitions =
                        new NonTerminal("DEFINITIONS");
    public static final NonTerminal Def =
                        new NonTerminal("DEFINITION");
    public static final NonTerminal Formals =
                        new NonTerminal("FORMALS");
    public static final NonTerminal Formal =
                        new NonTerminal("FORMAL");
    public static final NonTerminal NonEmptyFormals =
                        new NonTerminal("NONEMPTYFORMALS");
    public static final NonTerminal NonEmptyFormalsPrime =
                        new NonTerminal("NONEMPTYFORMALSPRIME");
    public static final NonTerminal Body =
                        new NonTerminal("BODY");
    public static final NonTerminal StatementList =
                        new NonTerminal("STATEMENTLIST");
    public static final NonTerminal Type =
                        new NonTerminal("TYPE");
    public static final NonTerminal Expr =
                        new NonTerminal("EXPRESSION");
    public static final NonTerminal ExprPrime =
                        new NonTerminal("EXPRESSIONPRIME");
    public static final NonTerminal SimpleExpr =
                        new NonTerminal("SIMPLEEXPRESSION");
    public static final NonTerminal SimpleExprPrime =
                        new NonTerminal("SIMPLEEXPRESSIONPRIME");
    public static final NonTerminal Term =
                        new NonTerminal("TERM");
    public static final NonTerminal TermPrime =
                        new NonTerminal("TERMPRIME");
    public static final NonTerminal Factor =
                        new NonTerminal("FACTOR");
    public static final NonTerminal Identifier =
                        new NonTerminal("IDENTIFIER");
    public static final NonTerminal Actuals =
                        new NonTerminal("ACTUALS");
    public static final NonTerminal NonEmptyActuals =
                        new NonTerminal("NONEMPTYACTUALS");
    public static final NonTerminal NonEmptyActualsPrime =
                        new NonTerminal("NONEMPTYACTUALSPRIME");
    public static final NonTerminal Literal =
                        new NonTerminal("LITTERAL");
    public static final NonTerminal PrintStatement =
                        new NonTerminal("PRINTSTATEMENT");

    private String typeName;

    private NonTerminal( String name )
    {
        typeName = name;
    }

    @Override
    public String toString()
    {
        return typeName;
    }
}
