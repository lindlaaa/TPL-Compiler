package src.parser.nodes;

public class NonEmptyFormalsNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof FormalNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	    tree.addleaf(semanticStack.pop());
	  }
  }
}
