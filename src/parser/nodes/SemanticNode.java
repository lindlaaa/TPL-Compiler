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

  public void addChild(SemanticNode child, SemanticNode p) {
    child.setParent(p);
    p.getChildren().add(child);
  }

  public void takeChildren(SemanticNode old, SemanticNode p){
    for(SemanticNode each : old.getChildren()){
      p.addChild(each, p);
    }
  }

  public String graphTree(SemanticNode node, int num, int inc){
    String content = "";
    int tmp = inc;
    for (SemanticNode each : node.getChildren()) {
      tmp++;
      content += "  \""+node+num+"\" -> \""+each+(num+tmp)+"\";\n";
    }

    //Call to its own children
    for (SemanticNode each : node.getChildren()) {
      inc++;
      /*content += "subgraph cluster_"+inc+"{\n"
                  +graphTree(each, num+inc, inc)
                  +"\n}";*/
      content += graphTree(each, num+inc, inc);
    }
    return content;
  }

  public void printTree(SemanticNode node, String appender) {
   System.out.println(appender + node);
   for (SemanticNode each : node.getChildren()) {
      printTree(each, appender + "| ");
    }
  }
}
