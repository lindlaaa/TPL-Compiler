package src.parser.nodes;



public class FactorIfNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    for(int i = 0; i < 3; i++){
      if(semanticStack.peek() instanceof ExprNode){
	      tree.addleaf(semanticStack.pop());
	    }
	  }
  }
}
