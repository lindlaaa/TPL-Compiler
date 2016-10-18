package src.parser.nodes;

class FactorNotNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode ||
	     semanticStack.peek() instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
