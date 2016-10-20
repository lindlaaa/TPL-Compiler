package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FormalNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsPrimeNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsNode";//TODO
  }
}
