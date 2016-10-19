package src.parser.nodes;



public class FactorNotNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode ||
	     semanticStack.peek() instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
