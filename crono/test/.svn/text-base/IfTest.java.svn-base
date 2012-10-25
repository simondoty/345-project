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
import crono.Symbol;

import static crono.Nil.NIL;

public class IfTest extends junit.framework.TestCase {
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

  public void testIfTrue() throws Exception {
    CronoType actual = setup("(if x x nil)");

    CronoType expected = Symbol.valueOf("x");

    assertEquals(expected, actual);
  }

  public void testIfTrueZero() throws Exception {
    CronoType actual = setup("(if 0 x nil)");

    CronoType expected = Symbol.valueOf("x");

    assertEquals(expected, actual);
  }

  public void testIfNil() throws Exception {
    CronoType actual = setup("(if nil x nil)");

    CronoType expected = NIL;

    assertEquals(expected, actual);
  }

  public void testIfNil2() throws Exception {
    CronoType actual = setup("(if () x nil)");

    CronoType expected = NIL;

    assertEquals(expected, actual);
  }
}
