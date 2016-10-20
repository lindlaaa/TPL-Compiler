package src.parser.nodes;

import src.parser.*;

public class IdentifierPrimeLP extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "IdentifierPrimeLP";//TODO
  }
}
