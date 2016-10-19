package src.parser.nodes;

import src.parser.*;

public class ExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	   }
    if(TableDrivenParser.semanticStack.pop() instanceof ExprPrimeLTNode ||
	     TableDrivenParser.semanticStack.pop() instanceof ExprPrimeExprNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
