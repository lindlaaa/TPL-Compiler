package src.parser.nodes;

import src.parser.*;

public class LiteralNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralNumberNode){
      this.addChild((LiteralNumberNode)TableDrivenParser.semanticStack.pop(), this);
    }else if(TableDrivenParser.semanticStack.peek() instanceof LiteralBooleanNode){
      this.addChild((LiteralBooleanNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "LiteralNode";
  }
}
