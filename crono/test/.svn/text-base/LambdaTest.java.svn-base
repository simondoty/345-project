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

public class LambdaTest extends junit.framework.TestCase {
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

  public void testSimpleLambda() throws Exception {
    CronoType actual = setup("((\\ (x) x) (cons 1 2))");

    CronoType expected = new Cons(CronoNumber.valueOf(1),
        CronoNumber.valueOf(2));

    assertEquals(expected, actual);
  }
}
