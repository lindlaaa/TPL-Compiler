package src.parser;

import java.util.Stack;
import src.scanner.*;

public class PushTerminal implements ParseRule
{
   private Token token;

   public PushTerminal( Token token )
   {
      this.token = token;
   }

   @SuppressWarnings("unchecked")
   public void execute( Stack stack )
   {
      stack.push( token );
   }

   public String toString()
   {
      return " " +  token;
   }
}
