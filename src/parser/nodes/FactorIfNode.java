package src.parser.nodes;

import src.parser.*;

public class FactorIfNode extends SemanticNode{
  @Override
  public void addChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
	      this.addChild(TableDrivenParser.semanticStack.pop());
	    }
	  }
  }

  @Override
  public String toString(){
    return "FactorIfNode";
  }
}
