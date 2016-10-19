package src.parser.nodes;

public class SimpleExprPrimeOrNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
