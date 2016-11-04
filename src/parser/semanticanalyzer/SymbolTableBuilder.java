package src.parser.semanticanalyzer;

import java.util.HashMap;
import java.util.List;
import src.parser.nodes.*;
import src.scanner.Token;
import src.parser.Parser;

public class SymbolTableBuilder{

  private HashMap testHash;
  private SemanticNode program;
  private SymbolTable table;
  private List<Token> tokenArray;
  private Parser parser;

  public SymbolTableBuilder(Parser p){
    testHash = new HashMap();
    tokenArray = p.getTokenArray();
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
    //this.populatePairs();
  }

  public void populatePairs(){
    //System.out.println("\n\n\nPairs");//FIXME
    /*for(Token each : tokenArray){
      System.out.println(each.getLexicalPair()+"-"+each);
    }
    for(Token each : tokenArray){
      //TODO
    }*/
  }

  public HashMap getTable(){
    return this.testHash;
  }

  public void printMap(){
    //System.out.println("\n\n\nSymbol Table:");//FIXME
    for(Object each : this.testHash.entrySet()){
      //System.out.println(each);//FIXME
    }
  }
}
