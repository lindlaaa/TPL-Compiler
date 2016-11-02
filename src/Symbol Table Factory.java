//package src.parser;

public class SymbolTableFactory{
	//this "program" class is Eugenes nonterminal parser type?
	private Program program;
	
	public SymbolTableFactory(Program inputAST){
		this.program = inputAST;
	}
	
	public SymbolTable createTable() throws SemanticException{
		SymbolTable symbolTable = new SymbolTable();
		//program has a declarations() method? like a getChildren?
		Declaration declaration = this.program.declarations();
		
		while(declaration != null){
			Declaration currentDeclaration = declaration.first();//declaration has a "first" method?
			BranchType currentType = null;
			
			if(currentDeclaration instanceof FloatDeclaration)//should be "integer"?
			{
				currentType = BranchType.Float; // ... = new integerToken()?
			}else if(currentDeclaration instanceof IntegerDeclaration)//should be "boolean"?
			{
				currentType = BranchType.Integer; // ... = new boooleanToken()?
			}else
			{
				throw new SemanticException("The type declaration is invalid");
			}
			symbolTable.addKeyValue(currentDeclaration.identifier().value().currentType);
			declaration = declaration.rest();
		}
		
		return symbolTable;
	}
}
