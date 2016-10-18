package src.parser.nodes;

class StatementListPSNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof PrintStatementNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof StatementListPSNode ||
	     semanticStack.peek() instanceof StatementListNode){
	    tree.addleaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
