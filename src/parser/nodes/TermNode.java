package src.parser.nodes;

import src.parser.*;

public class TermNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode ||
       TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeAndNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TermPrimeTimesNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TermPrimeDivideNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "TermNode";
  }
}
