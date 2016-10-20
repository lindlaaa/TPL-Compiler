package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FormalNode){
      this.addChild((FormalNode)TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	  this.addChild((NonEmptyFormalsPrimeNode)TableDrivenParser.semanticStack.pop());
	}
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsNode";
  }
}
