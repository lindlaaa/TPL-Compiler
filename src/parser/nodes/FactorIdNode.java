package src.parser.nodes;



public class FactorIDNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof IdentifierNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof IdentifierPrimeLP){
      tree.addleaf(semanticStack.pop());
    }
  }
}
