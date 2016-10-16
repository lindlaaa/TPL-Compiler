all:
	@javac -d bin src/main/*.java
	@echo -n '. '
	@javac -d bin src/scanner/*.java
	@echo -n '. '
	@javac -d bin src/parser/*.java
	@echo -n '. '
	@javadoc -d doc -quiet -package src.main src.scanner src.parser
	@echo !
	@echo "\n--Flair Compiler Ready!--"

test:
	@make
	@./flairTest

scanner:
	@javac -d bin src/main/*.java
	@echo -n '. '
	@javac -d bin src/scanner/*.java
	@echo -n '. '
	@javadoc -d doc -quiet -package src.main src.scanner
	@echo !
	@echo "\n--Flair Scanner Ready!--"

parser:
	@javac -d bin src/main/*.java
	@echo -n '. '
	@javac -d bin src/scanner/*.java
	@echo -n '. '
	@javac -d bin src/parser/*.java
	@echo -n '. '
	@javadoc -d doc -quiet -package src.main src.scanner src.parser
	@echo !
	@echo "\n--Flair Parser Ready!--"

clean:
	@#@rm -rf doc/*
	@cp test_ignore/doNotDelete doc/
	@rm -rf bin/*
	@cp test_ignore/doNotDelete bin/
	@echo "Done!"
