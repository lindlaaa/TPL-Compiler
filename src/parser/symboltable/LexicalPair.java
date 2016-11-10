package src.parser.symboltable;

public class LexicalPair{

  private int line;
  private int pos;

  public LexicalPair(int left,int right) {
    this.line = left;
    this.pos = right;
  }

  @Override
  public String toString(){
    return "["+this.line +", "+ this.pos +"]";
  }

  public int getLine() {
	return this.line;
  }
  public int getPos() {
    return this.pos;
  }

}
