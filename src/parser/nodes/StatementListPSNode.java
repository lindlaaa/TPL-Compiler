package src.parser.nodes;

import src.parser.*;

public class StatementListPSNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof PrintStatementNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
	    this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "StatementListPSNode";
  }
}
