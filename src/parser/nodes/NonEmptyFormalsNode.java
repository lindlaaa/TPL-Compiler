package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof FormalNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof NonEmptyFormalsPrimeNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "NonEmptyFormalsNode";//TODO
  }
}
