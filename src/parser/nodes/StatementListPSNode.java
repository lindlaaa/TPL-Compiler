package src.parser.nodes;

import src.parser.*;

public class StatementListPSNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof PrintStatementNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListPSNode ||
	     TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "StatementListPSNode";//TODO
  }
}
