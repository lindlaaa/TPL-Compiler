package src.parser.nodes;

import src.parser.*;

public class FactorNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode){
      this.addChild((FactorIfNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorNotNode){
      this.addChild((FactorNotNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.addChild((FactorIDNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof LiteralNode){
      this.addChild((LiteralNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorMinusNode){
      this.addChild((FactorMinusNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorExprNode){
      this.addChild((FactorExprNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "FactorNode";
  }
}
