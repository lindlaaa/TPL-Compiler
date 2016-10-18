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

  //Do we need this???FIXME TODO
	public SemanticNode removeElement(int childPosition){
	  //return children.get(childPosition);
	  return this;
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

class ProgramNode(){
  private ruleList[] = {new IdentifierNode(),
						new FormalsNode(),
                        new DefinitionsNode(),
                        new BodyNode()
                      } );	
  public ProgramNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      for(int i = 1; i < rulesList.length(); i++){
        if(semanticStack.peek().getNodeType() instanceof rulesList[i]){
	      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	    }
      }
    }
  }
}
//---
class DefinitionsNode(){
  public DefinitionsNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof DefNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      for(int i = 1; i < rulesList.length(); i++){
        if(semanticStack.peek().getNodeType() instanceof DefinitionsNode){
	      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	    }
      }
    }
  }
}
//---
class DefNode(){
  public DefNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof FormalsNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());
        if(semanticStack.peek().getNodeType() instanceof TypeIntegerNode ||
		   semanticStack.peek().getNodeType() instanceof TypeBooleanNode){
           SemanticNode.this.tree.addleaf(semanticStack.pop());	  
           if(semanticStack.peek().getNodeType() instanceof BodyNode){
	        SemanticNode.this.tree.addleaf(semanticStack.pop());		
	       }
        }
      }
    }  
  }
}
//---
class FormalsNode(){
  public FormalsNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NonEmptyFormalsNode(){
  public NonEmptyFormalsNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FormalNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsPrimeNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      }
    }
  }
}
//---
class NonEmptyFormalsPrimeNode(){
  public NonEmptyFormalsPrimeNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class FormalNode(){
  public FormalNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
      if(semanticStack.peek().getNodeType() instanceof TypeIntegerNode ||
	  semanticStack.peek().getNodeType() instanceof TypeBooleanNode){
        SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	  }
    }
  }
}
//---
class BodyNode(){
  public BodyNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	semanticStack.peek().getNodeType() instanceof StatementListReturnNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	}
  }
}
//---
class StatementListPSNode(){
  public StatementListPSNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof PrintStatementNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	  semanticStack.peek().getNodeType() instanceof StatementListReturnNode){ 
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	
      }
    }
  }
}
//---
class StatementListReturnNode(){
  public StatementListReturnNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  	  
    }
  }
}
//---
class TypeIntegerNode(){
  public TypeIntegerNode();
  private void getChildren(){  
  }  
 //only has a keyword terminal
}
//---
class TypeBooleanNode(){
  public TypeBooleanNode();
  private void getChildren(){  
  }  
  //only has a keyword terminal
}
//---
class ExprNode(){
  private ruleList[] = {new IdentifierNode(),
		              new FormalsNode(),
                        new DefinitionsNode(),
                        new BodyNode()
                      } );	
  public ExprNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimplExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof ExprPrimeLTNode ||
	  semanticStack.peek().getNodeType() instanceof ExprPrimeExprNode){ 
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	
      }
  }
}
//---
class ExprPrimeLTNode(){
  public ExprPrimeLTNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class ExprPrimeExprNode(){
  public ExprPrimeExprNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class SimpleExprNode(){
  public SimpleExprNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof SimpleExprPrimeOrNode ||
	  semanticStack.peek().getNodeType() instanceof SimpleExprPrimePlusNode ||
	  semanticStack.peek().getNodeType() instanceof SimpleExprPrimeMinusNode){
        SemanticNode.this.tree.addleaf(semanticStack.pop());			  
	  }
    }
  }
}
//---
class SimpleExprPrimeOrNode(){
  public SimpleExprPrimeOrNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class SimpleExprPrimePlusNode(){
  public SimpleExprPrimePlusNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class SimpleExprPrimeMinusNode(){
  public SimpleExprPrimeMinusNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class TermNode(){
  public TermNode();
 
  private void getChildren(){
      if(semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	  semanticStack.peek().getNodeType() instanceof FactorNotNode ||
	  semanticStack.peek().getNodeType() instanceof FactorIDNode){
        SemanticNode.this.tree.addleaf(semanticStack.pop());	  
        if(semanticStack.peek().getNodeType() instanceof TermPrimeAndNode ||
	    semanticStack.peek().getNodeType() instanceof TermPrimeTimesNode ||
	    semanticStack.peek().getNodeType() instanceof TermPrimeDivideNode){
          SemanticNode.this.tree.addleaf(semanticStack.pop());	
        }
      }
  }
}
//---
class TermPrimeAndNode(){
  public TermPrimeAndNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class TermPrimeTimesNode(){
  public TermPrimeTimesNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class TermPrimeDivideNode(){
  public TermPrimeDivideNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	    
	}
  }
}
//---
class FactorIfNode(){
  private ruleList[] = {new ExprNode(),
		                new ExprNode(),
                        new ExprNode()
                      } );	
  public FactorIfNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      for(int i = 1; i < rulesList.length(); i++){
        if(semanticStack.peek().getNodeType() instanceof rulesList[i]){
	      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	    }
      }
    }//else do nothing?
  }
}
//---
class FactorNotNode(){
  public FactorNotNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode ||
	semanticStack.peek().getNodeType() instanceof FactorIDNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	
    }
  }
}
//---
class FactorIDNode(){
  public FactorIDNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode ||){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
      if(semanticStack.peek().getNodeType() instanceof IdentifierPrimeLP){
        SemanticNode.this.tree.addleaf(semanticStack.pop());
	  }		
    }
  }
}
//---
class IfIntLiteralNode(){
  public IfIntLiteralNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof LiteralNumberNode ||
	semanticStack.peek().getNodeType() instanceof LiteralBooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	   
	}
  }
}
//---
class IfIntMinusNode(){
  public IfIntMinusNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIDNode ||
	semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class IfIntLPNode(){
  public IfIntLPNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class IdentifierPrimeLP(){
  public IdentifierPrimeLP();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class ActualsNode(){
  public ActualsNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NonEmptyActualsNode(){
  public NonEmptyActualsNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsPrimeNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	  }
    }
  }
}
//---
class NonEmptyActualsPrimeNode(){
  public NonEmptyActualsPrimeNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
      if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsPrimeNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	  }
    }
  }
}
//---
class LiteralNumberNode(){
  public LiteralNumberNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NumberNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class LiteralBooleanNode(){
  public LiteralBooleanNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof BooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class PrintStatementNode(){
  public PrintStatementNode();
 
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());	  
    }
  }
}
//---
class NumberNode(){
  public NumberNode();
 
  private void getChildren(){  
  }
}
//---
class BooleanNode(){
  public BooleanNode();
 
  private void getChildren(){  
  }
}
//---
class IdentifierNode(){
  public IdentifierNode();
 
  private void getChildren(){  
  }
}
//---

