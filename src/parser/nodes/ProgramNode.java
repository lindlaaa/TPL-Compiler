package src.parser.nodes;

import src.parser.*;

public class ProgramNode extends SemanticNode{
  public void getChildren(){
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
}
