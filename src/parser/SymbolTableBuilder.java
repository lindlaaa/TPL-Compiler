package src.parser;

import java.util.*;
import src.parser.nodes.*;

public class SymbolTableBuilder{

  private SemanticNode program;
  private SymbolTable table;

  public SymbolTableBuilder(SemanticNode p){

    program = p;
    System.out.println("\n\n\n\n");
  }

  public void buildTable(SemanticNode q){

    for(SemanticNode each : q.getChildren()){

      if(each instanceof FormalNode){
        System.out.println(each.getChildren().get(0));
        System.out.println(each.getChildren().get(1));
        System.out.println("\n");
      }else if(each instanceof DefNode){
        System.out.println(each.getChildren().get(1));
        System.out.println(each.getChildren().get(3));
        System.out.println("\n");
      }
      this.buildTable(each);
    }
  }
}
