package src.parser.nodes;

import src.parser.*;

public class FormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsNode){
      this.addChild((NonEmptyFormalsNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "FormalsNode";
  }
}
