package src.parser.nodes;

import java.util.Stack;
import src.parser.*;


@SuppressWarnings("unchecked")
public class NodeFactory{

  public static NodeFactory  factory = null;
  protected NodeFactory(){}

  public static NodeFactory getInstance(){
    if(factory == null){
      factory = new NodeFactory();
    }
    return factory;
  }



  public static SemanticNode createNewNode(SemanticAction inputNodeType) throws ParseException{
    int nodeInt = inputNodeType.getSemanticActionNumber();
    SemanticNode temp;
    switch(nodeInt){
      case 1:  temp = new ProgramNode();              break;
      case 2:  temp = new DefinitionsNode();          break;
      case 3:  temp = new DefNode();                  break;
      case 4:  temp = new FormalsNode();              break;
      case 5:  temp = new NonEmptyFormalsNode();      break;
      case 6:  temp = new NonEmptyFormalsPrimeNode(); break;
      case 7:  temp = new FormalNode();               break;
      case 8:  temp = new BodyNode();                 break;
      case 9:  temp = new StatementListPSNode();      break;
      case 10: temp = new StatementListReturnNode();  break;
      //MISSING
      //MISSING
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
      //case 27: temp = new IfIntLiteralNode();         break;
      //case 28: temp = new IfIntMinusNode();           break;
      //case 29: temp = new IfIntLPNode();              break;
      case 30: temp = new IdentifierPrimeLP();        break;
      case 31: temp = new ActualsNode();              break;
      case 32: temp = new NonEmptyActualsNode();      break;
      case 33: temp = new NonEmptyActualsPrimeNode(); break;
      case 34: temp = new LiteralNumberNode();        break;
      case 35: temp = new LiteralBooleanNode();       break;
      case 36: temp = new PrintStatementNode();       break;
      case 37: temp = new IdentifierNode();           break;

      //NEW
      case 38: temp = new FactorExprNode();           break;
      case 39: temp = new FactorMinusNode();          break;
      case 40: temp = new FactorLiteralNode();        break;
      case 41: temp = new FactorNode();               break;
      case 42: temp = new TermPrimeNode();            break;
      case 43: temp = new LiteralNode();              break;
      case 44: temp = new StatementListNode();        break;



      default:  throw new ParseException("--Parse Error--");
		}

    return temp;
  }
}

/*
class ProgramNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek() instanceof IdentifierNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek(). instanceof FormalsNode){
  	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek(). instanceof DefinitionsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
    if(TableDrivenParser.semanticStack.peek(). instanceof BodyNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class DefinitionsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof DefNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek(). instanceof DefinitionsNode){
	     tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class DefNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof IdentifierNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
    if(TableDrivenParser.semanticStack.peek(). instanceof FormalsNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof TypeIntegerNode ||
    TableDrivenParser.semanticStack.peek(). instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof BodyNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class FormalsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class NonEmptyFormalsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof FormalNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyFormalsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class NonEmptyFormalsPrimeNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class FormalNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof TypeIntegerNode ||
	TableDrivenParser.semanticStack.peek(). instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class BodyNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof StatementListPSNode ||
	TableDrivenParser.semanticStack.peek(). instanceof StatementListtemp =Node){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class StatementListPSNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof PrintStatementNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof StatementListPSNode ||
	TableDrivenParser.semanticStack.peek(). instanceof StatementListtemp =Node){
	  tree.addLeaf(TableDrivenParser.TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class StatementListNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class TypeIntegerNode extends SemanticNode{
  public void addChildren(){
  }
 //only has a keyword terminal
}
//---
class TypeBooleanNode extends SemanticNode{
  public void addChildren(){
  }
  //only has a keyword terminal
}
//---
class ExprNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof SimplExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprPrimeLTNode ||
	TableDrivenParser.semanticStack.peek(). instanceof ExprPrimeExprNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class ExprPrimeLTNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class ExprPrimeExprNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class SimpleExprNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof SimpleExprPrimeOrNode ||
	TableDrivenParser.semanticStack.peek(). instanceof SimpleExprPrimePlusNode ||
	TableDrivenParser.semanticStack.peek(). instanceof SimpleExprPrimeMinusNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class SimpleExprPrimeOrNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class SimpleExprPrimePlusNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class SimpleExprPrimeMinusNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class TermNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.peek(). instanceof FactorNotNode ||
    TableDrivenParser.semanticStack.peek(). instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof TermPrimeAndNode ||
	TableDrivenParser.semanticStack.peek(). instanceof TermPrimeTimesNode ||
	TableDrivenParser.semanticStack.peek(). instanceof TermPrimeDivideNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class TermPrimeAndNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class TermPrimeTimesNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class TermPrimeDivideNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class FactorIfNode extends SemanticNode{
  public void addChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.peek());
	  }
	}
  }
}
//---
class FactorNotNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.peek(). instanceof FactorNotNode ||
	TableDrivenParser.semanticStack.peek(). instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class FactorIDNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof IdentifierPrimeLP){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class IfIntLiteralNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof LiteralNumberNode ||
	TableDrivenParser.semanticStack.peek(). instanceof LiteralBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
  }
}
//---
class IfIntMinusNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof FactorIDNode ||
	TableDrivenParser.semanticStack.peek(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.peek(). instanceof FactorNotNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class IfIntLPNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class IdentifierPrimeLP extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class ActualsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class NonEmptyActualsNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyActualsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class NonEmptyActualsPrimeNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
	}
    if(TableDrivenParser.semanticStack.peek(). instanceof NonEmptyActualsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class LiteralNumberNode extends SemanticNode{
  public void addChildren(){
  }
}
//---
class LiteralBooleanNode extends SemanticNode{
  public void addChildren(){
  }
}
//---
class PrintStatementNode extends SemanticNode{
  public void addChildren(){
    if(TableDrivenParser.semanticStack.peek(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.peek());
    }
  }
}
//---
class IdentifierNode extends SemanticNode{
  public void addChildren(){
  }
}
*/
