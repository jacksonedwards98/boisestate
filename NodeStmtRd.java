//Author Jackson Edwards
import java.util.Scanner;

public class NodeStmtRd extends NodeStmt{

	private String id;
	static Scanner scan;
	
	/*
	 * Constructor
	 */
	public NodeStmtRd(String id) {
		this.id = id;
	}
	/**
	 * Does the read statement
	 * 
	 */
	public double eval(Environment env) throws EvalException {
		scan = new Scanner(System.in);
		double value = scan.nextDouble();
		return env.put(id, value);
		
	}
}
