package src.parser.nodes;

import src.parser.*;

public class BoolTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "boolean";
  }

  public String getValue(){
    return this.value;
  }
  @override
  public void typeCheck(){
	  //unique implementation
  }
  @Override
  public String toString(){
    return "BoolTypeNode ";
  }
}
