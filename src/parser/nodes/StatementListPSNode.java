package src.parser.nodes;

public class StatementListPSNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof PrintStatementNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof StatementListPSNode ||
	     semanticStack.peek() instanceof StatementListNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
