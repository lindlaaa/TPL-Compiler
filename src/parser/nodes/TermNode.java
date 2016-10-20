package src.parser.nodes;

import src.parser.*;

public class TermNode extends SemanticNode{
  @Override
  public void setChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof FactorIfNode){	
      this.addChild((FactorIfNode)TableDrivenParser.semanticStack.pop());	
	}else if(TableDrivenParser.semanticStack.peek() instanceof FactorNotNode){
      this.addChild((FactorNotNode)TableDrivenParser.semanticStack.pop());		
	}else if(TableDrivenParser.semanticStack.peek() instanceof FactorIDNode){
      this.addChild((FactorIDNode)TableDrivenParser.semanticStack.pop());
	}
	  	  
    if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeAndNode){
	  this.addChild((TermPrimeAndNode)TableDrivenParser.semanticStack.pop());	
	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeTimesNode){
	  this.addChild((TermPrimeTimesNode)TableDrivenParser.semanticStack.pop());	
	}else if(TableDrivenParser.semanticStack.peek() instanceof TermPrimeDivideNode){
      this.addChild((TermPrimeDivideNode)TableDrivenParser.semanticStack.pop());
    }
  }

  @Override
  public String toString(){
    return "TermNode";
  }
}
