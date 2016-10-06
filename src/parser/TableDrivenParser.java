package src.parser;

import java.util.Stack;
import src.scanner.*;

public class TableDrivenParser extends Parser{

  private Parsetable flairTable;

  public TableDrivenParser(Scanner source) throws ScanException,
                                                  Exception{

    super(source);
    flairTable = makeParsingTable();
  }


  public boolean parseProgram(){
    //TODO
    //Add pseudo code from Slack here
    //FIXME
    return true;
  }


  private Parsetable makeParsingTable() throws ScanException,
                                                Exception{

    Parsetable table = new Parsetable();

    //Make the parse table on google sheets
    //Declare and Add the rules to the table here
    ParseRule rule00 = new PushNull();

    table.add( NonTerminal.Program, new KeywordToken("Program", 1), rule00 );

    //return the table to use in parseProgram()
    return table;
  }
}
