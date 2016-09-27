package src.scanner;
import java.nio.file.*;
import java.util.*;


/**
 *This class serves as a state machine. Its constructor expects a
 *string as input. The input serves as a file path. If the files
 *contents can’t be retrieved as a string, a generic exception
 *is thrown. The scanner holds information about the current state
 *and what is accepted as tokens. The scanner’s methods create tokens,
 *fill the token array, and display the output
 *for tokens within the array.
 */
public class Scanner{


  public static final int LOOKING = 0;
  public static final int INTEGER = 1;
  public static final int STRING = 2;
  String symbolString = "+-*/<=(){.,:;";
  String[] keywordArray = {"if", "then", "else", "integer", "boolean",
    "true", "false", "not", "or", "and", "print", "program",
    "function", "return", "begin", "end"};

  int currentState = LOOKING;
  List<Token> tokenArray = new ArrayList<>();
  String accum = "";
  char curChar;
  String inputFile;
  int curIndex = 0;

  public Scanner(String filePath) throws ScanException{
      try{
      inputFile = new String(Files.readAllBytes(Paths.get(filePath)));
      } catch (Exception e)
      {
        throw new ScanException(" --FLAIR FILE READ EXCEPTION--\n");
      }
  }

  /**
   * There is no input because this method works off of the token
   * array that the other methods create.
   * A for loop iterates through each element of the array and
   * prints the token.
   * Although the array may carry different types of tokens,
   * the token interface enforced a toString() method.
   */
  public void printTokenStrings()
  {
      for (Token individualToken : tokenArray)
      {
         System.out.println(individualToken);
      }
  }

  /**
   * This method takes no input and may throw ScanException.
   * It iterates through every character of inputFile by repeatedly
   * calling takeNextToken().
   * After the file has been fully read, the accumulator may
   * still contain information.
   * This method checks to see if it is empty or not and
   * categorizes the accumulators content if necessary.
   */
  public void takeAllTokens() throws ScanException{
      do{
          takeNextToken();
      }
      while (curIndex < inputFile.length());

      if(!accum.isEmpty()){
          if(accum.equals("false") || accum.equals("true"))
          {
              tokenArray.add(new BoolToken(accum));
          }else if(Arrays.asList(keywordArray).contains(accum))
          {//need to add keywords to the array still
              tokenArray.add(new KeywordToken(accum));
          }/*else if(accum.isNumeric())TODO TODO TODO FIXME
          {
            tokenArray.add(new IntToken(accum));
          }*/else
          {
              tokenArray.add(new IdentifierToken(accum));
          }
      }
  }

  /**TODO FIXME
   *  This function returns the contents of a comment,
   *  returning an error if no end of comment symbol is found
   *
   *  @return String Returns the entirety of a comment
   */
  private String getComment() throws ScanException{
    String output = "";
    try{
      while(curChar != '}')
      {
        output += curChar;
        curIndex++;
        curChar = inputFile.charAt(curIndex);
      }
      return output + "}";
    }catch(Exception e){
      throw new ScanException(" --HIT E.O.F. WHEN " +
        "EXPECTING END COMMENT SYMBOL--\n");
    }
  }

  /**
   * The individual characters are categorized in this method.
   * It has no input parameter because it utilizes the scanner’s
   * attributes.
   * The code mirrors the DFA.
   * The creation of tokens depends on the current state and
   * what is read as the curChar. Unexpected characters will throw the
   * ScanException. It serves more as a helper function
   * to takeAllTokens().
   */
  public void takeNextToken() throws ScanException{
    curChar = inputFile.charAt(curIndex);

    switch (currentState){
      //TODO do we need a state for comment?
      case LOOKING:
          if(Character.isDigit(curChar)){
              accum += curChar;
              currentState = INTEGER;//integer
          }else if(Character.isLetter(curChar)){//boolToken, keywordToken, and identifierToken
              accum += curChar;
              currentState = STRING;
          }else if(symbolString.indexOf(curChar) != -1){//terminator, punctuationToken, and opToken
              accum = "";
              switch (curChar){//all symbols are self-delimiting
                  case ';': case '.':
                      tokenArray.add(new TerminatorToken(curChar));
                      break;
                  case '+': case '-': case '*':
                  case '/': case '<': case '=':
                      tokenArray.add(new OpToken(curChar));
                      break;
                  case '(': case ')': case ',': case ':': //FIXME
                      tokenArray.add(new PunctuationToken(curChar));
                      break;
                  case '{': //TODO FIXME
                      tokenArray.add(new CommentToken(getComment()));
                      break;
              }
          }else if(!Character.isWhitespace(curChar)){
            throw new ScanException(" --STATE: LOOKING, STARTED WITH |"+
              accum + "| HAD UNEXPECTED CHARACTER |"
              + curChar + "|--\n");
          }
          curIndex++;
          break;
      case INTEGER:
          if(Character.isDigit(curChar)){
              accum += curChar;
          }else if(Character.isWhitespace(curChar)){
              tokenArray.add(new IntToken(accum));
              accum = "";
              currentState = LOOKING;
          }else if(symbolString.indexOf(curChar) != -1){
              tokenArray.add(new IntToken(accum));
              accum = "";
              switch (curChar){
                  case ';': case '.':
                      tokenArray.add(new TerminatorToken(curChar));
                      break;
                  case '+': case '-': case '*':
                  case '/': case '<': case '=':
                      tokenArray.add(new OpToken(curChar));
                      break;
                  case '(': case ')': case ',': case ':':
                      tokenArray.add(new PunctuationToken(curChar));
                      break;
                  case '{': //TODO FIXME
                      tokenArray.add(new CommentToken(getComment()));
                      break;                              
              }
              currentState = LOOKING;
          }else{
              throw new ScanException(" --STATE: INTEGER, STARTED WITH |"+
                accum + "| HAD UNEXPECTED CHARACTER |"
                + curChar + "|--\n");
          }
          curIndex++;
          break;
      case STRING:
          if(Character.isLetterOrDigit(curChar)){
              accum += curChar;
          }else if(Character.isWhitespace(curChar)){
              if(accum.equals("false") || accum.equals("true")){
                 tokenArray.add(new BoolToken(accum));
              }else if(Arrays.asList(keywordArray).contains(accum)){
                  tokenArray.add(new KeywordToken(accum));
              }else{
                  tokenArray.add(new IdentifierToken(accum));
              }
              accum = "";
              currentState = LOOKING;
          }else if(symbolString.indexOf(curChar) != -1){
              if(accum.equals("false") || accum.equals("true")){
                 tokenArray.add(new BoolToken(accum));
              }else if(Arrays.asList(keywordArray).contains(accum)){
                  tokenArray.add(new KeywordToken(accum));
              }else{
                  tokenArray.add(new IdentifierToken(accum));
              }
              accum = "";
              switch (curChar){
                  case ';': case '.':
                      tokenArray.add(new TerminatorToken(curChar));
                      break;
                  case '+': case '-': case '*':
                  case '/': case '<': case '=':
                      tokenArray.add(new OpToken(curChar));
                      break;
                  case '(': case ')': case ',': case ':':
                      tokenArray.add(new PunctuationToken(curChar));
                      break;
                  case '{': //TODO FIXME
                      tokenArray.add(new CommentToken(getComment()));
                      break;
              }
              currentState = LOOKING;
          }else{
            throw new ScanException(" --STATE: STRING, STARTED WITH |"+
              accum + "| HAD UNEXPECTED CHARACTER |"
              + curChar + "|--\n");
          }
          curIndex++;
          break;
    }
  }
}
