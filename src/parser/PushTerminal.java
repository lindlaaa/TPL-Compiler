import java.util.Stack;

public class PushTerminal implements ParseAction
{
   private Token token;

   public PushTerminal( Token token )
   {
      this.token = token;
   }

   public void execute( Stack stack )
   {
      stack.push( token );
   }

   public String toString()
   {
      return " " +  token;
   }
}
