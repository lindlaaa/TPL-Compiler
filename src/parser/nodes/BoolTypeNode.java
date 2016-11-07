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
  /*
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
		//check if children have assigned types
  }
}*/
  @Override
  public String toString(){
    return "BoolTypeNode";
  }
}
