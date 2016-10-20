package src.parser.nodes;

import src.parser.*;

public class TermNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode ||
       TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeAndNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TermPrimeTimesNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TermPrimeDivideNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "TermNode";//TODO
  }
}
