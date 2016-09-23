
/**
 *
 */
public class ScanException extends Exception{


  public ScanException( String s ){

    super( s );
  }


  @Override
  public String toString(){

    return "--A FLAIR SCAN EXCEPTION--\n" + super.toString();
  }
}
