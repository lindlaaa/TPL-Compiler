package src.parser.nodes;

class NonEmptyActualsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
