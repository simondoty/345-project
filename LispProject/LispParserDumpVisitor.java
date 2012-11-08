/* Dump Visitor */

public class LispParserDumpVisitor implements LispParserVisitor
{
  private int indent = 0;

  // *********************************************************************
  private String indentString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < indent; ++i) {
      sb.append(' ');
    }
    return sb.toString();
  }

  // *********************************************************************
  public Object visit(SimpleNode node, Object data) {
    System.out.println(indentString() + node +
                   ": acceptor not unimplemented in subclass?");
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }
  
  // *********************************************************************
  public Object visit(ASTProgram node, Object data) {
    System.out.println(indentString() + node);
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

   // *********************************************************************
  public Object visit(ASTArithExpr node, Object data) {
    String sOp = node.getOp();
    
    if(sOp.equals("+")) {
  		System.out.println(indentString() + "add");      
    
    } else if(sOp.equals("-")) {
  		System.out.println(indentString() + "sub");      
    
    } else if(sOp.equals("*")) {
  		System.out.println(indentString() + "mul");          
    
    } else if(sOp.equals("/")) {
      System.out.println(indentString() + "div");              
    
    }
  	    
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

  // *********************************************************************
  public Object visit(ASTNum node, Object data) {
    System.out.println(indentString() + "num " + node.getVal());
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }
  
  // *********************************************************************
	public Object visit(ASTIdentifier node, Object data) {
    //since this is a num, just return value
    return "id '" + node.getIdentifier();
    
  }
      
  // *********************************************************************      
  public Object visit(ASTLambdaExpr node, Object data) {
    System.out.println(indentString() + "fun");
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

 /*
    public Object visit(ASTBody node, Object data) {
    //System.out.println(indentString() + node);
   // ++indent;
    data = node.childrenAccept(this, data);
    //--indent;
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
