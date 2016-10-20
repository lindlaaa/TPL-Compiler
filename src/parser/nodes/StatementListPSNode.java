package src.parser.nodes;

import src.parser.*;

public class StatementListPSNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof PrintStatementNode){
      this.addChild((PrintStatementNode)TableDrivenParser.semanticStack.pop());
	}
	  
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode){
      this.addChild((StatementListPSNode)TableDrivenParser.semanticStack.pop());		
	}else if(TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
	  this.addChild((StatementListNode)TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "StatementListPSNode";
  }
}
