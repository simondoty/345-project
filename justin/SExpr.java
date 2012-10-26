// abstract base class for s-expressions.
// atom, identifier, list, and function applications all derive from this.
public abstract class SExpr {
	// eval method overwritten by children.
	// has to return an Object, because all of the children are different.
	// (some might eval to Strings, numbers, lists, etc.)
	public abstract Object eval();
	//public abstract void replaceParam(SymbolAtom parameter, SExpr argument);

}
