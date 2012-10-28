// string atom handles parsed strings
public class StringAtom extends Atom {
	private String val = "";
	

	public StringAtom(String val_) {
		val = val_;
		
	}
	
	public String toString() {	
		return val;
	}
	
	// override SExpr's eval() method.
	public String eval() {
	   return val;		
	}

}
