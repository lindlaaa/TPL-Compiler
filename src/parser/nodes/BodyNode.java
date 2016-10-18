package src.parser.nodes;

class BodyNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof StatementListPSNode ||
	     semanticStack.peek() instanceof StatementListNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
