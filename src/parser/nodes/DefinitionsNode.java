package src.parser.nodes;

import src.parser.*;

public class DefinitionsNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
	    this.takeChildren((DefinitionsNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof DefNode){
      this.addChild((DefNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }
  /*
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
		//check if children have assigned types
  }
}*/
  @Override
  public String toString(){
    return "DefinitionsNode";
  }
}
