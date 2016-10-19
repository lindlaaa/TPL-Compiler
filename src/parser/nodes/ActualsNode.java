package src.parser.nodes;

import src.parser.*;

public class ActualsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof NonEmptyActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
