package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class FactorIfNode extends SemanticNode{
  @Override
  public void setChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
	      this.takeChildren((ExprNode)TableDrivenParser.semanticStack.pop(), this);
	    }
	  }
  }


  @Override
  public String evaluate(){
    String test = this.getChild(0).evaluate();
    String tempLabel = Generator.newLabel();
    Generator.emit("If",test,"GOTO",Generator.newLabel());
    return test;
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
    return "IfNode";
  }
}
