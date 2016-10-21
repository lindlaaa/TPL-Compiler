package src.parser.nodes;

import src.parser.*;

public class TermNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeNode){
  	  this.addChild((TermPrimeNode)TableDrivenParser.semanticStack.pop(), this);
  	}

    if(TableDrivenParser.semanticStack.peek() instanceof FactorNode){
  	  this.addChild((FactorNode)TableDrivenParser.semanticStack.pop(), this);
  	}
  }

  @Override
  public String toString(){
    return "TermNode";
  }
}
