package src.main;

import src.scanner.*;

//Last Modified: Avery 09.11


public class Compiler{

  public static void main(String[] args) throws ScanException{
    Scanner test = new Scanner(args[0]);

    test.takeAllTokens();
    test.printTokenStrings();
  }
}
