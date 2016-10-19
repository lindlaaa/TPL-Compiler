package src.parser.nodes;

import src.parser.*;

public class IfIntMinusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof FactorIDNode ||
	     TableDrivenParser.semanticStack.pop() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.pop() instanceof FactorNotNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
