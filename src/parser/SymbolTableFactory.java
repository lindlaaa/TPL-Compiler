package src.parser;
/*
All the grammar rules that include non-terminal "TYPE" might be useful...

<DEF>    ::= function <IDENTIFIER> Make-Identifier ( <FORMALS> ) : <TYPE> <BODY> ; Make-Def
<FORMAL> ::= <IDENTIFIER> Make-Identifier : <TYPE> Make-Formal
<TYPE>   ::= integer  Make-Integer
           | boolean  Make-Boolean
*/

public class SymbolTableFactory{
	/*
	//this "program" class is Eugenes nonterminal parser type?
	private Program program;

	public SymbolTableFactory(Program inputAST){
		this.program = inputAST;
	}
	*/

	/*
	NDS: Thought process...

	1. need to iterate through tree branches by checking the .getChildren() of all branches
	2. if current SemanticNode instanceof SemanticNode.Identifier hold string of identifier in a buffer,
	3. get next SemanticNode
	4. if next semanticnode instanceof SemanticNode.LiteralNumberNode || SemanticNode.LiteralBooleanNode,
	then place identifier in the buffer into the hashmap as the key, and enter the SemanticNode..getValue() as the value to the key
		i.e. SymbolTable.addKeyValue(string name of the current id node, .getvalue() of next node)
	*/
	/*
	public SymbolTable createTable() throws ParseException{
		SymbolTable symbolTable = new SymbolTable();
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
				throw new ParseException("The type declaration is invalid");
			}
			symbolTable.addKeyValue(currentDeclaration.identifier().value().currentType);
			declaration = declaration.rest();
		}

		return symbolTable;
	}
	*/
}
