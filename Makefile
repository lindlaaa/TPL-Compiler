all:
	javac -d bin src/main/*.java
	javac -d bin src/scanner/*.java
	javadoc -d doc -package src.main src.scanner

clean:
	@rm -rf doc/*
	@cp test/doNotDelete doc/
	@rm -rf bin/*
	@cp test/doNotDelete bin/
	@fortune | cowsay -f www | lolcat
