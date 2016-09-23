Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggelston
File to compile: Scanner.java
Compile:    javac -d src/main/scanner/bin src/main/scanner/Scanner.java
Run:        java src/main/scanner/bin/Scanner <path of input file>
Date:       2016/09/23


#Scanner
   Our scanner uses the same logic as a finite state automata. As the scanner takes in tokens, it switches states for when it is looking for a token, when it is taking an integer, and when it is taking in a string. It switches through these states to treat each token or character appropriately, and returns the type of each individual token.
