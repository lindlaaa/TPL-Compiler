package src.parser.nodes;

public class NonEmptyActualsNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
