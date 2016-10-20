package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FormalNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	  this.addChild(TableDrivenParser.semanticStack.pop());
	}
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsNode";
  }
}
