package src.parser.nodes;

import src.parser.*;

public class TermPrimeTimesNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "TermPrimeTimesNode";//TODO
  }
}
