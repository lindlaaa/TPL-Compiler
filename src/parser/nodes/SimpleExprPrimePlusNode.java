package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimePlusNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "SimpleExprPrimePlusNode";//TODO
  }
}
