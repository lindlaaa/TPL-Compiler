package src.main;

import src.parser.TableDrivenParser;
import src.scanner.Scanner;
import src.scanner.ScanException;

/*
Where we will can and run our Parser object
 */

public class ParserMain{

  @SuppressWarnings("checkstyle:javadocmethod")
  /**
   *  <b>Runs the Parser</b>
   *
   *  @param args List of args, first being the
   *              path of file to Scan and Parse.
   */
  public static void main(String[] args) throws ScanException,
                                                Exception{

    TableDrivenParser parser = new TableDrivenParser( new Scanner( args[0] ));

    parser.parseProgram();
  }
}
