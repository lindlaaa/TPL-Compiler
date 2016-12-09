package src.parser.nodes;

import src.parser.*;
import src.scanner.IntToken;
import src.codegen.Generator;

public class LiteralNumberNode extends SemanticNode{

  IntToken value;
  long intValue;

  @Override
  public String evaluate(){
    String temp = Generator.newTemp();
    Generator.addTemp( temp, this.value.getVal() ); //TODO FIXME
    //Generator.emit("assign",Long.toString(this.value.getVal()),temp);
	  return temp;
  }

  @Override
  public void setChildren(){
    //this.addChild((int)TableDrivenParser.semanticBuffer.pop());FIXME
    //TableDrivenParser.semanticBuffer.pop();
    this.value = (IntToken)TableDrivenParser.semanticBuffer.pop();
  }
  /*
  @Override
  public void typeCheck(){
    this.setNodeType(BranchType.INTEGER);
  }
  */

  @Override
  public String toString(){
    return "Integer "+ this.value.getVal();
  }
}
