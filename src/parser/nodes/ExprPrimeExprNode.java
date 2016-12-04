package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class ExprPrimeExprNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof ExprNode){
      this.takeChildren((ExprNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }


  @Override
  public String evaluate(){
    String arg1 = this.getChild(0).evaluate();
    String arg2 = this.getChild(1).evaluate();
    String temp = Generator.newTemp();

    Generator.emit("equalTo", arg1, arg2, temp);

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
    return "isEqualTo?";
  }
}
