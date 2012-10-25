package crono;

import java.util.HashMap;
import java.util.Map;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;


public class CombSymbol extends Symbol
{
    //private final String name;
    public static final int S_COMBINATOR=0; 
    public static final int K_COMBINATOR=1;
    public static final int B_COMBINATOR=2;
    public static final int C_COMBINATOR=3;
    public static final int Y_COMBINATOR=4;


  
      private static final HashMap<CombSymbol, Integer> combinators = new HashMap<CombSymbol, Integer>();

    //	private CronoTypeVar ctv;
  
    public CombSymbol(String s) 
    {
	super(s);
	//	this.name = s.toUpperCase();
	//	ctv = new CronoTypeVar();

	//super(s);
    }

    public String toString() 
    {
	return super.toString();
    }

    public boolean equals(Object o) 
    {
	/*	if (o instanceof CombSymbol) {
	    return ((CombSymbol)o).name.equals(this.name);
	} else {
	    return false;
	    }*/
	return super.equals(o);
    }
  
    public static void initCombinators()
    {
	combinators.put(new CombSymbol("<S>"), S_COMBINATOR);
	combinators.put(new CombSymbol("<K>"), K_COMBINATOR);
	combinators.put(new CombSymbol("<B>"), B_COMBINATOR);
	combinators.put(new CombSymbol("<C>"), C_COMBINATOR);
	combinators.put(new CombSymbol("<Y>"), Y_COMBINATOR);
    }
  
    public static boolean isCombinatorSymbol(CombSymbol symbol)
    {
	if(combinators.containsKey(symbol))
	    return true;
	return false;
    }
  
    public static int getValueOf(CombSymbol symbol)
    {
	return combinators.get(symbol);
    }

    public CronoTypeVar type() {
	/*if(ctv == null) ctv = new CronoTypeVar();
	  return ctv;*/
	 return super.type();
    }
	
    public CronoTypeConstraint[] constraint(CronoType[] args) {
	return super.combConstraint(args);
    }
  
  
}
