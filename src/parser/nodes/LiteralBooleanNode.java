package src.parser.nodes;

import src.parser.*;
import src.scanner.BoolToken;

public class LiteralBooleanNode extends SemanticNode{

  BoolToken value;
  int intValue = 0;


  @Override
  public String evaluate(){
	  if(this.value.getVal()){
	    intValue = 1;
    }
	  return Integer.toString(this.intValue);
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
