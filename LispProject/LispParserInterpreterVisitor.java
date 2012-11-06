/* Interpreter Visitor */

public class LispParserInterpreterVisitor implements LispParserVisitor
{
  private int indent = 0;

  private String indentString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < indent; ++i) {
      sb.append(' ');
    }
    return sb.toString();
  }

  public Object visit(SimpleNode node, Object data) {
    //System.out.println(indentString() + node +
    //               ": acceptor not unimplemented in subclass?");
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

  public Object visit(ASTProgram node, Object data) {
    //System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    //return data;
    
    return node.jjtGetChild(0).jjtAccept(this, data);    
    
  }

 
  public Object visit(ASTExpr node, Object data) {	    
		//only addition for now
		int sum = 0;
		Object arg1 = node.jjtGetChild(0).jjtAccept(this, data);
		Object arg2 = node.jjtGetChild(1).jjtAccept(this, data);		
	  ++indent;		 
	  --indent;
	  sum = (Integer)arg1 + (Integer) arg2;
		//System.out.println("arg1: " + arg1 + " and arg2: " + arg2 + " and sum is: " + sum);
    return (Integer) sum;
  }

  	public Object visit(ASTNum node, Object data) {
    //since this is a num, just return value
    return node.getVal();
  }
 
      public Object visit(ASTLambda node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

      public Object visit(ASTBody node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }


  /*public Object visit(ASTInteger node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }*/
}
