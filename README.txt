Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggleston

Input:            File in the test/ folder you want used.
Output:           String representing the result of your command.

Compile & Run:
  A.  Everything
    1.  make

  B.  Scanner
    1.  make scanner

  C.  Parser
    1.  make parser


Documentation:    doc/

Date:       2016/10/01


Known Issues: Does not recognize statements such as 0 = (n-1)


Directory Structure:
  bin/ : stores the binary files
  doc/ : stores the javadoc in html form
  src/ : stores the .java files in a structure that is self descriptive
  test_ignore/: stores the .flr files used to test the project parts


High level Scanner design:
  Our scanner uses the same logic as a finite state automata.
  As the scanner takes in tokens, it switches states for when
  it is looking for a token, when it is taking an integer,
  and when it is taking in a string.
  It switches through these states to treat each token or character
  appropriately, and returns the type of each individual token.

High level Parser design:
  TODO
