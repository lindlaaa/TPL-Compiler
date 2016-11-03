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
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "DefinitionsNode";
  }
}
