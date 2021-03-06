package src.main;

import java.io.File;
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

    //TableDrivenParser parser;
    String fileName;
    try{
      if(args[0].equals("-t")){
        treeVisible = true;
        fileName = args[1];
      }else{
        fileName = args[0];
      }
    }catch(Exception e){
      fileName = args[0];
    }

    File file = new File(fileName);
    String name = file.getName();
    name = name.substring(0, name.length()-4);
    //System.out.println("Program name:\n" + name); //FIXME

    TableDrivenParser parser = new TableDrivenParser( new Scanner( fileName ));
    System.out.println(parser.parseProgram(treeVisible, name)); //FIXME ?
  }
}
