package src.parser.nodes;

import src.parser.*;

public class DefNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof IdentifierNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof FormalsNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof TypeIntegerNode ||
       TableDrivenParser.semanticStack.pop() instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop() instanceof BodyNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
  }

  @Override
  public String toString(){
    return "DefNode";//TODO
  }
}
