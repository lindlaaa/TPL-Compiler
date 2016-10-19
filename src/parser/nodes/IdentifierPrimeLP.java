package src.parser.nodes;

import src.parser.*;

public class IdentifierPrimeLP extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof ActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "IdentifierPrimeLP";//TODO
  }
}
