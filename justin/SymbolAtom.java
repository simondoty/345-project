// Symbol class used for parsed symbols like x, or f5, etc.

public class SymbolAtom extends Atom {
	private String sym = "";
	
	// ****************************************
	// constructor used when parsing
    public SymbolAtom(String s_) {
      sym = s_;
    	
    }
	 
	public String toString () {
		return sym;
	}
	// ****************************************
    // eval method does symbol lookup within environment, then upward, until it finds it.
	public Object eval() {
	 return lookup();
		
	}	
	
	// ****************************************
    // equals - tests to see if two symbols are equal
	public boolean equals(SymbolAtom other) {
	 System.out.println("Inside symbol equals....");		
	 return (this.sym.toString().equals(other.sym.toString()));
		
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
