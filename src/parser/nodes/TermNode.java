package src.parser.nodes;

import src.parser.*;

public class TermNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.pop() instanceof FactorNotNode ||
       TableDrivenParser.semanticStack.pop() instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof TermPrimeAndNode ||
	     TableDrivenParser.semanticStack.pop() instanceof TermPrimeTimesNode ||
	     TableDrivenParser.semanticStack.pop() instanceof TermPrimeDivideNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
