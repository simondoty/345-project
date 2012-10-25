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

public class RealProgTest extends junit.framework.TestCase {
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

  public void testFactorial() throws Exception {
    String prog =
      "(define factorial (n)" +
      "  (if (= n 0)" +
      "    1" +
      "    (* (factorial (- n 1)) n)))" +
      "(factorial 4)";
    CronoType actual = setup(prog);

    CronoType expected = CronoNumber.valueOf(24);

    assertEquals(expected, actual);
  }

  public void testMapDefine() throws Exception {
    String prog =
      "(define map (f l)" +
      "  (if (= l nil)" +
      "    nil" +
      "    (cons (f (car l)) (map f (cdr l)))))" +
      "(define mult2 (x) (* x 2))" +
      "(map mult2 (cons 1 (cons 2 (cons 3 nil))))";
    CronoType actual = setup(prog);

    List<CronoType> exp = new ArrayList<CronoType>();
    exp.add(CronoNumber.valueOf(2));
    exp.add(CronoNumber.valueOf(4));
    exp.add(CronoNumber.valueOf(6));
    exp.add(NIL);
    CronoType expected = new Cons(exp);

    assertEquals(expected, actual);
  }

  public void testMapLambda() throws Exception {
    String prog =
      "(define map (f l)" +
      "  (if (= l nil)" +
      "    nil" +
      "    (cons (f (car l)) (map f (cdr l)))))" +
      "(map (\\ (x) (* x 2)) (cons 1 (cons 2 (cons 3 nil))))";
    CronoType actual = setup(prog);

    List<CronoType> exp = new ArrayList<CronoType>();
    exp.add(CronoNumber.valueOf(2));
    exp.add(CronoNumber.valueOf(4));
    exp.add(CronoNumber.valueOf(6));
    exp.add(NIL);
    CronoType expected = new Cons(exp);

    assertEquals(expected, actual);
  }
}
