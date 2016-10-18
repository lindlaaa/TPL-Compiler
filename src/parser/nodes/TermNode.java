package src.parser.nodes;

class TermNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode ||
       semanticStack.peek() instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof TermPrimeAndNode ||
	     semanticStack.peek() instanceof TermPrimeTimesNode ||
	     semanticStack.peek() instanceof TermPrimeDivideNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
