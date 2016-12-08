package src.parser.nodes;

import src.parser.*;
import src.scanner.BoolToken;
import src.codegen.Generator;

public class LiteralBooleanNode extends SemanticNode{

  BoolToken value;
  int intValue;


  @Override
  public String evaluate(){
    String temp = Generator.newTemp();
	  if(this.value.getVal()){
	    Generator.emit("assign","1",temp);
    }else{
      Generator.emit("assign","0",temp);
    }
	  return temp;
  }

  @Override
  public void setChildren(){
    //this.addChild((BoolToken)TableDrivenParser.semanticBuffer.pop());FIXME
    //TableDrivenParser.semanticBuffer.pop();
    this.value = (BoolToken)TableDrivenParser.semanticBuffer.pop();
  }

  /*
  @Override
  public void typeCheck(){
    this.setNodeType(BranchType.BOOLEAN);
  }
  */

  @Override
  public String toString(){
    return "Boolean "+ this.value.getVal();
  }
}
