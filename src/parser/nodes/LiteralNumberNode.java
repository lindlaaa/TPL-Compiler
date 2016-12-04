package src.parser.nodes;

import src.parser.*;
import src.scanner.IntToken;

public class LiteralNumberNode extends SemanticNode{

  IntToken value;
  int intValue = value.getVal();  
  
  public int getLiteralVal(){
	  return intValue;
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
