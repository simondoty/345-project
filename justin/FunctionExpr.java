// type function - includes as data members: parameters, body, arguments

public class FunctionExpr extends SExpr {
    
    //name of the function (if it has one)
	//private SymbolAtom name;	
	//this could change to a list of parameters
	private SymbolAtom parameter;
	
	
	// need to flush out implementation of how to set this (through parsing) 
	private boolean hasParent;
	
	
	//private Environment currentEnvironment;
	//private FunctionExpr parent;
	private String environment;
	
	// the body of the function represented as an expr() 	
	private SExpr body;
	
	private SExpr argument;	
	
	public Object eval() {
		return this;
	}
	
	// getter method
	public SExpr getBody () {
		return this.body;
	}
	
	//constructor
	public FunctionExpr (SymbolAtom parameter, SExpr body, SExpr argument) {
		System.out.println("Inside Function Constructor");
		
		/********************************  SET PARAMETER *****************************/
		// parameter has to be a SymbolAtom
		this.parameter = parameter; // this is a SymbolAtom with "x"
		
		
		/********************************  GET ARGUMENT *****************************/
		
		// SExpr argument could be three things: Expr, Atom, FunctionExpr
		// if argument is an Expr, evaluate the expression. Could return an SExpr, or Atom
		if(argument instanceof Expr) {
		    System.out.println("argument is instance of Expr - inside if");	    
			this.argument = ((Expr)(argument)).eval(); //downcast from SExpr to Expr
		}
		
		// if function expr, evaluate the function expression first
		if(argument instanceof FunctionExpr) {
			System.out.println("argument is instance of FunctionExpr - inside if");	    
			this.argument = ((Expr)(argument)).eval(); //downcast from SExpr to Expr
		}
		
		// argument must be an atom
		else {
		     System.out.println("argument is instance of Atom - inside if");	 
			 this.argument = argument;
		}
		
	
		/********************************  SET BODY *****************************/
		
		// body can either be a FunctionExpr, Atom, or Expr
		this.body = body;		
	    boolean canEvaluate = false;
	    
		if (this.body instanceof Expr) {					
			canEvaluate = ((Expr)(this.body)).replaceParam(this.parameter, this.argument);	//downcast from SExpr to Expr
		} 
		
		// if body is an Atom, only replace if it's a SymbolAtom
		else if (this.body instanceof SymbolAtom) {					
			if(((SymbolAtom)(this.body)).equals(parameter)) {
				//System.out.println("Inside body.equals parameter....");		
				canEvaluate = true;
				this.body = argument;
			}				
		}	
		
		// eval only if Expr has no unresolved symbols
	    if( this.body instanceof Expr && canEvaluate)
			this.body = ((Expr)(this.body)).eval();	    	    	    
	}	
	public String toString() {
		
		return body.toString();		
	}

}


