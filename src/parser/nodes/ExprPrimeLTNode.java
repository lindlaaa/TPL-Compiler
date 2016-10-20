package src.parser.nodes;

import src.parser.*;

public class ExprPrimeLTNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
      this.getChildren(1).setParent(this);
	  }
  }

  @Override
  public String toString(){
    return "ExprPrimeLTNode";//TODO
  }
}
