package src.parser.nodes;

import src.parser.*;

public class BodyNode extends SemanticNode{
  @Override
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }

  @Override
  public String toString(){
    return "BodyNode";//TODO
  }
}
