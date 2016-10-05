package src.parser;

import java.util.Stack;

public class TableDrivenParser extends Parser{

  private Parsetable flairTable;

  public TableDrivenParser(Scanner source){

    super(source);
    flairTable = makeParsingTable();
  }


  public boolean parseProgram(){

    //TODO
  }


  private void makeParsingTable(){

    //TODO
  }
}
