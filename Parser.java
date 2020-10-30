
// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

/*
 * Documented by Jackson Edwards
 */
public class Parser {

	private Scanner scanner; // scanner

	/*
	 * Match
	 * 
	 * @param - String
	 */
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	/*
	 * Current returns a token that the scanner is at
	 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/*
	 * pos returns scanner pos
	 */
	private int pos() {
		return scanner.pos();
	}
	
	
	/*
	 * My implementation of parsing relation operators
	 */
	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(), ">");
		} else if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(), "<");
		} else if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(), ">=");
		} else if (curr().equals(new Token("<="))) {
			match("<=");
			return new NodeRelop(pos(), "<=");
		} else if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(), "<>");
		}
		match("==");
		return new NodeRelop(pos(), "==");

	}
	

	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr left = parseExpr();
		NodeRelop relOp = parseRelop();
		NodeExpr right = parseExpr();
		return new NodeBoolExpr(left, relOp, right);
	}

	/*
	 * parseMulop checks if it is a * and calls NodeMulop
	 */
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	/*
	 * Addop checks if it has + and called NodeAddop
	 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	/*
	 * parseFact implements unary minus ( and id
	 */
	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact unary = parseFact();
			return new NodeFactUnaryMinus(unary);
		}
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/*
	 * Creates Term with mulop Term returns nodeterm
	 */
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	/*
	 * Creates expression with addop Expr returns NodeExpr
	 */
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	/*
	 * Creates new NodeAssn returns NodeAssn
	 */
	private NodeAssn parseAssn() throws SyntaxException {
		
		
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
		
		
		
		
	}

	/*
	 * creates new NodeStmt and returns it
	 */
	private NodeStmt parseStmt() throws SyntaxException {
		
		if(curr().equals(new Token("id"))) {
			NodeAssn assn = parseAssn();
			return new NodeStmtAssn(assn);
		}
		if(curr().equals(new Token("rd"))) {
			match("rd");
			Token id = curr();
			match("id");
			return new NodeStmtRd(id.lex());
		}
		if(curr().equals(new Token("wr"))) {
			match("wr");
			NodeExpr expr = parseExpr();
			return new NodeStmtWr(expr);
		}
		if(curr().equals(new Token("if"))) {
			match("if");
			NodeBoolExpr boolexpr = parseBoolExpr();
			match("then");
			NodeStmt ifThenStmt = parseStmt();
			if(curr().lex().equals("else")) {
				match("else");
				NodeStmt elseStmt = parseStmt();
				return new NodeStmtIfThenElse(boolexpr,ifThenStmt,elseStmt);
			}
			else {
				return new NodeStmtIfThen(boolexpr,ifThenStmt);
			}
		}
		if(curr().equals(new Token("while"))) {
			match("while");
			NodeBoolExpr whileBoolExpr = parseBoolExpr();
			match("do");
			NodeStmt whileStmt = parseStmt();
			return new NodeStmtWhile(whileBoolExpr,whileStmt);
		}
		if(curr().equals(new Token("begin"))) {
		match("begin");
		NodeBlock block = parseBlock();
		match("end");
		return new NodeStmtBegin(block);
		}
		return null;
	
	}
	
	 
	private NodeBlock parseBlock() throws SyntaxException {
		NodeStmt stmt = parseStmt();
		
		if(curr().equals(new Token(";"))) {
			match(";");
			NodeBlock block = parseBlock();
			return new NodeBlock(stmt,block);
		}
		else {
			NodeBlock block = new NodeBlock(stmt,null);
			return block;
		}
		
	}
	
	
	

	/*
	 * Creates new NodeStmt and returns it
	 */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();
		NodeBlock block = parseBlock();
		match("EOF");
		return block;
	}

}
