package src.parser.nodes;

class SimpleExprPrimePlusNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
