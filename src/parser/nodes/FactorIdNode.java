package src.parser.nodes;

import src.parser.*;

public class FactorIDNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierPrimeLP){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "FactorIDNode";
  }
}
