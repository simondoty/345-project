package crono;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;

public class CronoNumber implements Atom {
	public final Long num;
	private CronoTypeVar ctv;

	public CronoNumber(Long num) {
		this.num = num;
		ctv = new CronoTypeVar();
	}

	public CronoNumber(int num) {
		this((long) num);
	}

	public boolean equals(Object o) {
		if (o instanceof CronoNumber) {
			CronoNumber n = (CronoNumber)o;
			return this.num.equals(n.num);
		} else {
			return false;
		}
	}

	public String toString() {
		return num.toString();
	}

	public static CronoNumber valueOf(int i) {
		return new CronoNumber(Long.valueOf(i));
	}

	public static CronoNumber valueOf(long l) {
		return new CronoNumber(Long.valueOf(l));
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
