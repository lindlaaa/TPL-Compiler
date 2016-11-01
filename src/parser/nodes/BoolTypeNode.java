package src.parser.nodes;

import src.parser.*;

public class BoolTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "boolean";
  }

  @Override
  public String toString(){
    return "BoolTypeNode ";
  }
}
