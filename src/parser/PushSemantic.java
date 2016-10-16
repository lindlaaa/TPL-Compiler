package src.parser;

import java.util.Stack;

public class PushSemantic implements ParseRule
{
   private SemAction makeAction;

   public PushSemantic( SemAction makeAction )
   {
      this.makeAction = makeAction;
   }

   @SuppressWarnings("unchecked")
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
