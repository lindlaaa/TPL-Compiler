package src.parser.nodes;

import src.parser.*;

public class LiteralNumberNode extends SemanticNode{
  @Override
  public void addChildren(){
    this.addChild(TableDrivenParser.semanticBuffer.pop());
  }

  @Override
  public String toString(){
    return "LiteralNumberNode";
  }
}
