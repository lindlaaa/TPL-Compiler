package src.parser;

import java.util.Stack;
import src.scanner.*;

public class TableDrivenParser extends Parser{

  private Parsetable flairTable;

  public TableDrivenParser(Scanner source) throws ScanException{

    super(source);
    flairTable = makeParsingTable();
  }


  public boolean parseProgram(){

    return true;
  }


  private Parsetable makeParsingTable(){

    return new Parsetable();
  }
}
