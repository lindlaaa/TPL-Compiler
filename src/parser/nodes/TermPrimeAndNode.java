package src.parser.nodes;

class TermPrimeAndNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
