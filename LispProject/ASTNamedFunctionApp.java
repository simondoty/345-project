/* Generated By:JJTree: Do not edit this line. ASTNamedFunctionApp.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTNamedFunctionApp extends SimpleNode {
  public ASTNamedFunctionApp(int id) {
    super(id);
  }

  public ASTNamedFunctionApp(LispParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(LispParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e50921f28f7ef831c8892cfe07645011 (do not edit this line) */
