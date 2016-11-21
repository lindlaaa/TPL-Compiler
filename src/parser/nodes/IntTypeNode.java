package src.parser.nodes;

import src.parser.*;

public class IntTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "integer";
  }

  public String getValue(){
    return this.value;
  }
  /*
  @Override
  public void typeCheck(){
    this.setNodeType(BranchType.INTEGER);
  }
  */
  @Override
  public String toString(){
    return "IntTypeNode";
  }
}
