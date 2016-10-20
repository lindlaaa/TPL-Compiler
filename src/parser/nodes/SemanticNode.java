package src.parser.nodes;

import java.util.ArrayList;
import java.util.List;
import src.parser.*;

public class SemanticNode implements NodeBehavior{

  private String id;
  private List<SemanticNode> children = new ArrayList<>();
  private SemanticNode parent;

  public SemanticNode() {
    this.parent = null;
  }

  public void setParent(SemanticNode newParent){
    this.parent = newParent;
  }

  public List<SemanticNode> getChildren() {
    return children;
  }

  public SemanticNode getParent() {
    return parent;
  }

  public void setChildren(){};

  public void addChild(SemanticNode child) {
    child.setParent(this);
    parent.getChildren().add(child);
  }

  private void printTree(SemanticNode node, String appender) {
   System.out.println(appender + this.getClass());
   for (SemanticNode each : node.getChildren()) {
      printTree(each, appender + appender);
    }
  }
}
