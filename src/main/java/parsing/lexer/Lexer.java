package parsing.lexer;

public abstract class Lexer {
    static final char EOF = (char)-1; //represent end of file char
    public static final int EOF_TYPE = 1; //represent EOF token type

    protected String input;
    protected int p = 0;
    protected char c;

    public Lexer(String input) {
        this.input = input;
        c = input.charAt(p); //prime lookahead
    }

    /**
     * Move one character; detect "end of file"
     */
    protected void consume() {
        p++;
        c = (p >= input.length()) ? EOF : input.charAt(p);
    }

    /**
     * Ensure x is next character on the input stream
     */
    public void match(char x) {
        if (c == x) consume();
        else throw new Error("expecting " +x +"; found "+c);
    }

    protected boolean isLetter() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    /** WS: (' '|'\t'|'\n'|'\r')* ; ignore any whitespace */
    protected void WS() {
        while(c== ' ' || c =='\t' || c=='\n' || c== '\r')
            consume();
    }

    public abstract Token nextToken();
    public abstract String getTokenName(int tokenType);
}
