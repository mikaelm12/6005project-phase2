package BoardExpr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pingball.datatypes.Board;
import pingball.datatypes.Gadget;



public class BoardFactory {
    
    
    /**
     * @param input a file representing the users board that
     *          they want to play.
     * @return A board corresponding to the input file
     */
    public static Board parse(String input) {
        
        //Mainly reused code from Recitation 13!
        
        // Create a stream of tokens using the lexer.
        CharStream stream;
        stream = new ANTLRInputStream(input);
        
        BoardGrammarLexer lexer = new BoardGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        BoardGrammarParser parser = new BoardGrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.board(); // "expression" is the starter rule
        
        // debugging option #1: print the tree to the console
//        System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
        //((RuleContext)tree).inspect(parser);

        
        // Finally, construct Board by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardFileListener listener = new BoardFileListener();
        walker.walk(listener, tree);
        
        // return the Board that the listener created
        return listener.getBoard(); 
    }
    
    

}
