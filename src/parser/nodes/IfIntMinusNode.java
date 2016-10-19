package src.parser.nodes;

import src.parser.*;

public class IfIntMinusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIDNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
