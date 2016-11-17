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

  d.  SemanticAnalyzer
    1.  make parser
    2.  ./flairp <filename>
    3.  Compile as ./flairp -t <filename>
      a. This saves AST to a file in the root directory called ast.dot
      b. Open with GraphViz to see the new tree graph

  X.  Test script
    1.  make run


Documentation:    doc/

Date:       2016/11/04


Known Issues: Does not compile my other homework.
              Does not have great documentation for Parser Components...yet...still....
              SymbolTable is not complete.
              TypeChecker is not complete.
              The hashMap in SymbolTable is giving us trouble when trying
                  to reference the keys. We cannot access the HashMap.
              We regenerate JavaDoc on every compilation.
                This is a design decision and is intentional.
                Final submission at the end of the semester will not.

Directory Structure:
NEW:
  flairc  : script used to create a .tm file with same name as original

OLD:
  bin/ : stores the binary files
  doc/ : stores the javadoc in html form
  src/ : stores the .java files in a structure that is self descriptive
  test_ignore/: stores the .flr files used to test the project parts
  two-exponents.flr: The program required to turn into the AST Parser
  flairp: The script to run the parser
  flairs: The script to run the scanner
  flairTest:  The script that runs all of our test programs
  Makefile: Contains the make commands for ease-of-use
  ~Other files~: Used to support the GitHub Pages website.


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

  Upon completing the parsing of a file, the object returns true or
  false representing if the file is a proper flair program. The AST
  that is generated is then stored in the 'TableDrivenParser' object.

High level AST Design:
  Our AST is an object type ProgramNode.
  This SemanticNode object has children that are also different types
    SemanticNodes. Upon completing the SymbolTable and TypeChecker
    these nodes will contain attributes referencing their datatypes and
    other necessary information. Upon completing the 'ParseProgram()'
    method in TableDrivenParser we store the final tree which can be
    accessed using 'getAST()'.
