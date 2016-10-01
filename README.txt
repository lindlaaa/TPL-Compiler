Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggleston

Input:            File in the test/ folder you want used.
Output:           String representing the result of your command.

Compile & Run:
  A.  Scanner
    1.  make s foo=<filepath>
  B.  Parser
    1.  make p foo=<filepath>

Documentation:    doc/

Date:       2016/10/01


Known Issues: Some strange error after running 'make clean'.


Directory Structure:
  bin/ : stores the binary files
  doc/ : stores the javadoc in html form
  src/ : stores the .java files in a structure that is self descriptive
  test/: stores the .flr files used to test the project parts


High level Scanner design:
  Our scanner uses the same logic as a finite state automata.
  As the scanner takes in tokens, it switches states for when
  it is looking for a token, when it is taking an integer,
  and when it is taking in a string.
  It switches through these states to treat each token or character
  appropriately, and returns the type of each individual token.

High level Parser design:
  TODO
