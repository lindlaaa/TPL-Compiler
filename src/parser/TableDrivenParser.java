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
    //TODO
    //Add pseudo code from Slack here
    //FIXME
    return true;
  }


  private Parsetable makeParsingTable(){
    //Make the parse table on paper
    //Declare and Add the rules to the table here
    //return the table to use in parseProgram()
    return new Parsetable();
  }
}
