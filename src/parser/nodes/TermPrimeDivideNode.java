package src.parser.nodes;

import src.parser.*;

public class TermPrimeDivideNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof TermNode){
      this.addChild((TermNode)TableDrivenParser.semanticStack.pop());
	}
  }

  @Override
  public String toString(){
    return "TermPrimeDivideNode";
  }
}
