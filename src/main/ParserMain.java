package src.main;

import src.parser.TableDrivenParser;
import src.scanner.Scanner;
import src.scanner.ScanException;

/*
Where we will can and run our Parser object
 */

public class ParserMain{

  private static boolean treeVisible;

  @SuppressWarnings("checkstyle:javadocmethod")
  /**
   *  <b>Runs the Parser</b>
   *
   *  @param args List of args, first being the
   *              path of file to Scan and Parse.
   */
  public static void main(String[] args) throws ScanException,
                                                Exception{

    TableDrivenParser parser;
    try{
      if(args[0].equals("-t")){
        treeVisible = true;
        parser = new TableDrivenParser( new Scanner( args[1] ));
      }else{
        parser = new TableDrivenParser( new Scanner( args[0] ));
      }
    }catch(Exception e){
      parser = new TableDrivenParser( new Scanner( args[0] ));
    }
    System.out.println(parser.parseProgram(treeVisible));
  }
}
