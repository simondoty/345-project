package crono;

import crono.AbstractSyntax.Atom;
import crono.AbstractSyntax.CronoType;
import crono.Cons;

public class Nil extends Cons implements Atom {
  public static final Nil NIL = new Nil();
  private CronoTypeVar ctv;

  private Nil() {}

  @Override
  public CronoType car() {
    return this;
  }

  @Override
  public CronoType cdr() {
    return this;
  }

  @Override
  public String toString() {
    return "NIL";
  }
  
  public CronoTypeVar type() {
	  if(ctv == null) ctv = new CronoTypeVar();
	  return ctv;
  }
  
  public CronoTypeConstraint[] constraint(CronoType[] args) {
	  ctv = new CronoTypeVar();
	  CronoTypeConstraint[] ret = new CronoTypeConstraint[1];
	  ret[0] = new CronoTypeConstraint(ctv, new CronoTypeVar(false));
	  return ret;
  }
}
