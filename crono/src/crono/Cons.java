package crono;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import crono.AbstractSyntax.*;

import static crono.CronoOptions.err;
import static crono.Nil.NIL;

public class Cons implements CronoType, Iterable<CronoType> {
    /**
     * Wrapper for our List's iterator since we don't want to allow remove().
     */
    private class ConsIterator implements Iterator<CronoType> {
	private ListIterator<CronoType> it;

	public ConsIterator(ListIterator<CronoType> it) {
	    this.it = it;
	}

	public boolean hasNext() {
	    if (it.hasNext()) {
		CronoType next = it.next();
		// Don't return nil-terminator in a nil-terminated list.
		if (next == NIL && !it.hasNext()) {
		    return false;
		} else {
		    it.previous(); //rewind iterator.
		    return true;
		}
	    } else {
		return false;
	    }
	}

	public CronoType next() {
	    return it.next();
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }

    protected List<CronoType> list;
    private CronoTypeVar ctv;

    // Only we (or NIL) can create empty Conses.
    protected Cons() {
	list = new LinkedList<CronoType>();
	ctv = new CronoTypeVar();
	ctv.makeNil();
    }

    public Cons(CronoType car, CronoType cdr) {
	this(Arrays.asList(car, cdr));
	ctv = new CronoTypeVar();
    }

    public Cons(List<CronoType> list) {
	if (list.size() == 0) {
	    err("Cannot create empty cons");
	}
	if (list.size() == 1) {
	    // NIL-pad the list
	    list.add(NIL);
	}

	this.list = new LinkedList<CronoType>();
	for(CronoType ct : list) {
	    this.list.add(ct);
	}
		
	ctv = new CronoTypeVar();
    }

    private Cons(Cons c) {
	this.list = new LinkedList<CronoType>(c.list);
	ctv = new CronoTypeVar();
    }

    /**
     * Cons 'car' onto the cdr.
     */
    public static Cons cons(CronoType car, Cons cdr) {
	Cons result = new Cons(cdr);
	result.list.add(0, car);
	if (cdr == NIL) {
	    result.list.add(cdr);
	}
	return result;
    }

    public Iterator<CronoType> iterator() {
	return new ConsIterator(this.list.listIterator(0));
    }

    public CronoType car() {
	return list.get(0);
    }

    public void append(CronoType add) {
	  boolean wasRemoved = list.remove(Nil.NIL);
	  list.add(add);
	  if(wasRemoved)
		  list.add(Nil.NIL);
    } 

    public CronoType cdr() {
	if (list.size() == 1) {
	    // Pretend list is NIL-terminated.
	    // Potentially the source of a bug later on :(.
	    return NIL;
	} else if (list.size() == 2) {
	    CronoType cdr = list.get(1);
	    if (cdr instanceof Atom) {
		return cdr;
	    } else {
		// cdr is a Cons, must maintain cons-ness.
		Cons result = new Cons();
		result.list.add(cdr);
		return result;
	    }
	} else {
	    return new Cons(list.subList(1, list.size()));
	}
    }

    public boolean equals(Object o) {
	if (o instanceof Cons) {
	    Cons c = (Cons)o;
	    return this.list.equals(c.list);
	} else {
	    return false;
	}
    }

    public String toString() {
	StringBuilder result = new StringBuilder();

	result.append("(");
	Iterator<CronoType> it = list.iterator();
	while(it.hasNext()) {
	    CronoType next = it.next();
	    if (!it.hasNext()) {
		if (next != NIL) {
		    result.append(". ");
		    result.append(next.toString());
		}
	    } else {
		result.append(next.toString());
		result.append(" ");
	    }
	}
	return result.toString().trim() + ")";
    }

    public CronoTypeVar type() {
	return ctv;
    }
	
    public CronoTypeConstraint[] constraint(Environment e) {
		
	CronoTypeConstraint[] ret = new CronoTypeConstraint[2];
		
	// handle if the first element if Nil
	if(list.get(0) instanceof Nil) {
	    return new CronoTypeConstraint[0];
	}
		
	// handle if this is a CronoNumber
	if(list.get(0) instanceof CronoNumber) {
			
	}
		
	// handle if this is a Cons
	if(list.get(0) instanceof Cons) {
			
	}
		
	// handle if the first is a Symbol, meaning that it is a function
	if(list.get(0) instanceof Symbol && e.containsKey((Symbol) list.get(0))) {
			
	}
		
	return ret;
    }

    public CronoTypeConstraint[] constraint(CronoType[] args) {
	// TODO Auto-generated method stub
	return null;
    }
	
    public int size() {
	return list.size();
    }
}
