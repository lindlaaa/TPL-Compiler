package src.parser.nodes;

class TermPrimeTimesNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
