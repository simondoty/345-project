package crono;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;

public class CronoString implements Atom 
{
    public final String str;
    private CronoTypeVar ctv;

    public CronoString(String str) 
    {
	this.str = str;
	ctv = new CronoTypeVar();
    }

    public boolean equals(Object o) 
    {
	if (o instanceof CronoString) 
	    {
		CronoString s = (CronoString)o;
		return this.str.equals(s.str);
	    } 
	else 
	    {
		return false;
	    }
    }

    public String toString() {
	return str;
    }

    public CronoTypeVar type() {
	if(ctv == null) ctv = new CronoTypeVar();
	return ctv;
    }
	
    public CronoTypeConstraint[] constraint(CronoType[] args) {
	CronoTypeConstraint[] ret = new CronoTypeConstraint[1];
	ret[0] = new CronoTypeConstraint(ctv, new CronoTypeVar(1)); //TODO make clone????
	return ret;
    }


  
}
