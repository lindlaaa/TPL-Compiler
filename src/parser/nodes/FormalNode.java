package src.parser.nodes;

import src.parser.*;

public class FormalNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof BoolTypeNode){
	    this.addChild((BoolTypeNode)TableDrivenParser.semanticStack.pop(), this);
	  }else if(TableDrivenParser.semanticStack.peek() instanceof IntTypeNode){
      this.addChild((IntTypeNode)TableDrivenParser.semanticStack.pop(), this);
    }
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
      this.addChild((IdentifierNode)TableDrivenParser.semanticStack.pop(), this);
	  }
  }
  /*
  @Override
  public void typeCheck() throws Exception{
    BranchType formalTypeNode;
    BranchType formalIDNode;
    for(SemanticNode childNode : this.getChildren()){
      childNode.typeCheck();	
    }
	
    formalTypeNode = this.getChildren().get(0).getNodeType();
    formalIDNode = this.getChildren().get(1).getNodeType();
    if(formalTypeNode.equals(formalIDNode)){
      this.setNodeType(formalTypeNode);  		
    }else{
      throw new Exception("formal node was assigned conflicting types");
    }
  }
  */
  @Override
  public String toString(){
    return "FormalNode";
  }
}
