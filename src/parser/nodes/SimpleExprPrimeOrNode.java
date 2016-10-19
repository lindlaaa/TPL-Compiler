package src.parser.nodes;

import src.parser.*;

public class SimpleExprPrimeOrNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
