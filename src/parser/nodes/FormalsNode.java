package src.parser.nodes;

import src.parser.*;

public class FormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsNode){
      this.takeChildren((NonEmptyFormalsNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "FormalsNode";
  }
}
