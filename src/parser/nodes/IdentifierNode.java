package src.parser.nodes;

import src.parser.*;
import src.scanner.IdentifierToken;
import src.parser.symboltable.*;
import src.codegen.Generator;
import java.util.ArrayList;
//import src.codegen.Project5; //TODO FIXME


@SuppressWarnings("unchecked")
public class IdentifierNode extends SemanticNode{

  IdentifierToken value;

  @Override
  public void setChildren(){
    //this.addChild((String)TableDrivenParser.semanticBuffer.pop());FIXME
    this.value = (IdentifierToken)TableDrivenParser.semanticBuffer.pop();
  }


  @Override
  public String evaluate(){

    Symbol sym = SymbolTable.get(this.getID());
    ArrayList paramArray = new ArrayList();
    String temp = "";

    if( sym.getIsFunction() ){ //Function ID
      temp = Generator.newTemp();

      for(int i = 0; i < sym.getNumOfArgs(); i++){ //Evaluate each child
        temp = this.getChild(i).evaluate();
        paramArray.add(temp);
      }

      temp = Generator.newTemp(); //TODO FIXME not new temp but reference the most receltly made temp
      Generator.emitFunctionCall(this.getID(), paramArray, temp);
      return temp;
    }else{ //Leaf: Variable ID
      temp = Generator.newTemp();
      //Project5.testTable.setVal(temp,Integer.MIN_VALUE); //TODO FIXME
      Generator.emit("assign",this.getID(),temp);
      return temp;

    }
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
