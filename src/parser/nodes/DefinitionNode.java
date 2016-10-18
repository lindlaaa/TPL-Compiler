package src.parser.nodes;

class DefinitionsNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof DefNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof DefinitionsNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
