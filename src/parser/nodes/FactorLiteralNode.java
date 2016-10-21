package src.parser.nodes;

import src.parser.*;

public class FactorLiteralNode extends SemanticNode{
  @Override
  public void setChildren(){
    /*
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralBoolNode){
      this.addChild((FactorNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralIntNode){
      this.addChild((FactorNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    */
  }

  @Override
  public String toString(){
    return "FactorLiteralNode";
  }
}
