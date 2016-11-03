package src.parser.nodes;

import src.parser.*;
import src.scanner.IdentifierToken;

public class IdentifierNode extends SemanticNode{

  IdentifierToken value;

  @Override
  public void setChildren(){
    //this.addChild((String)TableDrivenParser.semanticBuffer.pop());FIXME
    this.value = (IdentifierToken)TableDrivenParser.semanticBuffer.pop();
  }

  @Override
  public String getID(){
    return this.value.toString();
  }
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "IdentifierNode "+this.value;
  }
}
