package src.parser.nodes;

import src.parser.*;

public class ActualsNode extends SemanticNode{
  @Override
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof NonEmptyActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "ActualsNode";//TODO
  }
}
