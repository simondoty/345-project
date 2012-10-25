package crono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import crono.AbstractSyntax.CronoType;
import crono.AbstractSyntax.CronoFunction;
import crono.AbstractSyntax.Function;
import crono.CronoOptions;
import crono.Environment;
import crono.Symbol;

public class CombinatorFunction implements Function
{
    public final List<Symbol> args;
    public final List<CronoType> statements;
    public final Environment environment;
    private CronoTypeVar ctv;

    public CombinatorFunction(List<CronoType> body)
    {
	this(body, new ArrayList<Symbol>());
    }

    public CombinatorFunction(List<CronoType> body, List<Symbol> args) {
	this(body, args, new Environment());
    }

    public CombinatorFunction(List<CronoType> body, Environment environment) {
	this(body, new ArrayList<Symbol>(), environment);
    }

    public CombinatorFunction(List<CronoType> body, List<Symbol> args, Environment environment) {
	this.statements = body;
	this.args = args;
	this.environment = environment;
	ctv = new CronoTypeVar();
    }

    public String toString() 
    {
	/*StringBuilder sb = new StringBuilder();

	  sb.append("(<> (");
	  Iterator<Symbol> argit = args.iterator();
	  while(argit.hasNext()) {
	  sb.append(argit.next().toString());
	  if (argit.hasNext()) {
	  sb.append(" ");
	  }
	  }
	  sb.append(") ");
	  Iterator<CronoType> sit = statements.iterator();
	  while(sit.hasNext()) {
	  sb.append(sit.next().toString());
	  if (sit.hasNext()) {
	  sb.append(" ");
	  }
	  }
	  sb.append(")");

	  if (CronoOptions.LAMBDA_SHOW_CLOSURE) {
	  sb.append(" [");
	  sb.append(closure());
	  sb.append("]");
	  }*/

	return statements.toString();
    }

    public boolean evalArgs() {
	return true;
    }
  

    /**
     * Essentially copies Environment.toString() but saves us from infinite
     * recursion if this LambdaFunction is in its own environment.
     */
    // TODO: move this toString technique to Environment.toString()
    private String closure() {
	StringBuilder result = new StringBuilder();

	Iterator<Symbol> it = environment.iterator();
	List<Symbol> keys = new ArrayList<Symbol>();
	List<CronoType> vals = new ArrayList<CronoType>();
	while(it.hasNext()) {
	    Symbol key = it.next();
	    CronoType val = environment.get(key);
	    if (!(val instanceof CronoFunction) ||
		CronoOptions.ENVIRONMENT_SHOW_BUILTIN) {
		keys.add(key);
		vals.add(val);
	    }
	}

	Iterator<Symbol> keyit = keys.iterator();
	Iterator<CronoType> valit = vals.iterator();

	while(keyit.hasNext()) {
	    Symbol key = keyit.next();
	    CronoType val = valit.next();
	    result.append(key.toString());
	    result.append(": ");
	    if (val instanceof LambdaFunction) {
		boolean prev = CronoOptions.LAMBDA_SHOW_CLOSURE;
		CronoOptions.LAMBDA_SHOW_CLOSURE = false;
		result.append(val.toString());
		CronoOptions.LAMBDA_SHOW_CLOSURE = prev;
	    } else {
		result.append(val.toString());
	    }

	    if (CronoOptions.ENVIRONMENT_SHOW_TYPES) {
		System.out.println("In CombinatorFunction's closure function");
		result.append(" [");
		result.append(val.getClass().getName());
		result.append("]");
	    }
	    if (keyit.hasNext()) {
		result.append(", ");
	    }
	}
	return result.toString();
    }
  
    public static boolean isCombinatorFunctionDeepSearch(CronoType function)
    {
	if(function instanceof Cons)
	    {
		while(!function.equals(Nil.NIL))
		    {
            if(((Cons)function).car() instanceof CombSymbol) {
// System.out.println(" [D[" + ((Cons)function) + "]]");
			    return true;
            }
			else if(((Cons)function).car() instanceof Cons)
			    {
				boolean ans = isCombinatorFunctionDeepSearch(((Cons)function).car());
				if(ans)
				    return true;
			    }
			function = ((Cons)function).cdr();
		    }
	    }
	return false;
    }
  
    public static boolean isCombinatorFunctionShallowSearch(CronoType function)
    {
	if(function instanceof Cons)
	    {
        if(((Cons)function).car() instanceof CombSymbol) {
// System.out.println(" [S[" + ((Cons)function) + "]]");
		    return true;
        }
		else if(((Cons)function).car() instanceof Cons &&((Cons)function).car()!=Nil.NIL)
		    return isCombinatorFunctionShallowSearch(((Cons)function).car());
	    }
	return false;
    }

    public CronoTypeVar type() {
	if(ctv == null) ctv = new CronoTypeVar();
	return ctv; 
    }

    public CronoTypeConstraint[] constraint(CronoType[] args) {
	/**
	 *NEED TO ADD CODE HERE, LATER
	 *
	 */

	/*for(CronoType ct: args) {
	    System.out.println("COMB CT: " + ct);
	}*/
	return null;
    }

}
