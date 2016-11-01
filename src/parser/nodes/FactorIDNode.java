package src.parser.nodes;

import src.parser.*;

public class FactorIDNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierPrimeLP){
      this.takeChildren((IdentifierPrimeLP)TableDrivenParser.semanticStack.pop(), this);
    }

    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "FactorIDNode";
  }
}
