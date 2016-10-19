package src.parser.nodes;



public class BodyNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof StatementListPSNode ||
	     semanticStack.peek() instanceof StatementListNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
