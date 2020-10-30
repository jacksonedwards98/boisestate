/**
 * 
 * @author jacksonedwards
 *
 */
public class NodeStmtWhile extends NodeStmt{
	private NodeBoolExpr boolexpr;
	private NodeStmt whileStmt;
	
	/**
	 * Constructor
	 */
	public NodeStmtWhile(NodeBoolExpr boolexpr,NodeStmt whileStmt) {
		this.boolexpr = boolexpr;
		this.whileStmt = whileStmt;
	}
	
	public double eval(Environment env) throws EvalException {
		double result = 0;
		while(boolexpr.eval(env) == 1) {
			result += whileStmt.eval(env);
		}
		return result;
	}
}
