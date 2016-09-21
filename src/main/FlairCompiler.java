
import java.io.PushBackInputStream;
import java.io.PrintStream;

//Last Modified: Avery 09.11


public class FlairCompiler{

  PushBackInputStream source;
  PrintStream target;


  public FlairCompiler( PushBackInputStream s, PrintStream t ){
    source = s;
    target = t;
  }


  public void run(){
    Parser parser = new Parser( source );
    AbstractSyntaxTree ast = parser.parse();
    SymbolTableFactory stf = new SymbolTableFactory( (Program) ast );

    try{
      SymbolTable table = stf.build();
      TypeChecker tc = new TypeChecker( table );

      tc.check( (Program) ast );

      CodeGenerator generator = new CodeGenerator( target );
      generator.generate( ast );
    } catch (Exception e){
      System.out.println(e);
    }
  }
}
