package src.parser.nodes;

import src.parser.*;

public class ProgramNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
      this.addChild((BodyNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
      this.addChild((DefinitionsNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalsNode){
  	  this.addChild((FormalsNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }

  @Override
  public String toString(){
    return "ProgramNode";
  }
}
