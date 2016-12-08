package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class FactorNotNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorNode){
      this.takeChildren((FactorNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }


  @Override
  public String evaluate(){
    String temp = Generator.newTemp();

    String result = getChild(0).evaluate();
    Generator.emit("negate", result, temp);

    return temp;
  }



  @Override
  public String toString(){
    return "Negate";
  }
}
