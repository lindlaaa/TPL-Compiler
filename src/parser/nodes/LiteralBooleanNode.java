package src.parser.nodes;

import src.parser.*;

public class LiteralBooleanNode extends SemanticNode{
  @Override
  public void addChildren(){
    this.addChild(TableDrivenParser.semanticBuffer.pop());
  }

  @Override
  public String toString(){
    return "LiteralBooleanNode";
  }
}
