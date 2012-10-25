package crono;

import java.util.LinkedList;

import crono.AbstractSyntax.CronoType;

public class CronoUnit {
/*
	public static void main(String[] args) {
		Tester t = new Tester();
		
		Identifier.unit(t);
		CronoTypeVar.unit(t);
		CronoTypeConstraint.unit(t);
		CronoConstraintCreator.unit(t);
		//CronoTypeInferTesting();
		t.close();
	}
	*/
	public static void CronoTypeInferTesting() {

		System.out.println("Testing [a] = 1 and [d] = [a], both should equal a number.");
		CronoTypeVar a1 = new CronoTypeVar("a");
		CronoTypeVar a2 = new CronoTypeVar(1);
		CronoTypeVar b1 = new CronoTypeVar("d");
		CronoTypeVar b2 = new CronoTypeVar("a");
		
		CronoTypeConstraint consTest1 = new CronoTypeConstraint(a1, a2);
		CronoTypeConstraint consTest2 = new CronoTypeConstraint(b1, b2);

		CronoTypeInfer unifyTest = new CronoTypeInfer();
		unifyTest.addConstraint(consTest1);
		unifyTest.addConstraint(consTest2);
		unifyTest.unify();

		System.out.println("Testing [a] = 1 -> 2 and [d] = [a], both should equal a number.");
		
		a1 = new CronoTypeVar("a");
		a2 = new CronoTypeVar(1);
		a2.addVar(new CronoTypeVar(3));
		
		b1 = new CronoTypeVar("d");
		b2 = new CronoTypeVar("a");
		
		consTest1 = new CronoTypeConstraint(a1, a2);
		consTest2 = new CronoTypeConstraint(b1, b2);

		unifyTest = new CronoTypeInfer();
		unifyTest.addConstraint(consTest1);
		unifyTest.addConstraint(consTest2);
		unifyTest.unify();
		
//		// Testing [a] -> [b] = [c], This should result in an error thrown
//		
//		System.out.println("Testing [a] -> [b] = [1] and [d] = [a], both should equal a number.");
//		
//		a1 = new CronoTypeVar("a");
//		a2 = new CronoTypeVar(1);
//		a1.addVar(new CronoTypeVar("b"));
//		
//		b1 = new CronoTypeVar("d");
//		b2 = new CronoTypeVar("a");
//		
//		consTest1 = new CronoTypeConstraint(a1, a2);
//		consTest2 = new CronoTypeConstraint(b1, b2);
//
//		unifyTest = new CronoTypeInfer();
//		unifyTest.addConstraint(consTest1);
//		unifyTest.addConstraint(consTest2);
//		unifyTest.unify();
//		
//		System.out.println("Testing [a] -> [b] = [1] -> [2] -> [3] and [d] = [a], both should equal a number.");
//		
//		a1 = new CronoTypeVar("a");
//		a2 = new CronoTypeVar(1);
//		a1.addVar(new CronoTypeVar("b"));
//		a2.addVar(new CronoTypeVar(2));
//		a2.addVar(new CronoTypeVar(3));
//
//		b1 = new CronoTypeVar("d");
//		b2 = new CronoTypeVar("a");
//		
//		consTest1 = new CronoTypeConstraint(a1, a2);
//		consTest2 = new CronoTypeConstraint(b1, b2);
//
//		unifyTest = new CronoTypeInfer();
//		unifyTest.addConstraint(consTest1);
//		unifyTest.addConstraint(consTest2);
//		unifyTest.unify();
//		
//		System.out.println("Testing [a] -> [b] = [1] -> [2] and [d] = [a], both should equal a number.");
//		
//		a1 = new CronoTypeVar("a");
//		a2 = new CronoTypeVar(1);
//		a1.addVar(new CronoTypeVar("b"));
//		a2.addVar(new CronoTypeVar(2));
//
//		b1 = new CronoTypeVar("d");
//		b2 = new CronoTypeVar("a");
//		
//		consTest1 = new CronoTypeConstraint(a1, a2);
//		consTest2 = new CronoTypeConstraint(b1, b2);
//
//		unifyTest = new CronoTypeInfer();
//		unifyTest.addConstraint(consTest1);
//		unifyTest.addConstraint(consTest2);
//		unifyTest.unify();
//		
//		// Testing [a] -> [b] = [c] -> [d]
//		// this should end up splitting
//		
//		
////	Testing [a] = [f] -> [g], [a] -> [b] = [c] -> [d] -> [e], This should result in an error
//	System.out.println("Testing [a] = [f] -> [g] and [a] -> [b] = [1] -> [2] -> [3] and [d] = [a]");
//	
//	
//	CronoTypeVar c1 = new CronoTypeVar("a");
//	CronoTypeVar c2 = new CronoTypeVar("f");
//	c2.addVar(new CronoTypeVar("g"));
//	a1 = new CronoTypeVar("a");
//	a2 = new CronoTypeVar(1);
//	a1.addVar(new CronoTypeVar("b"));
//	a2.addVar(new CronoTypeVar(2));
//	a2.addVar(new CronoTypeVar(3));
//
//	b1 = new CronoTypeVar("d");
//	b2 = new CronoTypeVar("a");
//	
//	CronoTypeConstraint consTest3 = new CronoTypeConstraint(c1, c2);
//	consTest1 = new CronoTypeConstraint(a1, a2);
//	consTest2 = new CronoTypeConstraint(b1, b2);
//
//	unifyTest = new CronoTypeInfer();
//	unifyTest.addConstraint(consTest3);		
//	unifyTest.addConstraint(consTest1);
//	unifyTest.addConstraint(consTest2);
//	unifyTest.unify();
		
		System.out.println("Testing [2] = [3] -> [1] and [a] = [1] -> [2] and [d] = [a]");	
		CronoTypeVar c1 = new CronoTypeVar("2");
		CronoTypeVar c2 = new CronoTypeVar("3");
		c2.addVar(new CronoTypeVar("1"));
		
		a1 = new CronoTypeVar("2");
		a2 = new CronoTypeVar("x");
		a2.addVar(new CronoTypeVar("x"));
	
		b1 = new CronoTypeVar("3");
		b2 = new CronoTypeVar(1);
		
		CronoTypeConstraint consTest3 = new CronoTypeConstraint(c1, c2);
		consTest1 = new CronoTypeConstraint(a1, a2);
		consTest2 = new CronoTypeConstraint(b1, b2);
	
		unifyTest = new CronoTypeInfer();
		unifyTest.addConstraint(consTest3);		
		unifyTest.addConstraint(consTest1);
		unifyTest.addConstraint(consTest2);
		unifyTest.unify();
	}
}
