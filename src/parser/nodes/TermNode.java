package src.parser.nodes;

public class TermNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof FactorIfNode ||
	     semanticStack.peek() instanceof FactorNotNode ||
       semanticStack.peek() instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof TermPrimeAndNode ||
	     semanticStack.peek() instanceof TermPrimeTimesNode ||
	     semanticStack.peek() instanceof TermPrimeDivideNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
