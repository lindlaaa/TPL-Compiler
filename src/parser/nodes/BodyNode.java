package src.parser.nodes;

import src.parser.*;

public class BodyNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.pop() instanceof StatementListNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "BodyNode";//TODO
  }
}
