package src.parser.nodes;

import src.parser.*;

public class FactorNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode){
      this.takeChildren((FactorIfNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorNotNode){
      this.takeChildren((FactorNotNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.takeChildren((FactorIDNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof LiteralNode){
      this.takeChildren((LiteralNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorMinusNode){
      this.takeChildren((FactorMinusNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    else if(TableDrivenParser.semanticStack.peek() instanceof FactorExprNode){
      this.takeChildren((FactorExprNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "FactorNode";
  }
}
