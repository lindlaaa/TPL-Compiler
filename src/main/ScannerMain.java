package src.main;

import src.scanner.Scanner;
import src.scanner.ScanException;

/**
 *  <b>This is the main class for the project,
 *  where we will run all of the compilation and testing</b>
 */
public class ScannerMain{


  @SuppressWarnings("checkstyle:javadocmethod")
  /**
   *  <b>Runs the Scanner</b>
   *
   *  @param args[] List of args, first being path of file to scan
   */
  public static void main(String[] args) throws ScanException{
    Scanner test = new Scanner(args[0]);

    test.takeAllTokens();
    test.printTokenStrings();
  }
}
