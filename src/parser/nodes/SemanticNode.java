package src.parser.nodes;

import java.util.Stack;
import src.parser.*;

public class SemanticNode implements NodeBehavior{

  public Tree tree;

  public void createTree(){
    tree = new Tree(this);
  }

  public Tree getNodeTree(){
    return tree;
  }

  public void getChildren(){}

  @Override
  public String toString(){
    return "------------------------------------------------------------";
  }
}
