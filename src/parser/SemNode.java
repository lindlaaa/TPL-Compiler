package src.parser;
 
import java.util.Stack;

public class SemNode
{
	SemAction nodeType;
	Stack nodeBody = new Stack();

    public SemNode( SemAction inputNodeType )
    {
      this.nodeType = inputNodeType;
    }
	
	public void addElement(T inputAction)
	{
	  this.nodeBody.push(inputAction);
	}
	
	public T removeElement(){
	  return this.nodeBody.pop();
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