package src.parser;

@SuppressWarnings("unchecked")
public class  SemanticNode
{
  private SemanticAction nodeType;
  private Tree tree;

  public SemanticNode( SemanticAction inputNodeType )
  {  
    int tempInt = inputNodeType.getSemanticActionNumber();
    switch(tempInt){
      case 1: this.nodeType = new ProgramNode();
	    break;
      case 2: this.nodeType = new DefinitionsNode();
        break;
      case 3: this.nodeType = new DefNode();
	    break;
      case 4: this.nodeType = new FormalsNode();
	    break;
      case 5: this.nodeType = new NonEmptyFormalsNode();
        break;
      case 6: this.nodeType = new NonEmptyFormalsPrimeNode();
	    break;
      case 7: this.nodeType = new FormalNode();
	    break;
      case 8: this.nodeType = new BodyNode();
        break;
      case 9: this.nodeType = new StatementListPSNode();
	    break;
      case 10: this.nodeType = new StatementListReturnNode();
	    break;
      case 11: this.nodeType = new TypeIntegerNode();
	    break;		
      case 12: this.nodeType = new TypeBooleanNode();
        break;
      case 13: this.nodeType = new ExprNode();
	    break;
      case 14: this.nodeType = new ExprPrimeLTNode();
	    break;
      case 15: this.nodeType = new ExprPrimeExprNode();
        break;
      case 16: this.nodeType = new SimpleExprNode();
	    break;
      case 17: this.nodeType = new SimpleExprPrimeOrNode();
	    break;
      case 18: this.nodeType = new SimpleExprPrimePlusNode();
        break;
      case 19: this.nodeType = new SimpleExprPrimeMinusNode();
	    break;
      case 20: this.nodeType = new TermNode();
        break;		
      case 21: this.nodeType = new TermPrimeAndNode();
	    break;
      case 22: this.nodeType = new TermPrimeTimesNode();
        break;
      case 23: this.nodeType = new TermPrimeDivideNode();
	    break;
      case 24: this.nodeType = new FactorIfNode();
	    break;
      case 25: this.nodeType = new FactorNotNode();
        break;
      case 26: this.nodeType = new FactorIDNode();
	    break;
      case 27: this.nodeType = new IfIntLiteralNode();
	    break;
      case 28: this.nodeType = new IfIntMinusNode();
        break;
      case 29: this.nodeType = new IfIntLPNode();
	    break;
      case 30: this.nodeType = new IdentifierPrimeLP();
	    break;		
      case 31: this.nodeType = new ActualsNode();
	    break;
      case 32: this.nodeType = new NonEmptyActualsNode();
        break;
      case 33: this.nodeType = new NonEmptyActualsPrimeNode();
	    break;
      case 34: this.nodeType = new LiteralNumberNode();
	    break;
      case 35: this.nodeType = new LiteralBooleanNode();
        break;
      case 36: this.nodeType = new PrintStatementNode();
	    break;
      case 37: this.nodeType = new NumberNode();
	    break;
      case 38: this.nodeType = new BooleanNode();
        break;
      case 39: this.nodeType = new IdentifierNode();
	    break;
		}		

    createTree();
  }

  //Init the tree this node contains
  private void createTree(){
    tree = new Tree(this);
  }
  
  public SemanticAction getNodeType(){
	  return this.nodeType;
  }

  //recieves input node from the top of the semantic stack.
	public void addElement(SemanticNode childNode)
	{
	  this.tree.addLeaf(childNode);
	}

    //@Override
    //public String toString(){
	//  String tempString;
	//  for(int i=0; i < nodeBody.length(); i++)
	//  {
	//	  tempstring += nodeBody.pop().toString();
	//  }
	//  return tempString;
    //}
}

class ProgramNode{
  private ruleList[] = {new IdentifierNode(),
			new FormalsNode(),
                        new DefinitionsNode(),
                        new BodyNode()
                      };	
  private void getChildren(){  
    for(int i = 0; i < rulesList.length(); i++){
      if(semanticStack.peek().getNodeType() instanceof rulesList[i]){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      }
    }
  }
}
//---
class DefinitionsNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof DefNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof DefinitionsNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class DefNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());
	  }	  
    if(semanticStack.peek().getNodeType() instanceof FormalsNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek().getNodeType() instanceof TypeIntegerNode ||
    semanticStack.peek().getNodeType() instanceof TypeBooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof BodyNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
  }
}
//---
class FormalsNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NonEmptyFormalsNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FormalNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsPrimeNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
  }
}
//---
class NonEmptyFormalsPrimeNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class FormalNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek().getNodeType() instanceof TypeIntegerNode ||
	semanticStack.peek().getNodeType() instanceof TypeBooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class BodyNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	semanticStack.peek().getNodeType() instanceof StatementListReturnNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	}
  }
}
//---
class StatementListPSNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof PrintStatementNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	semanticStack.peek().getNodeType() instanceof StatementListReturnNode){ 
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class StatementListReturnNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  	  
    }
  }
}
//---
class TypeIntegerNode{
  private void getChildren(){  
  }  
 //only has a keyword terminal
}
//---
class TypeBooleanNode{
  private void getChildren(){  
  }  
  //only has a keyword terminal
}
//---
class ExprNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimplExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof ExprPrimeLTNode ||
	semanticStack.peek().getNodeType() instanceof ExprPrimeExprNode){ 
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class ExprPrimeLTNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class ExprPrimeExprNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class SimpleExprNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof SimpleExprPrimeOrNode ||
	semanticStack.peek().getNodeType() instanceof SimpleExprPrimePlusNode ||
	semanticStack.peek().getNodeType() instanceof SimpleExprPrimeMinusNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());			  
    }
  }
}
//---
class SimpleExprPrimeOrNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class SimpleExprPrimePlusNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class SimpleExprPrimeMinusNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class TermNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode ||
    semanticStack.peek().getNodeType() instanceof FactorIDNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof TermPrimeAndNode ||
	semanticStack.peek().getNodeType() instanceof TermPrimeTimesNode ||
	semanticStack.peek().getNodeType() instanceof TermPrimeDivideNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class TermPrimeAndNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class TermPrimeTimesNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class TermPrimeDivideNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class FactorIfNode{
  private void getChildren(){	  
    for(int i = 0; i < 3; i++){
      if(semanticStack.peek().getNodeType() instanceof ExprNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	  }
	}
  }
}
//---
class FactorNotNode
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode ||
	semanticStack.peek().getNodeType() instanceof FactorIDNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class FactorIDNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode ||){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek().getNodeType() instanceof IdentifierPrimeLP){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class IfIntLiteralNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof LiteralNumberNode ||
	semanticStack.peek().getNodeType() instanceof LiteralBooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	   
	}
  }
}
//---
class IfIntMinusNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIDNode ||
	semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class IfIntLPNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class IdentifierPrimeLP{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class ActualsNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NonEmptyActualsNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsPrimeNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NonEmptyActualsPrimeNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}	  
    if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsPrimeNode){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class LiteralNumberNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NumberNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class LiteralBooleanNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof BooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class PrintStatementNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NumberNode{
  private void getChildren(){  
  }
}
//---
class BooleanNode{
  private void getChildren(){  
  }
}
//---
class IdentifierNode{
  private void getChildren(){  
  }
}

