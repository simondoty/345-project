package crono;

import java.util.ArrayList;
import java.util.Iterator;


public class CronoTypeVar implements Cloneable{

	// variable
	public ArrayList<Object> vars;


	/**
	 * Constructor taking no arguments 
	 * Makes the var a unique Identifier
	 */
	public CronoTypeVar() {
		vars = new ArrayList<Object>();
		vars.add(new Identifier());
	}

	// constructor for know variable
	public CronoTypeVar(String s) {
		vars = new ArrayList<Object>();
		vars.add(s);
	}

	// constructor for a number
	public CronoTypeVar(Integer i) {
		vars = new ArrayList<Object>();
		vars.add(i);
	}

	// constructor for a list type
	public CronoTypeVar(CronoTypeVar ctv) {
		vars = new ArrayList<Object>();
		vars.add(ctv);
	}

	// constructor of boolean type
	public CronoTypeVar(boolean b) {
		vars = new ArrayList<Object>();
		vars.add(b);
	}

	// Constructor for Object
	private CronoTypeVar(Object o) {
		vars = new ArrayList<Object>();
		vars.add(o);
	}


	// set is nil
	public void makeNil() {
		if(needsBreakUp()) {
			throw new IllegalStateException("Problem with Function");
		}
		vars.clear();
		vars.add(null);
	}

	// add VarToList
	public void addVar(CronoTypeVar ctv) {
		ArrayList<Object> ctvC = new ArrayList<Object>(ctv.vars);
		for(int i = 0; i < ctvC.size(); i++) {
			vars.add(ctvC.get(i));
		}
	}

	// print variable - using "->"
	public String toString() {
		Iterator<Object> it = vars.iterator();
		String out = "";
		if(it.hasNext()) {
			out += getIdentifier(it.next());
		}
		//System.out.println("Printing Type");
		while(it.hasNext()) {
			out += " -> " + getIdentifier(it.next());
		}

		return out;
	}

	private String getIdentifier(Object o) {
		if(o instanceof Integer) {
			return "number";
		}
		if(o instanceof String) {
			String s = (String) o;
			return "[" + s + "]";
		}
		if(o == null) {
			return "NIL";
		}
		if(o instanceof CronoTypeVar) {
			return "list(" + o.toString() + ")";
		}
		if(o instanceof Identifier) {
			return o.toString();
		}
		if(o instanceof Boolean) {
			return "boolean";
		}
		throw new IllegalStateException();
	}

	// subVariable(CronoTypeRule ctr)
	public void subVariable(CronoTypeVar from, CronoTypeVar to) {
		if(this.equals(from)) {
			vars = new ArrayList<Object>(to.vars);
			return;
		}
		//if(from.equals(to)) return;
		int start = find(from);
		if (start >= 0) {
			replace(start, from.vars.size(), to);
			subVariable(from, to);
		}
	}

	private int find(CronoTypeVar from) {
		int out = -1;
		for(int i = 0; i < this.vars.size() && out == -1; i++) {
			boolean found = this.vars.get(i).equals(from.vars.get(0));
			for(int j = 0; found && j < from.vars.size() && (j+i) < this.vars.size(); j++) { // && (j+i) < this.vars.size()
				found = this.vars.get(j + i).equals(from.vars.get(j));
			}
			if(found) out = i;
		}

		return out;
	}

	private void replace(int start, int length, CronoTypeVar to) {

		for(int i = start; i < length+start; i++) {
			vars.remove(start);
		}
		for(int i = 0; i < to.vars.size(); i++) {
			vars.add(i+start, to.vars.get(i));
		}
	}

	// equals
	public boolean equals(CronoTypeVar other) {
		if (vars.size() == other.vars.size()) {
			for(int i = 0; i < this.vars.size(); i++) {
				if(!this.vars.get(i).equals(other.vars.get(i))) { 
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (o instanceof CronoTypeVar) {
			return equals((CronoTypeVar) o);
		} else
			return false;
	}

	public boolean needsBreakUp() {
		return vars.size() > 1;
	}

	public int size() {
		return vars.size();
	}

	public CronoTypeVar[] breakUpVar() {
		CronoTypeVar[] out = new CronoTypeVar[vars.size()];

		for(int i = 0; i < vars.size(); i++) {
			out[i] = new CronoTypeVar(vars.get(i));
		}

		return out;
	}

	public CronoTypeVar clone() {
		CronoTypeVar ret = new CronoTypeVar();
		ret.vars = new ArrayList<Object>();
		for(int i = 0; i < vars.size(); i++) {
			if(vars.get(i) instanceof Identifier)
				ret.vars.add(((Identifier) vars.get(i)).clone());
			else 
				ret.vars.add(vars.get(i));
		}
		return ret;
	}

	public boolean isIdentifier() {
		for(int i = 0; i < vars.size(); i++) {
			if(vars.get(i) instanceof Identifier) {
				return true;
			}
		}
		return false;
	}

	public boolean isSymbol() {
		if(vars.size() > 1) {
			return false;
		} else {
			if(vars.get(0) instanceof String) {
				return true;
			}
			return false;
		}
	}


	public boolean canBeEqual(CronoTypeVar varR) {
		if(this.isIdentifier() || this.isSymbol()) return true;
		if(varR.isIdentifier() || varR.isSymbol()) return true;
		if(this.vars.size() == varR.vars.size()) {
			for(int i = 0; i < vars.size(); i++) {
				if(this.vars.get(i).getClass() != varR.vars.get(i).getClass()) 
					return false;
			}
			return true;
		}
		return false;
	}





	public static void unit(Tester t) {
		t.set_object("CronoTypeVar");

		t.set_method("CronoTypeVar()");
		CronoTypeVar ctv1 = new CronoTypeVar();
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("[[5]]", ctv1.vars.get(0).toString());

		ctv1 = new CronoTypeVar();
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("[[6]]", ctv1.vars.get(0).toString());

		ctv1 = new CronoTypeVar();
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("[[7]]", ctv1.vars.get(0).toString());






		t.set_method("CronoTypeVar(int)");
		ctv1 = new CronoTypeVar(99);
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(99, ctv1.vars.get(0));

		ctv1 = new CronoTypeVar(7653);
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(7653, ctv1.vars.get(0));

		ctv1 = new CronoTypeVar(2);
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(2, ctv1.vars.get(0));

		ctv1 = new CronoTypeVar(45);
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(45, ctv1.vars.get(0));

		ctv1 = new CronoTypeVar(789);
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(789, ctv1.vars.get(0));





		t.set_method("CronoTypeVar(String)");
		ctv1 = new CronoTypeVar("n");
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("n", ctv1.vars.get(0));

		ctv1 = new CronoTypeVar("x");
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("x", ctv1.vars.get(0));

		ctv1 = new CronoTypeVar("name");
		t.is_equal(1, ctv1.vars.size());
		t.is_equal("name", ctv1.vars.get(0));





		t.set_method("CronoTypeVar(boolean)");
		CronoTypeVar ctvb1 = new CronoTypeVar(true);
		t.is_equal(1, ctvb1.vars.size());
		t.is_equal(new Boolean(true), ctvb1.vars.get(0));
		t.is_equal("boolean", ctvb1.toString());




		t.set_method("CronoTypeVar(CronoTypeVar)");
		CronoTypeVar ctvL1 = new CronoTypeVar(new CronoTypeVar(9));
		t.is_equal(1, ctvL1.vars.size());
		t.is_equal(ctvL1.vars.get(0), new CronoTypeVar(9));





		t.set_method("makeNil()");
		ctv1.makeNil();
		t.is_equal(1, ctv1.vars.size());
		t.is_equal(null, ctv1.vars.get(0));






		t.set_method("addVar(CronoTypeVar)");
		CronoTypeVar ctv2 = new CronoTypeVar(2);
		ctv1 = new CronoTypeVar(1);
		ctv1.addVar(ctv2);
		t.is_equal(2, ctv1.vars.size());
		t.is_equal(1, ctv1.vars.get(0));
		t.is_equal(2, ctv1.vars.get(1));






		t.set_method("toString()");
		t.is_equal("number -> number", ctv1.toString());
		ctv1 = new CronoTypeVar();
		ctv1.addVar(new CronoTypeVar());
		t.is_equal("[[8]] -> [[9]]", ctv1.toString());
		ctv1.addVar(new CronoTypeVar("x"));
		t.is_equal("[[8]] -> [[9]] -> [x]", ctv1.toString());
		CronoTypeVar ctv4 = new CronoTypeVar(new CronoTypeVar(9));
		t.is_equal("list(number)", ctv4.toString());





		t.set_method("size()");
		t.is_equal(3, ctv1.size());
		t.is_equal(1, (new CronoTypeVar()).size());




		t.set_method("isDouble()");
		t.is_true(ctv1.needsBreakUp());
		t.is_true(!((new CronoTypeVar()).needsBreakUp()));







		t.set_method("equals(Object)");
		t.is_true((new CronoTypeVar(1)).equals((new CronoTypeVar(1))));
		t.is_true((new CronoTypeVar("name")).equals((new CronoTypeVar("name"))));
		t.is_true(!(new CronoTypeVar(1)).equals((new CronoTypeVar("name"))));
		t.is_true(!(new CronoTypeVar("name")).equals((new CronoTypeVar(1))));





		t.set_method("breakUpVar()");
		CronoTypeVar[] ctvArray = ctv1.breakUpVar();
		t.is_equal(3, ctvArray.length);
		t.is_equal(ctvArray[0].vars.get(0), ctv1.vars.get(0));
		t.is_equal(ctvArray[1].vars.get(0), ctv1.vars.get(1));
		t.is_equal(ctvArray[2].vars.get(0), ctv1.vars.get(2));





		t.set_method("replace(int, int, CronoTypeVar)");
		CronoTypeVar ctvFrom = new CronoTypeVar("x");
		CronoTypeVar ctvTo = new CronoTypeVar("t");
		ctvTo.addVar(new CronoTypeVar("m"));
		ctv1.replace(2, 1, ctvTo);
		t.is_equal(4, ctv1.vars.size());
		t.is_equal("[[8]] -> [[9]] -> [t] -> [m]", ctv1.toString());
		ctv1.replace(2, 2, ctvFrom);
		t.is_equal(3, ctv1.vars.size());
		t.is_equal("[[8]] -> [[9]] -> [x]", ctv1.toString());





		t.set_method("find(CronoTypeVar)");
		t.is_equal(2, ctv1.find(ctvFrom));




		t.set_method("subVariable(CronoTypeVar, CronoTypeVar)");
		ctv1.subVariable(ctvFrom, ctvTo);
		t.is_equal(4, ctv1.vars.size());
		t.is_equal("[[8]] -> [[9]] -> [t] -> [m]", ctv1.toString());


		ctv1.subVariable(ctvTo, ctvFrom);
		t.is_equal(3, ctv1.vars.size());
		t.is_equal("[[8]] -> [[9]] -> [x]", ctv1.toString());

		ctv1.addVar(new CronoTypeVar("x"));
		ctv1.subVariable(ctvFrom, ctvTo);
		t.is_equal(6, ctv1.vars.size());
		t.is_equal("[[8]] -> [[9]] -> [t] -> [m] -> [t] -> [m]", ctv1.toString());

		ctv1 = new CronoTypeVar();
		CronoTypeVar ctv1c = ctv1.clone();
		t.is_equal("[[12]]", ctv1.toString());
		t.is_equal("[[12]]", ctv1c.toString());
		t.is_true(ctv1 != ctv1c);
		ctv1.addVar(ctv1);
		t.is_equal("[[12]] -> [[12]]", ctv1.toString());
		ctv1.subVariable(ctv1c, ctvFrom);
		t.is_equal("[x] -> [x]", ctv1.toString());

		/*
		ctv1 = new CronoTypeVar();
		ctv1c = ctv1.clone();
		ctvTo = new CronoTypeVar();
		ctvTo.addVar(new CronoTypeVar());
		ctvTo.addVar(new CronoTypeVar());
		ctv1.subVariable(ctv1c, ctvTo);
		t.is_equal("", ctv1.toString());
		 */
	}




}
