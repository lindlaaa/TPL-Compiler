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
  int curLine = 1;
  int curPos = 1;

  public Scanner(String filePath) throws ScanException{
    try{
    inputFile = new String(Files.readAllBytes(Paths.get(filePath)));
    }catch (Exception e){
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
  public void printTokenStrings(){
    for (Token individualToken : tokenArray){
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
      curPos++;
    }while (curIndex < inputFile.length());

    if(!accum.isEmpty()){
      if(accum.equals("false") || accum.equals("true")){
        tokenArray.add(new BoolToken(accum, curLine));
      }else if(Arrays.asList(keywordArray).contains(accum)){
        tokenArray.add(new KeywordToken(accum, curLine));
      }else{
        tokenArray.add(new IdentifierToken(accum, curLine));
      }
    }
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
  public void takeNextToken() throws ScanException
  {
    curChar = inputFile.charAt(curIndex);

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
          accum = "";
          switch (curChar)
          {
            case ';': case '.':
              tokenArray.add(new TerminatorToken(curChar, curLine));
              break;
            case '+': case '-': case '*':
            case '/': case '<': case '=':
              tokenArray.add(new OpToken(curChar, curLine));
              break;
            case '(': case ')': case ',': case ':':
              tokenArray.add(new PunctuationToken(curChar, curLine));
              break;
            case '{':
              tokenArray.add(new CommentToken(getComment(), curLine));
              break;
          }
        }else if(!Character.isWhitespace(curChar))
        {
          throw new ScanException(
                  "\n--HAD UNEXPECTED CHARACTER |" +curChar+ "| at\n"+
                  "  Scanner.java:ln:"+curLine+"col:"+curPos+"--\n");
        }
        if(curChar == ('\n'))
        {
          curLine++;
          curPos = 0;
        }
        curIndex++;
        break;
      case INTEGER:
        if(Character.isDigit(curChar))
        {
          accum += curChar;
        }else if(Character.isWhitespace(curChar))
        {
          if(curChar == ('\n'))
          {
            curLine++;
            curPos = 0;
          }
          tokenArray.add(new IntToken(accum, curLine));
          accum = "";
          currentState = LOOKING;
        }else if(symbolString.indexOf(curChar) != -1)
        {
          tokenArray.add(new IntToken(accum, curLine));
          accum = "";
          switch (curChar)
          {
            case ';': case '.':
              tokenArray.add(new TerminatorToken(curChar, curLine));
              break;
            case '+': case '-': case '*':
            case '/': case '<': case '=':
              tokenArray.add(new OpToken(curChar, curLine));
              break;
            case '(': case ')': case ',': case ':':
              tokenArray.add(new PunctuationToken(curChar, curLine));
              break;
            case '{':
              tokenArray.add(new CommentToken(getComment(), curLine));
              break;
          }
            currentState = LOOKING;
        }else
        {
          throw new ScanException(
                  "\n--HAD UNEXPECTED CHARACTER |" +curChar+ "| at\n"+
                  "  Scanner.java:ln:"+curLine+"col:"+curPos+"--\n");
        }
        curIndex++;
        break;
      case STRING:
        if(Character.isLetterOrDigit(curChar))
        {
          accum += curChar;
        }else if(Character.isWhitespace(curChar))
        {
          if(curChar == ('\n'))
          {
            curLine++;
            curPos = 0;
          }
          if(accum.equals("false") || accum.equals("true"))
          {
             tokenArray.add(new BoolToken(accum, curLine));
          }else if(Arrays.asList(keywordArray).contains(accum))
          {
              tokenArray.add(new KeywordToken(accum, curLine));
          }else
          {
              tokenArray.add(new IdentifierToken(accum, curLine));
          }
          accum = "";
          currentState = LOOKING;
        }else if(symbolString.indexOf(curChar) != -1)
        {
          if(accum.equals("false") || accum.equals("true"))
          {
             tokenArray.add(new BoolToken(accum, curLine));
          }else if(Arrays.asList(keywordArray).contains(accum))
          {
              tokenArray.add(new KeywordToken(accum, curLine));
          }else
          {
              tokenArray.add(new IdentifierToken(accum, curLine));
          }
          accum = "";
          switch (curChar)
          {
            case ';': case '.':
              tokenArray.add(new TerminatorToken(curChar, curLine));
              break;
            case '+': case '-': case '*':
            case '/': case '<': case '=':
              tokenArray.add(new OpToken(curChar, curLine));
              break;
            case '(': case ')': case ',': case ':':
              tokenArray.add(new PunctuationToken(curChar, curLine));
              break;
            case '{':
              tokenArray.add(new CommentToken(getComment(), curLine));
              break;
          }
          currentState = LOOKING;
        }else
        {
          throw new ScanException(
                  "\n--HAD UNEXPECTED CHARACTER |" +curChar+ "| at\n"+
                  "  Scanner.java:ln:"+curLine+" col:"+curPos+"--\n");
        }
        curIndex++;
        break;
    }
  }
}
