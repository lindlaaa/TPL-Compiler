package src.parser;

@SuppressWarnings("unchecked")
public class  SemanticNode
{
  private Tree tree;
  private static SemanticNode instance = NULL;

  public SemanticNode(){
    //Defeat instantiation
  }

  public static SemanticNode getInstance() {
    if(instance == null) {
      instance = new SemanticNode();
    }
    return instance;
  }

  public SemanticNode createNewNode(SemanticAction inputNodeType){
    int tempInt = inputNodeType.getSemanticActionNumber();
    switch(tempInt){
      case 1:  return new ProgramNode();
      case 2:  return new DefinitionsNode();
      case 3:  return new DefNode();
      case 4:  return new FormalsNode();
      case 5:  return new NonEmptyFormalsNode();
      case 6:  return new NonEmptyFormalsPrimeNode();
      case 7:  return new FormalNode();
      case 8:  return new BodyNode();
      case 9:  return new StatementListPSNode();
      case 10: return new StatementListReturnNode();
      case 11: return new TypeIntegerNode();
      case 12: return new TypeBooleanNode();
      case 13: return new ExprNode();
      case 14: return new ExprPrimeLTNode();
      case 15: return new ExprPrimeExprNode();
      case 16: return new SimpleExprNode();
      case 17: return new SimpleExprPrimeOrNode();
      case 18: return new SimpleExprPrimePlusNode();
      case 19: return new SimpleExprPrimeMinusNode();
      case 20: return new TermNode();
      case 21: return new TermPrimeAndNode();
      case 22: return new TermPrimeTimesNode();
      case 23: return new TermPrimeDivideNode();
      case 24: return new FactorIfNode();
      case 25: return new FactorNotNode();
      case 26: return new FactorIDNode();
      case 27: return new IfIntLiteralNode();
      case 28: return new IfIntMinusNode();
      case 29: return new IfIntLPNode();
      case 30: return new IdentifierPrimeLP();
      case 31: return new ActualsNode();
      case 32: return new NonEmptyActualsNode();
      case 33: return new NonEmptyActualsPrimeNode();
      case 34: return new LiteralNumberNode();
      case 35: return new LiteralBooleanNode();
      case 36: return new PrintStatementNode();
      case 37: return new IdentifierNode();
		}

    createTree();//FIXME
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

}


class ProgramNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
	    SemanticNode.nodetype.tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek().getNodeType() instanceof FormalsNode){
  	  SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek().getNodeType() instanceof DefinitionsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek().getNodeType() instanceof BodyNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class DefinitionsNode extends SemanticNode{
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
class DefNode extends SemanticNode{
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
class FormalsNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class NonEmptyFormalsNode extends SemanticNode{
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
class NonEmptyFormalsPrimeNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyFormalsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class FormalNode extends SemanticNode{
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
class BodyNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	semanticStack.peek().getNodeType() instanceof StatementListReturnNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class StatementListPSNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof PrintStatementNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek().getNodeType() instanceof StatementListPSNode ||
	semanticStack.peek().getNodeType() instanceof StatementListReturnNode){
	  SemanticNode.this.tree.addleaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class StatementListReturnNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class TypeIntegerNode extends SemanticNode{
  private void getChildren(){
  }
 //only has a keyword terminal
}
//---
class TypeBooleanNode extends SemanticNode{
  private void getChildren(){
  }
  //only has a keyword terminal
}
//---
class ExprNode extends SemanticNode{
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
class ExprPrimeLTNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class ExprPrimeExprNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class SimpleExprNode extends SemanticNode{
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
class SimpleExprPrimeOrNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimePlusNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimeMinusNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof SimpleExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class TermNode extends SemanticNode{
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
class TermPrimeAndNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class TermPrimeTimesNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class TermPrimeDivideNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof TermNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class FactorIfNode extends SemanticNode{
  private void getChildren(){
    for(int i = 0; i < 3; i++){
      if(semanticStack.peek().getNodeType() instanceof ExprNode){
	    SemanticNode.this.tree.addleaf(semanticStack.pop());
	  }
	}
  }
}
//---
class FactorNotNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode ||
	semanticStack.peek().getNodeType() instanceof FactorIDNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class FactorIDNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof IdentifierNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek().getNodeType() instanceof IdentifierPrimeLP){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IfIntLiteralNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof LiteralNumberNode ||
	semanticStack.peek().getNodeType() instanceof LiteralBooleanNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class IfIntMinusNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof FactorIDNode ||
	semanticStack.peek().getNodeType() instanceof FactorIfNode ||
	semanticStack.peek().getNodeType() instanceof FactorNotNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IfIntLPNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IdentifierPrimeLP extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class ActualsNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof NonEmptyActualsNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class NonEmptyActualsNode extends SemanticNode{
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
class NonEmptyActualsPrimeNode extends SemanticNode{
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
class LiteralNumberNode extends SemanticNode{
  private void getChildren(){
  }
}
//---
class LiteralBooleanNode extends SemanticNode{
  private void getChildren(){
  }
}
//---
class PrintStatementNode extends SemanticNode{
  private void getChildren(){
    if(semanticStack.peek().getNodeType() instanceof ExprNode){
      SemanticNode.this.tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IdentifierNode extends SemanticNode{
  private void getChildren(){
  }
}
