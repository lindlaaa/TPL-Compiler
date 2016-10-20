package src.parser.nodes;

import src.parser.*;

public class FactorNotNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "FactorNotNode";
  }
}
