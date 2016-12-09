package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class ReturnNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.takeChildren((ExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }


  @Override
  public String evaluate(){
    String temp = this.getChild(0).evaluate();
    Generator.emit("Return",temp);
    return temp;
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
    return "ReturnNode";
  }
}
