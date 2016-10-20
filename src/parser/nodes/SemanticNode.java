package src.parser.nodes;

import java.util.ArrayList;
import java.util.List;
import src.parser.*;

public class SemanticNode implements NodeBehavior{

  private String id;
  private final List<SemanticNode> children = new ArrayList<>();
  private final Node parent;

  public SemanticNode() {
    this.parent = null;
  }

  public void setParent(SemanticNode newParent){
    this.parent = newParent;
  }

  public List<Node> getChildren() {
    return children;
  }

  public Node getParent() {
    return parent;
  }

  public void getChildren(){};

  private static Node addChild(Node child) {
    child.setParent(this);
    parent.getChildren().add(child);
    return node;
  }

  private static void printTree(Node node, String appender) {
   System.out.println(appender + node.getId());
   for (Node each : node.getChildren()) {
      printTree(each, appender + appender);
    }
  }
}
