package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	    this.takeChildren((NonEmptyFormalsPrimeNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalNode){
      this.addChild((FormalNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsNode";
  }
}
