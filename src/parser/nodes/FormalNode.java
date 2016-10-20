package src.parser.nodes;

import src.parser.*;

public class FormalNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof TypeIntegerNode ||
	     TableDrivenParser.semanticStack.peek() instanceof TypeBooleanNode){
      this.addChild(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "FormalNode";
  }
}
