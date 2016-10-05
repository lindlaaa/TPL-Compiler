import java.util.Stack;

public class PushNothing implements ParseRule
{
   public PushNothing() {}

   public void execute( Stack stack ) {}

   public String toString()
   {
      return "(empty)";
   }
}
