package src.parser.nodes;

import src.parser.*;

public class ExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeLTNode){
      this.takeChildren((ExprPrimeLTNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeExprNode){
	    this.takeChildren((ExprPrimeExprNode)TableDrivenParser.semanticStack.pop(), this);
    }

    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.takeChildren((SimpleExprNode)TableDrivenParser.semanticStack.pop(), this);
	   }
  }

  @Override
  public String toString(){
    return "ExprNode";
  }
}
