/**
 * 
 * @author jacksonedwards
 *
 */
public class NodeStmtIfThenElse extends NodeStmt {
	private NodeBoolExpr boolexpr;
	private NodeStmt ifThenStmt;
	private NodeStmt elseStmt;
	
	/**
	 * Constructor
	 */
	public NodeStmtIfThenElse(NodeBoolExpr boolexpr,NodeStmt ifThenStmt, NodeStmt elseStmt) {
		this.boolexpr = boolexpr;
		this.ifThenStmt = ifThenStmt;
		this.elseStmt = elseStmt;
	}
	
	public double eval(Environment env) throws EvalException {
		if (boolexpr.eval(env)==1) {
			return ifThenStmt.eval(env);
		}
		else if(elseStmt!= null) {
			return elseStmt.eval(env);
		}
		return 0;
	}
}
