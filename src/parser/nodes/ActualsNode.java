package src.parser.nodes;

class ActualsNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof NonEmptyActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
