Very basic interpreter.

Class hierarchy is like so:

SExpr (abstract)
|
---------------------------------------------------
|                             |                   |
Atom  (abstract)           AtomList             Expr
|
NumericAtom
StringAtom
SymbolAtom


Atoms hold their data. They don't evaluate to anything except their data.

AtomList holds an arraylist of Atoms. Lists can't really be evaluated to anything but themselves.

Expr holds an expression that is the function being applied,
         and an expression for each argument that must be evaluated before application.


I worked from scratch, but I actually ended up with a class breakdown kinda similar to Crono.
