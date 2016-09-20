package src.main.scanner;
import src.main.exception;
import src.main.token;
import java.util.*;


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
    String symbolString = "+-*/<=(){},:;"; TODO
    String[] keywordArray = {"if", "then", "else"};//has more than this TODO
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

    //needs to be called by a loop until length is greater than curIndex
      //FIXME must not let keywords self-delemit. ex) if8 is an identifierTokennot keywordToken
      //FIXME add token only if we KNOW we have one
    //make sure not to ignore what was in the accum at the end of the
    public void takeNextToken(){
      do{
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
                {//terminator, punctToken, and opToken
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
                            tokenArray.add(new PunctToken(curChar));
                        default:
                            break;
                    }
                }else if(!Character.isWhitespace(curChar))
                {
                    //throw error
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
                            tokenArray.add(new PunctToken(curChar));
                    }
                    accum = "";
                    currentState = 0;//looking again
                }else
                {
                    //throw error
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
                            tokenArray.add(new PunctToken(curChar));
                        accum = "";
                        currentState = 0;//looking again
                    }
                }else
                {
                   //throw error
                }
                curIndex++;
                break;
        }


      }while( !str.isEmpty() );
    }

}
