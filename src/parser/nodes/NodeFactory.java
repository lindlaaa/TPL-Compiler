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
      case 10: temp = new StatementListNode();        break;
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
      default:  throw new ParseException("--Token DUMB--");
		}

    temp.createTree();
    return temp;
  }
}

/*
class ProgramNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop() instanceof IdentifierNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop(). instanceof FormalsNode){
  	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop(). instanceof DefinitionsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
    if(TableDrivenParser.semanticStack.pop(). instanceof BodyNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class DefinitionsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof DefNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop(). instanceof DefinitionsNode){
	     tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class DefNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof IdentifierNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
    if(TableDrivenParser.semanticStack.pop(). instanceof FormalsNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof TypeIntegerNode ||
    TableDrivenParser.semanticStack.pop(). instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof BodyNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class FormalsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class NonEmptyFormalsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof FormalNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyFormalsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class NonEmptyFormalsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyFormalsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class FormalNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof TypeIntegerNode ||
	TableDrivenParser.semanticStack.pop(). instanceof TypeBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class BodyNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof StatementListPSNode ||
	TableDrivenParser.semanticStack.pop(). instanceof StatementListtemp =Node){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class StatementListPSNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof PrintStatementNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof StatementListPSNode ||
	TableDrivenParser.semanticStack.pop(). instanceof StatementListtemp =Node){
	  tree.addLeaf(TableDrivenParser.TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class StatementListNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class TypeIntegerNode extends SemanticNode{
  public void getChildren(){
  }
 //only has a keyword terminal
}
//---
class TypeBooleanNode extends SemanticNode{
  public void getChildren(){
  }
  //only has a keyword terminal
}
//---
class ExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof SimplExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprPrimeLTNode ||
	TableDrivenParser.semanticStack.pop(). instanceof ExprPrimeExprNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class ExprPrimeLTNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class ExprPrimeExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class SimpleExprNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof SimpleExprPrimeOrNode ||
	TableDrivenParser.semanticStack.pop(). instanceof SimpleExprPrimePlusNode ||
	TableDrivenParser.semanticStack.pop(). instanceof SimpleExprPrimeMinusNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimeOrNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimePlusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class SimpleExprPrimeMinusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof SimpleExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class TermNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.pop(). instanceof FactorNotNode ||
    TableDrivenParser.semanticStack.pop(). instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof TermPrimeAndNode ||
	TableDrivenParser.semanticStack.pop(). instanceof TermPrimeTimesNode ||
	TableDrivenParser.semanticStack.pop(). instanceof TermPrimeDivideNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class TermPrimeAndNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class TermPrimeTimesNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class TermPrimeDivideNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof TermNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class FactorIfNode extends SemanticNode{
  public void getChildren(){
    for(int i = 0; i < 3; i++){
      if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
	    tree.addLeaf(TableDrivenParser.semanticStack.pop());
	  }
	}
  }
}
//---
class FactorNotNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.pop(). instanceof FactorNotNode ||
	TableDrivenParser.semanticStack.pop(). instanceof FactorIDNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class FactorIDNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof IdentifierNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof IdentifierPrimeLP){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class IfIntLiteralNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof LiteralNumberNode ||
	TableDrivenParser.semanticStack.pop(). instanceof LiteralBooleanNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
  }
}
//---
class IfIntMinusNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof FactorIDNode ||
	TableDrivenParser.semanticStack.pop(). instanceof FactorIfNode ||
	TableDrivenParser.semanticStack.pop(). instanceof FactorNotNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class IfIntLPNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class IdentifierPrimeLP extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class ActualsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyActualsNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class NonEmptyActualsNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyActualsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class NonEmptyActualsPrimeNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
	}
    if(TableDrivenParser.semanticStack.pop(). instanceof NonEmptyActualsPrimeNode){
	  tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class LiteralNumberNode extends SemanticNode{
  public void getChildren(){
  }
}
//---
class LiteralBooleanNode extends SemanticNode{
  public void getChildren(){
  }
}
//---
class PrintStatementNode extends SemanticNode{
  public void getChildren(){
    if(TableDrivenParser.semanticStack.pop(). instanceof ExprNode){
      tree.addLeaf(TableDrivenParser.semanticStack.pop());
    }
  }
}
//---
class IdentifierNode extends SemanticNode{
  public void getChildren(){
  }
}
*/
