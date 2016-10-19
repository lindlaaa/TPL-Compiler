package src.parser.nodes;

import src.parser.*;

public class NonEmptyFormalsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }
}
