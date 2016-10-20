package src.parser.nodes;

import src.parser.*;

public class FactorNotNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorNotNode ||
	     TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "FactorNotNode";//TODO
  }
}
