/*
  Token datatype definition
  Last Modified: Avery 09.11
*/

public class Token{

  private int tType;
  private String sValue;
  private int intValue;
  private boolean boolValue;

  //OPERATOR, PUNCTUATION???FIXME
  public Token(int t){
    this(t, NULL, 0, NULL);
  }

  //IDENTIFIER, KEYWORD
  public Token(int t, String s){
    this(t, s, 0, NULL);
  }

  //INTEGER
  public Token(int t, int i){
    this(t, NULL, i, NULL);
  }

  //BOOLEAN
  public Token(int t, boolean b){
    this(t, b.toString(), 0, b);
  }

  private Token(int t, String s, int i, boolean b){
    tType = t;
    sValue = s;
    intValue = i;
    boolValue = b;
  }

  public int type(){ return tType; }
  public String sValue(){ return sValue; }
  public int intValue(){ return intValue; }
  public boolean boolValue(){ return boolValue; }

  //toString method here
  @Override
  public String toString(){

    if( type() < 10 ){
      return "operator " + sValue();
    } else if( type() == 11 ){
      //do same with keyword, integerm boolean, punctuation, identifier
    }
    return "EOF" //tells we are at end of file input
  }
}
