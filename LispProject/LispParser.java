/* Generated By:JJTree&JavaCC: Do not edit this line. LispParser.java */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class LispParser/*@bgen(jjtree)*/implements LispParserTreeConstants, LispParserConstants {/*@bgen(jjtree)*/
  protected JJTLispParserState jjtree = new JJTLispParserState();public static void main(String args[]) {
    System.out.println("Welcome to Lisp Parser. Type commands or quit.");

    boolean bQuit = false;

    do {
      System.out.print("> ");

      // read a line of input
      String sInput = "";
      Scanner scanIn = new Scanner(System.in);
      sInput = scanIn.nextLine();

      if(sInput.equals("quit"))
        bQuit = true;

      else if(sInput.equals("test")) {
        RunTests();

      } else {
        RunCommand(sInput + ";");

      }

    } while(!bQuit);

    System.out.println("Parse Completed Successfully.");

  }

  // *************************************************************************
  public static void RunCommand(String sCommand_) {
    try {

      // build ast from stream created from the input command
      InputStream in = new ByteArrayInputStream(sCommand_.getBytes());
      LispParser p = new LispParser(in);
      ASTProgram root = p.Program();

      // print the AST
      LispParserVisitor v = new LispParserDumpVisitor();
      root.jjtAccept(v, null);

      // interpret the AST
      LispParserVisitor i = new LispParserInterpreterVisitor();
      System.out.println(root.jjtAccept(i, null) );


    } catch (Exception e) {
      System.out.println("Oops.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

  }

  // **************************************************************************
  public static void RunTests() {
    System.out.println("**************** test 1: 5 **************************");
    RunCommand("5;");
    System.out.println("**************** test 2: (+ 3 4 5) **************************");
    RunCommand("(+ 3 4 5);");
    System.out.println("**************** test 3: (- 3 1) **************************");
    RunCommand("(- 3 1);");
    System.out.println("**************** test 4: (* 7 9) **************************");
    RunCommand("(* 7 9);");
    System.out.println("**************** test 5: (+ 3 4 (* 7 (- 4 2))) **************************");
    RunCommand("(+ 3 4 (* 7 (- 4 2)));");

  }

// ***************************************************************************
// program entry point
  final public ASTProgram Program() throws ParseException {
                        /*@bgen(jjtree) Program */
  ASTProgram jjtn000 = new ASTProgram(JJTPROGRAM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGIT:
        Num();
        break;
      case LPAR:
        ArithExpr();
        break;
      default:
        jj_la1[0] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(SEMI);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

// ***************************************************************************
/*
void SExpr():
{ }
{
  (Num() | Identifier() | ArithExpr() | LambdaExpr() | FuncApp() | LetExpr())
  
}
*/

// ***************************************************************************
/*
void Body():
{}
{
LOOKAHEAD(2)
 <LPAR> <LPAR>
        Lambda()
 | Expr()
 | Num()
}

// ***************************************************************************
void Lambda() :
{}
{
    //Case: Lambda Expression

        <LAMBDA>
        <LPAR>
    <IDENTIFIER>
        <RPAR>
    Body()
        <RPAR>
    Body()
        <RPAR>
}
*/
// ***************************************************************************
  final public void ArithExpr() throws ParseException {
                   /*@bgen(jjtree) ArithExpr */
                    ASTArithExpr jjtn000 = new ASTArithExpr(JJTARITHEXPR);
                    boolean jjtc000 = true;
                    jjtree.openNodeScope(jjtn000);Token t; String s;
    try {
      jj_consume_token(LPAR);
      t = jj_consume_token(OP);
                  s = t.image; jjtn000.setOp(s);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DIGIT:
          Num();
          break;
        case LPAR:
          ArithExpr();
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAR:
        case DIGIT:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_1;
        }
      }
      jj_consume_token(RPAR);
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

// ***************************************************************************
  final public void Identifier() throws ParseException {
 /*@bgen(jjtree) Identifier */
  ASTIdentifier jjtn000 = new ASTIdentifier(JJTIDENTIFIER);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(SYMBOL);
                 jjtree.closeNodeScope(jjtn000, true);
                 jjtc000 = false;
                 jjtn000.setIdentifier(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

// ***************************************************************************
  final public void Num() throws ParseException {
 /*@bgen(jjtree) Num */
  ASTNum jjtn000 = new ASTNum(JJTNUM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(DIGIT);
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                jjtn000.setVal(Integer.parseInt(t.image));
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  /** Generated Token Manager. */
  public LispParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[3];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2200,0x2200,0x2200,};
   }

  /** Constructor with InputStream. */
  public LispParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public LispParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LispParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public LispParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LispParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public LispParser(LispParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LispParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[20];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 3; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 20; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
