package src.parser;

import java.util.Stack;

@SuppressWarnings("unchecked")
public class PushSemantic implements ParseRule
{
   private SemanticAction makeAction;

   public PushSemantic( SemanticAction makeAction )
   {
      this.makeAction = makeAction;
   }

   public void execute( Stack stack )
   {
      stack.push( makeAction );
   }

   //semanticExecute
   //takes appropriate number of pops and makes them its attributes/internal list
   //huge switch case
   //needs to pop off of parser stack and then create a semantic node within the semantic stack
   public String toString()
   {
      return " " +  makeAction;
   }
}
