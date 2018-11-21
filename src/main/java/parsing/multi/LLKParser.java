package parsing.multi;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

import java.util.stream.IntStream;

public abstract class LLKParser {
    public LLKParser(Lexer input, int k) {
        this.input = input;
        this.k = k;
        lookahead = new Token[k];
        IntStream.range(0,k).forEach(i -> consume());
    }

    protected Lexer input;  //from where do we get tokens?
    protected Token[] lookahead; //circular lookahead buffer
    protected int k; //how many lookahead symbols
    protected int p = 0; //circular index of next token position to fill

    public void consume() {
        lookahead[p] = input.nextToken();
        p = (p+1)%k;
    }

    public Token LT(int i) {
        return lookahead[(p + i - 1) % k];
    }

    public int LA(int i) {
        return LT(i).type;
    }

    public void match(int x) {
        if (LA(1) == x)
            consume();
        else
            throw new Error("expecting "+input.getTokenName(x)+"; found "+LT(1));
    }
}
