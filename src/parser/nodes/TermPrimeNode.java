package src.parser.nodes;

import src.parser.*;

public class TermPrimeNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeAndNode){
  	  this.addChild((TermPrimeAndNode)TableDrivenParser.semanticStack.pop(), this);
  	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeTimesNode){
  	  this.addChild((TermPrimeTimesNode)TableDrivenParser.semanticStack.pop(), this);
  	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeDivideNode){
      this.addChild((TermPrimeDivideNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "TermPrimeNode";
  }
}
