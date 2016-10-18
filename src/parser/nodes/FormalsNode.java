package src.parser.nodes;

class FormalsNode extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof NonEmptyFormalsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
