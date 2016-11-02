//package src.parser;

public class Pair{

  private int line;
  private int pos;

  public Pair(int left,int right) {
    this.line = left;
    this.pos = right;
  }

  public int getLine() { 
	return this.line; 
  }
  public int getPos() {
    return this.pos; 
  }
}