package src.parser.nodes;

import src.parser.*;

public class StatementListReturnNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.addChild((ExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "StatementListReturnNode";
  }
}
