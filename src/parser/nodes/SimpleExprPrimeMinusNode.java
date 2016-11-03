package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimeMinusNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.takeChildren((SimpleExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "SimpleExprPrimeMinusNode";
  }
}
