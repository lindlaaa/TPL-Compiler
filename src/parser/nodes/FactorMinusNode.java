package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class FactorMinusNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorNode){
      this.takeChildren((FactorNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }


  @Override
  public String evaluate(){
    String result = this.getChild(0).evaluate();
    String temp = Generator.newTemp();

    Generator.emit("negate", result, temp);

    return temp;
  }



  @Override
  public String toString(){
    return "Negate";
  }
}
