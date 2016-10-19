package src.parser.nodes;



public class FormalsNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof NonEmptyFormalsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
