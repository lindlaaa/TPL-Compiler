package src.parser.nodes;

import src.parser.*;

public class ExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.addChildren(TableDrivenParser.semanticStack.pop());
	   }
    if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeLTNode ||
	     TableDrivenParser.semanticStack.peek() instanceof ExprPrimeExprNode){
	    this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "ExprNode";
  }
}
