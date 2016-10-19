package src.parser.nodes;

public class IdentifierPrimeLP extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof ActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
