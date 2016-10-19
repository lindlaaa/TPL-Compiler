package src.parser.nodes;

import src.parser.*;

public class ExprPrimeLTNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "ExprPrimeLTNode";//TODO
  }
}
