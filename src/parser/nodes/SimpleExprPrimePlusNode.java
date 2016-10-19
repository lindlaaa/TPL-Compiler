package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimePlusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "SimpleExprPrimePlusNode";//TODO
  }
}
