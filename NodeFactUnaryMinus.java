
public class NodeFactUnaryMinus extends NodeFact{
	private NodeFact unary;
	
	public NodeFactUnaryMinus (NodeFact unary) {
		this.unary = unary;
	}
	public double eval(Environment env) throws EvalException {
		return -unary.eval(env);
	    }
}
