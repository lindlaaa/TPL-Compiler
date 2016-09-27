package src.main;

import src.scanner.*;

/**
 *  <b>This is the main class for the project,
 *  where we will run all of the compilation and testing</b>
 */
public class Compiler{


  /**
   *  <b>Runs the compiler</b>
   *
   *  @param args[] List of args, first being path of file to compile
   */
  public static void main(String[] args) throws ScanException{
    Scanner test = new Scanner(args[0]);

    test.takeAllTokens();
    test.printTokenStrings();
  }
}
