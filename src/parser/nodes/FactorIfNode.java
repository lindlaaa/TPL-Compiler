package src.parser.nodes;

import src.parser.*;

public class FactorIfNode extends SemanticNode{
  @Override
  public void setChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
	      this.addChild((ExprNode)TableDrivenParser.semanticStack.pop(), this);
	    }
	  }
  }

  @Override
  public String toString(){
    return "FactorIfNode";
  }
}
