package src.parser.nodes;

class SimpleExprNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof SimpleExprPrimeOrNode ||
	     semanticStack.peek() instanceof SimpleExprPrimePlusNode ||
	     semanticStack.peek() instanceof SimpleExprPrimeMinusNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
