package src.parser.nodes;

import src.parser.*;

public class TermPrimeDivideNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }

  @Override
  public String toString(){
    return "TermPrimeDivideNode";//TODO
  }
}
