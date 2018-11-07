package parsing.multi;

import parsing.lexer.LookAheadLexer;

public class LookAheadParserTest {
    public static void main(String[] args) {
        LookAheadLexer lexer = new LookAheadLexer(args[0]);
        LookAheadParser parser = new LookAheadParser(lexer, 2);
        parser.list();
    }
}
