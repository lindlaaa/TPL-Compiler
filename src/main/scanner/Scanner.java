

/*
  Scanner datatype definition
  Last Modified: Avery 09.20
*/

package src.main.scanner;
import java.nio.file.*;
import java.util.*;


/**
 *
 */
public class Scanner{


    public static final int LOOKING = 0;
    public static final int INTEGER = 1;
    public static final int STRING = 2;
    String symbolString = "+-*/<=(){}.,:;";
    String[] keywordArray = {"if", "then", "else", "integer", "boolean",
      "true", "false", "not", "or", "and", "print", "program",
      "function", "return", "begin", "end"};

    int currentState = LOOKING;
    List<Token> tokenArray = new ArrayList<>();
    String accum = "";
    char curChar;
    String inputFile;
    int curIndex = 0;

    public Scanner(String filePath){
        try{
        inputFile = new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (Exception e)
        {
            System.err.println("IOException");
        }
    }

    /**
     * [printTokenStrings description]
     */
    public void printTokenStrings()
    {
        for (Token individualToken : tokenArray)
        {
           System.out.println(individualToken);
        }
    }

    /**
     * Sets a loop that gets all tokens from the input string.
     * Then makes sure accum is accounted for as a Token.
     * @throws ScanException
     */
    public void takeAllTokens() throws ScanException{
        do
        {
            takeNextToken();
        }
        while (curIndex < inputFile.length());

        if(!accum.isEmpty())
        {
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

    /**
     * [takeNextToken description]
     */
    public void takeNextToken() throws ScanException{
        curChar = inputFile.charAt(curIndex);

        switch (currentState)
        {
            case LOOKING:
                if(Character.isDigit(curChar))
                {
                    accum += curChar;
                    currentState = INTEGER;//integer
                }else if(Character.isLetter(curChar))
                {//boolToken, keywordToken, and identifierToken
                    accum += curChar;
                    currentState = STRING;
                }else if(symbolString.indexOf(curChar) != -1)
                {//terminator, punctuationToken, and opToken
                    switch (curChar)
                    {//all symbols are self-delimiting
                        case ';': case '.':
                            tokenArray.add(new TerminatorToken(curChar));
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                            break;
                    }
                }else if(!Character.isWhitespace(curChar))
                {
                  throw new ScanException(" --STATE INTEGER STARTED WITH |"+
                    accum + "| HAD UNEXPECTED CHARACTER |"
                    + curChar + "|--");
                }
                curIndex++;
                break;
            case INTEGER:
                if(Character.isDigit(curChar))
                {
                    accum += curChar;
                }else if(Character.isWhitespace(curChar))
                {
                    tokenArray.add(new IntToken(accum));
                    accum = "";
                    currentState = LOOKING;
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    tokenArray.add(new IntToken(accum));
                    switch (curChar)
                    {
                        case ';': case '.':
                            tokenArray.add(new TerminatorToken(curChar));
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                            break;
                    }
                    accum = "";
                    currentState = LOOKING;
                }else
                {
                    throw new ScanException(" --STATE INTEGER STARTED WITH |"+
                      accum + "| HAD UNEXPECTED CHARACTER |"
                      + curChar + "|--");
                }
                curIndex++;
                break;
            case STRING:
                if(Character.isLetterOrDigit(curChar))
                {
                    accum += curChar;
                }else if(Character.isWhitespace(curChar))
                {
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum));
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }
                    accum = "";
                    currentState = LOOKING;
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum));
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }
                    switch (curChar)
                    {
                        case ';': case '.':
                            tokenArray.add(new TerminatorToken(curChar));
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                            break;
                    }
                    accum = "";
                    currentState = LOOKING;
                }else
                {
                  throw new ScanException(" --STATE INTEGER STARTED WITH |"+
                    accum + "| HAD UNEXPECTED CHARACTER |"
                    + curChar + "|--");
                }
                curIndex++;
                break;
        }
    }

    public static void main(String[] args) throws ScanException{
      Scanner test = new Scanner("palindrome.flr");

      test.takeAllTokens();
      test.printTokenStrings();
    }


}
