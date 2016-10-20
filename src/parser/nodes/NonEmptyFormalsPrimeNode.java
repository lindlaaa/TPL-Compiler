package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsPrimeNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	}
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsPrimeNode";
  }
}
