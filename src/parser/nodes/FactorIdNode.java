package src.parser.nodes;

class FactorIDNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof IdentifierNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof IdentifierPrimeLP){
      tree.addleaf(semanticStack.pop());
    }
  }
}
