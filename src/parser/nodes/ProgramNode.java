package src.parser.nodes;

import src.parser.*;

public class ProgramNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof IdentifierNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop() instanceof FormalsNode){
  	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop() instanceof DefinitionsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop() instanceof BodyNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "ProgramNode";//TODO
  }
}
