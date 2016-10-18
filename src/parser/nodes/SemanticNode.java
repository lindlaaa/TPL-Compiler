package src.parser;


@SuppressWarnings("unchecked")
public abstract class SemanticNode
{

  public SemanticNode createNewNode(SemanticAction inputNodeType){
    int tempInt = inputNodeType.getSemanticActionNumber();
    SemanticNode temp;
    switch(tempInt){
      case 1:  temp = new ProgramNode();              break;
      case 2:  temp = new DefinitionsNode();          break;
      case 3:  temp = new DefNode();                  break;
      case 4:  temp = new FormalsNode();              break;
      case 5:  temp = new NonEmptyFormalsNode();      break;
      case 6:  temp = new NonEmptyFormalsPrimeNode(); break;
      case 7:  temp = new FormalNode();               break;
      case 8:  temp = new BodyNode();                 break;
      case 9:  temp = new StatementListPSNode();      break;
      case 10: temp = new StatementListtempNode();    break;
      case 11: temp = new TypeIntegerNode();          break;
      case 12: temp = new TypeBooleanNode();          break;
      case 13: temp = new ExprNode();                 break;
      case 14: temp = new ExprPrimeLTNode();          break;
      case 15: temp = new ExprPrimeExprNode();        break;
      case 16: temp = new SimpleExprNode();           break;
      case 17: temp = new SimpleExprPrimeOrNode();    break;
      case 18: temp = new SimpleExprPrimePlusNode();  break;
      case 19: temp = new SimpleExprPrimeMinusNode(); break;
      case 20: temp = new TermNode();                 break;
      case 21: temp = new TermPrimeAndNode();         break;
      case 22: temp = new TermPrimeTimesNode();       break;
      case 23: temp = new TermPrimeDivideNode();      break;
      case 24: temp = new FactorIfNode();             break;
      case 25: temp = new FactorNotNode();            break;
      case 26: temp = new FactorIDNode();             break;
      case 27: temp = new IfIntLiteralNode();         break;
      case 28: temp = new IfIntMinusNode();           break;
      case 29: temp = new IfIntLPNode();              break;
      case 30: temp = new IdentifierPrimeLP();        break;
      case 31: temp = new ActualsNode();              break;
      case 32: temp = new NonEmptyActualsNode();      break;
      case 33: temp = new NonEmptyActualsPrimeNode(); break;
      case 34: temp = new LiteralNumberNode();        break;
      case 35: temp = new LiteralBooleanNode();       break;
      case 36: temp = new PrintStatementNode();       break;
      case 37: temp = new IdentifierNode();           break;
		}

    temp.createTree();
    return temp;
  }


  private void createTree(){
    tree = new Tree(this);
  }
}


class ProgramNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek() instanceof IdentifierNode){
	    tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek(). instanceof FormalsNode){
  	  tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek(). instanceof DefinitionsNode){
      tree.addleaf(semanticStack.pop());
    }
    if(semanticStack.peek(). instanceof BodyNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class DefinitionsNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof DefNode){
      tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek(). instanceof DefinitionsNode){
	     tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class DefNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof IdentifierNode){
	  tree.addleaf(semanticStack.pop());
	  }
    if(semanticStack.peek(). instanceof FormalsNode){
	  tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof TypeIntegerNode ||
    semanticStack.peek(). instanceof TypeBooleanNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof BodyNode){
	  tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class FormalsNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof NonEmptyFormalsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class NonEmptyFormalsNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof FormalNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof NonEmptyFormalsPrimeNode){
	  tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class NonEmptyFormalsPrimeNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof NonEmptyFormalsNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class FormalNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof IdentifierNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof TypeIntegerNode ||
	semanticStack.peek(). instanceof TypeBooleanNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class BodyNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof StatementListPSNode ||
	semanticStack.peek(). instanceof StatementListtemp =Node){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class StatementListPSNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof PrintStatementNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof StatementListPSNode ||
	semanticStack.peek(). instanceof StatementListtemp =Node){
	  tree.addleaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class StatementListtemp =Node extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class TypeIntegerNode extends NodeBehavior{
  public void getChildren(){
  }
 //only has a keyword terminal
}
//---
class TypeBooleanNode extends NodeBehavior{
  public void getChildren(){
  }
  //only has a keyword terminal
}
//---
class ExprNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof SimplExprNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof ExprPrimeLTNode ||
	semanticStack.peek(). instanceof ExprPrimeExprNode){
	  tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class ExprPrimeLTNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class ExprPrimeExprNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class SimpleExprNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof SimpleExprPrimeOrNode ||
	semanticStack.peek(). instanceof SimpleExprPrimePlusNode ||
	semanticStack.peek(). instanceof SimpleExprPrimeMinusNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimeOrNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimePlusNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimeMinusNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof SimpleExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class TermNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof FactorIfNode ||
	semanticStack.peek(). instanceof FactorNotNode ||
    semanticStack.peek(). instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof TermPrimeAndNode ||
	semanticStack.peek(). instanceof TermPrimeTimesNode ||
	semanticStack.peek(). instanceof TermPrimeDivideNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class TermPrimeAndNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class TermPrimeTimesNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class TermPrimeDivideNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof TermNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class FactorIfNode extends NodeBehavior{
  public void getChildren(){
    for(int i = 0; i < 3; i++){
      if(semanticStack.peek(). instanceof ExprNode){
	    tree.addleaf(semanticStack.pop());
	  }
	}
  }
}
//---
class FactorNotNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof FactorIfNode ||
	semanticStack.peek(). instanceof FactorNotNode ||
	semanticStack.peek(). instanceof FactorIDNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class FactorIDNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof IdentifierNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof IdentifierPrimeLP){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IfIntLiteralNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof LiteralNumberNode ||
	semanticStack.peek(). instanceof LiteralBooleanNode){
      tree.addleaf(semanticStack.pop());
	}
  }
}
//---
class IfIntMinusNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof FactorIDNode ||
	semanticStack.peek(). instanceof FactorIfNode ||
	semanticStack.peek(). instanceof FactorNotNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IfIntLPNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IdentifierPrimeLP extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class ActualsNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof NonEmptyActualsNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class NonEmptyActualsNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof NonEmptyActualsPrimeNode){
	  tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class NonEmptyActualsPrimeNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
	}
    if(semanticStack.peek(). instanceof NonEmptyActualsPrimeNode){
	  tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class LiteralNumberNode extends NodeBehavior{
  public void getChildren(){
  }
}
//---
class LiteralBooleanNode extends NodeBehavior{
  public void getChildren(){
  }
}
//---
class PrintStatementNode extends NodeBehavior{
  public void getChildren(){
    if(semanticStack.peek(). instanceof ExprNode){
      tree.addleaf(semanticStack.pop());
    }
  }
}
//---
class IdentifierNode extends NodeBehavior{
  public void getChildren(){
  }
}
