/* Generated By:JavaCC: Do not edit this line. LispParserVisitor.java Version 5.0 */
public interface LispParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTProgram node, Object data);
  public Object visit(ASTLambdaExpr node, Object data);
  public Object visit(ASTArithExpr node, Object data);
  public Object visit(ASTIdentifier node, Object data);
  public Object visit(ASTNum node, Object data);
}
/* JavaCC - OriginalChecksum=a0ab476e1c651855e13f54cb814da563 (do not edit this line) */
