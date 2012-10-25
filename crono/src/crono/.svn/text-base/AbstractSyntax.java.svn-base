package crono;

/**
 * Container class for various interfaces/types defined.
 */
public class AbstractSyntax {
  public static interface CronoType {
    // Won't affect compilation;
    // Just a reminder that all CronoTypes should implement a toString().
    public String toString();
    public CronoTypeVar type();
    public CronoTypeConstraint[] constraint(CronoType[] args);
  }

  public static interface Atom extends CronoType {};

  public static interface Function extends Atom {
    public boolean evalArgs();
  }
  
  /**
   * A built-in Crono function.
   */
  public static interface CronoFunction extends Function {
    public CronoType run(CronoType[] args, Environment environment);
  }
}
