package src.parser.nodes;

import src.parser.*;

public class FormalNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof TypeIntegerNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
