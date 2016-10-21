package src.parser.nodes;

import src.parser.*;

public class StatementListNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListReturnNode){
      this.addChild((StatementListReturnNode)TableDrivenParser.semanticStack.pop(), this);
    }

    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode){
      this.addChild((StatementListPSNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "StatementListNode";
  }
}
