package src.parser.nodes;

import src.parser.*;
import src.scanner.BoolToken;

public class LiteralBooleanNode extends SemanticNode{

  BoolToken value;
  boolean boolValue = this.value.getVal();
  int intValue = 0;

  public String evaluate(){
	if(boolValue){
	  intValue = 1;
    }
	return this.intValue.toString();
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
