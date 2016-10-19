package src.parser.nodes;

public class IfIntMinusNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof FactorIDNode ||
	     semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
