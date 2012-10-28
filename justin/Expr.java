import java.util.ArrayList;

// expression class.  first parameter is function.  everything afterward is a list of arguments.
public class Expr extends SExpr {

	private SExpr op;
	private ArrayList<SExpr> args = null;
	
	// ****************************************
	// constructor used when parsing get the function.
    public Expr(SExpr op_) {
      op = op_;
      args = new ArrayList<SExpr>();    	
    }
	 
	// ****************************************
    // add argument. also used when parsing.
	public void AddArg(SExpr arg_) {
	  args.add(arg_);		
	}
	
	public String toString() {
		String result = "(";
		result += op;
		for( SExpr arg : args){			
			result += " " + arg;			
		}	
		result += ")";
		return result;
	}
	
	// *****************************************
	// replace method; replaces symbols that match parameter with argument value
	public boolean replaceParam(SymbolAtom parameter, SExpr argument) {
		
		for(int i = 0; i < this.args.size(); i++) {
		
			System.out.println("Inside replaceParam Expr");			
			if(!(this.args.get(i) instanceof NumericAtom)) {				
							
				// if not a symbolatom, and is an expr, recursively call replaceParam
				if( this.args.get(i) instanceof Expr){
					System.out.println("Inside if expr recursive call within replaceParam Expr");
					((Expr)(this.args.get(i))).replaceParam( parameter, argument);
				}
				else if( ((SymbolAtom)(this.args.get(i))).equals(parameter)) {
					System.out.println("Inside if within replaceParam Expr");
					this.args.set(i, argument);
				}
			
			}			
		}
		// after replacing symbols with argument, check if any symobls are remaining, if so,
		// return false so the Expr isn't evaluated
		for(int i = 0; i < this.args.size(); i++) {
			if(this.args.get(i) instanceof SymbolAtom)
				return false;
		}
		return true;			
	}
	
	
	
	// *****************************************
	// eval called during interpretation.
	
	public NumericAtom eval() {
	  Object f = op.eval();
		
	  // --------------------- addition -------------------------------
	  if(f.equals("+")) {
		  if(args.size() == 0) {
			  throw new IllegalStateException("function + expected at least 1 argument. got " + args.size());
		  }
		  
		  int sum = 0;
		  
		  for(SExpr e: args) {
			  Object evald = e.eval();
			    
			  if(evald instanceof Integer) {
                // if worked out to be a double, then ok.
				  
			  // if worked out to a numeric atom, instead of a number, re-evaluate into just a number object.
			  } else if(evald instanceof NumericAtom ) {
				  evald = ((NumericAtom) evald).eval();
				  
			  } else {
			    throw new IllegalStateException("function + took non-numeric argument: " + evald.getClass());	  
					  
			  }
			  
			  // argument ok, so add it.
			  int d = Integer.parseInt( evald.toString() );
			  
			  sum = sum + d;
			  
		  }
		  
		  return new NumericAtom(sum);
		  
      // ----------------- subtraction ------------------------
	  } else if(f.equals("-")) {
		  if(args.size() != 2) {
			  throw new IllegalStateException("function - expected exactly 2 arguments, got " + args.size());
		  }
		  
		  
		  int diff = 0;
		  
		  // evaluate the two arguments.  boil them down into doubles.
		  Object arg1evald = args.get(0).eval();
		  Object arg2evald = args.get(1).eval();
		  
		  // handle arg1
		  if( arg1evald instanceof Integer) {
		  			  // ok 
			  
		  }
		  else if(arg1evald instanceof NumericAtom) {
			  arg1evald = ((NumericAtom) arg1evald).eval();
			  
		  } else {
			  throw new IllegalStateException("function - argument 1 did not eval to Double.");			  
			  
		  }

		  
		  // handle arg2
		  if( arg2evald instanceof Integer){ 
			  // ok 

		  } else if(arg2evald instanceof NumericAtom) {
			  arg2evald = ((NumericAtom) arg2evald).eval();
			  
		  } else {
			  throw new IllegalStateException("function - argument 2 did not eval to Double.");			  
			  
		  }

		  
		  Integer d1 = (Integer) arg1evald;
		  Integer d2 = (Integer) arg2evald;
		  
		  return( new NumericAtom(d1 - d2) );
		  
	      // ----------------- subtraction ------------------------
	  } else if(f.equals("*")) {
		  if(args.size() != 2) {
			  throw new IllegalStateException("function * expected exactly 2 arguments, got " + args.size());
		  }
		  
		  
		  int prod = 0;
		  
		  // evaluate the two arguments.  boil them down into doubles.
		  Object arg1evald = args.get(0).eval();
		  Object arg2evald = args.get(1).eval();
		  
		  // handle arg1
		  if( arg1evald instanceof Integer) {
			  // ok 
			  
		  }
		  else if(arg1evald instanceof NumericAtom) {
			  arg1evald = ((NumericAtom) arg1evald).eval();
			  
		  } else {
			  throw new IllegalStateException("function - argument 1 did not eval to Double.");			  
			  
		  }

		  
		  // handle arg2
		  if( arg2evald instanceof Integer){ 
			  // ok 

		  } else if(arg2evald instanceof NumericAtom) {
			  arg2evald = ((NumericAtom) arg2evald).eval();
			  
		  } else {
			  throw new IllegalStateException("function - argument 2 did not eval to Double.");			  
			  
		  }

		  
		  Integer d1 = (Integer) arg1evald;
		  Integer d2 = (Integer) arg2evald;
		  
		  return( new NumericAtom(d1 * d2) );
		  		  
	  // ------------------------- car or first --------------------------	  
	  } else if(f.equals("car") || f.equals("first")  ) {
		  // car/first expect a single argument.
		  if(args.size() != 1) {
			  throw new IllegalStateException("function car expected 1 argument, got " + args.size());
		  }
		  
		  // evaluate the first argument
		  SExpr arg1 = args.get(0);
		  SExpr evald = (SExpr) arg1.eval();
		  
		  // make sure valid list to do car on
		  if(! (evald instanceof AtomList ) ) {
			  throw new IllegalStateException("function car expects argument of AtomList ");			  
			  
		  }
		  
		  // evaluate
		  /*AtomList l = (AtomList) evald;
		 
		  if(l.size() == 0) {
			  throw new IllegalStateException("function car's list argument was empty.");			  
			   
		  } else {
			  // car wants first item.
			  return l.GetItem(0);	  
	  
		  }*/
	return new NumericAtom(0);	  
	  } else {
		  throw new IllegalStateException("unknown function " + f);
		  
	  }
	  
	}

}
