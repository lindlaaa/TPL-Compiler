package src.parser;

import java.io.IOException;
import java.io.PushbackInputStream;

public class SymbolTableFactoryTest{
	public static void main(String[] args) throws SemanticException{
		Token token = null;
		
		Parser parser = new Parser(new Scanner(new PushbackInputStream(System.in)));
		AbstractSyntaxTree ast = parser.parse();
		SymbolTableFactory stf = new SymbolTableFactory((Program) ast);
		
		try{
			SymbolTable symbolTable = stf.createTable();
			System.out.println(symbolTable);
		}catch(SemanticException e){
			System.out.println(e);
		}
	}
} 