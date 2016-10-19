package src.parser.nodes;

import src.parser.*;

public class NonEmptyActualsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof NonEmptyActualsPrimeNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
