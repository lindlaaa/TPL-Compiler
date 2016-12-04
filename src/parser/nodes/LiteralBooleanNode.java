package src.parser.nodes;

import src.parser.*;
import src.scanner.BoolToken;

public class LiteralBooleanNode extends SemanticNode{

  BoolToken value;
  boolean boolValue = value.getVal();
  int intValue = 0;
  if(boolValue){
	  intValue = 1;
  }

  public int getLiteralVal(){
	  return intValue;
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
    return "LiteralBooleanNode "+ this.value;
  }
}
