package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimePlusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
