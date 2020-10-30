//Author Jackson Edwards
public class NodeBoolExpr {
	private final NodeExpr left;
	private final NodeRelop relOp;
	private final NodeExpr right;
	
	/*
	 * Constructor
	 */
	public NodeBoolExpr(NodeExpr left,NodeRelop relOp,NodeExpr right) {
		this.left = left;
		this.relOp = relOp;
		this.right = right;
	}
	
	public double eval(Environment env) throws EvalException {
		return relOp.op(left.eval(env), right.eval(env));
	}
}
