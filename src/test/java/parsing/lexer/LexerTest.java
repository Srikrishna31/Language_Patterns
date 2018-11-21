package parsing.lexer;

public class LexerTest {
    //java Test '[a,b]'
    public static void main(String... args) {
        runTest("[a,b]");
    }

    private static void runTest(String input) {
        ListLexer lexer = new ListLexer(input);
        Token t = lexer.nextToken();

        while(t.type != Lexer.EOF_TYPE) {
            System.out.println(t);
            t = lexer.nextToken();
        }
        System.out.println(t) ; //EOF
    }
}
