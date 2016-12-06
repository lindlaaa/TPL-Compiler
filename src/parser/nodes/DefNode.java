package src.parser.nodes;

import src.parser.*;
import src.codegen.Generator;

public class DefNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
	    this.addChild((BodyNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof BoolTypeNode){
	    this.addChild((BoolTypeNode)TableDrivenParser.semanticStack.pop(), this);
	  }else if(TableDrivenParser.semanticStack.peek() instanceof IntTypeNode){
      this.addChild((IntTypeNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalsNode){
	    this.addChild((FormalsNode)TableDrivenParser.semanticStack.pop(), this);
	  }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }



  @Override
  public String evaluate(){
    Generator.emit("");
    Generator.emit("Entry_" + this.getChild(3));
    String temp = getChild(0).evaluate(); // eval body node
    Generator.emit("END_" + this.getChild(3));

    Generator.emit("");
    return temp;
  }



  /*
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
		//check if children have assigned types
  }
}*/
  @Override
  public String toString(){
    return "DefNode";
  }
}
