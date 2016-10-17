package src.parser;

@SuppressWarnings("unchecked")
public class  SemanticNode
{
  private SemanticAction nodeType;
  private Tree tree;

  public SemanticNode( SemanticAction inputNodeType )
  {
  /*switch(inputNodeType)
      case:SemanticAction.Program
        this.nodeType = new ProgramNode();
	break;
      case:...
        this.nodeType = new ...Node();
        break;
	. . .
  */
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
