// type function - includes as data members: parameters, body, arguments

public class FunctionExpr extends SExpr {
    
    //name of the function (if it has one)
	//private SymbolAtom name;	
	
	//this could change to a list of parameters
	private SymbolAtom parameter;
	
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
		
		// parameter has to be a SymbolAtom
		this.parameter = parameter; // this is a SymbolAtom with "x"
		
		// SExpr argument could be three things: Expr, Atom, FunctionExpr
		// if argument is an Expr, evaluate the expression. Could return an SExpr, or Atom
		if(argument instanceof Expr) {
		    System.out.println("argument is instance of Expr - inside if");	    
			this.argument = ((Expr)(argument)).eval(); //downcast from SExpr to Expr
		}
		
		// argument must be an atom
		else {
		     System.out.println("argument is instance of Atom - inside if");	 
			this.argument = argument;
		}
		
		// add case for argument being a FunctionExpr
		
		this.body = body;
		// body can either be a FunctionExpr, Atom, or Expr
		//if body is an Expr
		if (this.body instanceof Expr) {
			System.out.println("Going to call replaceParam....");			
			((Expr)(this.body)).replaceParam(this.parameter, this.argument);	//downcast from SExpr to Expr
		} 
		
		// if body is an Atom, only replace if it's a SymbolAtom
		else if(this.body instanceof SymbolAtom) {
			System.out.println("inside body instanceof symbol....");
			System.out.println("Printing this.body: " +  this.body + " and parameter: " + parameter);				
			if( ((SymbolAtom)(this.body)).equals(parameter)) {
				System.out.println("Inside body.equals parameter....");		
				this.body = argument;
			}
			// else check if body is in the repository 			
		}	
		
		//System.out.println("Returned from replaceParam. Calling body.eval()");	    
	    
	    
	    System.out.println(this.body);
	    if( this.body instanceof Expr)
			this.body = ((Expr)(this.body)).eval();	    	    	    
	}	
	public String toString() {
		return "";
		
	}

}


