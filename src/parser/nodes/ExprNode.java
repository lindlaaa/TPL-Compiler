package src.parser.nodes;

import src.parser.*;

public class ExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeLTNode){
      this.addChild((ExprPrimeLTNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeExprNode){
	    this.addChild((ExprPrimeExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
    
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.addChild((SimpleExprNode)TableDrivenParser.semanticStack.pop(), this);
	   }
  }

  @Override
  public String toString(){
    return "ExprNode";
  }
}
