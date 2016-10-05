package src.parser;

import java.util.Stack;

public class PushNull implements ParseRule
{
   public PushNull() {}

   public void execute( Stack stack ) {}

   public String toString()
   {
      return "(empty)";
   }
}
