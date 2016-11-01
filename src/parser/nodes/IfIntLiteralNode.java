package src.parser.nodes;

import src.parser.*;

public class IfIntLiteralNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralNumberNode){
      this.takeChildren((LiteralNumberNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof LiteralBooleanNode){
      this.takeChildren((LiteralBooleanNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "IfIntLiteralNode";
  }
}
