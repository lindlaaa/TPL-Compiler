package src.parser.nodes;

import src.parser.*;

public class ExprNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	   }
    if(TableDrivenParser.semanticStack.peek() instanceof ExprPrimeLTNode ||
	     TableDrivenParser.semanticStack.peek() instanceof ExprPrimeExprNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "ExprNode";//TODO
  }
}
