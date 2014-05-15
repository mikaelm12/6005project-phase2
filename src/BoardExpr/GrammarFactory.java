package BoardExpr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pingball.datatypes.Board;

public class GrammarFactory {
    
    /**
     * This method takes in a file formatted as specified in the specifications in the 6.005 web page.
     * @param file containing the information about the board and the objects it should contain.
     * @return a new board.
     * @throws Exception 
     */
    public static Board parse(File file) throws Exception {
        String input = FileToString(file);
        
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        GrammarLexer lexer = new GrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        GrammarParser parser = new GrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.boardInfo(); // "expression" is the starter rule
        
        // show the tree in a window
//        ((RuleContext)tree).inspect(parser);

        // Finally, construct an Expression value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardCreatorListener listener = new BoardCreatorListener();
        walker.walk((BoardCreatorListener) listener, tree);

        return listener.getBoard();
    }
    
    /**
     * This method takes in a string containing the content of the file that is formatted as specified 
     * in the specifications in the 6.005 web page.
     * @param String containing the file which has the information about the board and the objects it should contain.
     *        Note: there must be a "\n" at the end of each line in the file for this string to be parsed correctly.
     * @return a new board.
     * @throws Exception 
     */
    public static Board parse(String fileStr) throws Exception {
        String input = fileStr;
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(input);
        GrammarLexer lexer = new GrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        GrammarParser parser = new GrammarParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.boardInfo(); // "expression" is the starter rule

        // show the tree in a window
        //((RuleContext)tree).inspect(parser);
        
        // Finally, construct an Expression value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        BoardCreatorListener listener = new BoardCreatorListener();
        walker.walk((ParseTreeListener) listener, tree);

        return listener.getBoard();
    }
    
    /**
     * This method takes in a file containing the board information and then puts all the content of the file
     * into one large string.  Note: "\n" is added at the end of each line that is read in from the file.  This
     * is necessary for ANTLR to parse correctly.
     * @param file containing the board information
     * @return a string 
     */
    public static String FileToString(File file){
        String textString = "";

        //read in file
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
          //add all lines to linesInFile
            String line = reader.readLine();
            while (line != null) {
                textString += line + "\n";
                line = reader.readLine();
            }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't read in file.");
        } 
        
        return textString;
    }

}
