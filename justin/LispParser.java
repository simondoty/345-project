public class LispParser {
	
	// ******************************************
	public static void main(String[] args) {
		Interpret("5");

	}
	
	// ******************************************
    public static void Interpret(String s_) {
	    System.out.println("-------- test 1: atom of 5 eval() -------");
		// parse and build a tree of SExprs.
		SExpr exprRoot = new NumericAtom(5);
		
		System.out.println(exprRoot.eval());
		
		
		
	    System.out.println("-------- test 2:  addition (+ 5 12) ---------");
	    
	    Expr e = new Expr( new StringAtom("+") );
	    e.AddArg( new NumericAtom(5));
	    e.AddArg( new NumericAtom(12));
	   
		System.out.println(e.eval());
		
		
	    System.out.println("-------- test 3 - nested eval (+ 5 (- 4 3)) ---------");
		
	    // build (- 4 3)
	    Expr e2 = new Expr( new StringAtom("-") );
	    e2.AddArg( new NumericAtom(4));
	    e2.AddArg( new NumericAtom(3));
	
	    // build (+ 5 (that stuff above) )
	    e = new Expr( new StringAtom("+") );
	    e.AddArg( new NumericAtom(5));
	    e.AddArg(e2);
	    
		System.out.println(e.eval());
	    
	
	    System.out.println("-------- test 4 - nested eval (* (+ 5 4 3 1) (- 6 3)) ---------");

	    // build (+ 5 4 3 1)
	    Expr e3 = new Expr( new StringAtom("+") );
	    e3.AddArg( new NumericAtom(5));
	    e3.AddArg( new NumericAtom(4));
	    e3.AddArg( new NumericAtom(3));
	    e3.AddArg( new NumericAtom(1));	    
	    
	    // build (- 6 3)
	    e2 = new Expr( new StringAtom("-") );
	    e2.AddArg( new NumericAtom(6) );
	    e2.AddArg( new NumericAtom(3) );

	    // build (* (prev 1) (prev 2) )
	    e = new Expr( new StringAtom("*") );
	    e.AddArg( e3 );
	    e.AddArg( e2 );
	    
		System.out.println(e.eval());
	    
		
	    System.out.println("-------- test 5 - car ('(7 3 4)) ---------");
	    AtomList l = new AtomList();
	    l.Add( new NumericAtom(7) );
	    l.Add( new NumericAtom(3) );
	    l.Add( new NumericAtom(4) );
		
	    e = new Expr( new StringAtom("car"));
	    e.AddArg(l);
	    
		System.out.println(e.eval());
	    
	    System.out.println("-------- test 6 - (* 3 (car ('(7 3 4))) ---------");
	    
	    l = new AtomList();
	    l.Add( new NumericAtom(7) );
	    l.Add( new NumericAtom(3) );
	    l.Add( new NumericAtom(4) );
		
	    e2 = new Expr( new StringAtom("car"));
	    e2.AddArg(l);
	    
	    e = new Expr( new StringAtom("*") );
	    e.AddArg( new NumericAtom(3) );
	    e.AddArg( e2 );
	
		System.out.println(e.eval());
	    	
    }
    
}