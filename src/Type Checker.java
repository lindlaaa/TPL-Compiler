package src.parser;

public class TypeChecker{
	private SymbolTable symbolTable;
	
	public TypeChecker(SymbolTable inputTable){
		this.symbolTable = inputTable;
	}
	
	public void check(Program inputProgram) throws SemanticException{
		Statement statements = inputProgram.statements();
		while (statements != null){
			checkStatement( statements.first());
			statements = statements.rest();
		}
	}
	
	protected void checkStatement(Statement inputStatement) throws SemanticException{
		if(inputStatement instanceof AssignmentStatement){
			AssignmentStatement s = (AssignmentStatement) inputStatement;
			checkExpression(s.value());
			
			Identifier id = s.identifier();
			DataType type = this.symbolTable.lookupID(id.value());
			
			id.setType(type);
			s.setValue(convertToType(s.value(), id.type() ));
		}else if(inputStatement instanceof PrintStatement){
			PrintStatement s = (PrintStatement) inputStatement;
			Identifier id = s.identifier();
			id.setType(this.symbolTable.lookupID(id.value());					
		}else
		{//assuming "inputStatement" has a toString() method
		  throw new SemanticException(
			"the type checker was expecting " +inputStatement+" to be an assignment or print statement");	
		}
	}
	
	public void checkExpression(Value inputValue) throws SemanticException{
		if(inputValue instanceof Identifier){
			Identifier id - (identifier) inputValue;
			inputValue.setType(this.symbolTable.lookupID(id.value());
		}else if(inputValue instanceof FloatValue){ //flair doesnt have floats but does have booleans
			inputValue.setType(DataType.Float);
		}else if(inputValue instanceof IntegerValue){
			inputValue.setType(DataType.Integer);
		}else if(inputValue instanceof ExpressionTail){//ExpressionPrime?
			ExpressionTail exp = (ExpressionTail) inputValue;
			
			Value left = exp.leftOperand();
			Value right = exp.rightOperand();
			
			checkExpression(left);
			checkExpression(right);
			
			DataType type = generalize(left, right);
			exp.setLeftOperand(convertToType(left, type)); 
			//"type" is assigned in checkStatement() function
			exp.setRightOperand(convertToType(right, type));
			exp.setType(type);
		}else{
			throw new SemanticException(
				"The type checker has found a type of expression that is unhandled");
		}
	}
		
	protected DataType generalize(Value inputVal1, Value inputVal2){//flair doesnt have float types
		if(inputVal1.type().equals(DataType.Float) ||
		inputVal2.type().equals(DataType.Float)){
			return DataType.Float;
		}
		return DataType.Integer;
	}
		
	protected Value convertToType(Value inputValue, DataType newType) throws SemanticException{
		DataType valueType = inputValue.type();
		//don't have fliat types but do have booleans
		if(valueType.equals(DataType.Float) && //bool=true ==>1; bool=false ==>0 
		newType.equals(DataType.Integer)){
			throw new SemanticException("cannot convert float to int");
		}
		if(valueType.equals(DataType.Integer) &&
		newType.equals(DataType.Float)){
			return new IntToFloatConversion(value);//int=1 ==>true; int=0 ==>false; else cannot convert int to bool
		}
		return inputValue;
	}
	
}
				

