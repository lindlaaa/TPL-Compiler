package src.parser.nodes;

import src.parser.*;
import src.scanner.IntToken;

public class LiteralNumberNode extends SemanticNode{

  IntToken value;
<<<<<<< HEAD
  long intValue;

  @Override
  public String evaluate(){
    this.intValue = this.value.getVal();
	  return Long.toString(this.intValue);
=======
  
  public String evaluate(){
	int intValue = this.value.getVal();  	  
	return this.intValue.toString();
>>>>>>> master
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
