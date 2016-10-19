package src.parser.nodes;

import src.parser.*;

public class TermPrimeAndNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }
}
