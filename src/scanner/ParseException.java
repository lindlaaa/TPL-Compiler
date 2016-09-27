package src.scanner;
/**
 * This class extends the Exception class.
 * When this exception is thrown, it will return a string
 * to explain the error.
 */
public class ParseException extends Exception{

  /**
   *  The constructor calls the super class’ constructor.
   *
   *  @param  s A string is expected that describes the error
   */
  public ParseException( String s ){

    super( s );
  }

  /**
   *  When this exception is thrown, it will return a string
   *  to explain the error.
   *
   *  @return String  A string to explain the error.
   */
  @Override
  public String toString(){

    return "FLAR PARSE EXCEPTION -- " + super.toString();
  }
}
