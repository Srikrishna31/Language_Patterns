package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public abstract class Parser {
    public Parser(Lexer input) {
        this.input = input;
        lookahead = input.nextToken();
    }

    protected Lexer input;
    protected Token lookahead;
}
