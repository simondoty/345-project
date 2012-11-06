mv LispParserDumpVisitor.java LispParser.temp
mv LispParserInterpreterVisitor.java InterpreterParser.temp
mv ASTNum.java Atom.temp
mv ASTExpr.java Expr.temp
rm *.java
rm *.class
rm *.jj
mv LispParser.temp LispParserDumpVisitor.java
mv InterpreterParser.temp LispParserInterpreterVisitor.java
mv Atom.temp ASTNum.java
mv Expr.temp ASTExpr.java
jjtree LispParser.jjt
javacc LispParser.jj
javac *.java
