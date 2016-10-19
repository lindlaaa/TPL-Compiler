package src.parser.nodes;

import src.parser.*;

public class SimpleExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeOrNode ||
	     TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimePlusNode ||
	     TableDrivenParser.semanticStack.peek() instanceof SimpleExprPrimeMinusNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
