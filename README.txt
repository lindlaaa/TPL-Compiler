Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggleston

Input:            Name of the file you want used.
Output:           String representing the result of your command.

Compile & Run:
  A.  Everything
    1.  make

  B.  Scanner
    1.  make scanner
    2.  ./flairs <filename>

  C.  Parser
    1.  make parser
    2.  ./flairp <filename>

  d.  AST
    1.  make parser
    2.  ./flairp <filename>
    3.  Compile as ./flairp <filename> -t
      a. This saves AST to a file in the root directory called ast.dot
      b. Open with GraphViz to see the new tree graph

  X.  Test script
    1.  make run


Documentation:    doc/

Date:       2016/10/21


Known Issues: Graphviz picture shows duplicate lines from parent nodes
              Does not compile ramen noodles.
              Does not have great documentation for Parser Components...yet
              


Directory Structure:
  bin/ : stores the binary files
  doc/ : stores the javadoc in html form
  src/ : stores the .java files in a structure that is self descriptive
  test_ignore/: stores the .flr files used to test the project parts
  two-exponents.flr: The program required to turn into the AST Parser
  flairp: The script to run the parser
  flairs: The script to run the scanner
  flairTest:  The script that runs all of our test programs
  Makefile: Contains the make commands for ease-of-use


High level Scanner design:
  Our scanner uses the same logic as a finite state automata.
  As the scanner takes in tokens, it switches states for when
  it is looking for a token, when it is taking an integer,
  and when it is taking in a string.
  It switches through these states to treat each token or character
  appropriately, and returns the type of each individual token.

High level Parser design:
  The parser takes the grammar rules, based on the grammar logic table for flair.
  This table is represented in a parseTable, and within this we create rule objects,
  add those objects to the table in specific locations, and the parse algorithm then
  uses that table to confirm there is no bad flair grammar in the input file.

High level AST Parser Design:
  //TODO FIXME
