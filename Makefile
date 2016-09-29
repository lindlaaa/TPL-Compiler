all:
	javac -d bin src/main/*.java
	javac -d bin src/scanner/*.java
	javadoc -d doc -package src.main src.scanner

clean:
	rm -rf doc/*
	rm -rf bin/*
