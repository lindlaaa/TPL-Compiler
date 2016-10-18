package src.parser.nodes;

class ExprNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof SimplExprNode){
      tree.addleaf(semanticStack.pop());
	   }
    if(semanticStack.peek() instanceof ExprPrimeLTNode ||
	     semanticStack.peek() instanceof ExprPrimeExprNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
