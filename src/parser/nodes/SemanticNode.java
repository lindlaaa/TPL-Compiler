package src.parser.nodes;

import java.util.ArrayList;
import java.util.List;
import src.parser.*;
import src.parser.semanticanalyzer.*;
import src.parser.symboltable.Symbol;
import src.codegen.Generator;
import java.util.ArrayList;


public class SemanticNode implements NodeBehavior{

  private String id;
  private List<SemanticNode> children = new ArrayList<>();
  private SemanticNode parent;
  public int position = -1;
  public static int counter = 1;
  private BranchType type;

  public SemanticNode() {
    this.parent = null;
  }

  public String evaluate(){
    return "FAIL";
  }

  public String getID(){
    return "";
  }

  public BranchType getType(){
    return this.type;
  }
  public void setType(BranchType t){
    this.type = t;
  }

  public void setPosition(int pos){
    if (this.getPosition() == -1){
      this.position = pos;
    }
  }

  public int getPosition(){
    return this.position;
  }

  public void setParent(SemanticNode newParent){
    this.parent = newParent;
  }

  public List<SemanticNode> getChildren() {
    return children;
  }

  public SemanticNode getChild(int pos){
    try{
      return this.children.get(pos);
    }catch(IndexOutOfBoundsException e){
      return new NullNode();
    }
  }

  public SemanticNode getParent() {
    return parent;
  }

  public void setChildren(){};

  public void addChild(SemanticNode child, SemanticNode p) {
    if( !(child instanceof NullNode) ){
      child.setParent(p);
      p.getChildren().add(child);
    }
  }

  public void takeChildren(SemanticNode old, SemanticNode p){
    for(SemanticNode each : old.getChildren()){
      p.addChild(each, p);
    }
  }

  public String graphTree(SemanticNode node){
    String content = "";

    node.setPosition(SemanticNode.counter-1);

    for (SemanticNode each : node.getChildren()) {
      each.setPosition(SemanticNode.counter);
      content += "  \""+node+" "+node.getPosition()+"\" -> \""+each+" "+each.getPosition()+"\";\n";
      SemanticNode.counter++;

    }

    //Call to its own children
    for (SemanticNode each : node.getChildren()) {
      /*content += "subgraph cluster_"+inc+"{\n"
                  +graphTree(each, num+inc, inc)
                  +"\n}";*/
      content += graphTree(each);
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
