package src.parser.nodes;

import src.parser.*;
import src.scanner.IntToken;

public class LiteralNumberNode extends SemanticNode{

  IntToken value;

  @Override
  public void setChildren(){
    //this.addChild((int)TableDrivenParser.semanticBuffer.pop());FIXME
    //TableDrivenParser.semanticBuffer.pop();
    this.value = (IntToken)TableDrivenParser.semanticBuffer.pop();
  }

  @Override
  public String toString(){
    return "LiteralNumberNode "+ this.value;
  }
}
