package src.parser;

import java.io.IOException;
import java.io.PushbackInputStream;

public class TypeCheckerTest{
	
	public static void main(String[] args){
		Parser parser = new Parser(new Scenner(new PushbackInputStream(Sytem.in)));
		AbstractSyntaxTree ast = parser.parse();
		SymbolTableFactory stf = new SymbolTableFactory((Program) ast);
		
		try{
			SymbolTable symbolTable = stf.createTable();
			TypeChecker tc = new TypeChecker(symbolTable);
			tc.check((Program) ast);
			System.out.println(ast);
		}catch(SemanticException e){
			System.out.println(e);
		}
	}
}