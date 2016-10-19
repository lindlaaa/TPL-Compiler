package src.parser.nodes;

public class IfIntLiteralNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof LiteralNumberNode ||
	     semanticStack.peek() instanceof LiteralBooleanNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
