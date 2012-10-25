package crono;

public class Identifier  implements Cloneable {
	private static int curIdent = 1;
	private int ident;
	
	/**
	 * Constructor of Identifier, assigns a numeric value for the 
	 * instance of Identifier, and updates a counter so that 
	 * two values are not reused
	 */
	public Identifier() {
		ident = curIdent++;
		if(curIdent <= 0) curIdent = 1;
	}
	
	/**
	 * returns the string representation of the Identifier
	 * If its value is 1 it will return "[[1]]"
	 * If its value is 897 it will return "[[897]]"
	 */
	public String toString() {
		return "[[" + ident + "]]";
	}
	
	/**
	 * Tests that the two identifiers are equal
	 * @param i - the other identifier
	 * @return true if both are equal
	 */
	public boolean equals(Identifier i) {
		return this.ident == i.ident;
	}
	
	public boolean equals(Object o) {
		
		if(o == null) return false;
		if(this == o) return true;
		if(o instanceof Identifier) {
			Identifier i = (Identifier) o;
			return this.ident == i.ident;
		}
		return false;
	}
	
	public Identifier clone() {
		Identifier ret = new Identifier();
		curIdent--;
		ret.ident = this.ident;
		return ret;
	}
	
	public static void unit(Tester t) {
		t.set_object("Identifier");
		
		
		t.set_method("Identifier()");
		Identifier i1 = new Identifier();
		t.is_equal(2, curIdent);
		t.is_equal(1, i1.ident);
		
		i1 = new Identifier();
		t.is_equal(3, curIdent);
		t.is_equal(2, i1.ident);
		
		i1 = new Identifier();
		t.is_equal(4, curIdent);
		t.is_equal(3, i1.ident);
		
		
		
		
		t.set_method("toString()");
		t.is_equal("[[3]]", i1.toString());
		i1.ident = 90;
		t.is_equal("[[90]]", i1.toString());
		i1.ident = 67459;
		t.is_equal("[[67459]]", i1.toString());
		
		
		
		
		t.set_method("equals(Identifier)");
		Identifier i2 = new Identifier();
		t.is_true(i1.equals(i1));
		t.is_true(i2.equals(i2));
		t.is_true(!i1.equals(i2));
		t.is_true(!i2.equals(i1));
		i2.ident = 67459;
		t.is_true(i1.equals(i2));
		t.is_true(i2.equals(i1));
	}
}
