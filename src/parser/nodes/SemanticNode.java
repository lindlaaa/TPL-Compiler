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
    //System.out.println("---before setparent---");
    //System.out.println(p);
    child.setParent(p);
    //System.out.println("---after setparent---");
    //System.out.println("parent = "+p.getChildren());
    p.getChildren().add(child);
    //System.out.println("---after .add---");

  }

  public String graphTree(SemanticNode node, int num, int inc){
    String content = "";
    int tmp = inc;
    //int inc = 0;
    //content += appender + node + "\n";
    //Point to its children
    for (SemanticNode each : node.getChildren()) {
      tmp++;
      content += "  \""+node+num+"\" -> \""+each+(num+tmp)+"\";\n";
    }

    //inc = 0;
    //Call to its own children
    for (SemanticNode each : node.getChildren()) {
      inc++;
      content += "subgraph cluster_"+inc+"{\n"
                  +graphTree(each, num+inc, inc)
                  +"\n}";
    }
    return content;
  }

  public void printTree(SemanticNode node, String appender) {
   System.out.println(appender + node);
   //System.out.println(node.getChildren());
   for (SemanticNode each : node.getChildren()) {
     //System.out.println("Next");
      printTree(each, appender + "| ");
    }
  }
}
