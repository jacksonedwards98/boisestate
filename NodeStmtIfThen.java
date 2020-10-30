/**
 * 
 * @author jacksonedwards
 *
 */
public class NodeStmtIfThen extends NodeStmt{
	private NodeBoolExpr boolexpr;
	private NodeStmt ifThenStmt;
	
	/**
	 * Constructor
	 */
	public NodeStmtIfThen(NodeBoolExpr boolexpr,NodeStmt ifThenStmt) {
		this.boolexpr = boolexpr;
		this.ifThenStmt = ifThenStmt;
	}
	
	public double eval(Environment env) throws EvalException {
		return ifThenStmt.eval(env);
	}
}
