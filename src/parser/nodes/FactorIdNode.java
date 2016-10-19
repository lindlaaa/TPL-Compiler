package src.parser.nodes;

import src.parser.*;

public class FactorIDNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierPrimeLP){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
