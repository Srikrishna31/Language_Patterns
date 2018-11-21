package parsing.recursive_descent;

import parsing.lexer.ListLexer;

public class ParserTest {
    public static void main(String... args) {
        runTest("[a,b]");
        runTest("[a,]");
    }

    private static void runTest(String input) {
        ListLexer lexer = new ListLexer(input);
        ListParser parser = new ListParser(lexer);

        parser.list();

    }
}
