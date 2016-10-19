package src.parser.nodes;

import src.parser.*;

public class FactorNotNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
