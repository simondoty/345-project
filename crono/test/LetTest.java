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

public class LetTest extends junit.framework.TestCase {
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

  public void testSimpleLet() throws Exception {
    CronoType actual = setup("(let ((x 1)) (cons x nil))");

    CronoType expected = new Cons(CronoNumber.valueOf(1), NIL);

    assertEquals(expected, actual);
  }

  public void testLetLambda() throws Exception {
    CronoType actual = setup("(let ((f (\\ (n) (* n n)))) (f 10))");

    CronoType expected = CronoNumber.valueOf(100);

    assertEquals(expected, actual);
  }
}
