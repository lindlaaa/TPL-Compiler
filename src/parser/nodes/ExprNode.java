package src.parser.nodes;



public class ExprNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
	   }
    if(semanticStack.peek() instanceof ExprPrimeLTNode ||
	     semanticStack.peek() instanceof ExprPrimeExprNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
