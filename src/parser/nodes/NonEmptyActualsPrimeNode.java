package src.parser.nodes;

import src.parser.*;

public class NonEmptyActualsPrimeNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	    this.takeChildren((NonEmptyActualsPrimeNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.takeChildren((ExprNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "NonEmptyActualsPrimeNode";
  }
}
