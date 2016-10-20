package src.parser.nodes;

import src.parser.*;

public class IdentifierPrimeLP extends SemanticNode{
  @Override
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ActualsNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "IdentifierPrimeLP";
  }
}
