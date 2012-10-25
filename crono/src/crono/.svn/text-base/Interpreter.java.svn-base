package crono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoFunction;
import crono.AbstractSyntax.CronoType;
import crono.AbstractSyntax.Function;
import crono.CronoOptions;

import static crono.Nil.NIL;
import static crono.CronoOptions.err;

public class Interpreter {
    private static  CronoType baseCaseTerm = Nil.NIL;
    private static  CronoType recursiveTerm = Nil.NIL;
    private static boolean isCombinator = false;

    private static final Environment globals = getDefaultEnvironment();

    /**
     * Sets up default environment with fundamental functions in scope.
     */
    public static Environment getDefaultEnvironment() {
	Environment result = new Environment();

	for(CronoFunctions cfs : CronoFunctions.values()) {
	    result.put(Symbol.valueOf(cfs.function.toString()), cfs.function);
	}

	return result;
    }

    public static CronoType run(LambdaFunction function) {
	return run(function, function.environment.update(globals));
    }

    public static CronoType run(LambdaFunction function, Environment environment) {
	CronoType result = NIL;

	for(CronoType statement : function.statements) {
	    if(CombinatorFunction.isCombinatorFunctionShallowSearch(statement)) {
		isCombinator = true;
		ArrayList<CronoType> combFunc = new ArrayList<CronoType>();
		CronoType copyStatement = copyOf(statement);
		combFunc.add(copyStatement);
		result = run(new CombinatorFunction(combFunc,environment), 0, environment);
		result = eval(result, environment);
		isCombinator = false;
	    }
	    else {
		  
		result = eval(statement, environment);
	    }
	    
	  
	}

	return result;
    }

    public static CronoType eval(CronoType statement, Environment environment) {

	CronoType result = NIL;

	if (statement instanceof Atom) {
	    if (CronoOptions.DPRINT_SHOW_ATOM_EVAL) {
		CronoOptions.dprint("Evaluating: %s\n", statement);
		CronoOptions.DPRINT_I++;

		if (CronoOptions.ENVIRONMENT_SHOW) {
		    if (environment.toString().length() > 0) {
			CronoOptions.dprint("Environment:\n%s\n", environment);
		    } else {
			CronoOptions.dprint("Environment: empty\n");
		    }
		}
	    }
	    if (statement instanceof Symbol && environment.containsKey((Symbol) statement)) {
		result = environment.get((Symbol)statement);
	    } else {
		result = statement;
	    }
	    if (CronoOptions.DPRINT_SHOW_ATOM_EVAL) {
		CronoOptions.DPRINT_I--;
		CronoOptions.dprint("Result: %s\n", result);
	    }
	} else if (statement instanceof Cons) {
	    CronoOptions.dprint("Evaluating: %s\n", statement);
	    CronoOptions.DPRINT_I++;

	    /* if (CronoOptions.ENVIRONMENT_SHOW) {
	       if (environment.toString().length() > 0) {
	       CronoOptions.dprint("Environment:\n%s\n", environment);
	       } else {
	       CronoOptions.dprint("Environment: empty\n");
	       }
	       }*/

	    Cons cons = (Cons)statement;
	    if (cons != NIL) {
		// Lookup function.
		CronoType f = eval(cons.car(), environment);
		
		Function function = null;
		if (f instanceof Symbol) {
		    Symbol funcKey = (Symbol)f; 
		    CronoType val = environment.get(funcKey);
		    if (val instanceof Function) {
			function = (Function)val;
		    } else {
			err("%s is not a function", val);
		    }
		} else if (f instanceof Function) {
		    function = (Function)f;
		} else {
		    err("%s is not a function name.", f);
		}

		// Evaluate members.
		if(!function.toString().equalsIgnoreCase("cond")) {
		    CronoType cdr = cons.cdr();
		    List<CronoType> argList = new ArrayList<CronoType>();
		    if (cdr instanceof Cons) {
			Cons args = (Cons)cdr;
			for(CronoType arg : args) {
			    // Perform argument evaluation.
			    if (function.evalArgs()) {
				arg = eval(arg, environment);
				// Perform substitution.
				if (arg instanceof Symbol &&
				    environment.containsKey((Symbol)arg)) {
				    arg = environment.get((Symbol)arg);
				}
			    }
			    argList.add(arg);
			}
		    } else {
			if (function.evalArgs()) {
			    cdr = eval(cdr, environment);
			}
			argList.add(cdr);
		    }

		    // Call function with arguments.
		    if (function instanceof LambdaFunction) {
			LambdaFunction lf = (LambdaFunction)function;
			Environment env;
			if (CronoOptions.ENVIRONMENT_DYNAMIC) {
			    // Copy current environment.
			    env = new Environment(environment);
			} else {
			    // Copy lambda's environment.
			    env = new Environment(lf.environment);
			}
			// Add argument mapping.
			Iterator<Symbol> keyit = lf.args.iterator();
			Iterator<CronoType> valit = argList.iterator();
			while(keyit.hasNext() && valit.hasNext()) {
			    env.put(keyit.next(), valit.next());
			}
			if (keyit.hasNext()) {
			    err("too few arguments given to function: %s", function);
			}
			if (valit.hasNext()) {
			    err("too many arguments given to function: %s", function);
			}
			result = run(lf, env);
		    } else if (function instanceof CronoFunction) {
			CronoFunction cf = (CronoFunction)function;
			result = cf.run(argList.toArray(new CronoType[] {}), environment);
		    }
		}
		else
		    {
			Cons args = (Cons)cons.cdr();
			CronoType args1If = args.car();
			args = (Cons)args.cdr();
			args = (Cons)args.cdr();
			args = (Cons)args.cdr();
			CronoType args3If = args.car();
			//System.out.println(args);
        	
			ArrayList<CronoType> equation = new ArrayList<CronoType>();
			Interpreter.apply(args1If, args3If,equation);
			//System.out.println("equation: "+equation);
			args = (Cons)cons.cdr();
			equation.add(Nil.NIL);
			ArrayList<CronoType> al = new ArrayList<CronoType>();
			al.add(new Cons(equation));
			//System.out.println("result if: "+Interpreter.run(new LambdaFunction(al), environment));
			if(Interpreter.run(new LambdaFunction(al), environment).toString().equalsIgnoreCase("T"))
			    {
				args = (Cons)args.cdr();
				CronoType arg1 = args.car();
				args = (Cons)args.cdr();
				args = (Cons)args.cdr();
				CronoType arg3 = args.car();
				Cons rest = (Cons)args.cdr();
				ArrayList<CronoType> list = new ArrayList<CronoType>();
				apply(arg1, arg3, list);
        		
				while(!rest.car().equals(Nil.NIL))
				    {
					list.add(rest.car());
					rest = (Cons)rest.cdr();
				    }
        		
				list.add(Nil.NIL);
				Cons c = new Cons(list);
				ArrayList<CronoType> listR = new ArrayList<CronoType>();
				listR.add(c);
				LambdaFunction lf = new LambdaFunction(listR,environment);
				result = Interpreter.run(lf, environment);
			    }
			else
			    {
				args = (Cons)args.cdr();
				CronoType arg1 = args.car();
				args = (Cons)args.cdr();
				CronoType arg2 = args.car();
				args = (Cons)args.cdr();
				CronoType arg3 = args.car();
				Cons rest = (Cons)args.cdr();
				ArrayList<CronoType> list = new ArrayList<CronoType>();
				apply(arg2, arg3, list);
        		
				while(!rest.car().equals(Nil.NIL))
				    {
					list.add(rest.car());
					rest = (Cons)rest.cdr();
				    }
        		
				list.add(Nil.NIL);
				Cons c = new Cons(list);
				ArrayList<CronoType> listR = new ArrayList<CronoType>();
				listR.add(c);
				LambdaFunction lf = new LambdaFunction(listR,environment);
				result = Interpreter.run(lf, environment);
				
			    }
		    }
	    }

	    if(result.toString().equalsIgnoreCase("T") || result.toString().equals("*")) {
		isCombinator = false;
	    }

	    CronoOptions.DPRINT_I--;
	    CronoOptions.dprint("Result: %s\n", result);
	   
	     if(isCombinator) {
			List finalResult = new ArrayList<CronoType>();
			Cons copy = (Cons)copyOf(recursiveTerm);
			apply(copy, baseCaseTerm, finalResult);
			finalResult.add(Nil.NIL);

			baseCaseTerm = new Cons(finalResult);
			CronoOptions.dprint("========> Final Evaluation Term: %s\n", baseCaseTerm.toString());
		}
	    
	} else {
	    err("Encountered statement of unknown type: %s\n\t%s\n",
		statement.getClass().getName(), statement);
	}

	return result;
    }

    public static CronoType run(CombinatorFunction function, int step, Environment env) 
    {

	isCombinator = true;

	CronoType statement = function.statements.get(0);

	CronoType result=statement;
	while(CombinatorFunction.isCombinatorFunctionDeepSearch(result))
	    {
		step++;
		Cons search = (Cons)result;
		List<CronoType> list = new ArrayList<CronoType>();
		while(!CombinatorFunction.isCombinatorFunctionShallowSearch(search))
		    {
			if(search.car() instanceof Cons && CombinatorFunction.isCombinatorFunctionDeepSearch(search.car())) {
			    ArrayList<CronoType> l = new ArrayList<CronoType>();
			    l.add(search.car());
			    list.add(Interpreter.run(new CombinatorFunction(l),0,env));
			}
			else {
			    list.add(search.car());
			}

			search = (Cons)search.cdr();

			if(search.equals(Nil.NIL)) {
			    break;
			}
		    }
			
		CronoType reduced = Nil.NIL;
			
		if(search.car().equals(Nil.NIL)) {
		    list.add(search.car());
		}
		else {
		    if(search.car() instanceof CombSymbol) {

			reduced = Interpreter.reduce(search,env);
			list.add(reduced);
		    }
		    else if(search.car() instanceof Cons) {
			reduced = Interpreter.reduce(search.car(),env);
			
			if(reduced instanceof Cons) {
			    Cons rest = (Cons)reduced;
					
			    while(!rest.car().equals(Nil.NIL)) {
				list.add(rest.car());
				rest= (Cons)rest.cdr();
			    }
			}
			else {
			    list.add(reduced);
			}
			search = (Cons)search.cdr();
				
			while(search.car()!=Nil.NIL) {
			    list.add(search.car());
			    search = (Cons)search.cdr();
			}

			list.add(Nil.NIL);			
		    }

		}

		result = new Cons(list);
		if(((Cons)((Cons)result).cdr()).car().equals(Nil.NIL)) {
		    result = ((Cons)result).car();
		}
	    }
	if(result instanceof Cons)
	    {
		if(((Cons)((Cons)result).cdr()).car().equals(Nil.NIL))
		    return ((Cons)result).car();
		else
		    return result;
	    }
	else
	    return result;
    }

    public static CronoType reduce(CronoType statement, Environment env) 
    {
	if(statement instanceof Cons)
	    {
		CombSymbol symbol = (CombSymbol)(((Cons)statement).car());
		Cons arguments = (Cons)((Cons)statement).cdr();
			
		if(symbol.equals(new CombSymbol("<k>")))
		    {
			return arguments.car();
		    }
		else if(symbol.equals(new CombSymbol("<S>")))
		    {
			CronoType argument1 = arguments.car();
			CronoType argument2 = ((Cons)(arguments.cdr())).car();
			CronoType argument3 = ((Cons)((Cons)(arguments.cdr())).cdr()).car();
			CronoType rest = ((Cons)((Cons)(arguments.cdr())).cdr()).cdr();   //Nil Usually

			List result = new ArrayList<CronoType>();
			apply(argument1, argument3, result);
			List result2 = new ArrayList<CronoType>();
			apply(argument2,argument3,result2,result);

			while(!rest.equals(Nil.NIL)) {
			    result.add(((Cons)rest).car());
			    rest = ((Cons)rest).cdr();
			}
			    

			result.add(Nil.NIL);
			Cons consR = new Cons(result);
			return consR;
		    }
		else if(symbol.equals(new CombSymbol("<B>")))
		    {
			CronoType argument1 = arguments.car();
			CronoType argument2 = ((Cons)(arguments.cdr())).car();
			CronoType argument3 = ((Cons)((Cons)(arguments.cdr())).cdr()).car();
			CronoType rest = ((Cons)((Cons)(arguments.cdr())).cdr()).cdr();

		      
			List result = new ArrayList<CronoType>();
			result.add(argument1);
			List result2 = new ArrayList<CronoType>();
			apply(argument2, argument3,result2,result);
			while(!rest.equals(Nil.NIL))
			    {
				result.add(((Cons)rest).car());
				rest = ((Cons)rest).cdr();
			    }
			result.add(Nil.NIL);
			Cons consR = new Cons(result);

			return consR;
		    }
		else if(symbol.equals(new CombSymbol("<C>")))
		    {
			CronoType argument1 = arguments.car();
			CronoType argument2 = ((Cons)(arguments.cdr())).car();
			CronoType argument3 = ((Cons)((Cons)(arguments.cdr())).cdr()).car();
			CronoType rest = ((Cons)((Cons)(arguments.cdr())).cdr()).cdr();
						
			List result = new ArrayList<CronoType>();
			result.add(argument1);
			result.add(argument3);
			result.add(argument2);
			while(!rest.equals(Nil.NIL))
			    {
				result.add(((Cons)rest).car());
				rest = ((Cons)rest).cdr();
			    }
			result.add(Nil.NIL);

			Cons consR = new Cons(result);
			return consR;
		    }
		else if(symbol.equals(new CombSymbol("<Y>"))) {
		   		
		    CronoType argument1 = arguments.car();
		    CronoType argument1Orig = copyOf(argument1);
		    CronoType func = getFunction((Cons)argument1);

		    arguments = (Cons)arguments.cdr();
		    CronoType argument2 = arguments.car();
		    
		    ArrayList<CronoType> l = new ArrayList<CronoType>();
		    apply(argument1, argument2, l);
		    l.add(Nil.NIL);
			
		    Cons c = new Cons(l);

		    ArrayList<CronoType> function = new ArrayList<CronoType>();
		    function.add(c);
		    CombinatorFunction cf = new CombinatorFunction(function);
						
		    CronoType reduced = Interpreter.run(cf, 0,env);
		    Cons arg1 = (Cons)((Cons)reduced).car();
			
		    reduced = ((Cons)reduced).cdr();

		    CronoType arg2 = ((Cons)reduced).car();
		    arg1.append(arg2);

		    ArrayList<CronoType> l2 = new ArrayList<CronoType>();
		    l2.add(func);
		    l2.add(argument2);
		    l2.add(Nil.NIL);
		    arg1.append(new Cons(l2));

		    Cons temp = arg1;
				
		    ArrayList<CronoType> iterate = new ArrayList<CronoType>();
		    temp = (Cons)temp.cdr();			
		    Cons equals = (Cons)temp.car();          //The Boolean test in the COND
		    temp = (Cons)temp.cdr();

		    CronoType baseCase = temp.car();          //baseCase = z

		    temp = (Cons)temp.cdr();
			
		    CronoType notBaseCase = temp.car();       //notBaseCase = (+ 1)

		    if(baseCase.toString().equals("1")) {
			baseCase = notBaseCase;
		    }

		    System.out.println("BAse Case: " + baseCase);
		    System.out.println("Not BAse Case: " + notBaseCase);


		    baseCaseTerm = baseCase;
		    recursiveTerm = notBaseCase;

		    temp = (Cons)temp.cdr();
		    temp = (Cons)temp.car();

		    Cons funcCons = temp;                    //The argument (5 arg) in the COND   //(pred (pred ... (pred x)...))

		    apply(equals,temp, iterate);
		    iterate.add(Nil.NIL);

		    Cons r = new Cons(iterate);
		  
		    ArrayList<CronoType> f = new ArrayList<CronoType>();
		    f.add(r);                               //(= 0 (pred (pred ... (pred (pred (pred x)))...)))

		    CronoType resultIf = run(new LambdaFunction(f),env);  // evaluates the condition in the COND: for example  (= 0 (pred x))
				 
		    if(resultIf.toString().equalsIgnoreCase("T")) {				
			List baseResult = new ArrayList<CronoType>();
			
			if(baseCase.toString().equals("*")) {
			   Cons copy = (Cons)copyOf(argument2);
			    baseResult.add(baseCase);
			    baseResult.add(new CronoNumber((long)1));
			    baseResult.add(copy);
			    baseResult.add(Nil.NIL);
			    //System.out.println("++++++++++++++++++++++++++++++++++++++++ " + baseResult);
			    return new Cons(baseResult);
			}
			else{
			    Cons consCopy = (Cons)copyOf(notBaseCase);
			    apply(consCopy, baseCase, baseResult);
			    baseResult.add(Nil.NIL);


			    return new Cons(baseResult);
			}
		    }
		    else			    {
			List result = new ArrayList<CronoType>();
			List resultYComb = new ArrayList<CronoType>();
			
			resultYComb.add(symbol);
			resultYComb.add(argument1Orig);
			resultYComb.add(funcCons);
			resultYComb.add(Nil.NIL);
			
			Cons yCombCons = new Cons(resultYComb);

			List yCombresult = new ArrayList<CronoType>();
			yCombresult.add(yCombCons);
			
				baseCaseTerm = baseCase;
				recursiveTerm = notBaseCase;

			CronoType answer = Interpreter.run(new CombinatorFunction(yCombresult),0,env);
			//	System.out.println("Constructing Answer: " + answer);

			if(baseCase.toString().equals("*"))
			    {
				if(argument2 instanceof Cons)					    {
				    Cons copy = (Cons)copyOf(argument2);
				    result.add(baseCase);
				    result.add(copy);
				}
				else					    {
				    result.add(baseCase);
				    result.add(argument2);
				}
				result.add(answer);
				result.add(Nil.NIL);

				return new Cons(result);
			    }
			else				    {
			    Cons copy = (Cons)copyOf(notBaseCase);
			    apply(copy, answer, result);
			    result.add(Nil.NIL);
			    return new Cons(result);
			}
		    }
		}
	    }	
	
	return statement;
    }
  
    public static void apply(CronoType base, CronoType arg, List<CronoType> l)
    {
	if(base instanceof Cons)
	    {
		Cons func = (Cons)base;
		while(!func.car().equals(Nil.NIL))
		    {
			l.add(func.car());
			func = (Cons)func.cdr();
		    }
		l.add(arg);
	    }
	else
	    {
		l.add(base);
		l.add(arg);
	    }
    }
    public static void apply(CronoType base, CronoType arg, List<CronoType> l,List<CronoType> f)
    {
	if(base instanceof Cons)
	    {
		Cons func = (Cons)base;
		func.append(arg);
		f.add(func);
	    }
	else
	    {
		l.add(base);
		l.add(arg);
		l.add(Nil.NIL);
		f.add(new Cons(l));
	    }
    }
  
    public static CronoType getFunction(Cons arg)
    {
	Cons temp = (Cons)arg.cdr();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.car();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.car();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.cdr();
	return temp.car();
    }
  
    private static CronoType copyOf(CronoType copy)
    {
	if(copy instanceof Symbol)
	    {
		return new Symbol(copy.toString());
	    }
	else if(copy instanceof CombSymbol)
	    {
		return new CombSymbol(copy.toString());
	    }
	else if(copy instanceof CronoNumber)
	    {
		return new CronoNumber(Long.parseLong(copy.toString()));
	    }
	else if(copy instanceof CronoDouble)
	    {
		return new CronoDouble(Double.parseDouble(copy.toString()));
	    }
	else if(copy instanceof CronoString)
	    {
		return new CronoString(copy.toString());
	    }
	else if(copy instanceof Cons)
	    {
		ArrayList<CronoType> result = new ArrayList<CronoType>();
		Cons cons = (Cons)copy;
		while(!cons.car().equals(Nil.NIL))
		    {
			if(cons.car() instanceof Cons)
			    result.add(copyOf(cons.car()));
			else
			    result.add(cons.car());
			cons = (Cons)cons.cdr();
		    }
		  
		result.add(Nil.NIL);
		Cons c = new Cons(result);
		return c;
	    }
	else
	    {
		return Nil.NIL;
	    }
    }
}
