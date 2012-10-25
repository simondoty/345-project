import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import crono.AbstractSyntax.*;
import crono.Cons;
import crono.CronoOptions;
import crono.CronoNumber;
import crono.Interpreter;
import crono.Parser;

import static crono.Nil.NIL;

public class ScopeTest extends junit.framework.TestCase {
  private static Parser parser = new Parser(new StringReader(""));

  private CronoType setup(String input, boolean debug) throws Exception {
    CronoOptions.DPRINT_ENABLE = debug;
    StringReader in = new StringReader(input);
    parser.ReInit(in);
    return parser.prog(Interpreter.getDefaultEnvironment());
  }

  private CronoType setup(String input) throws Exception {
    return setup(input, false);
  }

  public void testStaticScope() throws Exception {
    CronoOptions.ENVIRONMENT_DYNAMIC = false;

    String prog = "(let ((z 20)) (let ((z 3) (a 5) (x (\\ (x y)" +
      "(- x (+ y z))))) (let ((z 50) (a 15)) (x z a))))";
    CronoType actual = setup(prog);

    CronoType expected = CronoNumber.valueOf(15);

    assertEquals(expected, actual);
  }

  public void testDyanmicScope() throws Exception {
    CronoOptions.ENVIRONMENT_DYNAMIC = true;

    String prog = "(let ((a 20)) (let ((a 3) (z 5) (r (\\ (r s)" +
      "(- r (+ s a))))) (let ((a 10) (z 5)) (r a z))))";
    CronoType actual = setup(prog);

    CronoType expected = CronoNumber.valueOf(-5);

    assertEquals(expected, actual);
  }
}
