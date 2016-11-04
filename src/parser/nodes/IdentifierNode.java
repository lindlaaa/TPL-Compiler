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
    return this.value.toString();
  }
  @Override
  public void typeCheck(){
    for(SemanticNode childNode : this.getChildren()){
		childNode.typeCheck();
		//check if children have assigned types
	}
	//assuming that our containsKey is working and only has the value as the key
	try{
      if(SymbolTable.table.containsKey(this.value)){
		//I assume the hashmap key is "x" instead of "Identifier x"
	    this.setNodeType(SymbolTable.table.get(this.value));
		
        /*should set BranchType to first item in the value array, assuming the value is a generic array		
		this.setNodeType(SymbolTable.table.get(this.value).get(0));
		*/
	  }
	}catch(Exception e) {	
      System.out.println("Exception thrown  :" + e);
    }	
  }
  @Override
  public String toString(){
    return "IdentifierNode "+this.value;
  }
}
