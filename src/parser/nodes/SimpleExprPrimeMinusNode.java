package src.parser.nodes;

public class SimpleExprPrimeMinusNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
