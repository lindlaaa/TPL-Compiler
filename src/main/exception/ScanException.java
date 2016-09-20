package src.main.exception;

//Last Modified: Avery 09.20


public class ScanException extends Exception{


  public ScanException( String s ){

    super( s );
  }


  public String toString(){

    return "FLAR SCAN EXCEPTION -- " + super.toString();
  }
}
