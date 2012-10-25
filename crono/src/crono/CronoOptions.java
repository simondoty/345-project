package crono;

import java.util.Arrays;

/**
 * Options and utilities for debugging and examining parts of the Crono
 * language.
 */
// TODO:
//      - cli argument parsing
public class CronoOptions {
    // Switch controlling whether dprint prints or not.
    public static boolean DPRINT_ENABLE = true;

    // Whether or not to use indention when dprinting.
    public static boolean DPRINT_INDENT = true;

    // Whether or not to show atoms being evaluated.
    public static boolean DPRINT_SHOW_ATOM_EVAL = false;

    // Level of indention to use when using dprint.
    public static int DPRINT_I = 0;

    // Whether or not to print the environment when executing.
    public static boolean ENVIRONMENT_SHOW = true;

    // Whether or not to show CronoFunctions when printing the environment.
    public static boolean ENVIRONMENT_SHOW_BUILTIN = false;

    // Controls whether we use static or dynamic scoping.
    public static boolean ENVIRONMENT_DYNAMIC = false;

    // Whether or not to print types in the environment.
    public static boolean ENVIRONMENT_SHOW_TYPES = false;

    // Whether or not to use multiple lines when printing the environment.
    public static boolean ENVIRONMENT_MULTILINE = true;

    // Whether or not to show closures when printing lambdas.
    public static boolean LAMBDA_SHOW_CLOSURE = true;

    // Whether or not to show parser debug output.
    public static boolean PARSER_DPRINT = false;

    // Whether or not to show unification process
    public static boolean INFER_PRINTING = false;
	
    // Whether of not to cycle through unification
    public static boolean INFER_CYCLING = false;

    public static void dprint(String msg, Object... args) {
	if (DPRINT_ENABLE) {
	    // Do string foramtting.
	    String formatted = String.format(msg, args);
	    String[] lines = formatted.split("\n");

	    // Print first line with normal indention.
	    for(int i = 0; DPRINT_INDENT && i < DPRINT_I; i++) {
		System.out.print("  ");
	    }
	    // Don't print newline if original message didn't call for it.
	    if (lines.length == 1) {
		System.out.print(formatted);
	    } else {
		System.out.println(lines[0]);
	    }

	    // Further indent subsequent lines.
	    // TODO: don't print newline on last line if original message didn't call for it
	    DPRINT_I++;
	    for(String line : copyOfRange(lines, 1, lines.length)) {
		for(int i = 0; DPRINT_INDENT && i < DPRINT_I; i++) {
		    System.out.print("  ");
		}
		System.out.println(line);
	    }
	    DPRINT_I--;
	}
    }

    public static String[] copyOfRange(String[] input, int start, int end) {
	String[] output = new String[end-start];
	for (int i = start; i < end && i < input.length; i++) {
	    output[i-start] = input[i];
	}
	return output;
    }

    public static void err(String msg, Object... args) {
	throw new RuntimeException(String.format(msg, args));
    }
}
