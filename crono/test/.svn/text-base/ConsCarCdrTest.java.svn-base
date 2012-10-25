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

public class ConsCarCdrTest extends junit.framework.TestCase {
  private static Parser parser = new Parser(new StringReader(""));

  private CronoType setup(String input) throws Exception {
    CronoOptions.DPRINT_ENABLE = false;
    StringReader in = new StringReader(input);
    parser.ReInit(in);
    return parser.prog(Interpreter.getDefaultEnvironment());
  }

  public void testCons12 () throws Exception {
    CronoType actual = setup("(cons 1 2)");

    List<CronoType> exp = new ArrayList<CronoType>(Arrays.asList(
          CronoNumber.valueOf(1),CronoNumber.valueOf(2)));
    Cons expected = new Cons(exp);

    assertEquals(expected, actual);
  }

  public void testCons1nil () throws Exception {
    CronoType actual = setup("(cons 1 nil)");

    List<CronoType> exp = new ArrayList<CronoType>(Arrays.asList(
          CronoNumber.valueOf(1), NIL));
    Cons expected = new Cons(exp);

    assertEquals(expected, actual);
  }

  public void testCar12 () throws Exception {
    CronoType actual = setup("(car (cons 1 2))");

    CronoNumber expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testCar1nil() throws Exception {
    CronoType actual = setup("(car (cons 1 nil))");

    CronoNumber expected = CronoNumber.valueOf(1);

    assertEquals(expected, actual);
  }

  public void testCdr12 () throws Exception {
    CronoType actual = setup("(cdr (cons 1 2))");

    CronoNumber expected = CronoNumber.valueOf(2);

    assertEquals(expected, actual);
  }

  public void testCdr1nil() throws Exception {
    CronoType actual = setup("(cdr (cons 1 nil))");

    CronoType expected = NIL;

    assertEquals(expected, actual);
  }

  public void testCons() throws Exception {
    CronoType actual = setup("(cons (cons (cons 1 2) 3) 4)");

    Cons expected = new Cons(new Cons(new Cons(CronoNumber.valueOf(1),
                                               CronoNumber.valueOf(2)),
                                      CronoNumber.valueOf(3)),
                             CronoNumber.valueOf(4));

    assertEquals(expected, actual);
  }

  public void testConsProper() throws Exception {
    CronoType actual = setup("(cons 4 (cons 3 (cons 2 1)))");

    List<CronoType> exp = new ArrayList<CronoType>();
    exp.add(CronoNumber.valueOf(4));
    exp.add(CronoNumber.valueOf(3));
    exp.add(CronoNumber.valueOf(2));
    exp.add(CronoNumber.valueOf(1));
    Cons expected = new Cons(exp);

    assertEquals(expected, actual);
  }

  public void testBigCons() throws Exception {
    String program = "(cdr (car (cons (cons 1 (cons 2 (cons 3 nil)))" +
      "(cons 5 (cons 6 7)))))";
    CronoType actual = setup(program);

    List<CronoType> exp = new ArrayList<CronoType>();
    exp.add(CronoNumber.valueOf(2));
    exp.add(CronoNumber.valueOf(3));
    exp.add(NIL);
    Cons expected = new Cons(exp);

    assertEquals(expected, actual);
  }

  public void testNil() throws Exception {
    CronoType actual = setup("()");

    CronoType expected = NIL;

    assertEquals(expected, actual);
  }
}
