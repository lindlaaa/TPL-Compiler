package src.parser.nodes;

import src.parser.*;

public class ProgramNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof FormalsNode){
  	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof DefinitionsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek() instanceof BodyNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }

  @Override
  public String toString(){
    return "ProgramNode";//TODO
  }
}
