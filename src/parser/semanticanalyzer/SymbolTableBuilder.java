package src.parser.semanticanalyzer;

import java.util.*;
import src.parser.nodes.*;
import src.scanner.*;
import src.parser.*;

public class SymbolTableBuilder{

  private HashMap testHash;
  private SemanticNode program;
  private SymbolTable table;
  private List<Token> tokenArray;
  private Parser parser;

  public SymbolTableBuilder(Parser p){
    testHash = new HashMap();
    tokenArray = p.getTokenArray();
    //System.out.println(tokenArray);
    for(Token each : tokenArray){
      System.out.println(each.getLexicalPair()+"-"+each);
    }
    this.populatePairs();
  }

  public void populatePairs(){
    for(Token each : tokenArray){
      if(each instanceof IdentifierToken){
        String test = each.toString();

          //System.out.println(test.getClass());
          //System.out.println(test.toString().length());
        if(testHash.containsKey(test)){
          //System.out.println( "\nwinner winner");
        }else{
          //System.out.println(test);
          //System.out.println("\nLoser loser");
        }
      }
    }
  }

  public HashMap getTable(){
    return this.testHash;
  }

  public void printMap(){
    //System.out.println("\n\n\nSymbol Table:");
    for(Object each : this.testHash.keySet()){
      //System.out.println(each);
      //System.out.println(each.toString().length());
      //System.out.println(each.getClass());
    }
  }

  public void buildTable(SemanticNode q){

    for(SemanticNode each : q.getChildren()){

      if(each instanceof FormalNode){
        testHash.put(each.getChildren().get(1).getID(),each.getChildren().get(0));
      }else if(each instanceof DefNode){
        testHash.put(each.getChildren().get(3).getID(),each.getChildren().get(1));
      }
      this.buildTable(each);
    }
  }
}
