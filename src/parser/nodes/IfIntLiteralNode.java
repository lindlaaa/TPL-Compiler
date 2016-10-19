package src.parser.nodes;

import src.parser.*;

public class IfIntLiteralNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof LiteralNumberNode ||
	     TableDrivenParser.semanticStack.pop() instanceof LiteralBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }
}
