package src.parser.nodes;

import src.parser.*;

public class StatementListPSNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof PrintStatementNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.pop() instanceof StatementListNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "StatementListPSNode";//TODO
  }
}
