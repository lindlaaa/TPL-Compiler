package src.parser.nodes;

import src.parser.*;
import src.parser.semanticanalyzer.BranchType;

public class IntTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "integer";
    setType(BranchType.INTEGER);
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
