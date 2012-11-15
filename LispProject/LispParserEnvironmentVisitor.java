/* Interpreter Visitor */
import java.util.*;
import java.text.DecimalFormat;

public class LispParserEnvironmentVisitor implements LispParserVisitor
{
  private StringBuilder sb = new StringBuilder("(mtSub)");

     // *********************************************************************
  public Object visit(SimpleNode node, Object data) {
    return null;

  }

  // *********************************************************************
  public Object visit(ASTProgram node, Object data) {
    return node.jjtGetChild(0).jjtAccept(this, data);    
    
  }

   // *********************************************************************
  public Object visit(ASTArithExpr node, Object data) {
    String sTemp = "";
  
    String sOp = node.getOp();
    
    if(sOp.equals("+")) {
      sTemp += "(+";
            
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
      
        Node chile = node.jjtGetChild(iChild);        
        sTemp += " " + chile.jjtAccept(this, data);        
        
      }
      
      sTemp += ")";
      return sTemp;
    
    } else if(sOp.equals("-")) {
      sTemp += "(-";
      
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
    		sTemp += " " + node.jjtGetChild(iChild).jjtAccept(this, data);      
        
      }
      
      sTemp += ")";
      return sTemp;
       
    } else if(sOp.equals("*")) {
      sTemp += "(*";    
    
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
    	  sTemp += " " + node.jjtGetChild(iChild).jjtAccept(this, data);      
        
      }

      sTemp += ")";
      return sTemp;
          
    } else if(sOp.equals("/")) {
      sTemp += "(/";    
      
      for(int iChild=0;iChild < node.jjtGetNumChildren(); iChild++) {
    		sTemp += " " + node.jjtGetChild(iChild).jjtAccept(this, data);      
        
      }

      sTemp += sTemp + ")";
      return sTemp;
          
    } else {
      return "";
    
    }
    
  }

  // *********************************************************************
	public Object visit(ASTNum node, Object data) {
    // trim zeroes  
    Object n = node.getVal();
    DecimalFormat df = new DecimalFormat("#.###");  
    n = df.format(n);     	
  	
    //since this is a num, just return value
    return n;
    
  }
  
  // *********************************************************************
	public Object visit(ASTIdentifier node, Object data) {
	  return node.getIdentifier();

  }

    
  // *********************************************************************
  	public Object visit(ASTNamedFunctionApp node, Object data) {
    //since this is a num, just return value
    /*
    Node app = new ASTFunctionExpr(0);
    String funcID = (String) node.jjtGetChild(0).jjtAccept(this, data);
    Node func=  null;
    for (TreeMap<String, Object> t : env) {
      if (t.containsKey(funcID)){
        func = (Node) t.get(funcID);
      }  
    }
    //Node test = (Node) node.jjtGetChild(0).jjtAccept(this, data);
    //System.out.println( test.jjtGetChild(1).jjtGetChild(0));
    app.jjtAddChild((Node) node.jjtGetChild(0).jjtAccept(this, data), 0);
    app.jjtAddChild((Node) node.jjtGetChild(1), 1);   
    Object toRet = app.jjtAccept(this, data);
    env.remove(env.size() - 1);
    return toRet;
    */
    
    return null;
    
  }

  // FOR CHANGING TO DYNAMIC SCOPING, YOU MAY JUST HAVE TO TRAVERSE THROUGH
  // THE ARRAYLIST "ENV" THE OTHER WAY
      
  // *********************************************************************
  public Object visit(ASTLambdaExpr node, Object data) {
    return null;
    
  }
  
  // *********************************************************************
  
  // E.g. ((lambda (x) x) 3)
  
  // Child 0   = Lambda()                  = (lambda (x) x)
  // Child 0.0 = Identifier()              = x                ==> param
  // Child 0.1 = Anything() = Identifier() = x                ==> body
  
  // Child 1   = Anything() = Num()        = 3                ==> arg
  
  public Object visit(ASTFunctionExpr node, Object data) {
  /*
    String sOld = sb.toString();

    ASTIdentifier param = (ASTIdentifier) node.jjtGetChild(0).jjtGetChild(0);
    
    String sNew = "(aSub " + param.getIdentifier();

    Node body = node.jjtGetChild(0).jjtGetChild(1);
    
    if(body instanceof ASTNum) {
      return body.jjtAccept(this, null);
    
    } else if(body instanceof ASTLambdaExpr) {
       String sLambda = "(closureV ";

       sLambda += body.jjtGetChild(0);
       sLambda +=     
       return body.jjtAccept(this, null);   
    
    
    }
        
    sNew = sNew + body;
  
    sNew = sNew + sOld;
    sNew = sNew + ")";

    return sNew;
    
   */
   return null;

  }

     
}
