package parsing.multi;

import parsing.lexer.Lexer;
import parsing.lexer.LookAheadLexer;

public class LookAheadParser extends LLKParser {
    LookAheadParser(Lexer input, int k) {
        super(input, k);
    }

    void element() {
        if (LA(1) ==LookAheadLexer.NAME && LA(2) == LookAheadLexer.EQUALS) {
            match(LookAheadLexer.NAME);
            match(LookAheadLexer.EQUALS);
            match(LookAheadLexer.NAME);
    } else if (LA(1) == LookAheadLexer.NAME)
            match(LookAheadLexer.NAME);
        else if (LA(1) == LookAheadLexer.LBRACK)
            list();
        else
            throw new Error("expecting name or list; found "+LT(1));
    }


    /**
     * list: '[' elements ']'; //match bracketed list
     */
    public void list() {
        match(LookAheadLexer.LBRACK);
        elements();
        match(LookAheadLexer.RBRACK);
    }

    /**
     * elements: element (',' element)*
     */
    void elements() {
        element();
        while (lookahead[p].type == LookAheadLexer.COMMA) {
            match(LookAheadLexer.COMMA);
            element();
        }
    }
}
