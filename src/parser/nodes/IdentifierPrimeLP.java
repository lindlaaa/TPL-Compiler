package src.parser.nodes;

import src.parser.*;

public class IdentifierPrimeLP extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
