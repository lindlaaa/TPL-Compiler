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
    //lebels
    String firstLabel = Generator.newLabel();
    String secondLabel = Generator.newLabel();

    //test node
    String test = this.getChild(2).evaluate();

    Generator.emit("If",test,"GOTO",firstLabel);

    //then node
    this.getChild(1).evaluate();

    //Generator.emit("assign",this.getChild(1).evaluate());
    Generator.emit("GOTO",secondLabel);

    //else node
    Generator.emit("Label "+firstLabel);
    this.getChild(0).evaluate();
    //Generator.emit("assign",this.getChild(0).evaluate());

    Generator.emit("Label "+secondLabel);

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
