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

  private static final int LOOKING = 0;
  private static final int INTEGER = 1;
  private static final int STRING = 2;
  private String symbolString = "+-*/<=(){.,:;";
  private String[] keywordArray = {"if", "then", "else", "integer",
    "boolean", "true", "false", "not", "or", "and", "print", "program",
    "function", "return", "begin", "end"};
  private int currentState = LOOKING;
  private List<Token> tokenArray = new ArrayList<>();
  private String accum = "";
  private char curChar;
  private String inputFile;
  private int curIndex = 0;
  private int curLine = 1;
  private int curPos = 1;

  public Scanner(String filePath) throws ScanException{
    try{
      inputFile = new String(Files.readAllBytes(Paths.get(filePath)));
    }catch (Exception e){
      throw new ScanException("--Flair file read error--\n");
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
  public void printTokenStrings(){
    for (Token individualToken : tokenArray){
      System.out.println(individualToken);
    }
  }

  @SuppressWarnings("checkstyle:javadocmethod")
  /**
   * This method takes no input and may throw ScanException.
   * It iterates through every character of inputFile by repeatedly
   * calling takeNextToken().
   * After the file has been fully read, the accumulator may
   * still contain information.
   * This method checks to see if it is empty or not and
   * categorizes the accumulators content if necessary.
   *
   *  @return List<Token> Represents the input file as a List of Tokens
   */
  public List<Token> takeAllTokens() throws ScanException{
    do{
      takeNextToken();
    }while (curIndex < inputFile.length());

    if(!accum.isEmpty()){
      if(accum.equals("false") || accum.equals("true")){
        tokenArray.add(new BoolToken(accum, curLine, curPos));
      }else if(Arrays.asList(keywordArray).contains(accum)){
        tokenArray.add(new KeywordToken(accum, curLine, curPos));
      }else if(isNumeric(accum)){
        tokenArray.add(new IntToken(accum, curLine, curPos));
      }else{
        tokenArray.add(new IdentifierToken(accum, curLine, curPos));
      }
    }
    tokenArray.add(new EOFToken(curLine));
    return tokenArray;
  }

  /**
   *  CHecks if a string is a number value
   *
   *  @param  inputString String version of a number
   *
   *  @return boolean Tells if a string can be converted to a integer
   */
  private boolean isNumeric(String inputString)
  {
    int length = inputString.length();
    if(length == 0){
      return false;
    }
    for (int i = 0; i < length; i++) {
      char tempChar = inputString.charAt(i);
      if (!Character.isDigit(tempChar)) {
        return false;
      }
    }
    return true;
  }

  /**
   *  This function returns the contents of a comment,
   *  returning an error if no end of comment symbol is found
   *
   *  @return String Returns the entirety of a comment
   */
  private String getComment() throws ScanException{
    String output = "";

    try{
      while(curChar != '}'){
        output += curChar;
        curIndex++;
        curPos++;
        if(curChar == ('\n'))
        {
          curLine++;
          curPos = 1;
        }
        curChar = inputFile.charAt(curIndex);
      }
      return output + '}';
    }catch(Exception e){
      throw new ScanException("--Hit end of file when " +
                  "expecting end-comment symbol--\n");
    }
  }

  /**
   *  This function creates tokens after we know we are looking for one.
   *  Called from the takeNextToken.
   */
  private void handleSymbols() throws ScanException{
    accum = "";
    switch (curChar){//all symbols are self-delimiting
      case ';': case '.':
        tokenArray.add(new TerminatorToken(curChar, curLine, curPos));
        break;
      case '+': case '-': case '*':
      case '/': case '<': case '=':
        tokenArray.add(new OpToken(curChar, curLine, curPos));
        break;
      case '(': case ')': case ',': case ':':
        tokenArray.add(new PunctuationToken(curChar, curLine, curPos));
        break;
      case '{':
        tokenArray.add(new CommentToken(getComment(), curLine, curPos));
        break;
    }
  }

  /**
   *  This function creates tokens after we know we are looking for one.
   *  Called from the takeNextToken
   */
  private void handleStrings() throws ScanException{
    if(accum.equals("false") || accum.equals("true")){
      tokenArray.add(new BoolToken(accum, curLine, curPos));
    }else if(Arrays.asList(keywordArray).contains(accum)){
      tokenArray.add(new KeywordToken(accum, curLine, curPos));
    }else{
      tokenArray.add(new IdentifierToken(accum, curLine, curPos));
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
  private void takeNextToken() throws ScanException {
    try{
      curChar = inputFile.charAt(curIndex);
    }catch(StringIndexOutOfBoundsException e){
      throw new ScanException("--File is empty. Get to programming!--");
    }
    switch (currentState)
    {
      case LOOKING:
        if(Character.isDigit(curChar))
        {
          accum += curChar;
          currentState = INTEGER;
        }else if(Character.isLetter(curChar))
        {
          accum += curChar;
          currentState = STRING;
        }else if(symbolString.indexOf(curChar) != -1)
        {
          handleSymbols();
        }else if(!Character.isWhitespace(curChar))
        {
          throw new ScanException(
                  "--Unexpected character |" +curChar+ "| @"+
                  " line:"+curLine+" col:"+curPos+"--\n" +
                "                                                   ^");
        }
        if(curChar == ('\n'))
        {
          curLine++;
          curPos = 1;
        }
        break;
      case INTEGER:
        if(Character.isDigit(curChar))
        {
          accum += curChar;
        }else if(Character.isWhitespace(curChar))
        {
          tokenArray.add(new IntToken(accum, curLine, curPos));
          if(curChar == ('\n'))
          {
            curLine++;
            curPos = 1;
          }
          accum = "";
          currentState = LOOKING;
        }else if(symbolString.indexOf(curChar) != -1)
        {
          tokenArray.add(new IntToken(accum, curLine, curPos));
          handleSymbols();
          currentState = LOOKING;
        }else
        {
          throw new ScanException(
                  "--Has unexpected character |" +curChar+ "| at"+
                  " line:"+curLine+" col:"+curPos+"--\n");
        }
        break;
      case STRING:
        if(Character.isLetterOrDigit(curChar))
        {
          accum += curChar;
        }else if(Character.isWhitespace(curChar))
        {
          handleStrings();
          if(curChar == ('\n'))
          {
            curLine++;
            curPos = 1;
          }
          accum = "";
          currentState = LOOKING;
        }else if(symbolString.indexOf(curChar) != -1)
        {
          handleStrings();
          handleSymbols();
          curPos++;
          currentState = LOOKING;
        }else
        {
          throw new ScanException(
                  "--Had unexpected character|" +curChar+ "| at"+
                  " line:"+curLine+" col:"+curPos+"--\n");
        }
    }
    curIndex++;
    curPos++;
  }
}
