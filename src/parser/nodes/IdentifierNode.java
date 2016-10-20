package src.parser.nodes;

import src.parser.*;

public class IdentifierNode extends SemanticNode{
  @Override
  public void setChildren(){
    this.addChild(TableDrivenParser.semanticBuffer.pop());
  }

  @Override
  public String toString(){
    return "IdentifierNode";
  }
}
