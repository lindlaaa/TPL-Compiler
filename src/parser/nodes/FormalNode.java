package src.parser.nodes;

import src.parser.*;

public class FormalNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }

  @Override
  public String toString(){
    return "FormalNode";
  }
}
