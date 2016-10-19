package src.parser.nodes;



public class DefinitionsNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof DefNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek() instanceof DefinitionsNode){
	    tree.addleaf(semanticStack.pop());
    }
  }
}
