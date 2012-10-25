package crono;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;

import crono.Interpreter;
import crono.LambdaFunction;
//import crono.Parser;

public class Crono {
  public static void main(String[] args) throws FileNotFoundException,
						ParseException, Exception {
    // If there is a command line argument, interpret it as a stdin.
    InputStream in = System.in;
    if (args.length > 0 ) {
      in = new FileInputStream(args[0]);
    }

    Environment env = Interpreter.getDefaultEnvironment();
    Parser parser = new Parser(in);
    parser.prog(env);
  }
}
