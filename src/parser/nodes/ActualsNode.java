package src.parser.nodes;



public class ActualsNode extends SemanticNode{
  public void getChildren(Stack semanticStack){
    if(semanticStack.peek() instanceof NonEmptyActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
