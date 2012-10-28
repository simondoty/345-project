// string atom handles parsed strings
public class StringAtom extends Atom {
	private String val = "";
	

	public StringAtom(String val_) {
		val = val_;
		
	}
	
	// override SExpr's eval() method.
	public String eval() {
	  return val;
		
	}

}
