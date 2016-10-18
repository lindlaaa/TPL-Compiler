package src.parser.nodes;

class ExprPrimeLTNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
