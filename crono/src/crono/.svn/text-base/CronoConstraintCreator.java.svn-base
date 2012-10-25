package crono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import crono.AbstractSyntax.CronoFunction;
import crono.AbstractSyntax.CronoType;
import crono.AbstractSyntax.Function;

public class CronoConstraintCreator {

    private CronoTypeInfer cti;
    ArrayList<CronoTypeConstraint> list;
    Environment environment;
    boolean worked;

    public CronoConstraintCreator(Environment e) {
	cti = new CronoTypeInfer();
	list = new ArrayList<CronoTypeConstraint>();
	environment = e;
	worked = true;
    }
	
    public boolean unify() {
	if (worked) 
	    worked = cti.unify();
	return worked;
    }

    private static CronoType getFunction(Cons arg)
    {
	Cons temp = (Cons)arg.cdr();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.car();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.car();
	temp = (Cons)temp.cdr();
	temp = (Cons)temp.cdr();
	return temp.car();
    }

    private static CronoType copyOf(CronoType copy)
    {
	if(copy instanceof Symbol)
	    {
		return new Symbol(copy.toString());
	    }
	else if(copy instanceof CombSymbol)
	    {
		return new CombSymbol(copy.toString());
	    }
	else if(copy instanceof CronoNumber)
	    {
		return new CronoNumber(Long.parseLong(copy.toString()));
	    }
	else if(copy instanceof CronoDouble)
	    {
		return new CronoDouble(Double.parseDouble(copy.toString()));
	    }
	else if(copy instanceof CronoString)
	    {
		return new CronoString(copy.toString());
	    }
	else if(copy instanceof Cons)
	    {
		ArrayList<CronoType> result = new ArrayList<CronoType>();
		Cons cons = (Cons)copy;
		while(!cons.car().equals(Nil.NIL))
		    {
			if(cons.car() instanceof Cons)
			    result.add(copyOf(cons.car()));
			else
			    result.add(cons.car());
			cons = (Cons)cons.cdr();
		    }
		  
		result.add(Nil.NIL);
		Cons c = new Cons(result);
		return c;
	    }
	else
	    {
		return Nil.NIL;
	    }
    }

    public static CronoType copyCons(CronoType ct) {
	Cons newCt = (Cons)ct;
	Iterator<CronoType> it = newCt.iterator();
	
	Cons result;
	CronoType temp;
	
	if(it.hasNext()) {
	    temp = it.next();

	    if(temp instanceof Cons) {		
		result = new Cons((Cons)copyCons(temp), Nil.NIL);
	    }
	    else {
		result = new Cons(temp, Nil.NIL);
	    }
	}
	else {
	    result = new Cons();
	}

	while(it.hasNext()) {
	    temp = it.next();

	    if(temp instanceof Cons) {		
		result.append((Cons)copyCons(temp));
	    }else {
		result.append(temp);
	    }
	}

	return result;
    }

    public boolean infer(CronoType ct) {
	ArrayList<Symbol> defined = new ArrayList<Symbol>();
	
	infer(ct, list, defined);

	if(worked) {
	    for(int i = 0; i < list.size(); i++) {
		cti.addConstraint(list.get(i));
	    }
	}
	return worked;
    }

    public void infer(CronoType ct, ArrayList<CronoTypeConstraint> list, ArrayList<Symbol> defined) {
	// number
	if(ct instanceof CronoNumber) {
	    list.add(((CronoNumber) ct).constraint(null)[0]);
	}

	// instance of Symbol
	if(ct instanceof Symbol) {
	    list.add(ct.constraint(null)[0]);
	    list.add(ct.constraint(null)[1]);
	}

	// Cons
	if(ct instanceof Cons) {

	    Cons c = (Cons)ct;
	    CronoType first = c.car();
	    // instance of function
	  
	    if(first instanceof CombSymbol) {
		// it is a function
		CronoType ctcFun =  first;

		// get args
		Iterator<CronoType> it = c.iterator();
		CronoType[] args;// = new CronoType[c.size()-1];

		Cons argList = new Cons();
		CronoType firstArg;
		it.next();	//This will get the combinator function

		int index = 0;

		//Assuming that the function for <Y> combinator is always a cons
		if(first.toString().equalsIgnoreCase("<Y>")) {
		    args = new CronoType[2];
		  
		    if(it.hasNext()) {
			firstArg = it.next();
		
			if(firstArg instanceof Cons) {
			    argList = (Cons)firstArg;
			}
			else {
			    argList = new Cons(firstArg, Nil.NIL);
			}
		    }

		    while(it.hasNext()) {
			argList.append(it.next());			
		    }

		    args[0] = argList;
		    args[1] = c;			
		}
		else if(first.toString().equalsIgnoreCase("<B>")) {
		    args = new CronoType[2];

		    if(it.hasNext()) {
			firstArg = it.next();

			if(firstArg instanceof Cons) {
			    argList = (Cons) firstArg;
			}
			else {
			    argList = new Cons(firstArg, Nil.NIL);
			}

			if(it.hasNext()) {
			    Cons secondArg = (Cons)(it.next());
			    secondArg.append(it.next()); //g(x)

			    argList.append(secondArg); //f(g(x));
			}
		    }

		    args[0] = argList;
		    args[1] = c;
		}
		else if(first.toString().equalsIgnoreCase("<K>")) {
		    args = new CronoType[c.size()-1];

		    
		    while(it.hasNext()) {
			args[index] = it.next();
			index++;
		    }
		    args[index] = c;
		}
		else if(first.toString().equalsIgnoreCase("<C>")) {
		    args = new CronoType[2];
		    CronoType secondArg;
				
		    if(it.hasNext()) {
			firstArg = it.next();
					
			Cons cArg;
					
			if(firstArg instanceof Cons) {
			    cArg = (Cons) firstArg;
			}
			else {
			    cArg = new Cons(firstArg, Nil.NIL);
			}
			
			if(it.hasNext()) {
			    secondArg = it.next();
					
			    if(it.hasNext()) {
				cArg.append(it.next());
			    }

			    argList = new Cons(cArg, Nil.NIL);
			    argList.append(secondArg);
			}
			else {
			    argList = new Cons(cArg, Nil.NIL);
			}	
		    }
				
		    args[0] = argList;
		    args[1] = c;
		}
		else {
		    args = new CronoType[2];
		    CronoType secondArg;
		    CronoType thirdArg;
				
		    if(it.hasNext()) {
			Cons cArg;
			firstArg = it.next();
					
			if(firstArg instanceof Cons) {
			    argList = (Cons) firstArg;
			}
			else {
			    argList = new Cons(firstArg, Nil.NIL);
			}
					
			if(it.hasNext()) {
			    secondArg = it.next();
					
					
			    if(secondArg instanceof Cons) {
				cArg = (Cons) secondArg;
			    }
			    else {
				cArg = new Cons(secondArg, Nil.NIL);
			    }


			    if(it.hasNext()) {
				thirdArg = it.next();					
				cArg.append(thirdArg);

				argList.append(thirdArg);
				argList.append(cArg);
			    }
			}		
		    }
				
		    args[0] = argList;
		    args[1] = c;
		}

		// call function
		CronoTypeConstraint[] ctcArr = ctcFun.constraint(args);

		// add to list
		if(ctcArr != null) {
		    for(int i = 0; i < ctcArr.length; i++) {
			list.add(ctcArr[i]);
		    }
		}

		infer(args[0], list, defined);
	    }
	    else if(first instanceof Symbol) {
		if(environment.containsKey((Symbol) first)) {
		
		    // if define save symbol
		    boolean define = false;
		    
		    if(((Symbol) first).toString().equals("define")) {
			defined.add((Symbol) ((Cons) c.cdr()).car());
			define = true;
		    }
					
		    // it is a function
		    CronoType ctcFun = environment.get((Symbol) first);

		    
								       
		    // get args
		    Iterator<CronoType> it = c.iterator();
		    CronoType[] args;
		    it.next();

		    if(ctcFun.toString().equalsIgnoreCase("COND")) { 
			args = new CronoType[5];
			
			CronoType firstArg = ((Cons)c.cdr()).car();
			CronoType secondArg = ((Cons)((Cons)c.cdr()).cdr()).car();
			CronoType thirdArg = ((Cons)((Cons)((Cons)c.cdr()).cdr()).cdr()).car();
			CronoType fourthArg = ((Cons)((Cons)((Cons)((Cons)c.cdr()).cdr()).cdr()).cdr()).car();
		

			Cons secSym;
			if(!(secondArg instanceof Cons)) {
			    secSym = new Cons(secondArg, Nil.NIL);
			}
			else {
			    secSym = (Cons)secondArg;
			}


			Cons thirdSym;
			if(!(thirdArg instanceof Cons)) {
			    thirdSym = new Cons(thirdArg, Nil.NIL);
			}
			else {
			    thirdSym = (Cons)thirdArg;
			}

			
			/*	System.out.println("First: " + firstArg);
				System.out.println("Second: " + secondArg);
				System.out.println("Third: " + thirdArg);
				System.out.println("Fourth: " + fourthArg);
				System.out.println("Sec Sym: " +secSym);
				System.out.println("Thir
				return;*/

			args[0] = firstArg;
			args[1] = secSym;
			args[2] = thirdSym;
			args[3] = fourthArg;
			args[4] = c;

			// call function
			CronoTypeConstraint[] ctcArr = ctcFun.constraint(args);
			
			// add to list
			if(ctcArr != null) {
			    for(int i = 0; i < ctcArr.length; i++) {
				list.add(ctcArr[i]);
			    }
			}

			for(int i = 0; i<4; i++) {
			    if(!args[i].equals(Nil.NIL)) {
				infer(args[i], list, defined);
			    }
			}
		    }
		    else {
			args = new CronoType[c.size() - 1];

			int index = 0;

			while(it.hasNext()) {
			    args[index] = it.next();
			    index++;
			}

			args[index] = c;
		
			// call function
			CronoTypeConstraint[] ctcArr = ctcFun.constraint(args);

			// add to list
			if(ctcArr != null) {
			    for(int i = 0; i < ctcArr.length; i++) {
				list.add(ctcArr[i]);
			    }
			}

			// iterate through the rest of the list
			it = c.iterator();
			it.next();
			if(define) {
			    it.next();
			    Cons vars = (Cons) it.next();
			    Iterator<CronoType> it2 = vars.iterator();
			    while(it2.hasNext()) {
				infer(it2.next(), list, defined);
			    }
			}
			while(it.hasNext()) {
			    infer(it.next(), list, defined);
			}
		    }
		} else {
		    // it is not, see if it is in defined
		    if(defined.contains(first)) {
			// construct constraint
			CronoTypeVar right = ((Cons) c.cdr()).car().type();
			System.out.println("RIGHT: " + right);
			Iterator<CronoType> it = c.iterator();
			it.next();
			it.next();
			while(it.hasNext()) {
			    right.addVar(it.next().type());
			}

			list.add(new CronoTypeConstraint(first.type(), right));
		    } else {
			// if not throw error
			worked = false;
			return;
		    }
		}
	    }
			
	    else {
		// instance of list
		Iterator<CronoType> it = c.iterator();
		CronoType ct1 = it.next();
		CronoType ct2;
		infer(ct1, list, defined);
		while(it.hasNext()) {
		    ct2 = it.next();
		    list.add(new CronoTypeConstraint(ct1.type(), ct2.type()));
		    infer(ct2, list, defined);
		    ct1 = ct2;
		}
	    }	
	}
    }

    //used for testing
    public static void printList(List<CronoTypeConstraint> list) {
	for(int i = 0; i < list.size(); i++) {
	    System.out.println(list.get(i));
	}
    }
	
	
    public CronoTypeVar getType(CronoTypeVar ctv) {
	return cti.getType(ctv);
    }

    public static void unit(Tester t) {
	t.set_object("CronoNumber");
	Environment e = Interpreter.getDefaultEnvironment();

	int ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;
	CronoConstraintCreator ccc1 = new CronoConstraintCreator(e);

	t.set_method("type() && contraint()");
	CronoNumber cn1 = new CronoNumber(1);
	int cn1_int = ident++;
	t.is_equal("[[" + cn1_int + "]]", cn1.type().toString());



	Iterator<Symbol> it = e.iterator();
	Symbol funcKeyIF, funcKeyLET, funcKeySUB, funcKeyPRINT, funcKeyEQ, funcKeyLOAD, 
	    funcKeyCONS, funcKeyQUOTE, funcKeyADD, funcKeySET, funcKeyDEFINE, funcKeyDIV,
	    funcKeyCDR, funcKeyCAR, funcKeyMULT, funcKeyLT, funcKeyLAM;







	// testing CronoFunction - "IF"
	t.set_method("constraint() - IF");
	funcKeyIF = Symbol.valueOf("if");
	CronoFunction val = (CronoFunction) e.get(funcKeyIF);
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	CronoType[] args = new CronoType[4];
	args[0] = Symbol.valueOf("T");
	args[1] = new CronoNumber(30);
	args[2] = new CronoNumber(3);
	ArrayList<CronoType> list = new ArrayList<CronoType>();
	list.add(Symbol.valueOf("IF"));
	list.add(args[1]);
	list.add(args[2]);
	args[3] = new Cons(list);
	ident += 3;
	CronoTypeConstraint[] result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> " + args[2].type().toString() + " -> " + args[3].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = boolean -> [[52]] -> [[52]] -> [[52]]", result[1].toString());
	t.is_equal(2, result.length);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;

		
		


	// testing CronoFunction - "LET"
	t.set_method("constraint() - LET");
	funcKeyLET = Symbol.valueOf("let");
	val = (CronoFunction) e.get(funcKeyLET);
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	args = new CronoType[3];
	list.clear();
	list.add(new Symbol("x"));
	args[1] = new Cons(list);
	list.add(new CronoNumber(1));
	args[0] = new Cons(list);
	list.clear();
	list.add(new Symbol("IF"));
	list.add(args[1]);
	list.add(args[2]);
	args[2] = new Cons(list);
	t.is_equal("[[63]] = [[60]] -> [[58]] -> [[62]]", val.constraint(args)[0].toString());		
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;


		


	// testing CronoFunction - "-"
	t.set_method("constraint() - SUB");
	funcKeySUB = Symbol.valueOf("-");
	val = (CronoFunction) e.get(funcKeySUB);
	t.is_equal("[[" + (ident++) + "]]", val.type().toString());
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.clear();
	list.add(new Symbol("-"));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	t.is_equal(2, val.constraint(args).length);
	t.is_equal("[[80]] = [[70]] -> [[71]] -> [[73]]", val.constraint(args)[0].toString());
	t.is_equal("[[86]] = number -> number -> number", val.constraint(args)[1].toString());
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;


		


	// testing CronoFunction - "PRINT"
	t.set_method("constraint() - PRINT");
	funcKeyPRINT = Symbol.valueOf("print");
	val = (CronoFunction) e.get(funcKeyPRINT);
	t.is_equal(null, val.type());
	t.is_equal(null, val.constraint(null));


		
		



	// testing CronoFunction - "="
	t.set_method("constraint() - EQ");
	funcKeyEQ = Symbol.valueOf("=");
	val = (CronoFunction) e.get(funcKeyEQ);
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	list.clear();
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.add(new Symbol("="));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> " + args[2].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = number -> number -> boolean" , result[1].toString());
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;


		
		



	// testing CronoFunction - "load"
	t.set_method("constraint() - LOAD");
	funcKeyLOAD = Symbol.valueOf("load");
	val = (CronoFunction) e.get(funcKeyLOAD);
	t.is_equal(null, val.type());
	t.is_equal(null, val.constraint(null));



		



	// testing CronoFunction - "cons"
	t.set_method("constraint() - CONS");
	funcKeyCONS = Symbol.valueOf("cons");
	val = (CronoFunction) e.get(funcKeyCONS);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	list.clear();
	list.add(new CronoNumber(2));
	list.add(new CronoNumber(3));
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new Cons(list);
	list.clear();
	list.add(new Symbol("CONS"));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> " + args[2].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = [[114]] -> list([[114]]) -> list([[114]])", result[1].toString());
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 4))+1;

		
		


	// testing CronoFunction - " ' "
	t.set_method("constraint() - QUOTE");
	funcKeyQUOTE = Symbol.valueOf("'");
	val = (CronoFunction) e.get(funcKeyQUOTE);
	t.is_equal(null, val.type());
	t.is_equal(null, val.constraint(null));


		

		


	// testing CronoFunction - "+"
	t.set_method("constraint() - ADD");
	funcKeyADD = Symbol.valueOf("+");
	val = (CronoFunction) e.get(funcKeyADD);
	ident = 33;
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.clear();
	list.add(new Symbol("+"));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> "  + args[2].type().toString(), result[0].toString()); 
	t.is_equal(val.type().toString() + " = number -> number -> number", result[1].toString()); 


		
		



	// testing CronoFunction - "set"
	t.set_method("constraint() - SET");
	funcKeySET = Symbol.valueOf("set");
	val = (CronoFunction) e.get(funcKeySET);
	t.is_equal(null, val.type());
	t.is_equal(null, val.constraint(null));


		
		



	// testing CronoFunction - "define"
	t.set_method("constraint() - DEFINE");
	funcKeyDEFINE = Symbol.valueOf("define");
	val = (CronoFunction) e.get(funcKeyDEFINE);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
		
	args = new CronoType[3];
	args[0] = new Symbol("fact");
	args[1] = new Cons();
	args[2] = new LambdaFunction(null);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[1].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = " + args[0].type().toString(), result[1].toString());


		
		
		


	// testing CronoFunction - "/"
	t.set_method("constraint() - DIV");
	funcKeyDIV = Symbol.valueOf("/");
	val = (CronoFunction) e.get(funcKeyDIV);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.clear();
	list.add(new Symbol("/"));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> "  + args[2].type().toString(), result[0].toString()); 
	t.is_equal(val.type().toString() + " = number -> number -> number", result[1].toString());


		





	// testing CronoFunction - "cdr"
	t.set_method("constraint() - CDR");
	funcKeyCDR = Symbol.valueOf("cdr");
	val = (CronoFunction) e.get(funcKeyCDR);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	args = new CronoType[2];
	list.clear();
	list.add(new CronoNumber(1));
	args[0] = new Cons(list);
	args[1] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = list([[165]]) -> list([[165]])", result[1].toString()); 


		
		




	// testing CronoFunction - "car"
	t.set_method("constraint() - CAR");
	funcKeyCAR = Symbol.valueOf("car");
	val = (CronoFunction) e.get(funcKeyCAR);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	list.clear();
	args = new CronoType[2];
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	args[0] = new Cons(list);
	args[1] = new CronoNumber(1);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = list([[178]]) -> [[178]]", result[1].toString());


		
		




	// testing CronoFunction - "*"
	t.set_method("constraint() - MULT");
	funcKeyMULT = Symbol.valueOf("*");
	val = (CronoFunction) e.get(funcKeyMULT);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.clear();
	list.add(new Symbol("*"));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> " + args[2].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = number -> number -> number", result[1].toString());



		
		



	// testing CronoFunction - "<"
	t.set_method("constraint() - LT");
	funcKeyLT = Symbol.valueOf("<");
	val = (CronoFunction) e.get(funcKeyLT);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	list.clear();
	args = new CronoType[3];
	args[0] = new CronoNumber(1);
	args[1] = new CronoNumber(1);
	list.add(new Symbol("="));
	list.add(args[0]);
	list.add(args[1]);
	args[2] = new Cons(list);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[0].type().toString() + " -> " + args[1].type().toString() + " -> " + args[2].type().toString(), result[0].toString());
	t.is_equal(val.type().toString() + " = number -> number -> boolean" , result[1].toString());



		






	// testing CronoFunction - "\"
	t.set_method("constraint() - LAM");
	funcKeyLAM = Symbol.valueOf("\\");
	val = (CronoFunction) e.get(funcKeyLAM);
	ident = Integer.parseInt((new CronoTypeVar()).toString().substring(2, 5))+1;
	t.is_equal("[[" + ident++ + "]]", val.type().toString());
	list.clear();
	args = new CronoType[3];
	list.add(new Symbol("x"));
	args[0] = new Cons(list);
	args[2] = new CronoNumber(1);
	list.clear();
	list.add(new Symbol("x"));
	args[1] = new Cons(list);
	t.is_equal(2, val.constraint(args).length);
	result = val.constraint(args);
	t.is_equal(val.type().toString() + " = " + args[1].type().toString(), result[0].toString());
	t.is_equal("[[208]] = " + args[2].type().toString(), result[1].toString());




		






	t.set_object("CronoConstraintCreator");


	t.set_method("Construtor");
	t.is_true(ccc1.cti != null, "cti");
	t.is_true(ccc1.list != null, "list");
	t.is_true(ccc1.worked, "worked");
		
			
		


	t.set_method("Infer(CronoNumber) - CronoNumber");
	ccc1.infer(cn1);
	t.is_equal(1, ccc1.list.size());
	t.is_equal("[[40]] = number", ccc1.list.get(0).toString());
		
		
		
	t.set_method("Infer(CronoNumber) - Symbol");
	CronoType ct = new Symbol("n");
	ccc1 = new CronoConstraintCreator(e);
	ccc1.infer(ct);
	t.is_equal(2, ccc1.list.size());
	t.is_equal(ct.type().toString() + " = [n]", ccc1.list.get(0).toString());
	t.is_equal("[n] = " + ct.type().toString(), ccc1.list.get(1).toString());
		
		
	CronoOptions.INFER_PRINTING = true;
		
	t.set_method("Infer(CronoType) - ADD");
	ccc1 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeyADD);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	ct = new Cons(list);
	ccc1.infer(ct);
	t.is_equal(4, ccc1.list.size());
	t.is_equal("[[231]] = " + list.get(1).type() + " -> " + list.get(2).type() + " -> " + ct.type(), ccc1.list.get(0).toString());
	t.is_equal("[[231]] = number -> number -> number", ccc1.list.get(1).toString());
	t.is_equal(list.get(1).type() + " = number", ccc1.list.get(2).toString());
	t.is_equal(list.get(2).type() + " = number", ccc1.list.get(3).toString());
		
		
		
		
		
	t.set_method("Infer(CronoType) - SUB");
	CronoConstraintCreator ccc2 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeySUB);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	ct = new Cons(list);
	ccc2.infer(ct);
	t.is_equal("[[240]] = " + list.get(1).type() + " -> " + list.get(2).type() + " -> " + ct.type(), ccc2.list.get(0).toString());
	t.is_equal("[[240]] = number -> number -> number", ccc2.list.get(1).toString());
	t.is_equal(list.get(1).type() + " = number", ccc2.list.get(2).toString());
	t.is_equal(list.get(2).type() + " = number", ccc2.list.get(3).toString());
		
		
		
	CronoConstraintCreator ccc3 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeySUB);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	CronoType temp1 = new Cons(list);
	list.clear();
	list.add(funcKeySUB);
	list.add(temp1);
	list.add(new CronoNumber(1));
	ct = new Cons(list);
	ccc3.infer(ct);
	t.is_equal("[[251]] = [[248]] -> [[249]] -> [[250]]", ccc3.list.get(0).toString());
	t.is_equal("[[251]] = number -> number -> number", ccc3.list.get(1).toString());
	t.is_equal("[[257]] = [[246]] -> [[247]] -> [[248]]", ccc3.list.get(2).toString());
	t.is_equal("[[257]] = number -> number -> number", ccc3.list.get(3).toString());
	t.is_equal("[[246]] = number", ccc3.list.get(4).toString());
	t.is_equal("[[247]] = number", ccc3.list.get(5).toString());
	t.is_equal("[[249]] = number", ccc3.list.get(6).toString());
		
		
		
		
	t.set_method("Infer(CronoNumber) - DIV");
	CronoConstraintCreator ccc4 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeyDIV);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	ct = new Cons(list);
	ccc4.infer(ct);
	t.is_equal(4, ccc4.list.size());
	t.is_equal("[[266]] = " + list.get(1).type() + " -> " + list.get(2).type() + " -> " + ct.type(), ccc4.list.get(0).toString());
	t.is_equal("[[266]] = number -> number -> number", ccc4.list.get(1).toString());
	t.is_equal(list.get(1).type() + " = number", ccc4.list.get(2).toString());
	t.is_equal(list.get(2).type() + " = number", ccc4.list.get(3).toString());
		
		
		
		
		
	t.set_method("Infer(CronoNumber) - MULT");
	CronoConstraintCreator ccc5 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeyMULT);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	ct = new Cons(list);
	ccc5.infer(ct);
	t.is_equal(4, ccc5.list.size());
	t.is_equal("[[275]] = " + list.get(1).type() + " -> " + list.get(2).type() + " -> " + ct.type(), ccc5.list.get(0).toString());
	t.is_equal("[[275]] = number -> number -> number", ccc5.list.get(1).toString());
	t.is_equal(list.get(1).type() + " = number", ccc5.list.get(2).toString());
	t.is_equal(list.get(2).type() + " = number", ccc5.list.get(3).toString());
		
		
		
		
		
	t.set_method("Infer(CronoType) - IF");
	CronoConstraintCreator ccc6 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeyEQ);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	Cons con1 = new Cons(list);
	ArrayList<CronoType> list2 = new ArrayList<CronoType>();
	list2.add(funcKeyIF);
	list2.add(con1);
	list2.add(new CronoNumber(1));
	list2.add(new CronoNumber(0));
	ct = new Cons(list2);
	ccc6.infer(ct);
	t.is_equal(8, ccc6.list.size());
	t.is_equal("[[287]] = " + list2.get(1).type() + " -> " + list2.get(2).type() + " -> " + list2.get(3).type() + " -> " + ct.type(), ccc6.list.get(0).toString());
	t.is_equal("[[287]] = boolean -> [[293]] -> [[293]] -> [[293]]", ccc6.list.get(1).toString());
	t.is_equal("[[295]] = " + list.get(1).type() + " -> " + list.get(2).type() + " -> " + list2.get(1).type(), ccc6.list.get(2).toString());
	t.is_equal("[[295]] = number -> number -> boolean", ccc6.list.get(3).toString());
	t.is_equal("[[281]] = number", ccc6.list.get(4).toString());
	t.is_equal("[[282]] = number", ccc6.list.get(5).toString());
	t.is_equal("[[284]] = number", ccc6.list.get(6).toString());
	t.is_equal("[[285]] = number", ccc6.list.get(7).toString());
		
		
		
		
		
	t.set_method("Infer(CronoType) - DEFINE");
	CronoConstraintCreator ccc7 = new CronoConstraintCreator(e);
	list.clear();
	list.add(funcKeyDEFINE);
	list.add(new Symbol("double"));
	list.add(new Symbol("x"));
	list2.clear();
	list2.add(funcKeyMULT);
	list2.add(new Symbol("x"));
	list2.add(new CronoNumber(2));
	Cons cons1 = new Cons(list2);
	list.add(cons1);
	ct = new Cons(list);
	ccc7.infer(ct);
	t.is_equal(12, ccc7.list.size());
	t.is_equal("[[307]] = " + list.get(2).type() + " -> " + list.get(3).type(), ccc7.list.get(0).toString());
	t.is_equal("[[307]] = " + list.get(1).type(), ccc7.list.get(1).toString());
	t.is_equal("[[307]] = " + ct.type(), ccc7.list.get(2).toString());
	t.is_equal(list.get(1).type() + " = [double]", ccc7.list.get(3).toString());
	t.is_equal("[double] = " + list.get(1).type(), ccc7.list.get(4).toString());
	t.is_equal(list.get(2).type() + " = [x]", ccc7.list.get(5).toString());
	t.is_equal("[x] = " + list.get(2).type(), ccc7.list.get(6).toString());
	t.is_equal("[[323]] = " + list2.get(1).type() + " -> " + list2.get(2).type() + " -> " + cons1.type(), ccc7.list.get(7).toString());
	t.is_equal("[[323]] = number -> number -> number", ccc7.list.get(8).toString());
	t.is_equal(list2.get(1).type() + " = [x]", ccc7.list.get(9).toString());
	t.is_equal("[x] = " + list2.get(1).type(), ccc7.list.get(10).toString());
	t.is_equal(list2.get(2).type() + " = number", ccc7.list.get(11).toString());
		
		
		
	CronoOptions.INFER_PRINTING = false;
		
	t.set_method("Unify()");
	ccc1.unify();
	ccc2.unify();
	ccc3.unify();
	ccc4.unify();
	ccc5.unify();
	ccc6.unify();
	CronoOptions.INFER_PRINTING = true;
	CronoOptions.INFER_CYCLING = false;
	ccc7.unify();
	System.out.println(ccc7.getType(new CronoTypeVar("double")));
    }

	

    public static void unit2(Tester t) {
	t.set_method("Infer(CronoType) - ADD");
	Environment e = Interpreter.getDefaultEnvironment();
	CronoConstraintCreator ccc1 = new CronoConstraintCreator(e);
	ArrayList<CronoType> list = new ArrayList<CronoType>();
	Symbol funcKeyADD = Symbol.valueOf("-");
	list.add(funcKeyADD);
	list.add(new CronoNumber(1));
	list.add(new CronoNumber(1));
	CronoType ct = new Cons(list);
	ccc1.infer(ct);
    }
}
