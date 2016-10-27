package src.parser.nodes;

import src.parser.*;

public class IntTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "integer";
  }

  @Override
  public String toString(){
    return "IntTypeNode ";
  }
}
