package src.parser.nodes;

class IdentifierPrimeLP extends SemanticNode{
  public void getChildren(){
    if(semanticStack.peek() instanceof ActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
