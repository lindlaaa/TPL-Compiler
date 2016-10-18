package src.parser.nodes;

class IfIntMinusNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof FactorIDNode ||
	     semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
