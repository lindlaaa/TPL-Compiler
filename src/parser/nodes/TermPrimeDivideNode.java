package src.parser.nodes;

public class TermPrimeDivideNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	   }
  }
}
