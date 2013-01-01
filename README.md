345-project
===========
Project for 345 Programming Languiages, a University of Texas Computer Science class.

---------------------
Key Concepts:

Visitor Pattern, Abstract Syntax Tree, Deferred Substitution, Dynamic / Static Scoping,
BNF Grammar, Tokens, non-terminals

---------------------
Function:

This program is a nearly complete implementation of a Lisp Interpreter using JavaCC, JJTree, and Java files.

---------------------
Methodology:

JavaCC was used to build an interpreter with a BNF grammar and defined tokens and non-terminals. 
JJTree facilitates building an Abstract Syntax Tree with visitable nodes to represent the interpreted structure
of Lisp statements.
The visitor pattern was utilized with distinct visitors for different functionality - printing, building envirnonment, 
and interpreting and executing.

