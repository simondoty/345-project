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

/**
 * A dynamically created function.
 *
 * Contains a list of potentionally unexpanded Crono objects which are arguments
 * and a list of statements to be executed.
 */
public class LambdaFunction implements Function {
	public final List<Symbol> args;
	public final List<CronoType> statements;
	public final Environment environment;
	public CronoTypeVar ctv;

	public LambdaFunction(List<CronoType> body){
		this(body, new ArrayList<Symbol>());
	}

	public LambdaFunction(List<CronoType> body, List<Symbol> args) {
		this(body, args, new Environment());
	}

	public LambdaFunction(List<CronoType> body, Environment environment) {
		this(body, new ArrayList<Symbol>(), environment);
	}

	public LambdaFunction(List<CronoType> body, List<Symbol> args, Environment environment) {
		this.statements = body;
		this.args = args;
		this.environment = environment;
		this.ctv = new CronoTypeVar();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("(\\ (");
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
		}

		return sb.toString();
	}

	public boolean evalArgs() {
		return true;
	}

	public CronoTypeVar type() {
		return ctv;
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

	public CronoTypeConstraint[] constraint(CronoType[] args) {
		// TODO Auto-generated method stub
		return null;
	}
}


