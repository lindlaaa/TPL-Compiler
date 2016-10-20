package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimePlusNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "SimpleExprPrimePlusNode";
  }
}
