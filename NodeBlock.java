/**
 * 
 * @author jacksonedwards
 *
 */
public class NodeBlock extends Node {
	private NodeStmt stmt;
	private NodeBlock block;
	/**
	 * Constructor
	 */
	public NodeBlock(NodeStmt stmt,NodeBlock block) {
		this.stmt = stmt;
		this.block = block;
	}
	
	public double eval(Environment env) throws EvalException {
		double result = stmt.eval(env);
		if(block.block!= null)
			return block.eval(env);
		return result;
	}
}
