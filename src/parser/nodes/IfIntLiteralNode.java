package src.parser.nodes;

class IfIntLiteralNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof LiteralNumberNode ||
	     semanticStack.peek() instanceof LiteralBooleanNode){
      tree.addleaf(semanticStack.pop());
	  }
  }
}
