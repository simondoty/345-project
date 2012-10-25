package crono;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import crono.AbstractSyntax.CronoFunction;
import crono.AbstractSyntax.CronoType;
import crono.Cons;
import crono.Environment;
import crono.Interpreter;
//import crono.Parser;

import static crono.CronoOptions.err;
import static crono.Nil.NIL;

/**
 * Container class for all built in functions.
 */
public enum CronoFunctions {
    CONS(new CronoFunction() {

	    private CronoTypeVar ctv;

	    public CronoType run(CronoType[] args, Environment environment) {
		if (args.length > 2) {
		    err("too many arguments given to CONS: %s", Arrays.toString(args));
		} else if (args.length < 2) {
		    err("too few arguments given to CONS: %s", Arrays.toString(args));
		}

		CronoType car = args[0];
		CronoType cdr = args[1];
		CronoType result;

		if (cdr instanceof Cons) {
		    result = Cons.cons(car, (Cons)cdr);
		} else {
		    result = new Cons(car, cdr);
		}
		return result;
	    }

	    public String toString() {
		return "CONS";
	    }

	    public boolean evalArgs() {
		return true;
	    }

	    public CronoTypeVar type() {
		if(ctv == null) ctv = new CronoTypeVar();
		return ctv; 
	    }

	    public CronoTypeConstraint[] constraint(CronoType[] args) {
		ctv = new CronoTypeVar();

		CronoTypeConstraint[] ret = new CronoTypeConstraint[2];
		CronoTypeVar l1 = new CronoTypeVar();
		CronoTypeVar l2 = new CronoTypeVar(l1.clone());
		l1.addVar(l2);
		l1.addVar(l2.clone());

		ret[1] = new CronoTypeConstraint(ctv.clone(), l1);

		l1 = args[0].type().clone();
		l1.addVar(args[1].type().clone());
		l1.addVar(args[2].type().clone());

		ret[0] = new CronoTypeConstraint(ctv.clone(), l1);

		return ret;
	    }
	}),
	CAR(new CronoFunction() {

		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length > 1) {
			err("too many arguments given to CAR: %s", Arrays.toString(args));
		    } else if (args.length < 1) {
			err("too few arguments given to CAR: %s", Arrays.toString(args));
		    }

		    CronoType arg = args[0];

		    if (!(arg instanceof Cons)) {
			err("%s is not a list", arg);
		    }

		    Cons cons = (Cons)arg;
		    return cons.car();
		}

		public String toString() {
		    return "CAR";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv; 
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		    if(args.length == 2) {
			CronoTypeVar unknown = args[0].type().clone();
			CronoTypeVar type = new CronoTypeVar();
			CronoTypeVar known = new CronoTypeVar(type);
			unknown.addVar(args[1].type().clone());
			known.addVar(type);

			CronoTypeConstraint[] ret = new CronoTypeConstraint[2];
			ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
			ret[1] = new CronoTypeConstraint(ctv.clone(), known);

			return ret;
		    }
		    return null;
		}

	    }),
	CDR(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length > 1) {
			err("too many arguments given to CDR: %s", Arrays.toString(args));
		    } else if (args.length < 1) {
			err("too few arguments given to CDR: %s", Arrays.toString(args));
		    }

		    CronoType arg = args[0];

		    if (!(arg instanceof Cons)) {
			err("%s is not a list", arg);
		    }

		    Cons cons = (Cons)arg;
		    return cons.cdr();
		}

		public String toString() {
		    return "CDR";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		    if(args.length == 2) {
			CronoTypeVar type = new CronoTypeVar();

			CronoTypeVar unknown = args[0].type().clone();
			CronoTypeVar known = new CronoTypeVar(type);
			unknown.addVar(args[1].type().clone());
			known.addVar(new CronoTypeVar(type));

			CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

			ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
			ret[1] = new CronoTypeConstraint(ctv.clone(), known);

			return ret;
		    }
		    return null;
		}
	    }),
	QUOTE(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length > 1) {
			err("too many arguments given to QUOTE: %s", Arrays.toString(args));
		    } else if (args.length < 1) {
			err("too few arguments given to QUOTE: %s", Arrays.toString(args));
		    }

		    return args[0];
		}

		public String toString() {
		    return "'";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    return null;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    return null;
		}
	    }),
	DEFINE(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 3) {
			err("too few arguments given to DEFINE: %s", Arrays.toString(args));
		    }
		    if (!(args[0] instanceof Symbol)) {
			err("DEFINE: the name of a function must be a symbol, not %s", args[0]);
		    }
		    Symbol name = (Symbol)args[0];
		    LambdaFunction lambda = (LambdaFunction)LAMBDA.function.run(copyOfRange(args,1,args.length), environment);
		    environment.put(name, lambda);
		    return lambda;
		}

		public String toString() {
		    return "DEFINE";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		   			
		    if(args.length == 4) { 
			CronoTypeVar unknown = null;

			// get variables (arguments)
			Iterator<CronoType> it = ((Cons) args[1]).iterator();
			while(it.hasNext()) {
			    if(unknown == null) {
				unknown = it.next().type().clone();

			    } else {
				unknown.addVar(it.next().type().clone());
			    }			  
			}

			// get function
			if(unknown == null) {
			    unknown = args[2].type().clone();
			} else {
			    unknown.addVar(args[2].type().clone());
			}


			CronoTypeConstraint[] ret = new CronoTypeConstraint[3];
			ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
			ret[1] = new CronoTypeConstraint(ctv.clone(), args[0].type().clone());
			ret[2] = new CronoTypeConstraint(args[args.length-1].type().clone(), ctv.clone());

			return ret;
		    }
		    return null;
		}

	    }),
	LAMBDA(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 2) {
			err("too few arguments given to LAMBDA: %s", Arrays.toString(args));
		    }
		    if (!(args[0] instanceof Cons)) {
			err("LAMBDA: lambda list must be a cons, not %s", args[0]);
		    }
		    List<Symbol> argList = new ArrayList<Symbol>();
		    Cons largs = (Cons)args[0];
		    for(CronoType ct : largs) {
			if (ct instanceof Symbol) {
			    argList.add((Symbol)ct);
			} else {
			    err("LAMBDA: a lambda list may only contain symbols, not %s", ct);
			}
		    }
		    List<CronoType> body = new ArrayList<CronoType>();
		    for(int i = 1; i < args.length; i++) {
			body.add(args[i]);
		    }
		    return new LambdaFunction(body, argList, environment);
		}

		public String toString() {
		    return "\\";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();

		    if(args.length >= 3) {
			CronoTypeConstraint[] ret = new CronoTypeConstraint[args.length - 1];

			if(!(args[0] instanceof Cons)) {
			    throw new IllegalStateException();
			}

			ret[0] = new CronoTypeConstraint(ctv.clone(), args[1].type().clone()); // the result of the lambda is equal to the result of the 
			Iterator<CronoType> it = ((Cons) args[0]).iterator();
			int i = 2;
			while(it.hasNext()) {
			    ret[i-1] = new CronoTypeConstraint(it.next().type().clone(), args[i++].type().clone());
			}
			
			return ret;
		    }
		    return null;
		}
	    }),
	LET(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 2) {
			err("too few arguments to LET: %s", Arrays.toString(args));
		    }
		    if (!(args[0] instanceof Cons)) {
			err("LET: substitution list must be a cons, not %s", args[0]);
		    }
		    Cons subList = (Cons)args[0];
		    // Clone current environment and put in LambdaFunction to return.
		    Environment env = new Environment(environment);
		    for(CronoType ct : subList) {
			if (!(ct instanceof Cons)) {
			    err("LET: expected Cons in substitution list, not %s", args[0]);
			}
			Cons sub = (Cons)ct;
			CronoType car = sub.car();
			if (!(car instanceof Symbol)) {
			    err("LET: can only substitute symbols, found %s", args[0]);
			}
			Symbol key = (Symbol)car;
			if (key.equals(Symbol.valueOf("t"))) {
			    err("LET: T is a constant, may not be used as a variable");
			}
			CronoType cdr = sub.cdr();
			if (!(cdr instanceof Cons)) {
			    err("LET: substitution list must have two elements");
			}
			CronoType val = Interpreter.eval(((Cons)cdr).car(), environment);
			env.put(key, val);
		    }
		    List<CronoType> body = Arrays.asList(copyOfRange(args, 1, args.length));
		    LambdaFunction lambda = new LambdaFunction(body);
		    return Interpreter.run(lambda, env);
		}

		public String toString() {
		    return "LET";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		    if (args.length == 3) {
			CronoTypeConstraint[] ret = new CronoTypeConstraint[1];

			CronoTypeVar right = args[0].type().clone();
			right.addVar(args[1].type().clone());
			right.addVar(args[2].type().clone());

			ret[0] = new CronoTypeConstraint(ctv.clone(), right);

			return ret;
		    }
		    return null;
		}
	    }),
	IF(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 3) {
			err("too few arguments to IF: %s", Arrays.toString(args));
		    }
		    if (args.length > 3) {
			err("too many arguments to IF: %s", Arrays.toString(args));
		    }
		    if (Interpreter.eval(args[0], environment) == NIL) {
			return Interpreter.eval(args[2], environment);
		    }else{
			return Interpreter.eval(args[1], environment);
		    }
		}

		public String toString() {
		    return "IF";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();

		    CronoTypeConstraint ret[] = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known   = new CronoTypeVar(true);
		
		    CronoTypeVar same = new CronoTypeVar();

		    for(int i = 1; i<args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(same);
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		}
	    }),
	ADD(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    long sum = 0;

		    String str ="";
		    double sumD = 0;
		    int type = 0;

		    for (CronoType ct : args){
			if(ct instanceof CronoString) {
			    str+=((CronoString)ct).str.replace("\"","");
			    type = 0;
			}
			else if ((ct instanceof CronoNumber)) {
			    sum += ((CronoNumber)ct).num;
			    type = 1;
			}
			else if ((ct instanceof CronoDouble)) {
			    sumD+=((CronoDouble)ct).num;
			    type = 2;
			}
			else {
			    err("%s is not a number nor a string", ct);
			}		
		    }

		    if(type==1) {
			return new CronoNumber(sum);
		    } 
		    else if(type==0) {
			return new CronoString("\""+str+"\"");
		    }
		    else {
			return new CronoDouble(sumD);
		    }
		   
		}

		public String toString() {
		    return "+";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		  
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);
				
		    for(int i = 1; i < args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(1));
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		}
	    

	    }),
	SUB(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 1) {
			err("too few arguments to -: %s", Arrays.toString(args));
		    } else if (args.length == 1) {
			if((args[0] instanceof CronoNumber)) {
			    return CronoNumber.valueOf(-((CronoNumber)args[0]).num);
			} 
			else if((args[0] instanceof CronoDouble)) {
			    return new CronoDouble(-1*((CronoDouble)args[0]).num);
			}
			else {
			    err("%s is not a number", args[0]);
			}
		    }

		    if((args[0] instanceof CronoNumber)) {
			long difference = ((CronoNumber)args[0]).num;

			for (CronoType ct : Arrays.copyOfRange(args, 1, args.length)) {
			    if (!(ct instanceof CronoNumber)){
				err("%s is not a number", ct);
			    }
			    else {
				difference -= ((CronoNumber)ct).num;
			    }
			}

			return new CronoNumber(difference);
		    }
		    else if((args[0] instanceof CronoDouble)) {
			double differenceD = ((CronoDouble)args[0]).num;
			
			for (CronoType ct : Arrays.copyOfRange(args, 1, args.length)) {
			    if (!(ct instanceof CronoDouble)) {
				err("%s is not a double", ct);
			    }else {
				differenceD -= ((CronoDouble)ct).num;
			    }
			}

			return new CronoDouble(differenceD);
		    }
		    else {
			return null;
		    }
		}

		public String toString() {
		    return "-";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		   
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);

		    for(int i = 1; i < args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(1));
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		
		}
	    }),
	MULT(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    long product = 1;
		    double productD = 1;
		    int type=0;
		    for (CronoType ct : args) {
			if((ct instanceof CronoNumber)) {
			    product *= ((CronoNumber)ct).num;
			    type=0;
			}
			else if ((ct instanceof CronoDouble)) {
			    productD *= ((CronoDouble)ct).num;
			    type=1;
			}
			else {
			    err("%s is not a number", ct);          
			}
		    }
		    if(type==0) {
			return new CronoNumber(product);
		    } 
		    else {
			return new CronoDouble(productD);
		    }
		}

		public String toString() {
		    return "*";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		   
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);

		    for(int i = 1; i < args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(1));
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		
		}
	    }),
	DIV(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 2) {
			err("too few arguments to /: %s", Arrays.toString(args));
		    } else if (args.length > 2) {
			err("too many arguments to /: %s", Arrays.toString(args));
		    }
		    

		    if((args[0] instanceof CronoNumber) && (args[1] instanceof CronoNumber)) {
			CronoNumber n = (CronoNumber)args[0];
			CronoNumber d = (CronoNumber)args[1];
			return CronoNumber.valueOf(n.num / d.num);
		    }
		    else if((args[0] instanceof CronoDouble) && (args[1] instanceof CronoDouble)) {
			CronoDouble n = (CronoDouble)args[0];
			CronoDouble d = (CronoDouble)args[1];
			return new CronoDouble(n.num/d.num);
		    }
		    else {
			err("%s is not a number", args[1]);
		    }

		    return null;
		}

		public String toString() {
		    return "/";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		  
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);

		    for(int i = 1; i < args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(1));
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		
		}
	    }),
	EQ(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length == 0) {
			err("too few arguments to =: %s", Arrays.toString(args));
		    }

		    boolean eq = true;
		    CronoType prev = args[0];
		    for(int i = 1; eq && i < args.length; i++) {
			eq = prev.equals(args[i]);
			prev = args[i];
		    }

		    if (eq) {
			return Symbol.valueOf("T");
		    } else {
			return NIL;
		    }
		}

		public String toString() {
		    return "=";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}


		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		   
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = null; //args[0].type().clone();
		    CronoTypeVar known = null; //new CronoTypeVar(1);

		    for(int i = 0; i < args.length-1; i++) {
			if(unknown == null) {
			    unknown = args[i].type().clone();
			}else {
			    unknown.addVar(args[i].type().clone());
			}

			if(known == null) {
			    known = new CronoTypeVar(1);
			}
			else {
			    known.addVar(new CronoTypeVar(1));
			}
		    }

		    unknown.addVar(args[args.length - 1].type().clone());
		    known.addVar(new CronoTypeVar(true));

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		}	       
		
	    }),
	LT(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length == 0) {
			err("too few arguments to <: %s", Arrays.toString(args));
		    }

		    boolean lt = true;
		    if (!(args[0] instanceof CronoNumber)) {
			err("%s is not a number", args[0]);
		    }
		    CronoNumber prev = (CronoNumber)args[0];
		    for(int i = 1; lt && i < args.length; i++) {
			if (!(args[i] instanceof CronoNumber)) {
			    err("%s is not a number", args[i]);
			}
			CronoNumber n = (CronoNumber)args[i];
			lt = prev.num < n.num;
			prev = n;
		    }

		    if (lt) {
			return Symbol.valueOf("T");
		    } else {
			return NIL;
		    }
		}

		public String toString() {
		    return "<";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();

		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];

		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);

		    for(int i = 1; i < args.length-1; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(1));
		    }

		    unknown.addVar(args[args.length - 1].type().clone());
		    known.addVar(new CronoTypeVar(true));

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;
		}
	    }),
	SET(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 2) {
			err("too few arguments to SET: %s", Arrays.toString(args));
		    }
		    if (args.length > 2) {
			err("too many arguments to SET: %s", Arrays.toString(args));
		    }
		    if (!(args[0] instanceof Symbol)) {
			err("SET: can only use symbols as keys, got %s", args[0]);
		    }
		    String key = ((Symbol)args[0]).toString();
		    CronoType condition = Interpreter.eval(args[1], environment);
		    boolean value = (condition != NIL);

		    // possibly turn CronoOptions into an enum and use reflection to
		    //       set values.
		    // Wow, this is annoying to update.
		    if ("dprint_enable".equals(key)) {
			CronoOptions.DPRINT_ENABLE = value;
		    } else if ("dprint_indent".equals(key)) {
			CronoOptions.DPRINT_INDENT = value;
		    } else if ("dprint_show_atom_eval".equals(key)) {
			CronoOptions.DPRINT_SHOW_ATOM_EVAL = value;
		    } else if ("environment_show".equals(key)) {
			CronoOptions.ENVIRONMENT_SHOW = value;
		    } else if ("environment_show_builtin".equals(key)) {
			CronoOptions.ENVIRONMENT_SHOW_BUILTIN = value;
		    } else if ("environment_dynamic".equals(key)) {
			CronoOptions.ENVIRONMENT_DYNAMIC = value;
		    } else if ("environment_show_types".equals(key)) {
			CronoOptions.ENVIRONMENT_SHOW_TYPES = value;
		    } else if ("environment_multiline".equals(key)) {
			CronoOptions.ENVIRONMENT_MULTILINE = value;
		    } else if ("lambda_show_closure".equals(key)) {
			CronoOptions.LAMBDA_SHOW_CLOSURE = value;
		    } else if ("parser_dprint".equals(key)) {
			CronoOptions.PARSER_DPRINT = value;
		    } else if ("infer_print".equals(key)) {
			CronoOptions.INFER_PRINTING = value;
		    } else if ("infer_cycling".equals(key)) {
			CronoOptions.INFER_CYCLING = value;
		    }

		    return NIL;
		}

		public String toString() {
		    return "SET";
		}

		public boolean evalArgs() {
		    return false;
		}

		public CronoTypeVar type() {
		    return null;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    return null;
		}
	    }),
	LOAD(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    if (args.length < 0) {
			err("too few arguments to LOAD: %s", Arrays.toString(args));
		    }
		    for(CronoType ct : args) {
			if (!(ct instanceof Symbol)) {
			    err("LOAD: %s is not a symbol", ct);
			}

			try {
			    String filename = ((Symbol)ct).toString();
			    InputStream in = new FileInputStream(filename);
			    try{
				Parser parser = new Parser(in); //TODO replace
				parser.prog(environment); // TODO replace
			    } catch (Exception e) {
			    
			    }
			} catch (FileNotFoundException e) {
			    // Do nothing.
			}
		    }

		    return NIL;
		}

		public String toString() {
		    return "LOAD";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    return null; 
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    return null;
		}
	    }),
	PRINT(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    for(int i = 0; i < args.length; i++) {
			System.out.print(args[i]);
			if (i < args.length - 1) {
			    System.out.print(" ");
			}
		    }
		    System.out.print("\n");

		    return NIL;
		}

		public String toString() {
		    return "PRINT";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    return null;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    return null;
		}
	    }),
	COND(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) 
		{
		    if (args.length < 4){
			err("too few arguments to COND: %s", Arrays.toString(args));
		    }

		    if (args.length > 4) 
			{
			    err("too many arguments to COND: %s", Arrays.toString(args));
			}

		    ArrayList<CronoType> equation = new ArrayList<CronoType>();
		    Interpreter.apply(args[0], args[3],equation);
		    CronoType resultIf = Interpreter.run(new LambdaFunction(equation));
	        		
		    if(resultIf.toString().equalsIgnoreCase("T"))
			{
			    ArrayList<CronoType> l = new ArrayList<CronoType>();
			    l.add(args[1]);
			    l.add(args[3]);
			    Cons cons = new Cons(l);
			    System.out.println("DSSSSSSSSSSSSSSS");
			    ArrayList<CronoType> alist = new ArrayList<CronoType>();
			    alist.add(cons);
			    LambdaFunction f = new LambdaFunction(alist,environment);
			    //System.out.println(f);
			    return Interpreter.run(f,environment);
			}
		    else
			{
			    System.out.println("DSSSSSSSSSSSSSSS");
			    ArrayList<CronoType> l = new ArrayList<CronoType>();
			    l.add(args[2]);
			    l.add(args[3]);
			    Cons cons = new Cons(l);
			    ArrayList<CronoType> alist = new ArrayList<CronoType>();
			    alist.add(cons);
			    LambdaFunction f = new LambdaFunction(alist,environment);
			    return Interpreter.run(f,environment);	
			}
		}

		public String toString() {
		    return "COND";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		  
		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];
		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(true);
		    CronoTypeVar same = new CronoTypeVar();

		    Cons symbol = new Cons();

		    for(int i = 1; i<args.length; i++) {
			if(i != 3) {
			    unknown.addVar(args[i].type().clone());
			    known.addVar(same);
			}
		    }

		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);

		    return ret;


		}
	    }),
	PRED(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) 
		{
		    if (args.length < 1) 
		    	{
		            err("too few arguments to PRED: %s", Arrays.toString(args));
		        }
		    if (args.length > 1) 
		        {
		            err("too many arguments to PRED: %s", Arrays.toString(args));
		        }
		       
		    if(args[0] instanceof CronoNumber)
		        {
			    return new CronoNumber(((CronoNumber)args[0]).num - 1);
		        }
		    else if(args[0] instanceof Symbol)
		        {
			    CronoType envResult = environment.get((Symbol)args[0]);
			    if(envResult!=null)
		        	{
				    CronoNumber numb = (CronoNumber)envResult;
				    return new CronoNumber(numb.num-1);
		        	}
			    else
		        	{
				    err("%s is not a CronoNumber nor is it in the environment.", args[0]);
				    return Nil.NIL;
			        }
		        }
		    else
		        {
			    err("%s is not a CronoNumber.", args[0]);
			    return Nil.NIL;
		        }
		        
		        
		}

		public String toString() {
		    return "PRED";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    if(ctv == null) ctv = new CronoTypeVar();
		    return ctv;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    ctv = new CronoTypeVar();
		 
		    CronoTypeVar unknown = args[0].type().clone();
		    CronoTypeVar known = new CronoTypeVar(1);

		    for(int i = 1; i<args.length; i++) {
			unknown.addVar(args[i].type().clone());
			known.addVar(new CronoTypeVar(i));
		    }

		    CronoTypeConstraint[] ret = new CronoTypeConstraint[2];
		    ret[0] = new CronoTypeConstraint(ctv.clone(), unknown);
		    ret[1] = new CronoTypeConstraint(ctv.clone(), known);
		  
		    return ret;
		}
	    }),
	QUIT(new CronoFunction() {
		private CronoTypeVar ctv;

		public CronoType run(CronoType[] args, Environment environment) {
		    System.out.println("Good-bye");
		    System.exit(0);
		    return null;
		}

		public String toString() {
		    return "QUIT";
		}

		public boolean evalArgs() {
		    return true;
		}

		public CronoTypeVar type() {
		    return null;
		}

		public CronoTypeConstraint[] constraint(CronoType[] args) {
		    return null;
		}
	    }),
	;

    public final CronoFunction function;

    private CronoFunctions(CronoFunction function) {
	this.function = function;
    }

    public static String[] copyOfRange(String[] input, int start, int end) {
	String[] output = new String[end-start];
	for (int i = start; i < end && i < input.length; i++) {
	    output[i-start] = input[i];
	}
	return output;
    }

    public static CronoType[] copyOfRange(CronoType[] input, int start, int end) {
	CronoType[] output = new CronoType[end-start];
	for (int i = start; i < end && i < input.length; i++) {
	    output[i-start] = input[i];
	}
	return output;
    }
}
