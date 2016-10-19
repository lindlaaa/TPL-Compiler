package src.parser.nodes;

import src.parser.*;

public class FormalsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "FormalsNode";//TODO
  }
}
