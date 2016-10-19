package src.parser.nodes;

import src.parser.*;

public class FactorIfNode extends SemanticNode{
  public void getChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.pop() instanceof ExprNode){
	      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	    }
	  }
  }

  @Override
  public String toString(){
    return "FactorIfNode";//TODO
  }
}
