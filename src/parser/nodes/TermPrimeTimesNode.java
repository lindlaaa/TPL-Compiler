package src.parser.nodes;

import src.parser.*;

public class TermPrimeTimesNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      this.takeChildren((TermNode)TableDrivenParser.semanticStack.pop(), this);
	}
  }
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "TermPrimeTimesNode";
  }
}
