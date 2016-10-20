package src.parser.nodes;

import src.parser.*;

public class ProgramNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    this.addChild(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalsNode){
  	  this.addChild(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "ProgramNode";
  }
}
