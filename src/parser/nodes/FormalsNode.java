package src.parser.nodes;

import src.parser.*;

public class FormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsNode){
      this.takeChildren((NonEmptyFormalsNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
		//check if children have assigned types
	}
  }
  @Override
  public String toString(){
    return "FormalsNode";
  }
}
