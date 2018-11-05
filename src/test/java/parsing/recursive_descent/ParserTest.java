package parsing.recursive_descent;

import parsing.lexer.ListLexer;

public class ParserTest {
    public static void main(String... args) {
        ListLexer lexer = new ListLexer(args[0]);
        ListParser parser = new ListParser(lexer);

        parser.list();
    }
}
