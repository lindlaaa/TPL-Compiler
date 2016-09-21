/*
  Scanner datatype definition
  Last Modified: Avery 09.20
*/

import java.nio.file.*; 
import java.util.*; 

//state machine
public class Scanner{
    public static final int LOOKING = 0;
    public static final int INTEGER = 1;
    public static final int STRING = 2;
    String symbolString = "+-*/<=(){},:;";
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
    
    public void printTokenStrings()
    {
        for (Token individualToken : tokenArray)
        {
           System.out.println(individualToken);
        }
    }
    
    public void takeAllTokens(){
        do
        {
            takeNextToken();
        }
        while (curIndex <= inputFile.length());
        
        /*
        accum may not be empty. If the program doesnt end with a 
        whitespace, self delimitingsymbol, or semicolon the accum would
        not have been reset to ""
        */
        if(!accum.isEmpty())
        {
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
        }
    }
    
    public void takeNextToken(){
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
                    ScanException e = new ScanException(" --STATE 0 " +
                      "HAD UNEXPECTED CHARACTER-- ");
                    accum = "";
                    currentState = LOOKING;
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
                    currentState = LOOKING;
                }else
                {
                    ScanException e = new ScanException(" --STATE 1 " +
                      "HAD UNEXPECTED CHARACTER-- ");
                    accum = "";
                    currentState = LOOKING;
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
                        currentState = LOOKING;
                    }
                }else
                {
                   ScanException e = new ScanException(" --STATE 2 " +
                    "HAD UNEXPECTED CHARACTER-- ");
                }
                curIndex++;
                break;
        }
    }
}
