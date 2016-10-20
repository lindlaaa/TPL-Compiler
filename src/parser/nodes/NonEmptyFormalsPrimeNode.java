package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsPrimeNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsPrimeNode";//TODO
  }
}
