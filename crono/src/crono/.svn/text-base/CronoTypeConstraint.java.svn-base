package crono;

public class CronoTypeConstraint {

	CronoTypeVar varL;
	CronoTypeVar varR;
	boolean ToGo;

	
	public CronoTypeConstraint(CronoTypeVar l, CronoTypeVar r) {
		varL = l;
		varR = r;
		ToGo = true;
	}
	
	// subRule(CronoTypeRule)
	public void subConstraint(CronoTypeConstraint ctc) {
		if (ctc.varL.needsBreakUp()) {
			if (ctc.varL.size() > ctc.varR.size()) {
				throw new IllegalStateException("Function not formatted correctly");
			}
		}
		// if on ToGo
		if(ToGo) {
			// sub into both
			varL.subVariable(ctc.varL.clone(), ctc.varR.clone());
			varR.subVariable(ctc.varL, ctc.varR);
		} else {
			varR.subVariable(ctc.varL, ctc.varR);
		}
		
	}
	
	public CronoTypeVar getType(CronoTypeVar ctv) {
		if(varL.equals(ctv)) return varR;
		return null;
	}
	
	public CronoTypeConstraint[] breakUpConstraint() {
		if((varR.isIdentifier() || varR.isSymbol()) && varR.size() == 1) {
			CronoTypeConstraint[] out = new CronoTypeConstraint[1];
			out[0] = new CronoTypeConstraint(varR.clone(), varL.clone());
			return out;
		}
		if (varL.needsBreakUp()) {
			if (varL.size() != varR.size()) {
				throw new IllegalStateException("Function not formatted correctly");
			}
		}
		
		CronoTypeConstraint[] out = new CronoTypeConstraint[varL.size()];
		CronoTypeVar[] variablesL = varL.breakUpVar();
		CronoTypeVar[] variablesR = varR.breakUpVar();
		
		for(int i = 0; i < varL.size(); i++) {
			out[i] = new CronoTypeConstraint(variablesL[i], variablesR[i]);
		}
		
		return out;
	}
	
	// needs break up
	public boolean needsBreakUp() {
		return varL.needsBreakUp();
	}
	
	// printStack - uses "="
	public String toStringStack() {
		return toString(" = ");
	}
	
	// printSub - uses "=>"
	public String toStringSub() {
		return toString(" => ");
	}
	
	private String toString(String n) {
		String ret = varL.toString() + n;
		return ret + varR.toString();
	}
	
	// getVariableL
	public CronoTypeVar getVarL() {
		return varL;
	}
	
	// getVariableR
	public CronoTypeVar getVarR() {
		return varR;
	}
	
	// toString() - uses printRule
	public String toString() {
		return toStringStack();
	}
	
	public boolean equals(CronoTypeConstraint ctc) {
		boolean result = (this.ToGo == ctc.ToGo);
		result = result && this.varL.equals(ctc.varL);
		result = result && this.varR.equals(ctc.varR);
		return result;
	}

	// equals
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this == o) return true;
		if(o instanceof CronoTypeConstraint) {
			equals((CronoTypeConstraint) o);
		}
		return false;
	}
	
	public void finish() {
		ToGo = false;
	}
	
	public boolean isCorrect() {
		return varL.canBeEqual(varR);
	}
	
	public boolean canContinue() {
		if(varL.isIdentifier()) {
			return !(varL.equals(varR));
		} else if(varL.isSymbol()) {
			return !(varL.equals(varR));
		} else if ((varR.isIdentifier()) && varR.size() == 1) {
			CronoTypeVar temp = varL;
			varL = varR;
			varR = temp;
			return true;
		}
		return false;
	}

	public static void unit(Tester t) {
		t.set_object("CronoTypeConstraint");
		
		
		
		t.set_method("Constructor(CronoTypeVar, CronoTypeVar)");
		CronoTypeVar ctv11 = new CronoTypeVar();
		CronoTypeVar ctv12 = new CronoTypeVar();
		CronoTypeConstraint ctc1 = new CronoTypeConstraint(ctv11, ctv12);
		t.is_equal(ctv11, ctc1.varL);
		t.is_equal(ctv12, ctc1.varR);
		t.is_true(ctc1.ToGo);
		
		CronoTypeVar ctv21 = new CronoTypeVar("x");
		CronoTypeVar ctv22 = new CronoTypeVar("y");
		CronoTypeConstraint ctc2 = new CronoTypeConstraint(ctv21, ctv22);
		t.is_equal(ctv21, ctc2.varL);
		t.is_equal(ctv22, ctc2.varR);
		t.is_true(ctc2.ToGo);
		
		CronoTypeVar ctv31 = new CronoTypeVar(1);
		CronoTypeVar ctv32 = new CronoTypeVar(2);
		CronoTypeConstraint ctc3 = new CronoTypeConstraint(ctv31, ctv32);
		t.is_equal(ctv31, ctc3.varL);
		t.is_equal(ctv32, ctc3.varR);
		t.is_true(ctc3.ToGo);
		
		
		
		
		
		t.set_method("finish()");
		ctc1.finish();
		t.is_true(!ctc1.ToGo);
		ctc1.ToGo = true;
		
		
		
		
		t.set_method("getVarL()");
		t.is_equal(ctv11, ctc1.getVarL());
		t.is_equal(ctv21, ctc2.getVarL());
		t.is_equal(ctv31, ctc3.getVarL());
		
		
		
		
		t.set_method("getVarR()");
		t.is_equal(ctv12, ctc1.getVarR());
		t.is_equal(ctv22, ctc2.getVarR());
		t.is_equal(ctv32, ctc3.getVarR());
		
		
		
		
		t.set_method("toStringStack()");
		t.is_equal("[[14]] = [[15]]", ctc1.toStringStack());
		t.is_equal("[x] = [y]", ctc2.toStringStack());
		t.is_equal("number = number", ctc3.toStringStack());
		
		
		
		
		t.set_method("toStringSub()");
		t.is_equal("[[14]] => [[15]]", ctc1.toStringSub());
		t.is_equal("[x] => [y]", ctc2.toStringSub());
		t.is_equal("number => number", ctc3.toStringSub());
		
		
		
		
		t.set_method("subConstraint(CronoTypeConstraint()");
		CronoTypeVar ctvFrom = ctv11;
		CronoTypeVar ctvTo = new CronoTypeVar("to");
		CronoTypeConstraint ctcTo = new CronoTypeConstraint(ctvFrom, ctvTo);
		ctvTo.addVar(new CronoTypeVar("go"));
		ctc1.subConstraint(ctcTo);
		t.is_equal("[to] -> [go] => [[15]]", ctc1.toStringSub());
		
		
		
		
		t.set_method("breakUpConstraint()");
		CronoTypeVar ctvA1 = new CronoTypeVar();
		ctvA1.addVar(new CronoTypeVar());
		CronoTypeVar ctvB1 = new CronoTypeVar();
		ctvB1.addVar(new CronoTypeVar());
		CronoTypeConstraint ctvAB1 = new CronoTypeConstraint(ctvA1, ctvB1);
		t.is_true(ctvAB1.needsBreakUp());
		CronoTypeConstraint[] ctcArray = ctvAB1.breakUpConstraint();
		t.is_equal("[[18]] = [[20]]", ctcArray[0].toString());
		t.is_equal("[[19]] = [[21]]", ctcArray[1].toString());
		
	}

}
