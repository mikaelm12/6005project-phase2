// Generated from Grammar.g4 by ANTLR 4.0

package BoardExpr2;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__22=1, T__21=2, T__20=3, T__19=4, T__18=5, T__17=6, T__16=7, T__15=8, 
		T__14=9, T__13=10, T__12=11, T__11=12, T__10=13, T__9=14, T__8=15, T__7=16, 
		T__6=17, T__5=18, T__4=19, T__3=20, T__2=21, T__1=22, T__0=23, COMMENT=24, 
		NUM=25, ID=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'name'", "'friction1'", "'gravity'", "'board'", "'ball'", 
		"'xVelocity'", "'orientation'", "'='", "'height'", "'x'", "'y'", "'triangleBumper'", 
		"'fire'", "'action'", "'absorber'", "'squareBumper'", "'trigger'", "'leftFlipper'", 
		"'circleBumper'", "'friction2'", "'width'", "'rightFlipper'", "COMMENT", 
		"NUM", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", 
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "COMMENT", "NUM", "ID", 
		"WS"
	};


	    /**
	     * Call this method to have the lexer or parser throw a RuntimeException if
	     * it encounters an error.
	     */
	    public void reportErrorsAsExceptions() {
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 23: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 26: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\35\u0125\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\31\3\31\7\31\u00fa\n\31\f\31\16\31\u00fd\13"+
		"\31\3\31\3\31\3\32\5\32\u0102\n\32\3\32\6\32\u0105\n\32\r\32\16\32\u0106"+
		"\3\32\3\32\7\32\u010b\n\32\f\32\16\32\u010e\13\32\3\32\5\32\u0111\n\32"+
		"\3\32\6\32\u0114\n\32\r\32\16\32\u0115\5\32\u0118\n\32\3\33\6\33\u011b"+
		"\n\33\r\33\16\33\u011c\3\34\6\34\u0120\n\34\r\34\16\34\u0121\3\34\3\34"+
		"\2\35\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f"+
		"\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26"+
		"\1+\27\1-\30\1/\31\1\61\32\2\63\33\1\65\34\1\67\35\3\3\2\b\4\f\f\17\17"+
		"\3\62;\3\62;\3\62;\6\62;C\\aac|\5\13\f\17\17\"\"\u012d\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5C\3\2\2\2\7H\3\2\2\2"+
		"\tR\3\2\2\2\13Z\3\2\2\2\r`\3\2\2\2\17e\3\2\2\2\21o\3\2\2\2\23{\3\2\2\2"+
		"\25}\3\2\2\2\27\u0084\3\2\2\2\31\u0086\3\2\2\2\33\u0088\3\2\2\2\35\u0097"+
		"\3\2\2\2\37\u009c\3\2\2\2!\u00a3\3\2\2\2#\u00ac\3\2\2\2%\u00b9\3\2\2\2"+
		"\'\u00c1\3\2\2\2)\u00cd\3\2\2\2+\u00da\3\2\2\2-\u00e4\3\2\2\2/\u00ea\3"+
		"\2\2\2\61\u00f7\3\2\2\2\63\u0101\3\2\2\2\65\u011a\3\2\2\2\67\u011f\3\2"+
		"\2\29:\7{\2\2:;\7X\2\2;<\7g\2\2<=\7n\2\2=>\7q\2\2>?\7e\2\2?@\7k\2\2@A"+
		"\7v\2\2AB\7{\2\2B\4\3\2\2\2CD\7p\2\2DE\7c\2\2EF\7o\2\2FG\7g\2\2G\6\3\2"+
		"\2\2HI\7h\2\2IJ\7t\2\2JK\7k\2\2KL\7e\2\2LM\7v\2\2MN\7k\2\2NO\7q\2\2OP"+
		"\7p\2\2PQ\7\63\2\2Q\b\3\2\2\2RS\7i\2\2ST\7t\2\2TU\7c\2\2UV\7x\2\2VW\7"+
		"k\2\2WX\7v\2\2XY\7{\2\2Y\n\3\2\2\2Z[\7d\2\2[\\\7q\2\2\\]\7c\2\2]^\7t\2"+
		"\2^_\7f\2\2_\f\3\2\2\2`a\7d\2\2ab\7c\2\2bc\7n\2\2cd\7n\2\2d\16\3\2\2\2"+
		"ef\7z\2\2fg\7X\2\2gh\7g\2\2hi\7n\2\2ij\7q\2\2jk\7e\2\2kl\7k\2\2lm\7v\2"+
		"\2mn\7{\2\2n\20\3\2\2\2op\7q\2\2pq\7t\2\2qr\7k\2\2rs\7g\2\2st\7p\2\2t"+
		"u\7v\2\2uv\7c\2\2vw\7v\2\2wx\7k\2\2xy\7q\2\2yz\7p\2\2z\22\3\2\2\2{|\7"+
		"?\2\2|\24\3\2\2\2}~\7j\2\2~\177\7g\2\2\177\u0080\7k\2\2\u0080\u0081\7"+
		"i\2\2\u0081\u0082\7j\2\2\u0082\u0083\7v\2\2\u0083\26\3\2\2\2\u0084\u0085"+
		"\7z\2\2\u0085\30\3\2\2\2\u0086\u0087\7{\2\2\u0087\32\3\2\2\2\u0088\u0089"+
		"\7v\2\2\u0089\u008a\7t\2\2\u008a\u008b\7k\2\2\u008b\u008c\7c\2\2\u008c"+
		"\u008d\7p\2\2\u008d\u008e\7i\2\2\u008e\u008f\7n\2\2\u008f\u0090\7g\2\2"+
		"\u0090\u0091\7D\2\2\u0091\u0092\7w\2\2\u0092\u0093\7o\2\2\u0093\u0094"+
		"\7r\2\2\u0094\u0095\7g\2\2\u0095\u0096\7t\2\2\u0096\34\3\2\2\2\u0097\u0098"+
		"\7h\2\2\u0098\u0099\7k\2\2\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2\u009b"+
		"\36\3\2\2\2\u009c\u009d\7c\2\2\u009d\u009e\7e\2\2\u009e\u009f\7v\2\2\u009f"+
		"\u00a0\7k\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7p\2\2\u00a2 \3\2\2\2\u00a3"+
		"\u00a4\7c\2\2\u00a4\u00a5\7d\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7q\2\2"+
		"\u00a7\u00a8\7t\2\2\u00a8\u00a9\7d\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab"+
		"\7t\2\2\u00ab\"\3\2\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7s\2\2\u00ae\u00af"+
		"\7w\2\2\u00af\u00b0\7c\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7g\2\2\u00b2"+
		"\u00b3\7D\2\2\u00b3\u00b4\7w\2\2\u00b4\u00b5\7o\2\2\u00b5\u00b6\7r\2\2"+
		"\u00b6\u00b7\7g\2\2\u00b7\u00b8\7t\2\2\u00b8$\3\2\2\2\u00b9\u00ba\7v\2"+
		"\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7i\2\2\u00bd\u00be"+
		"\7i\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7t\2\2\u00c0&\3\2\2\2\u00c1\u00c2"+
		"\7n\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7h\2\2\u00c4\u00c5\7v\2\2\u00c5"+
		"\u00c6\7H\2\2\u00c6\u00c7\7n\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7r\2\2"+
		"\u00c9\u00ca\7r\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7t\2\2\u00cc(\3\2\2"+
		"\2\u00cd\u00ce\7e\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7t\2\2\u00d0\u00d1"+
		"\7e\2\2\u00d1\u00d2\7n\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7D\2\2\u00d4"+
		"\u00d5\7w\2\2\u00d5\u00d6\7o\2\2\u00d6\u00d7\7r\2\2\u00d7\u00d8\7g\2\2"+
		"\u00d8\u00d9\7t\2\2\u00d9*\3\2\2\2\u00da\u00db\7h\2\2\u00db\u00dc\7t\2"+
		"\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7e\2\2\u00de\u00df\7v\2\2\u00df\u00e0"+
		"\7k\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7\64\2\2\u00e3"+
		",\3\2\2\2\u00e4\u00e5\7y\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7f\2\2\u00e7"+
		"\u00e8\7v\2\2\u00e8\u00e9\7j\2\2\u00e9.\3\2\2\2\u00ea\u00eb\7t\2\2\u00eb"+
		"\u00ec\7k\2\2\u00ec\u00ed\7i\2\2\u00ed\u00ee\7j\2\2\u00ee\u00ef\7v\2\2"+
		"\u00ef\u00f0\7H\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3"+
		"\7r\2\2\u00f3\u00f4\7r\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7t\2\2\u00f6"+
		"\60\3\2\2\2\u00f7\u00fb\7%\2\2\u00f8\u00fa\n\2\2\2\u00f9\u00f8\3\2\2\2"+
		"\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe"+
		"\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\b\31\2\2\u00ff\62\3\2\2\2\u0100"+
		"\u0102\7/\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0117\3\2"+
		"\2\2\u0103\u0105\t\3\2\2\u0104\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010c\7\60"+
		"\2\2\u0109\u010b\t\4\2\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u0118\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010f\u0111\7\60\2\2\u0110\u010f\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0113\3\2\2\2\u0112\u0114\t\5\2\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117"+
		"\u0104\3\2\2\2\u0117\u0110\3\2\2\2\u0118\64\3\2\2\2\u0119\u011b\t\6\2"+
		"\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d"+
		"\3\2\2\2\u011d\66\3\2\2\2\u011e\u0120\t\7\2\2\u011f\u011e\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2"+
		"\2\2\u0123\u0124\b\34\3\2\u01248\3\2\2\2\r\2\u00fb\u0101\u0106\u010c\u0110"+
		"\u0115\u0117\u011a\u011c\u0121";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}