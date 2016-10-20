package src.parser.nodes;

import src.parser.*;

public class NonEmptyActualsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	    this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "NonEmptyActualsNode";
  }
}
