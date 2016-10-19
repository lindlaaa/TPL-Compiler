package src.parser.nodes;

import src.parser.*;

class IfIntLPNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
