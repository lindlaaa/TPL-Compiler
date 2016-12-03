package src.parser.nodes;

import src.parser.*;
import src.scanner.IdentifierToken;

public class IdentifierNode extends SemanticNode{

  IdentifierToken value;

  @Override
  public void setChildren(){
    //this.addChild((String)TableDrivenParser.semanticBuffer.pop());FIXME
    this.value = (IdentifierToken)TableDrivenParser.semanticBuffer.pop();
  }

  @Override
  public String getID(){
    return this.value.getVal();
  }
  /*
  @Override
  public void typeCheck(){
    try{
      if(SymbolTable.table.containsKey(this.value)){
        this.setNodeType(SymbolTable.table.get(this.value));
        //could set BranchType to first item in the value array, assuming the value is a generic array
        //this.setNodeType(SymbolTable.table.get(this.value).get(0));
       }
	}catch(Exception e) {
      System.out.println("Exception thrown  :" + e);
    }
  }
  */

  @Override
  public String toString(){
    return "Identifier "+this.getID();
  }
}
