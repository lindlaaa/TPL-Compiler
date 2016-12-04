package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class SimpleExprPrimeOrNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof SimpleExprNode){
      this.takeChildren((SimpleExprNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }


  @Override
  public String evaluate(){
    String arg1 = this.getChild(0).evaluate();
    String arg2 = this.getChild(1).evaluate();
    String temp = Generator.newTemp();

    Generator.emit("or", arg1, arg2, temp);

    return temp;
  }


  @Override
  public String toString(){
    return "Or";
  }
}
