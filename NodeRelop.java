//Author Jackson Edwards
//Node for relation ops
public class NodeRelop extends Node {
	private String relop;

	/*
	 * Constructor 
	 * @parm pos , relop
	 */
	public NodeRelop (int pos, String relop) {
		this.pos=pos;
		this.relop=relop;
	}
	
	/*
	 * Checks relation operation
	 */
	public double op(double o1,double o2) throws EvalException {
		if(relop.equals(">")) {
			if(o1 > o2)
				return 1;
				return 0;
		}
		if(relop.equals("<")) {
			if(o1 < o2)
				return 1;
				return 0;
		}
		if(relop.equals("<>")) {
			if(o1 != o2)
				return 1;
				return 0;
		}
		if(relop.equals(">=")) {
			if(o1 >= o2)
				return 1;
				return 0;
		}
		if(relop.equals("<=")) {
			if(o1 <= o2)
				return 1;
				return 0;
		}
		if(relop.equals("==")) {
			if(o1 == o2)
				return 1;
				return 0;
		}
		else {
			throw new EvalException(0,"Relation operation is not recognized.");
		}
	}

}
