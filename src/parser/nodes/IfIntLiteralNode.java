package src.parser.nodes;

import src.parser.*;

public class IfIntLiteralNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof LiteralNumberNode ||
	     TableDrivenParser.semanticStack.peek() instanceof LiteralBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }
}
