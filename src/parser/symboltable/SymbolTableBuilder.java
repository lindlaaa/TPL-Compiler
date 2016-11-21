package src.parser.symboltable;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import src.parser.nodes.*;
import src.scanner.Token;
import src.scanner.*;
import src.parser.Parser;

@SuppressWarnings("unchecked")
public class SymbolTableBuilder{

  private HashMap       testHash;
  private SemanticNode  program;
  private SymbolTable   table;
  private List<Token>   tokenArray;
  private Parser        parser;

  public SymbolTableBuilder(Parser p){
    testHash = new HashMap();
    tokenArray = p.getTokenArray();
  }

  public void buildTable(SemanticNode q){
    for(SemanticNode each : q.getChildren()){
      ArrayList parts = new ArrayList();
      if(each instanceof FormalNode){
        parts.add(each.getChildren().get(0));
        testHash.put(each.getChildren().get(1).toString(),parts);
      }else if(each instanceof DefNode){
        parts.add(each.getChildren().get(0));
        testHash.put(each.getChildren().get(3).toString(),parts);
      }
      this.buildTable(each);
    }
  }

  public void populatePairs(){
    ArrayList parts;
    System.out.println("\n\n\nIdentifier Reference Locations:");//FIXME
    for(Token each : tokenArray){
      if(each instanceof IdentifierToken){ //If ID
        System.out.println(each.getLexicalPair()+"-"+each.toString());
        if(testHash.containsKey(each.toString())){ //If known //RESUME FIXME FIXME
          System.out.println("inside");
          parts = (ArrayList)testHash.get(each.toString());
          parts.add(each.getLexicalPair());
          testHash.replace(each.toString(), parts);
        }
      }
    }
  }

  public HashMap getTable(){
    return this.testHash;
  }

  public void printMap(){
    System.out.println("\n\n\nSymbol Table Entries:");//FIXME
    for(Object each : this.testHash.entrySet()){
      //System.out.println(each);//FIXME
      System.out.println(testHash.entrySet());
    }
  }
}
