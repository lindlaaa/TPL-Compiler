package src.parser.nodes;

import src.parser.*;

public class DefNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
	    this.addChild((BodyNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof BoolTypeNode){
	    this.addChild((BoolTypeNode)TableDrivenParser.semanticStack.pop(), this);
	  }else if(TableDrivenParser.semanticStack.peek() instanceof IntTypeNode){
      this.addChild((IntTypeNode)TableDrivenParser.semanticStack.pop(), this);
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
    return "DefNode";
  }
}
