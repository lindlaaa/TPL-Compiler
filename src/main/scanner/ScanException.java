package src.main.scanner;
/**
 *
 */
public class ScanException extends Exception{


  public ScanException( String s ){

    super( s );
  }


  @Override
  public String toString(){

    return "FLAIR SCAN EXCEPTION -- " + super.toString();
  }
}
