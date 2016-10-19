package src.parser.nodes;

import src.parser.*;

public class FactorIfNode extends SemanticNode{
  public void getChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
	      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	    }
	  }
  }
}
