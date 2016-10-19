package src.parser.nodes;

class IfIntLPNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
