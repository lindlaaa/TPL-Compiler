package src.parser.nodes;

class DefNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof IdentifierNode){
	    tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof FormalsNode){
	    tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof TypeIntegerNode ||
       semanticStack.peek() instanceof TypeBooleanNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof BodyNode){
	    tree.addleaf(semanticStack.pop());
	  }
  }
}
