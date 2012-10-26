import java.util.ArrayList;

// list of atoms.
public class AtomList extends SExpr {
	private ArrayList<Atom> lst = null;
	
	// ****************************************
	// constructor used when parsing
    public AtomList() {
      lst = new ArrayList<Atom>();
    	
    }
	 
	// ****************************************
    // add method used when parsing.
	public void Add(Atom a_) {
	  lst.add(a_);
		
	}
	
	// *****************************************
	public Object eval() {
	  // calling eval() on a list just returns the list. nothing special.
	  return lst;
		
	}
	
	// *****************************************
	public int size() {
		return lst.size();
		
	}
	
	// *****************************************
	public Atom GetItem(int iIndex_) {
	  return lst.get(iIndex_);	
	
	}

}