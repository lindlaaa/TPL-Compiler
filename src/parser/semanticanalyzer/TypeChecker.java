package src.parser.semanticanalyzer;

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
			BranchType type = this.symbolTable.lookupID(id.value());

			id.setType(type);
			s.setValue(convertToType(s.value(), id.type() ));
		}else if(inputStatement instanceof PrintStatement){
			PrintStatement s = (PrintStatement) inputStatement;
			Identifier id = s.identifier();
			id.setType(this.symbolTable.lookupID(id.value()));
		}else
		{//assuming "inputStatement" has a toString() method
		  throw new ParseException(
			"the type checker was expecting " +inputStatement+" to be an assignment or print statement");
		}
	}

	public void checkExpression(Value inputValue) throws SemanticException{
		if(inputValue instanceof Identifier){
			//Identifier id - (Identifier) inputValue; FIXME
			inputValue.setType(this.symbolTable.lookupID(id.value()));
		}else if(inputValue instanceof FloatValue){ //flair doesnt have floats but does have booleans
			inputValue.setType(BranchType.Float);
		}else if(inputValue instanceof IntegerValue){
			inputValue.setType(BranchType.Integer);
		}else if(inputValue instanceof ExpressionTail){//ExpressionPrime?
			ExpressionTail exp = (ExpressionTail) inputValue;

			Value left = exp.leftOperand();
			Value right = exp.rightOperand();

			checkExpression(left);
			checkExpression(right);

			BranchType type = generalize(left, right);
			exp.setLeftOperand(convertToType(left, type));
			//"type" is assigned in checkStatement() function
			exp.setRightOperand(convertToType(right, type));
			exp.setType(type);
		}else{
			throw new ParseException(
				"The type checker has found a type of expression that is unhandled");
		}
	}

	protected BranchType generalize(Value inputVal1, Value inputVal2){//flair doesnt have float types
		if(inputVal1.type().equals(BranchType.Float) ||
		inputVal2.type().equals(BranchType.Float)){
			return BranchType.Float;
		}
		return BranchType.Integer;
	}

	protected Value convertToType(Value inputValue, BranchType newType) throws ParseException{
		BranchType valueType = inputValue.type();
		//don't have fliat types but do have booleans
		if(valueType.equals(BranchType.Float) && //bool=true ==>1; bool=false ==>0
		newType.equals(BranchType.Integer)){
			throw new ParseException("cannot convert float to int");
		}
		if(valueType.equals(BranchType.Integer) &&
		newType.equals(BranchType.Float)){
			return new IntToFloatConversion(value);//int=1 ==>true; int=0 ==>false; else cannot convert int to bool
		}
		return inputValue;
	}
}
