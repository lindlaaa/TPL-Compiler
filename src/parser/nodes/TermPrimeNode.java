package src.parser.nodes;

import src.parser.*;

public class TermPrimeNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeAndNode){
  	  this.takeChildren((TermPrimeAndNode)TableDrivenParser.semanticStack.pop(), this);
  	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeTimesNode){
  	  this.takeChildren((TermPrimeTimesNode)TableDrivenParser.semanticStack.pop(), this);
  	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeDivideNode){
      this.takeChildren((TermPrimeDivideNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "TermPrimeNode";
  }
}
