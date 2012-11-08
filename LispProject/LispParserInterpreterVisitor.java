/* Interpreter Visitor */
package Lisp;
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

  // *********************************************************************
  public Object visit(SimpleNode node, Object data) {
    //System.out.println(indentString() + node +
    //               ": acceptor not unimplemented in subclass?");
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

  // *********************************************************************
  public Object visit(ASTProgram node, Object data) {
    //System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    //return data;
    
    return node.jjtGetChild(0).jjtAccept(this, data);    
    
  }

   // *********************************************************************
  public Object visit(ASTArithExpr node, Object data) {
    String sOp = node.getOp();
    
    if(sOp.equals("+")) {
      int sum = 0;
      
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
    		Object result = node.jjtGetChild(iChild).jjtAccept(this, data);      
        
        sum += (Integer) result;
        
      }
      
      return (Integer) sum;
    
    } else if(sOp.equals("-")) {
      // first child minus the rest
      int diff = (Integer) node.jjtGetChild(0).jjtAccept(this, data);      
      
      for(int iChild=1;iChild < node.jjtGetNumChildren(); iChild++) {
    		Object result = node.jjtGetChild(iChild).jjtAccept(this, data);      
        
        diff -= (Integer) result;
        
      }
      
      return (Integer) diff;    
    
   
    } else if(sOp.equals("*")) {
      int prod = 1;
      
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
    		Object result = node.jjtGetChild(iChild).jjtAccept(this, data);      
        
        prod *= (Integer) result;
        
      }
      
      return (Integer) prod;    
    
    } else if(sOp.equals("/")) {
      int div = (Integer) node.jjtGetChild(0).jjtAccept(this, data);      
      
      for(int iChild=1;iChild < node.jjtGetNumChildren(); iChild++) {
    		Object result = node.jjtGetChild(iChild).jjtAccept(this, data);      
        
        div /= (Integer) result;
        
      }
      
      return (Integer) div;     
    
    } else {
      return null;
    
    }
    
  }

  // *********************************************************************
  	public Object visit(ASTNum node, Object data) {
    //since this is a num, just return value
    return node.getVal();
  }

  // *********************************************************************
	public Object visit(ASTIdentifier node, Object data) {
    //since this is a num, just return value
    return node.getIdentifier();
    
  }
    
  // *********************************************************************
  public Object visit(ASTLambdaExpr node, Object data) {
    // just return self.
    return node;
      
  }
  
  // *********************************************************************
  
  // E.g. ((lambda (x) x) 3)
  
  // Child 0   = Lambda()                  = (lambda (x) x)
  // Child 0.0 = Identifier()              = x                ==> param
  // Child 0.1 = Anything() = Identifier() = x                ==> body
  
  // Child 1   = Anything() = Num()        = 3                ==> arg
  
  public Object visit(ASTFunctionExpr node, Object data) {
    // get param id of lambda expr - DO NOT EVAL
    ASTIdentifier param = (ASTIdentifier) node.jjtGetChild(0).jjtGetChild(0);

    // get body of lambda expr - DO NOT EVAL
    Node body = node.jjtGetChild(0).jjtGetChild(1);

    // "evaluate" child argument. this would require an environment to build.
    Object arg = node.jjtGetChild(1).jjtAccept(this, data);   
    
    // call replace method
    // body can be:
    // number
    // identifier
    // lambda expression
    // lambda applicatoin
    
    for(int iChild=0;iChild < body.jjtGetNumChildren(); iChild++) {
      Node n = body.jjtGetChild(iChild);
      
      // based on body child, try to replace or not.
      if(n instanceof ASTIdentifier) {
        ASTIdentifier ident = (ASTIdentifier) n;
      
        // if part of body matches parameter,
        if(ident.getIdentifier().equals(param.getIdentifier()  )    ) {
          // replace that part of body with the passed-in argument.
          body.children[iChild]  = (Node) arg;
        
        }
      
      }
    
    }
      
    // just return self.
    return body.jjtAccept(this, data);
  
  }
  
  /*
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  */

 /*
      public Object visit(ASTBody node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }
*/

  /*public Object visit(ASTInteger node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }*/
}
