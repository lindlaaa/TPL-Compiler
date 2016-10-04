
// This class is an example of a type-safe enum in Java.

public class NonTerminal
{
    public static final NonTerminal Program        = new NonTerminal( "prog"    );
    public static final NonTerminal Declarations   = new NonTerminal( "decls"   );
    public static final NonTerminal Declaration    = new NonTerminal( "decl"    );
    public static final NonTerminal Statements     = new NonTerminal( "stmts"   );
    public static final NonTerminal Statement      = new NonTerminal( "stmt"    );
    public static final NonTerminal ExpressionTail = new NonTerminal( "exprTail");
    public static final NonTerminal Value          = new NonTerminal( "value"   );

    private String typeName;

    private NonTerminal( String name )
    {
        typeName = name;
    }

    public String toString()
    {
        return typeName;
    }
}
