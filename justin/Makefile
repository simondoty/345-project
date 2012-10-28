class: Parser.java
	javac *java

java: Parser.jj 
	javacc Parser.jj

#clean:
#	rm -f *.class *.java

eytang: Parser.jj
#	make clean
	make java
	make class
	cat test | java Parser > myOutput.txt
	cat myOutput.txt

trial: Parser.jj
#	make clean
	make java
	make class
	./test2 > myOutput.txt
	cat myOutput.txt
