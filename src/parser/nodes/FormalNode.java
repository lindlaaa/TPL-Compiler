package src.parser.nodes;

import src.parser.*;

public class FormalNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof TypeIntegerNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "FormalNode";//TODO
  }
}
