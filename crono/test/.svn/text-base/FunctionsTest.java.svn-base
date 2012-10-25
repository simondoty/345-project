import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import crono.AbstractSyntax.*;
import crono.Cons;
import crono.CronoOptions;
import crono.CronoNumber;
import crono.Environment;
import crono.Interpreter;
import crono.LambdaFunction;
import crono.Parser;
import crono.Symbol;

import static crono.Nil.NIL;

public class FunctionsTest extends junit.framework.TestCase {
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

  public void testLambdaParse() throws Exception {
    CronoType actual = setup("(\\ (x) (cons x nil))");

    assertTrue(actual instanceof LambdaFunction);
  }

  public void testDefineParse() throws Exception {
    String input = "(define foo (x) (cons x nil))";

    CronoOptions.DPRINT_ENABLE = false;
    StringReader in = new StringReader(input);
    parser.ReInit(in);
    Environment env = Interpreter.getDefaultEnvironment();
    CronoType actual = parser.prog(env);

    assertTrue(actual instanceof LambdaFunction);
    assertTrue(env.containsKey(Symbol.valueOf("foo")));
  }

  public void testDefineRunnability() throws Exception {
    String input = "(define foo (x) (cons x nil)) (foo 1)";
    CronoType actual = setup(input);

    CronoType expected = new Cons(CronoNumber.valueOf(1), NIL);

    assertEquals(expected, actual);
  }

  public void testMultipleDefine() throws Exception {
    String input = "(define foo (x) (cons x nil)) (define bar (x) (car x))" +
      "(bar (foo 1))";
    CronoType actual = setup(input);

    CronoType expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testSingleAdd() throws Exception {
    CronoType actual = setup("(+ 1 2)");

    CronoType expected = CronoNumber.valueOf(3);

    assertEquals(expected, actual);
  }

  public void testNestedAdd() throws Exception {
    CronoType actual = setup("(+ (+ 1 2) 3)");

    CronoType expected = CronoNumber.valueOf(6);

    assertEquals(expected, actual);
  }

  public void testListAdd() throws Exception {
    CronoType actual = setup("(+ 1 2 3 4)");

    CronoType expected = CronoNumber.valueOf(10);

    assertEquals(expected, actual);
  }

  public void testSingleSub() throws Exception {
    CronoType actual = setup("(- 2 1)");

    CronoType expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testNestedSub() throws Exception {
    CronoType actual = setup("(- (- 5 1) 3)");

    CronoType expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testUnarySub() throws Exception {
    CronoType actual = setup("(- 3)");

    CronoType expected = CronoNumber.valueOf(-3);

    assertEquals(expected, actual);
  }

  public void testListSub() throws Exception {
    CronoType actual = setup("(- 10 4 3 2 1)");

    CronoType expected = CronoNumber.valueOf(0);

    assertEquals(expected, actual);
  }

  public void testSingleMult() throws Exception {
    CronoType actual = setup("(* 2 3)");

    CronoType expected = CronoNumber.valueOf(6);

    assertEquals(expected, actual);
  }

  public void testNestedMult() throws Exception {
    CronoType actual = setup("(* (* 2 3) 4)");

    CronoType expected = CronoNumber.valueOf(24);

    assertEquals(expected, actual);
  }

  public void testListMult() throws Exception {
    CronoType actual = setup("(* 2 3 4)");

    CronoType expected = CronoNumber.valueOf(24);

    assertEquals(expected, actual);
  }
  public void testSingleDiv() throws Exception {
    CronoType actual = setup("(/ 6 3)");

    CronoType expected = CronoNumber.valueOf(2);

    assertEquals(expected, actual);
  }

  public void testNestedDiv() throws Exception {
    CronoType actual = setup("(/ (/ 12 3) 4)");

    CronoType expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testEq1() throws Exception {
    CronoType actual = setup("(= 1 1)");

    assertTrue(actual != NIL);
  }

  public void testEq2() throws Exception {
    CronoType actual = setup("(= cons cons)");

    assertTrue(actual != NIL);
  }

  public void testEqCons() throws Exception {
    CronoType actual = setup("(= (cons 1 nil) (cons 1 nil))");

    assertTrue(actual != NIL);
  }

  public void testNEq1() throws Exception {
    CronoType actual = setup("(= 1 2)");

    assertTrue(actual == NIL);
  }

  public void testNeq2() throws Exception {
    CronoType actual = setup("(= car cdr)");

    assertTrue(actual == NIL);
  }

  public void testNeq3() throws Exception {
    CronoType actual = setup("(= (cons 1 2) (cons 2 1))");

    assertTrue(actual == NIL);
  }
}
