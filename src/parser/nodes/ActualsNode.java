package src.parser.nodes;

import src.parser.*;

public class ActualsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsNode){
      this.addChild((NonEmptyActualsNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "ActualsNode";
  }
}
