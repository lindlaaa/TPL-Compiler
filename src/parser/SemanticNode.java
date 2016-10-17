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
      for(int i = i; i < rulesList.length(); i++){
        if(semanticStack.peek().getNodeType() instanceof rulesList[i]){
	  SemanticNode.this.tree.addleaf(semanticStack.pop());	  
	 }
      }
    }//else do nothing?
  }
}
/*
package src.parser;
 
import java.util.Stack;

public class SemNode
{
	SemAction nodeType;
	Stack nodeBody = new Stack();

    public SemNode( SemAction inputNodeType ){
      this.nodeType = inputNodeType;
    }
	public SemAction getNodeType(){
	  return this.nodeType;
	}
	
	public void addElement(T inputAction){
	  this.nodeBody.push(inputAction);
	}
	
	public T removeElement(){
	  return this.nodeBody.pop();
	}
	
	public int numberOfElementsToPop(){
	  return this.nodeType.getSemanticAction();	
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
*/
