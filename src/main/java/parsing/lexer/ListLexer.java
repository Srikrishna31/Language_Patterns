package parsing.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ListLexer extends Lexer {
    public final static int NAME = 2;
    public final static int COMMA = 3;
    public final static int LBRACK = 4;
    public final static int RBRACK = 5;

    public final static ArrayList<String> tokenNames = new ArrayList<>(
            Arrays.asList("n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK"));

    protected final static Map<Character, Supplier<Token>> tokenProviders =
                            new HashMap<>();
    public String getTokenName(int x) { return tokenNames.get(x); }

    static {

    }
    public ListLexer(String input) {
        super(input);

        Supplier<Token> dummyTokenProvider = () -> {
            WS();
            return new DummyToken();
        };

        tokenProviders.put(' ', dummyTokenProvider);
        tokenProviders.put('\t', dummyTokenProvider);
        tokenProviders.put('\n', dummyTokenProvider);
        tokenProviders.put('\r', dummyTokenProvider);

        tokenProviders.put(',', () -> {
            consume();
            return new Token(COMMA, ", ");
        });

        tokenProviders.put('[', () -> {
            consume();
            return new Token(LBRACK, "[");
        });

        tokenProviders.put(']', () -> {
            consume();
            return new Token(RBRACK, "]");
        });

    }

    @Override public Token nextToken() {
        while (c != EOF) {
            Supplier<Token> tokenSupplier = tokenProviders.getOrDefault(c, () -> {
                if (isLetter()) {
                    return Name();
                }
                throw new Error("invalid character: " + c);
            });

            Token token = tokenSupplier.get();

            if (token instanceof DummyToken)
                continue;
            else
                return token;
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
}
