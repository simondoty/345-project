package crono;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;

public class CronoDouble implements Atom 
{
  public final Double num;
  private CronoTypeVar ctv;

  public CronoDouble(String num) 
  {
    this.num = Double.parseDouble(num);
    ctv = new CronoTypeVar();
  }

  public CronoDouble(double num) 
  {
    this.num = num;
  }

    public boolean equals(Object o) {
	
	    if (o instanceof CronoDouble) {
	      CronoDouble n = (CronoDouble)o;
	      return this.num.equals(n.num);
	    } 
	    else { 
	      return false;
	    }
  }

  public String toString() 
  {
    return num.toString();
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
