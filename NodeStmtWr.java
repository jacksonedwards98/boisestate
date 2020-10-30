/**
 * 
 * @author jacksonedwards
 *
 */
public class NodeStmtWr extends NodeStmt{
	private NodeExpr expr;
	
	/**
	 * Constructor
	 */
	public NodeStmtWr(NodeExpr expr) {
		this.expr = expr;
		
	}
	
	public double eval(Environment env) throws EvalException {
		double a = expr.eval(env);
		System.out.println(a);
		return a;
	}
}
