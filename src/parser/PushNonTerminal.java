package src.parser;

import java.util.Stack;

@SuppressWarnings("unchecked")
public class PushNonTerminal implements ParseRule
{
   private NonTerminal symbol;

   public PushNonTerminal( NonTerminal symbol )
   {
      this.symbol = symbol;
   }

   public void execute( Stack stack )
   {
      stack.push( symbol );
   }

   public String toString()
   {
      return " " +  symbol;
   }
}
