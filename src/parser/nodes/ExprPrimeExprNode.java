package src.parser.nodes;

import src.parser.*;

public class ExprPrimeExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }
}
