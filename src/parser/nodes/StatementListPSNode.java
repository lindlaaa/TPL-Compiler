package src.parser.nodes;

import src.parser.*;

public class StatementListPSNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof PrintStatementNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
