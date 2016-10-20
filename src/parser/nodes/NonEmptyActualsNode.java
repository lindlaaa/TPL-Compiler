package src.parser.nodes;

import src.parser.*;

public class NonEmptyActualsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "NonEmptyActualsNode";//TODO
  }
}
