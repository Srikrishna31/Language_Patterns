package parsing.multi;

import parsing.lexer.LookAheadLexer;

public class LookAheadParserTest {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        runTest("[a,b=c,[d,e]]");
    }

    private static void test1() {
        runTest("[a,b=c,[d,e]]");
    }
    private static void runTest(String input) {
        LookAheadLexer lexer = new LookAheadLexer(input);
        LookAheadParser parser = new LookAheadParser(lexer, 2);
        parser.list();
    }
}
