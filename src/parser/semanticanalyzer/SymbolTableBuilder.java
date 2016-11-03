package src.parser.semanticanalyzer;

import java.util.*;
import src.parser.nodes.*;

public class SymbolTableBuilder{

  private HashMap testHash;
  private SemanticNode program;
  private SymbolTable table;

  public SymbolTableBuilder(){
    testHash = new HashMap();
  }

  public HashMap getTable(){
    return this.testHash;
  }

  public void printMap(){
    System.out.println("\n\n\nSymbol Table:");
    for(Object each : this.testHash.entrySet()){
      System.out.println(each);
    }
  }

  public void buildTable(SemanticNode q){

    for(SemanticNode each : q.getChildren()){

      if(each instanceof FormalNode){
        //System.out.println(each.getChildren().get(1));
        //System.out.println(each.getChildren().get(0));
        testHash.put(each.getChildren().get(1).getID(),each.getChildren().get(0));
        //System.out.println("\n");
      }else if(each instanceof DefNode){
        //System.out.println(each.getChildren().get(3));
        //System.out.println(each.getChildren().get(1));
        testHash.put(each.getChildren().get(3).getID(),each.getChildren().get(1));
        //System.out.println("\n");
      }
      this.buildTable(each);
    }
  }
}
