all:
	javac -d bin src/main/Compiler.java
	javadoc -d doc -package src.main src.scanner

clean:
	rm -rf doc/*
	rm -rf bin/*
