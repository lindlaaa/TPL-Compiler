package src.parser.nodes;

import src.parser.*;

public class TermPrimeAndNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      this.addChild((TermNode)TableDrivenParser.semanticStack.pop(), this);
	}
  }

  @Override
  public String toString(){
    return "TermPrimeAndNode";
  }
}
