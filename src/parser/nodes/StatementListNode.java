package src.parser.nodes;

import src.parser.*;

public class StatementListNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.addChild((ExprNode)TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "StatementListNode";
  }
}
