Authors: Avery Lindley,  Noah Schroeder, Grant Siebring, Blake Eggleston

File to compile:  Scanner.java
Input:            Path of the file you want scanned
Output:           String of token objects representing the input file

Compile & Run:    1. cd src/main/scanner
                  2. javac -d bin Scanner.java
                  3. cd bin
                  4. java Scanner <desire file path>

Documentation:    cd /doc

Date:       2016/09/23


#Scanner
   Our scanner uses the same logic as a finite state automata.
   As the scanner takes in tokens, it switches states for when
   it is looking for a token, when it is taking an integer,
   and when it is taking in a string.
   It switches through these states to treat each token or character
   appropriately, and returns the type of each individual token.


   Classes:

   Scanner
   	This class serves as a state machine. Its constructor expects a string as input. The input serves as a file path. If the files contents can’t be
   	retrieved as a string, a generic exception is thrown. The scanner holds information about the current state and what is accepted as tokens. The
   	scanner’s methods create tokens, fill the token array, and display the output for tokens within the array.

   BoolToken
   	The class constructor takes a string as input. Input is expected to be either “true” or “false”. It implements the Token interface, requiring a
   	toString() method. When the token’s toString() is called, it will return a concatenation of the string “Boolean “ with the string input.

   IdentifierToken
   	The class constructor takes a string as input. Input is expected to start with a letter while all other characters are alphanumeric.
   	It implements the Token interface, requiring a toString() method. When the token’s toString() is called, it will return a concatenation of the
   	string “Identifier “ with the character input.

   IntToken
   	The class constructor takes a string as input. All characters are expected to be digits. Once the value is stored, it will be parsed to a double
   	and a maximum value will be enforced. If the double is larger than the max, ScanException will be thrown. It implements the Token interface,
   	requiring a toString() method. When the token’s toString() is called, it will return a concatenation of the string “Integer “ with the string
   	input after it has been parsed to a double. Unlike the other tokens, the value may be returned by calling the getIntValue() method.

   KeywordToken
   	The class constructor a string as input. It is expected to be a reserved keyword for the language. It implements the Token interface, requiring
   	a toString() method. When the token’s toString() is called, it will return a concatenation of the string “Keyword “ with the string input.

   LexicalException
   	This class extends the Exception class. I string is expected as input. The constructor calls the super class’ constructor. When this exception
   	is thrown, it will return a string to explain the error.

   OpToken
   The class constructor takes a character as input. Input is expected to be a mathematical operator. It implements the Token interface, requiring a
   toString() method. When the token’s toString() is called, it will return a concatenation of the string “Operator “ with the character input.

   ParseException
   	This class extends the Exception class. I string is expected as input. The constructor calls the super class’ constructor. When this exception
   	is thrown, it will return a string to explain the error.

   PunctuationToken
   The class constructor takes a character as input. Input is expected to be a symbol understood by the language. It implements the Token interface,
   requiring a toString() method. When the token’s toString() is called, it will return a concatenation of the string “Punctuation “ with the character
   input.

   ScanException
   	This class extends the Exception class. I string is expected as input. The constructor calls the super class’ constructor. When this exception
   	is thrown, it will return a string to explain the error.

   TerminatorToken
   	The class constructor takes a character as input. Input is expected to be either a period or a semicolon. It implements the Token interface,
   	requiring a toString() method. When the token’s toString() is called, it will return a concatenation of the string “Terminator “ with the
   	character input.

   Token
   	This is an interface used to classify all the classes ending in “Token”. Token only enforces the toString() method. This interface is
   	especially useful when creating an array of mixed token types.


   Functions:
   Scanner.java
   	printTokenStrings() : void
   		There is no input because this method works off of the token array that the other methods create. A for loop iterates through each element
   		of the array and prints the token. Although the array may carry different types of tokens, the token interface enforced a toString() method.

   	takeAllTokens() : void
   		This method takes no input and may throw ScanException. It iterates through every character of inputFile by repeatedly calling takeNextToken().
   		After the file has been fully read, the accumulator may still contain information. This method checks to see if it is empty or not and
   		categorizes the accumulators content if necessary.

   takeNextToken() : void
   		The individual characters are categorized in this method. It has no input parameter because it utilizes the scanner’s attributes. The code
   		mirrors the DFA. The creation of tokens depends on the current state and what is read as the curChar. Unexpected characters will throw the
   		ScanException. It serves more as a helper function to takeAllTokens().
