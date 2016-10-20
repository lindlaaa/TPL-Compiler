package src.parser.nodes;

import src.parser.*;

public class IfIntLiteralNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralNumberNode ||
	     TableDrivenParser.semanticStack.peek() instanceof LiteralBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }

  @Override
  public String toString(){
    return "IfIntLiteralNode";//TODO
  }
}
