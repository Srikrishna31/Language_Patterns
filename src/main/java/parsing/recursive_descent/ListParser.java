package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.ListLexer;

public class ListParser extends Parser {
    public ListParser(Lexer input) {
        super(input);
    }

    /**
     * list: '[' elements ']'; //match bracketed list
     */
    public void list() {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    /**
     * elements: element (',' element)* ;
     */
    void elements() {
        element();
        while(lookahead.type == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    /**
     * element: name | list ; //element is name or nested list
     */
    void element() {
        if (lookahead.type == ListLexer.NAME)
            match(ListLexer.NAME);
        else if (lookahead.type == ListLexer.LBRACK)
            list();
        else
            throw new Error("expecting name or list; found"+lookahead);
    }

    /**
     * If lookahead token type matches x, consume & return else error
     */
    public void match(int x) {
        if (lookahead.type == x)
            consume();
        else
            throw new Error("expecting "+input.getTokenName(x) +
                    "; found " + lookahead);
    }

    public void consume() {
        lookahead = input.nextToken();
    }
}
