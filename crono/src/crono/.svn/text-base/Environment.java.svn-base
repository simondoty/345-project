package crono;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import crono.AbstractSyntax.CronoType;
import crono.AbstractSyntax.CronoFunction;
import crono.Symbol;

import static crono.CronoOptions.err;

public class Environment implements Iterable<Symbol> {
    private Map<Symbol, CronoType> vars;
    private Map<Symbol, CronoTypeVar> type;

    public Environment(){
	this.vars = new HashMap<Symbol, CronoType>();
    }

    public Environment(Environment environment) {
	this();
	this.update(environment);
    }

    public void put(Symbol k, CronoType v) {
	vars.put(k,v);
    }

    /**
     * Returns object mapped to by the symbol s.
     *
     * @throws: RuntimeException if there is no mapping for s
     */
    public CronoType get(Symbol s) {
		
	CronoType result = vars.get(s);
		
	if (result == null) {
	    err("No object %s in scope.", s);
	}

	return result;
    }

    public boolean containsKey(Symbol s) {
	return vars.containsKey(s);
    }

    public Iterator<Symbol> iterator() {
	return vars.keySet().iterator();
    }

    public Environment update(Environment other) {
	if (other != null) {
	    for(Symbol key : other) {
		this.put(key, other.get(key));
	    }
	}
	return this;
    }

    public String toString() {
	return toString(CronoOptions.ENVIRONMENT_MULTILINE);
    }

    public String toString(boolean multiline) {
	StringBuilder result = new StringBuilder();

	Iterator<Symbol> it = this.iterator();
	while(it.hasNext()) {
	    Symbol key = it.next();
	    CronoType val = this.get(key);
	    // Don't print if we're a CronoFunction and !ENVIRONMENT_SHOW_BUILTIN.
	    if (!(val instanceof CronoFunction) ||
		CronoOptions.ENVIRONMENT_SHOW_BUILTIN) {
		//System.out.println("******** " + key.toString());
		result.append(key.toString());
		result.append(": ");
		result.append(val.toString());
		
		
	
		if (CronoOptions.ENVIRONMENT_SHOW_TYPES) {
		    result.append(" [");
		    result.append(val.getClass().getName());
		    result.append("]");
		}
		if (it.hasNext()) {
		    result.append(multiline ? "\n" : ", ");
		}
	    }
	}

	return result.toString();
    }
}
