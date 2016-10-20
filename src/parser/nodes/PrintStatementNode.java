package src.parser.nodes;

import src.parser.*;

public class PrintStatementNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "PrintStatementNode";//TODO
  }
}
