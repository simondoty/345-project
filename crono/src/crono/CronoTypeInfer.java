package crono;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CronoTypeInfer {

	
	
	// use a LinkedList over a stack for easier printing
	LinkedList<CronoTypeConstraint> ListOnStack;
	// when constraints get popped off the stack they are added
	// into consDone to signal that they have finished being consumed
	LinkedList<CronoTypeConstraint> ConsFinished;

	public CronoTypeInfer() {
		ListOnStack = new LinkedList<CronoTypeConstraint>();
		ConsFinished = new LinkedList<CronoTypeConstraint>();
	}
	
	
	
	
	
	
	
	
	

	// Crono will call parser which yields the function then it
	// will send the function as an abstract syntax tree to <Graham's currently implementing this class>
	// which will "feed" the constraints to ListOnStack

	// adding a constraint onto the ListOnStack, later to be consumed
	public void addConstraint(CronoTypeConstraint con) {
		ListOnStack.add(con);
	}
	
	
	
	
	
	
	

	// "popping" the first element off the stack to be used 
	public CronoTypeConstraint getConstraint() {
		return (CronoTypeConstraint) ListOnStack.remove();
	}
	
	
	
	
	
	
	

	//  Unification algorithm:
	//	1. If X and Y are identical identifiers, do nothing.
	//	2. If X is an identifier, replace all occurrences of X by Y both on the stack and in the substitution, and
	//	add X -> Y to the substitution.
	//	3. If Y is an identifier, replace all occurrences of Y by X both on the stack and in the substitution, and
	//	add Y -> X to the substitution.
	//	4. If X is of the form C(X1, . . . ,Xn) for some constructor C,4 and Y is of the form C(Y1, . . . ,Yn) (i.e., it
	//	has the same constructor), then push Xi =Yi for all 1  i  n onto the stack.
	//	5. Otherwise, X and Y do not unify. Report an error.	

	
	public boolean unify() {
		// assert that no more constraints are going to be added
	    //System.out.println("Unifying.............");
	    /*  for(CronoTypeConstraint ctc: ListOnStack) {
		System.out.println("CTC: " + ctc);
		}*/
		Scanner sc = new Scanner(System.in);
		if(CronoOptions.INFER_PRINTING && CronoOptions.INFER_CYCLING) {
			System.out.print("Press enter begin unification process. ");
			sc.nextLine();
		}
		
		if(CronoOptions.INFER_PRINTING) {
			// printing the initial stack
			printLists();
		}
		
		while(ListOnStack.size() != 0)
		{

			// grab next constraint
			CronoTypeConstraint constraint = (CronoTypeConstraint) ListOnStack.remove();
			
			if(CronoOptions.INFER_PRINTING && CronoOptions.INFER_CYCLING) {
				System.out.print("Press enter to perform next iteration. ");
				sc.nextLine();
			}
			
			// check if constraint needs to be split
			if(constraint.needsBreakUp()) {
				
				if(CronoOptions.INFER_PRINTING && CronoOptions.INFER_CYCLING) {
					System.out.print("Adjusting the Constraint, press enter to continue.");
					sc.nextLine();
				}
				
				CronoTypeConstraint[] addConsToStack = constraint.breakUpConstraint();
				// add all broken constraints onto the stack to be processed
				for(int i = 0; i < addConsToStack.length; i++) {
				    //     System.out.println(addConsToStack[i] + " Stack..........");
					ListOnStack.addFirst(addConsToStack[i]);
				}
				
				if(CronoOptions.INFER_PRINTING) {
					printLists();
				}
				
				if(CronoOptions.INFER_PRINTING && CronoOptions.INFER_CYCLING) {
					System.out.print("Press enter to perform next iteration. ");
					sc.nextLine();
				}
				
				// throwing away the constraint that needed to be split and grabbing it's "child"
				constraint = (CronoTypeConstraint) ListOnStack.remove();
			}
			
			
			// check to make sure that the constraint isn't a problem
			if(!constraint.isCorrect()) {
				return false;
			}
			
			if(CronoOptions.INFER_PRINTING) {
				System.out.println("Working with " + constraint + "\n");
			}

			if(constraint.canContinue()) {	// make sure that you can continue, that the left is an identifier
				// apply constraint onto ListOnStack AND the ConsFinished
				for(int i = 0; i < ListOnStack.size(); i++) {
				    ((CronoTypeConstraint) ListOnStack.get(i)).subConstraint(constraint);
				}

				// fence post checking for initial i = 0, ConsFinished will be 0
				if(ConsFinished.size() != 0) {
					// substituting constraint into finished list
					for(int i = 0; i < ConsFinished.size(); i++)
						((CronoTypeConstraint) ConsFinished.get(i)).subConstraint(constraint);
				}	

				// put constraint into finished list
				ConsFinished.add(constraint);
			}

			if(CronoOptions.INFER_PRINTING) {
				// printing both the initial stack and finished stack
				printLists();
			}
		}
		
		if(CronoOptions.INFER_PRINTING)
			System.out.println("End of unification process. ");
			
		return true;
	}
	
	
	
	
	
	
	
	
	
	

	// printLists() is a reference for Cannata
	// this gets called every "iteration", printing out 
	// the stack and then the Substitution.
	// refer to page 281 for the figure.
	public void printLists() {
		CronoTypeConstraint constraint;
		if(ListOnStack.size() > 0) 
			System.out.println("List of Constraints on Stack: ");
		for(int i = 0; i < ListOnStack.size(); i++) {
			constraint = (CronoTypeConstraint) ListOnStack.get(i);
			System.out.println("\t" + constraint.toString());
		}
		if(ConsFinished.size() > 0)
			System.out.println("Constraints finished... ");
		for(int i = 0; i < ConsFinished.size(); i++) {
			constraint = (CronoTypeConstraint) ConsFinished.get(i);
			System.out.println("\t" + constraint.toString());
		}		
		System.out.println();
	}
	
	
	
	
	
	public CronoTypeVar getType(CronoTypeVar ctv) {
		Iterator<CronoTypeConstraint> it = ConsFinished.iterator();
		while(it.hasNext()) {
			CronoTypeConstraint temp = it.next();
			//System.out.println("Temp: ..... " + temp);
			CronoTypeVar out = temp.getType(ctv);
			if(out != null) return out;
		}
		return null;
	}
}
