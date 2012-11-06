/* Expr Node */

public
class ASTExpr extends SimpleNode {

	private String op;
	
  public ASTExpr(int id) {
    super(id);
  }

  public ASTExpr(LispParser p, int id) {
    super(p, id);
  }

  public void setOp (String op) {
  	this.op = op;
  }
  
  public String toString() {
  	return this.op;
  }  
  
  /** Accept the visitor. **/
  public Object jjtAccept(LispParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
  
  public String getOp () {
  	return this.op;  	
  }
}
/* JavaCC - OriginalChecksum=eae4947144c7d29d4c1825d1b4fb4268 (do not edit this line) */
