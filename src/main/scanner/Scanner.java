


/*
  Scanner datatype definition
  Last Modified: Avery 09.20
*/


//state machine
public class Scanner{
    public static final int looking = 0;
    public static final int integer = 1;
    public static final int string = 2;

    int currentState = 0;
    String symbolString = "+-*/<=(){},:;";
    String[] keywordArray = {"if", "then", "else", "integer", "boolean",
      "true", "false", "not", "or", "and", "print", "program",
      "function", "return", "begin", "end"};
    List<Token> tokenArray = new ArrayList<>();
    String accum = "";
    char curChar;
    String inputFile;
    int curIndex = 0;
    //TODO Do we need a lookahead variable with this implementation?

    public Scanner(String fileString){
        inputFile = fileString;
    }
    public void printTokenStrings()
    {
        for (Token individualToken : tokenArray)
        {
           System.out.println(individualToken);
        }
    }


    //make sure not to ignore what was in the accum at the end of the
    public void takeNextToken(){
      do{ //while we havent made a new token, DO
        curChar = inputFile.charAt(curIndex);
        switch (currentState)
        {
            case 0: //looking
                if(Character.isDigit(curChar))
                {//intToken
                    accum += curChar;
                    currentState = 1;//integer
                }else if(Character.isLetter(curChar))
                {//boolToken, keywordToken, and identifierToken
                    accum += curChar;
                    currentState = 2;//string
                }else if(symbolString.indexOf(curChar) != -1)
                {//terminator, punctuationToken, and opToken
                    switch (curChar)
                    {//all symbols are self-delimiting
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                        default:
                            break;
                    }
                }else if(!Character.isWhitespace(curChar))
                {
                    ScanException e = new ScanException(" --STATE 0,
                      HAD UNEXPECTED CHARACTER-- ");
                    accum = "";
                    currentState = 0;//looking again
                }
                curIndex++;
                break;
            case 1: //integer state
                if(Character.isDigit(curChar))
                {
                    accum += curChar;
                }else if(Character.isWhitespace(curChar))
                {
                    tokenArray.add(new IntToken(accum));
                    accum = "";
                    currentState = 0;//looking again
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    tokenArray.add(new IntToken(accum));
                    switch (curChar)
                    {//the symbols are self-delimiting
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                    }
                    accum = "";
                    currentState = 0;//looking again
                }else
                {
                    ScanException e = new ScanException(" --STATE 1,
                      HAD UNEXPECTED CHARACTER-- ");
                    accum = "";
                    currentState = 0;//looking again
                }
                curIndex++;
                break;
            case 2: //string state
                if(Character.isLetterOrDigit(curChar))
                {
                    accum += curChar;
                }else if(Character.isWhitespace(curChar))
                {
                    //boolToken, keywordToken, or identifierToken?
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum));
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {//need to add keywords to the array still
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }
                    accum = "";
                    currentState = 0;//looking again
                }else if(symbolString.indexOf(curChar) != -1)
                {
                    //boolToken, keywordToken, or identifierToken?
                    if(accum.equals("false") || accum.equals("true"))
                    {
                       tokenArray.add(new BoolToken(accum));
                    }else if(Arrays.asList(keywordArray).contains(accum))
                    {//need to add keywords to the array still
                        tokenArray.add(new KeywordToken(accum));
                    } else
                    {
                        tokenArray.add(new IdentifierToken(accum));
                    }
                    //boolToken, keywordToken, and identifierToken?
                    switch (curChar)
                    {//the symbols are self-delimiting
                        case ';':
                            tokenArray.add(new TerminatorToken());
                            break;
                        case '+': case '-': case '*':
                        case '/': case '<': case '=':
                            tokenArray.add(new OpToken(curChar));
                            break;
                        case '(': case ')': case '{':
                        case '}': case ',': case ':':
                            tokenArray.add(new PunctuationToken(curChar));
                        accum = "";
                        currentState = 0;//looking again
                    }
                }else
                {
                   ScanException e = new ScanException(" --STATE 2,
                    HAD UNEXPECTED CHARACTER-- ");
                }
                curIndex++;
                break;
        }


      }while( !accum.isEmpty() );
    }

}
