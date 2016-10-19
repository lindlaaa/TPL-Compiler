package src.parser.nodes;

class PrintStatementNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
