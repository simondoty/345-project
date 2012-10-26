// Symbol class used for parsed symbols like x, or f5, etc.
public class SymbolAtom extends Atom {
	private String sym = "";
	
	// ****************************************
	// constructor used when parsing
    public SymbolAtom(String s_) {
      sym = s_;
    	
    }
	 
	// ****************************************
    // eval method does symbol lookup within environment, then upward, until it finds it.
	public Object eval() {
	 return lookup();
		
	}
	
	// *****************************************
	public Object lookup() {
	  // look in environment for the value of sym.
	  // then look in parent environment.
	  // then parent environment.... and so on until found.
	  // return valueOf(sym);
	  return null;
		
	}

}
