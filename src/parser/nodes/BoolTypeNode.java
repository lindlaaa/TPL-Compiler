package src.parser.nodes;

import src.parser.*;
import src.parser.semanticanalyzer.BranchType;

public class BoolTypeNode extends SemanticNode{

  String value;

  @Override
  public void setChildren(){
    this.value = "boolean";
    setType(BranchType.BOOLEAN);
  }

  public String getValue(){
    return this.value;
  }
  /*
  @Override
  public void typeCheck(){
    this.setNodeType(BranchType.BOOLEAN);
  }
  */
  @Override
  public String toString(){
    return "BoolTypeNode";
  }
}
