package src.parser.nodes;

class ExprPrimeExprNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
