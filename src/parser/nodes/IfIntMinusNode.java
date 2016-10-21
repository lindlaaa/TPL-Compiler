package src.parser.nodes;

import src.parser.*;

public class IfIntMinusNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.addChild((FactorIDNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode){
      this.addChild((FactorIfNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof FactorNotNode){
      this.addChild((FactorNotNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "IfIntMinusNode";
  }
}
