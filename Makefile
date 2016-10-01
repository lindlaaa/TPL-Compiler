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
	@rm -rf doc/*
	@cp test/doNotDelete doc/
	@rm -rf bin/*
	@cp test/doNotDelete bin/
	@fortune -s | cowsay -f www | lolcat -d 30
