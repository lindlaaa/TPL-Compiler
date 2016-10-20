package src.parser.nodes;

import src.parser.*;

public class SimpleExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeOrNode ||
	     TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimePlusNode ||
	     TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeMinusNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "SimpleExprNode";
  }
}
