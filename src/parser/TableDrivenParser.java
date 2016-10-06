package src.parser;

import java.util.Stack;
import src.scanner.*;

public class TableDrivenParser extends Parser{

  private Parsetable flairTable;
  private Stack stack = new Stack();

  public TableDrivenParser(Scanner source) throws ScanException,
                                                  Exception{

    super(source);
    flairTable = makeParsingTable();
  }

  @SuppressWarnings("unchecked")
  public boolean parseProgram(){
    stack.push(new EOFToken(1)); //push EOF onto stack
    stack.push(NonTerminal.Program);//push start symbol onto stack

    //Main loop
    do{
      System.out.println("insode while loop");
    }while((stack.peek() instanceof EOFToken) == false);

    System.out.println(stack);
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
