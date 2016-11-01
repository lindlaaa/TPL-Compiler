package src.parser.nodes;

import src.parser.*;

public class BodyNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
      this.takeChildren((StatementListNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "BodyNode";
  }
}
