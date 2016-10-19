package src.parser.nodes;

import src.parser.*;

public class SimpleExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof SimpleExprPrimeOrNode ||
	     TableDrivenParser.semanticStack.pop() instanceof SimpleExprPrimePlusNode ||
	     TableDrivenParser.semanticStack.pop() instanceof SimpleExprPrimeMinusNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
