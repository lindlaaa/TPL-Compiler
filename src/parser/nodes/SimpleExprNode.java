package src.parser.nodes;

import src.parser.*;

public class SimpleExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeOrNode){
      this.addChild((SimpleExprPrimeOrNode)TableDrivenParser.semanticStack.pop(), this);
	  }else if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimePlusNode){
      this.addChild((SimpleExprPrimePlusNode)TableDrivenParser.semanticStack.pop(), this);
	  }else if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeMinusNode){
      this.addChild((SimpleExprPrimeMinusNode)TableDrivenParser.semanticStack.pop(), this);
    }

    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      this.addChild((TermNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "SimpleExprNode";
  }
}
