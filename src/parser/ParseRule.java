package src.parser;

import java.util.Stack;

public interface ParseRule
{
   public void   execute( Stack stack );
   public String toString();
}
