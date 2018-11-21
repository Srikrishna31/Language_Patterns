package parsing.lexer;

public class LookAheadLexer extends ListLexer {
    public final static int EQUALS = 6;

    static {
        tokenNames.add("EQUALS");
    }

    public LookAheadLexer(String input) {
        super(input);

        tokenProviders.put('=', () -> {
            consume();
            return new Token(EQUALS, "=");
        });
    }
}
