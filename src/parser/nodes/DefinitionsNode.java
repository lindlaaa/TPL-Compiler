package src.parser.nodes;

import src.parser.*;

public class DefinitionsNode extends SemanticNode{
  public void setChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof DefNode){
      this.getChildren().addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof DefinitionsNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "DefinitionsNode";//TODO
  }
}
