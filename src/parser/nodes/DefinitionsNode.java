package src.parser.nodes;

import src.parser.*;

public class DefinitionsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof DefNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof DefinitionsNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
