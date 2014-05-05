// Generated from BoardGrammar.g4 by ANTLR 4.0

package BoardExpr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WHITESPACE=2, INTEGER=3, X=4, Y=5, EQUALS=6, NAMEKEY=7, XVEL=8, 
		YVEL=9, GRAVITY=10, FRICTION1=11, FRICTION2=12, TRIGGER=13, ORIENTATIONKEY=14, 
		ACTION=15, WIDTH=16, HEIGHT=17, BOARD=18, BALL=19, ABSORBER=20, FIRE=21, 
		SQUAREBUMPER=22, CIRCLEBUMPER=23, TRIANGLEBUMPER=24, LEFTFLIPPER=25, RIGHTFLIPPER=26, 
		FLOAT=27, NAME=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"COMMENT", "WHITESPACE", "INTEGER", "'x'", "'y'", "'='", "'name'", "'xVelocity'", 
		"'yVelocity'", "'gravity'", "'friction1'", "'friction2'", "'trigger'", 
		"'orientation'", "'action'", "'width'", "'height'", "'board'", "'ball'", 
		"'absorber'", "'fire'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", 
		"'leftFlipper'", "'rightFlipper'", "FLOAT", "NAME"
	};
	public static final String[] ruleNames = {
		"COMMENT", "WHITESPACE", "INTEGER", "X", "Y", "EQUALS", "NAMEKEY", "XVEL", 
		"YVEL", "GRAVITY", "FRICTION1", "FRICTION2", "TRIGGER", "ORIENTATIONKEY", 
		"ACTION", "WIDTH", "HEIGHT", "BOARD", "BALL", "ABSORBER", "FIRE", "SQUAREBUMPER", 
		"CIRCLEBUMPER", "TRIANGLEBUMPER", "LEFTFLIPPER", "RIGHTFLIPPER", "FLOAT", 
		"NAME"
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


	public BoardGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BoardGrammar.g4"; }

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
		case 0: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 1: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
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
		"\2\4\36\u0127\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2"+
		"\3\2\7\2>\n\2\f\2\16\2A\13\2\3\2\3\2\3\2\3\2\3\3\6\3H\n\3\r\3\16\3I\3"+
		"\3\3\3\3\4\6\4O\n\4\r\4\16\4P\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\5\34\u0112\n\34\3\34\6\34\u0115\n\34\r\34\16"+
		"\34\u0116\3\34\3\34\6\34\u011b\n\34\r\34\16\34\u011c\5\34\u011f\n\34\3"+
		"\35\3\35\7\35\u0123\n\35\f\35\16\35\u0126\13\35\2\36\3\3\2\5\4\3\7\5\1"+
		"\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61"+
		"\32\1\63\33\1\65\34\1\67\35\19\36\1\3\2\t\3\f\f\6\13\f\17\17\"\"..\3\62"+
		";\3\62;\3\62;\5C\\aac|\b..\60\60\62;C\\aac|\u012e\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5G\3\2\2\2\7N\3"+
		"\2\2\2\tR\3\2\2\2\13T\3\2\2\2\rV\3\2\2\2\17X\3\2\2\2\21]\3\2\2\2\23g\3"+
		"\2\2\2\25q\3\2\2\2\27y\3\2\2\2\31\u0083\3\2\2\2\33\u008d\3\2\2\2\35\u0095"+
		"\3\2\2\2\37\u00a1\3\2\2\2!\u00a8\3\2\2\2#\u00ae\3\2\2\2%\u00b5\3\2\2\2"+
		"\'\u00bb\3\2\2\2)\u00c0\3\2\2\2+\u00c9\3\2\2\2-\u00ce\3\2\2\2/\u00db\3"+
		"\2\2\2\61\u00e8\3\2\2\2\63\u00f7\3\2\2\2\65\u0103\3\2\2\2\67\u0111\3\2"+
		"\2\29\u0120\3\2\2\2;?\7%\2\2<>\n\2\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?"+
		"@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\f\2\2CD\3\2\2\2DE\b\2\2\2E\4\3\2\2\2"+
		"FH\t\3\2\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\b\3\3\2"+
		"L\6\3\2\2\2MO\t\4\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\b\3\2\2"+
		"\2RS\7z\2\2S\n\3\2\2\2TU\7{\2\2U\f\3\2\2\2VW\7?\2\2W\16\3\2\2\2XY\7p\2"+
		"\2YZ\7c\2\2Z[\7o\2\2[\\\7g\2\2\\\20\3\2\2\2]^\7z\2\2^_\7X\2\2_`\7g\2\2"+
		"`a\7n\2\2ab\7q\2\2bc\7e\2\2cd\7k\2\2de\7v\2\2ef\7{\2\2f\22\3\2\2\2gh\7"+
		"{\2\2hi\7X\2\2ij\7g\2\2jk\7n\2\2kl\7q\2\2lm\7e\2\2mn\7k\2\2no\7v\2\2o"+
		"p\7{\2\2p\24\3\2\2\2qr\7i\2\2rs\7t\2\2st\7c\2\2tu\7x\2\2uv\7k\2\2vw\7"+
		"v\2\2wx\7{\2\2x\26\3\2\2\2yz\7h\2\2z{\7t\2\2{|\7k\2\2|}\7e\2\2}~\7v\2"+
		"\2~\177\7k\2\2\177\u0080\7q\2\2\u0080\u0081\7p\2\2\u0081\u0082\7\63\2"+
		"\2\u0082\30\3\2\2\2\u0083\u0084\7h\2\2\u0084\u0085\7t\2\2\u0085\u0086"+
		"\7k\2\2\u0086\u0087\7e\2\2\u0087\u0088\7v\2\2\u0088\u0089\7k\2\2\u0089"+
		"\u008a\7q\2\2\u008a\u008b\7p\2\2\u008b\u008c\7\64\2\2\u008c\32\3\2\2\2"+
		"\u008d\u008e\7v\2\2\u008e\u008f\7t\2\2\u008f\u0090\7k\2\2\u0090\u0091"+
		"\7i\2\2\u0091\u0092\7i\2\2\u0092\u0093\7g\2\2\u0093\u0094\7t\2\2\u0094"+
		"\34\3\2\2\2\u0095\u0096\7q\2\2\u0096\u0097\7t\2\2\u0097\u0098\7k\2\2\u0098"+
		"\u0099\7g\2\2\u0099\u009a\7p\2\2\u009a\u009b\7v\2\2\u009b\u009c\7c\2\2"+
		"\u009c\u009d\7v\2\2\u009d\u009e\7k\2\2\u009e\u009f\7q\2\2\u009f\u00a0"+
		"\7p\2\2\u00a0\36\3\2\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4"+
		"\7v\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		" \3\2\2\2\u00a8\u00a9\7y\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7f\2\2\u00ab"+
		"\u00ac\7v\2\2\u00ac\u00ad\7j\2\2\u00ad\"\3\2\2\2\u00ae\u00af\7j\2\2\u00af"+
		"\u00b0\7g\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7i\2\2\u00b2\u00b3\7j\2\2"+
		"\u00b3\u00b4\7v\2\2\u00b4$\3\2\2\2\u00b5\u00b6\7d\2\2\u00b6\u00b7\7q\2"+
		"\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7f\2\2\u00ba&\3\2"+
		"\2\2\u00bb\u00bc\7d\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7n\2\2\u00be\u00bf"+
		"\7n\2\2\u00bf(\3\2\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7d\2\2\u00c2\u00c3"+
		"\7u\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7d\2\2\u00c6"+
		"\u00c7\7g\2\2\u00c7\u00c8\7t\2\2\u00c8*\3\2\2\2\u00c9\u00ca\7h\2\2\u00ca"+
		"\u00cb\7k\2\2\u00cb\u00cc\7t\2\2\u00cc\u00cd\7g\2\2\u00cd,\3\2\2\2\u00ce"+
		"\u00cf\7u\2\2\u00cf\u00d0\7s\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2\7c\2\2"+
		"\u00d2\u00d3\7t\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7D\2\2\u00d5\u00d6"+
		"\7w\2\2\u00d6\u00d7\7o\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\u00da\7t\2\2\u00da.\3\2\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd\7k\2\2\u00dd"+
		"\u00de\7t\2\2\u00de\u00df\7e\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7g\2\2"+
		"\u00e1\u00e2\7D\2\2\u00e2\u00e3\7w\2\2\u00e3\u00e4\7o\2\2\u00e4\u00e5"+
		"\7r\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7t\2\2\u00e7\60\3\2\2\2\u00e8\u00e9"+
		"\7v\2\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7c\2\2\u00ec"+
		"\u00ed\7p\2\2\u00ed\u00ee\7i\2\2\u00ee\u00ef\7n\2\2\u00ef\u00f0\7g\2\2"+
		"\u00f0\u00f1\7D\2\2\u00f1\u00f2\7w\2\2\u00f2\u00f3\7o\2\2\u00f3\u00f4"+
		"\7r\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7t\2\2\u00f6\62\3\2\2\2\u00f7\u00f8"+
		"\7n\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7h\2\2\u00fa\u00fb\7v\2\2\u00fb"+
		"\u00fc\7H\2\2\u00fc\u00fd\7n\2\2\u00fd\u00fe\7k\2\2\u00fe\u00ff\7r\2\2"+
		"\u00ff\u0100\7r\2\2\u0100\u0101\7g\2\2\u0101\u0102\7t\2\2\u0102\64\3\2"+
		"\2\2\u0103\u0104\7t\2\2\u0104\u0105\7k\2\2\u0105\u0106\7i\2\2\u0106\u0107"+
		"\7j\2\2\u0107\u0108\7v\2\2\u0108\u0109\7H\2\2\u0109\u010a\7n\2\2\u010a"+
		"\u010b\7k\2\2\u010b\u010c\7r\2\2\u010c\u010d\7r\2\2\u010d\u010e\7g\2\2"+
		"\u010e\u010f\7t\2\2\u010f\66\3\2\2\2\u0110\u0112\7/\2\2\u0111\u0110\3"+
		"\2\2\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u0115\t\5\2\2\u0114"+
		"\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\u011e\3\2\2\2\u0118\u011a\7\60\2\2\u0119\u011b\t\6\2\2\u011a"+
		"\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\u011f\3\2\2\2\u011e\u0118\3\2\2\2\u011e\u011f\3\2\2\2\u011f"+
		"8\3\2\2\2\u0120\u0124\t\7\2\2\u0121\u0123\t\b\2\2\u0122\u0121\3\2\2\2"+
		"\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125:\3"+
		"\2\2\2\u0126\u0124\3\2\2\2\13\2?IP\u0111\u0116\u011c\u011e\u0124";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}