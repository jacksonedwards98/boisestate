// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

public class Interpreter {

    public static void main(String[] args) {
	Parser parser=new Parser(); //creates new Parse
	Environment env=new Environment(); // creates new enviornment
	for (String stmt: args) //reads in from args
	    try {
		parser.parse(stmt).eval(env); // prints out the result
	    } catch (SyntaxException e) {
		System.err.println(e);
	    } catch (EvalException e) {
		System.err.println(e);
	    }
    }

}
