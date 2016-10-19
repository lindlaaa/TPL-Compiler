package src.parser.nodes;

import src.parser.*;

public class FactorNotNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.pop() instanceof FactorNotNode ||
	     TableDrivenParser.semanticStack.pop() instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
