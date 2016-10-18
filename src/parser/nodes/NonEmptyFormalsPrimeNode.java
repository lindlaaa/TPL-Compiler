package src.parser.nodes;

class NonEmptyFormalsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof NonEmptyFormalsNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
