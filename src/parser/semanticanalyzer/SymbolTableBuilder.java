package src.parser.semanticanalyzer;

import java.util.HashMap;
import java.util.List;
import src.parser.nodes.*;
import src.scanner.Token;
import src.scanner.IdentifierToken;
import src.parser.Parser;

@SuppressWarnings("unchecked")
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
  }

  public void populatePairs(){
    System.out.println("\n\n\nIdentifier Reference Locations:");//FIXME
    for(Token each : tokenArray){
      if(each instanceof IdentifierToken){
        System.out.println(each.getLexicalPair()+"-"+each);
      }
    }/*
    for(Token each : tokenArray){
      //TODO
    }*/
  }

  public HashMap getTable(){
    return this.testHash;
  }

  public void printMap(){
    System.out.println("\n\n\nSymbol Table Entries:");//FIXME
    for(Object each : this.testHash.entrySet()){
      System.out.println(each);//FIXME
    }
  }
}
