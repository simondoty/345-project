/* Generated By:JJTree: Do not edit this line. ASTBody.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTBody extends SimpleNode {
  public ASTBody(int id) {
    super(id);
  }

  public ASTBody(LispParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LispParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=cc8076d7824f6e996c0d14b81fe3adac (do not edit this line) */
