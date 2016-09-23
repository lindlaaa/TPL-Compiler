Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggleston

File to compile:  Scanner.java
Input:            Path of the file you want scanned
Output:           String of token objects representing the input file

Compile & Run:    1. cd src/main/scanner
                  2. javac -d bin Scanner.java
                  3. cd bin
                  4. java Scanner <desire file path>

Documentation:    cd doc/src/main/scanner

Date:       2016/09/23


#Scanner
   Our scanner uses the same logic as a finite state automata.
   As the scanner takes in tokens, it switches states for when
   it is looking for a token, when it is taking an integer,
   and when it is taking in a string.
   It switches through these states to treat each token or character
   appropriately, and returns the type of each individual token.
