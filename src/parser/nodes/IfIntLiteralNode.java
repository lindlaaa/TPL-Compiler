package src.parser.nodes;

import src.parser.*;

public class IfIntLiteralNode extends SemanticNode{
  @Override
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralNumberNode ||
	     TableDrivenParser.semanticStack.peek() instanceof LiteralBooleanNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "IfIntLiteralNode";
  }
}
