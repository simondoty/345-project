package crono;

import java.util.HashMap;
import java.util.Map;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;

/**
 * Symbol class
 *
 * All symbols are unique and case insensitive.
 */
public class Symbol implements Atom {
    private final String name;
    private CronoTypeVar ctv;

    private static final Map<String, Symbol> symbols = new HashMap<String, Symbol>();

    public Symbol(String s) {
	this.name = s.toLowerCase();
	ctv = new CronoTypeVar();

    }

    public String toString() {
	return this.name;
    }

    public boolean equals(Object o) {
	if (o instanceof Symbol) {
	    return ((Symbol)o).name.equals(this.name);
	} else {
	    return false;
	}
    }

    public static Symbol valueOf(String s) {
	s = s.toLowerCase();
	if (symbols.containsKey(s)) {
	    return symbols.get(s);
	} else {
		   
	    Symbol result = new Symbol(s);
	    symbols.put(s, result);
	    return result;
	}
    }

    public CronoTypeVar type() {
	return ctv;
    }

    public CronoTypeConstraint[] constraint(CronoType[] args) {
	CronoTypeConstraint[] ret = null;
	if(!name.equals("t")) {
	    ret = new CronoTypeConstraint[2];
	    ret[0] = new CronoTypeConstraint(ctv.clone(), new CronoTypeVar(name));
	    ret[1] = new CronoTypeConstraint(new CronoTypeVar(name), ctv.clone());
	} else {
	    ret = new CronoTypeConstraint[1];
	    ret[0] = new CronoTypeConstraint(ctv, new CronoTypeVar(true));
	}
	return ret;
    }

    public CronoTypeConstraint[] combConstraint(CronoType[] args) {
	CronoTypeConstraint[] ret = null;

	if(!name.equalsIgnoreCase("t")) {
	    CronoTypeVar same = new CronoTypeVar();
 
	    CronoTypeVar unknown = args[0].type().clone();
	    // System.out.println("ARGS[0]: " + args[0]);
 
	    CronoTypeVar known = same;

	   
	    unknown.addVar(args[args.length - 1].type().clone());
	    known.addVar(same);
		       
	   
	    ret = new CronoTypeConstraint[2];
	    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
	    ret[1] = new CronoTypeConstraint(ctv.clone(), known);
	}
	else {
	    ret = new CronoTypeConstraint[1];
	    ret[0] = new CronoTypeConstraint(ctv, new CronoTypeVar(true));
	}
	return ret;
    }


}

