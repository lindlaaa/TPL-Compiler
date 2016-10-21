package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimeMinusNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.addChild((SimpleExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "SimpleExprPrimeMinusNode";
  }
}
