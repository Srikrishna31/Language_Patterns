package parsing.lexer;

public class LookAheadLexer extends Lexer {
    public final static int NAME = 2;
    public final static int COMMA = 3;
    public final static int LBRACK = 4;
    public final static int RBRACK = 5;
    public final static int EQUALS = 6;

    public final static String[] tokenNames =
            { "n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK", "EQUALS"};

    public String getTokenName(int x) { return tokenNames[x]; }

    public LookAheadLexer(String input) { super(input); }

    boolean isLetter() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
     }

     @Override public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    WS();
                    continue;
                case ',':
                    consume();
                    return new Token(COMMA, ", ");
                case '[':
                    consume();
                    return new Token(LBRACK, "[");
                case ']':
                    consume();
                    return new Token(RBRACK, "]");
                case '=':
                    consume();
                    return new Token(EQUALS, "=");
                default:
                    if (isLetter())
                        return Name();
                    throw new Error("invalid character: " +c);
            }
        }

        return new Token(EOF_TYPE, "<EOF>");
     }

     Token Name() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isLetter());
        return new Token(NAME, buf.toString());
     }

     /** WS: (' '|'\t'|'\n'|'\r')*; ignore any whitespace */
     void WS() {
         while (c==' '|| c == '\t' || c=='\n' || c=='\r')
             consume();
     }
}
