package src.parser.nodes;

import src.parser.*;

public class ExprPrimeLTNode extends SemanticNode{
  @Override
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "ExprPrimeLTNode";
  }
}
