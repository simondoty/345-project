// numeric atom handles parsed numbers.
// treated as double-precision as just a catch-all.
public class NumericAtom extends Atom {
	private double val = 0;
	
	// *************************************************
	// constructor is used when parsing.
	public NumericAtom(double val_) {
	  val = val_;	
	
	}
	
	// *************************************************
	// override SExpr's eval() method.
	public Object eval() {
	  return val;
		
	}
	
	// *************************************************
	public String toString() {
		return val + "";
		
	}

}
