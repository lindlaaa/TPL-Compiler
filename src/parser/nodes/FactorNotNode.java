package src.parser.nodes;

import src.parser.*;

public class FactorNotNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorNode){
      this.addChild((FactorNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "FactorNotNode";
  }
}
