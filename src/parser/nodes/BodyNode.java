package src.parser.nodes;

import src.parser.*;

public class BodyNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof StatementListNode){
      this.takeChildren((StatementListNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }


  @Override
  public String evaluate(){
    String temp = "";
    for(int i = this.getChildren().size()-1; i >=0; i--){
      if(this.getChild(i) instanceof ReturnNode){
        temp = this.getChild(i).evaluate();
      }else{ //TODO FIXME might cause errors
        temp = this.getChild(i).evaluate();
      }
    }

    return temp;
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
    return "BodyNode";
  }
}
