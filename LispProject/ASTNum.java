public
class ASTNum extends SimpleNode {

	private int value;
 
  public ASTNum (int id) {
    super(id);
  }

  public ASTNum(LispParser p, int id) {
    super(p, id);
  }

	public void setVal (int i) {
		value = i;
	}

	public int getVal() {
		return value;
	}
	
  /** Accept the visitor. **/
  public Object jjtAccept(LispParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=38c90221adcdaf3a80122983343b4ad5 (do not edit this line) */
