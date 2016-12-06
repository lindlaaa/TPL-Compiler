package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class ProgramNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
      this.addChild((BodyNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
      this.addChild((DefinitionsNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalsNode){
  	  this.addChild((FormalsNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
    }
  }



  public String evaluate(){
    String result = getChild(0).evaluate(); // body node
    Generator.emit("print",result);
    getChild(1).evaluate(); //definitions node
    return result;
  }



  /*
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
  }
}*/

  @Override
  public String toString(){
    return "ProgramNode";
  }
}
