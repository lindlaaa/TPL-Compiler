package src.parser.nodes;

import src.parser.*;

public class NonEmptyActualsPrimeNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsPrimeNode){
	  this.addChildtree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "NonEmptyActualsPrimeNode";
  }
}
