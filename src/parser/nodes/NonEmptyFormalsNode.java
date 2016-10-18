package src.parser.nodes;

class NonEmptyFormalsNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof FormalNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	    tree.addleaf(semanticStack.pop());
	  }
  }
}
