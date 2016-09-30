package src.parser;

import src.scanner.*;
import java.util.ArrayList;
import java.util.List;
/*
Main container of our Parser
 */
public class Parser{

  private Scanner source;
  private List<Token> stack = new ArrayList<>();

  public Parser(Scanner source) throws ScanException{

    this.source = source;
    stack = this.source.takeAllTokens();
  }

  //TODO
}
