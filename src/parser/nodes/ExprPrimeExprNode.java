package src.parser.nodes;



public class ExprPrimeExprNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
