

//Last Modified: Avery 09.20


public class LexicalException extends Exception{


  public LexicalException( String s ){

    super( s );
  }


  public String toString(){

    return "FLAIR LEXICAL EXCEPTION -- " + super.toString();
  }
}
