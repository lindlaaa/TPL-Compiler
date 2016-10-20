package src.parser.nodes;

import src.parser.*;

public class BodyNode extends SemanticNode{
  @Override
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "BodyNode";
  }
}
