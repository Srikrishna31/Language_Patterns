package parsing.lexer;

public class ListLexer extends Lexer {
    public final static int NAME = 2;
    public final static int COMMA = 3;
    public final static int LBRACK = 4;
    public final static int RBRACK = 5;

    public final static String[] tokenNames =
            {"n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK"};

    public String getTokenName(int x) { return tokenNames[x]; }

    public ListLexer(String input) { super(input); }

    boolean isLetter() { return c >= 'a' && c <='z' || c >= 'A' && c <= 'Z'; }

    @Override public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ':
                    case '\t':
                        case '\n':
                            case '\r':
                    WS();
                    continue;

                case ',' : consume(); return new Token(COMMA, ", ");
                case '[' : consume(); return new Token(LBRACK, "[");
                case ']' : consume(); return new Token(RBRACK, "]");
                default:
                    if (isLetter()) return Name();
                    throw new Error("invalid character: " + c);
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    /** Name: ('a'..'z' | 'A'..'Z')+;
     * Name is a sequence of >= 1 letter
     */
    Token Name() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isLetter());

        return new Token(NAME, buf.toString());
    }

    /** WS: (' '|'\t'|'\n'|'\r')* ; ignore any whitespace */
    void WS() {
        while(c== ' ' || c =='\t' || c=='\n' || c== '\r')
            consume();
    }
}
