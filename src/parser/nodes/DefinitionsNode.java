package src.parser.nodes;

import src.parser.*;

public class DefinitionsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof DefNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
