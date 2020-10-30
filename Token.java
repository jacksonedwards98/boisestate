// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")
// Documented by Jackson Edwards
public class Token {

    private String token;
    private String lexeme;

    /*
     * constructor 
     */
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }
    /*
     * another constructor?
     */
    public Token(String token) {
	this(token,token);
    }

    /*
     * getters
     */
    public String tok() { return token; } 
    public String lex() { return lexeme; }

    //setter
    public boolean equals(Token t) {
	return token.equals(t.token);
    }

    /*
     * toString 
     */
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
