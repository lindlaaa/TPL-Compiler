

//Last Modified: Avery 09.20


public class ParseException extends Exception{


  public ParseException( String s ){

    super( s );
  }


  public String toString(){

    return "FLAR PARSE EXCEPTION -- " + super.toString();
  }
}
