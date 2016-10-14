package src.parser;

import src.scanner.*;
import java.util.ArrayList;
import java.util.List;
/*
Main container of our Parser
 */
public class Parser{

  private Scanner source;
  List<Token> tokenArray = new ArrayList<>();
  int curPos;
  Token curToken;
  Token peekToken;

  public Parser(Scanner source) throws ScanException{

    this.source = source;
    tokenArray = this.source.takeAllTokens();
    tokenArray.add(new EOFToken());
    curPos = 0;
    curToken = tokenArray.get(curPos);
    peekToken = tokenArray.get(curPos+1);
  }
}
