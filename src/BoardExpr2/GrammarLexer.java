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
		T__6=17, T__5=18, T__4=19, T__3=20, T__2=21, T__1=22, T__0=23, KEYUP=24, 
		KEYDOWN=25, FIRE=26, TRIGGER=27, ACTION=28, PORTAL=29, COMMENT=30, NUM=31, 
		ID=32, WS=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'name'", "'friction1'", "'gravity'", "'otherPortal'", 
		"'board'", "'ball'", "'xVelocity'", "'orientation'", "'='", "'height'", 
		"'otherBoard'", "'x'", "'y'", "'triangleBumper'", "'key'", "'absorber'", 
		"'squareBumper'", "'leftFlipper'", "'circleBumper'", "'friction2'", "'width'", 
		"'rightFlipper'", "'keyup'", "'keydown'", "'fire'", "'trigger'", "'action'", 
		"'portal'", "COMMENT", "NUM", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", 
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "KEYUP", "KEYDOWN", "FIRE", 
		"TRIGGER", "ACTION", "PORTAL", "COMMENT", "NUM", "ID", "WS"
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
		case 29: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 32: WS_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4#\u0161\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\7\37\u0136\n\37\f\37\16\37\u0139\13\37\3\37\3\37"+
		"\3 \5 \u013e\n \3 \6 \u0141\n \r \16 \u0142\3 \3 \7 \u0147\n \f \16 \u014a"+
		"\13 \3 \5 \u014d\n \3 \6 \u0150\n \r \16 \u0151\5 \u0154\n \3!\6!\u0157"+
		"\n!\r!\16!\u0158\3\"\6\"\u015c\n\"\r\"\16\"\u015d\3\"\3\"\2#\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16"+
		"\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1"+
		"/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \2?!\1A\"\1C#\3\3"+
		"\2\b\4\f\f\17\17\3\62;\3\62;\3\62;\6\62;C\\aac|\5\13\f\17\17\"\"\u0169"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5O\3\2\2"+
		"\2\7T\3\2\2\2\t^\3\2\2\2\13f\3\2\2\2\rr\3\2\2\2\17x\3\2\2\2\21}\3\2\2"+
		"\2\23\u0087\3\2\2\2\25\u0093\3\2\2\2\27\u0095\3\2\2\2\31\u009c\3\2\2\2"+
		"\33\u00a7\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab\3\2\2\2!\u00ba\3\2\2\2#\u00be"+
		"\3\2\2\2%\u00c7\3\2\2\2\'\u00d4\3\2\2\2)\u00e0\3\2\2\2+\u00ed\3\2\2\2"+
		"-\u00f7\3\2\2\2/\u00fd\3\2\2\2\61\u010a\3\2\2\2\63\u0110\3\2\2\2\65\u0118"+
		"\3\2\2\2\67\u011d\3\2\2\29\u0125\3\2\2\2;\u012c\3\2\2\2=\u0133\3\2\2\2"+
		"?\u013d\3\2\2\2A\u0156\3\2\2\2C\u015b\3\2\2\2EF\7{\2\2FG\7X\2\2GH\7g\2"+
		"\2HI\7n\2\2IJ\7q\2\2JK\7e\2\2KL\7k\2\2LM\7v\2\2MN\7{\2\2N\4\3\2\2\2OP"+
		"\7p\2\2PQ\7c\2\2QR\7o\2\2RS\7g\2\2S\6\3\2\2\2TU\7h\2\2UV\7t\2\2VW\7k\2"+
		"\2WX\7e\2\2XY\7v\2\2YZ\7k\2\2Z[\7q\2\2[\\\7p\2\2\\]\7\63\2\2]\b\3\2\2"+
		"\2^_\7i\2\2_`\7t\2\2`a\7c\2\2ab\7x\2\2bc\7k\2\2cd\7v\2\2de\7{\2\2e\n\3"+
		"\2\2\2fg\7q\2\2gh\7v\2\2hi\7j\2\2ij\7g\2\2jk\7t\2\2kl\7R\2\2lm\7q\2\2"+
		"mn\7t\2\2no\7v\2\2op\7c\2\2pq\7n\2\2q\f\3\2\2\2rs\7d\2\2st\7q\2\2tu\7"+
		"c\2\2uv\7t\2\2vw\7f\2\2w\16\3\2\2\2xy\7d\2\2yz\7c\2\2z{\7n\2\2{|\7n\2"+
		"\2|\20\3\2\2\2}~\7z\2\2~\177\7X\2\2\177\u0080\7g\2\2\u0080\u0081\7n\2"+
		"\2\u0081\u0082\7q\2\2\u0082\u0083\7e\2\2\u0083\u0084\7k\2\2\u0084\u0085"+
		"\7v\2\2\u0085\u0086\7{\2\2\u0086\22\3\2\2\2\u0087\u0088\7q\2\2\u0088\u0089"+
		"\7t\2\2\u0089\u008a\7k\2\2\u008a\u008b\7g\2\2\u008b\u008c\7p\2\2\u008c"+
		"\u008d\7v\2\2\u008d\u008e\7c\2\2\u008e\u008f\7v\2\2\u008f\u0090\7k\2\2"+
		"\u0090\u0091\7q\2\2\u0091\u0092\7p\2\2\u0092\24\3\2\2\2\u0093\u0094\7"+
		"?\2\2\u0094\26\3\2\2\2\u0095\u0096\7j\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7k\2\2\u0098\u0099\7i\2\2\u0099\u009a\7j\2\2\u009a\u009b\7v\2\2\u009b"+
		"\30\3\2\2\2\u009c\u009d\7q\2\2\u009d\u009e\7v\2\2\u009e\u009f\7j\2\2\u009f"+
		"\u00a0\7g\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7D\2\2\u00a2\u00a3\7q\2\2"+
		"\u00a3\u00a4\7c\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7f\2\2\u00a6\32\3\2"+
		"\2\2\u00a7\u00a8\7z\2\2\u00a8\34\3\2\2\2\u00a9\u00aa\7{\2\2\u00aa\36\3"+
		"\2\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7c\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7i\2\2\u00b1\u00b2\7n\2\2"+
		"\u00b2\u00b3\7g\2\2\u00b3\u00b4\7D\2\2\u00b4\u00b5\7w\2\2\u00b5\u00b6"+
		"\7o\2\2\u00b6\u00b7\7r\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7t\2\2\u00b9"+
		" \3\2\2\2\u00ba\u00bb\7m\2\2\u00bb\u00bc\7g\2\2\u00bc\u00bd\7{\2\2\u00bd"+
		"\"\3\2\2\2\u00be\u00bf\7c\2\2\u00bf\u00c0\7d\2\2\u00c0\u00c1\7u\2\2\u00c1"+
		"\u00c2\7q\2\2\u00c2\u00c3\7t\2\2\u00c3\u00c4\7d\2\2\u00c4\u00c5\7g\2\2"+
		"\u00c5\u00c6\7t\2\2\u00c6$\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7s\2"+
		"\2\u00c9\u00ca\7w\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc\7t\2\2\u00cc\u00cd"+
		"\7g\2\2\u00cd\u00ce\7D\2\2\u00ce\u00cf\7w\2\2\u00cf\u00d0\7o\2\2\u00d0"+
		"\u00d1\7r\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7t\2\2\u00d3&\3\2\2\2\u00d4"+
		"\u00d5\7n\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7h\2\2\u00d7\u00d8\7v\2\2"+
		"\u00d8\u00d9\7H\2\2\u00d9\u00da\7n\2\2\u00da\u00db\7k\2\2\u00db\u00dc"+
		"\7r\2\2\u00dc\u00dd\7r\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7t\2\2\u00df"+
		"(\3\2\2\2\u00e0\u00e1\7e\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7t\2\2\u00e3"+
		"\u00e4\7e\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7D\2\2"+
		"\u00e7\u00e8\7w\2\2\u00e8\u00e9\7o\2\2\u00e9\u00ea\7r\2\2\u00ea\u00eb"+
		"\7g\2\2\u00eb\u00ec\7t\2\2\u00ec*\3\2\2\2\u00ed\u00ee\7h\2\2\u00ee\u00ef"+
		"\7t\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7v\2\2\u00f2"+
		"\u00f3\7k\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7p\2\2\u00f5\u00f6\7\64\2"+
		"\2\u00f6,\3\2\2\2\u00f7\u00f8\7y\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7"+
		"f\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7j\2\2\u00fc.\3\2\2\2\u00fd\u00fe"+
		"\7t\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7i\2\2\u0100\u0101\7j\2\2\u0101"+
		"\u0102\7v\2\2\u0102\u0103\7H\2\2\u0103\u0104\7n\2\2\u0104\u0105\7k\2\2"+
		"\u0105\u0106\7r\2\2\u0106\u0107\7r\2\2\u0107\u0108\7g\2\2\u0108\u0109"+
		"\7t\2\2\u0109\60\3\2\2\2\u010a\u010b\7m\2\2\u010b\u010c\7g\2\2\u010c\u010d"+
		"\7{\2\2\u010d\u010e\7w\2\2\u010e\u010f\7r\2\2\u010f\62\3\2\2\2\u0110\u0111"+
		"\7m\2\2\u0111\u0112\7g\2\2\u0112\u0113\7{\2\2\u0113\u0114\7f\2\2\u0114"+
		"\u0115\7q\2\2\u0115\u0116\7y\2\2\u0116\u0117\7p\2\2\u0117\64\3\2\2\2\u0118"+
		"\u0119\7h\2\2\u0119\u011a\7k\2\2\u011a\u011b\7t\2\2\u011b\u011c\7g\2\2"+
		"\u011c\66\3\2\2\2\u011d\u011e\7v\2\2\u011e\u011f\7t\2\2\u011f\u0120\7"+
		"k\2\2\u0120\u0121\7i\2\2\u0121\u0122\7i\2\2\u0122\u0123\7g\2\2\u0123\u0124"+
		"\7t\2\2\u01248\3\2\2\2\u0125\u0126\7c\2\2\u0126\u0127\7e\2\2\u0127\u0128"+
		"\7v\2\2\u0128\u0129\7k\2\2\u0129\u012a\7q\2\2\u012a\u012b\7p\2\2\u012b"+
		":\3\2\2\2\u012c\u012d\7r\2\2\u012d\u012e\7q\2\2\u012e\u012f\7t\2\2\u012f"+
		"\u0130\7v\2\2\u0130\u0131\7c\2\2\u0131\u0132\7n\2\2\u0132<\3\2\2\2\u0133"+
		"\u0137\7%\2\2\u0134\u0136\n\2\2\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2"+
		"\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013a\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u013a\u013b\b\37\2\2\u013b>\3\2\2\2\u013c\u013e\7/\2\2"+
		"\u013d\u013c\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0153\3\2\2\2\u013f\u0141"+
		"\t\3\2\2\u0140\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0148\7\60\2\2\u0145\u0147\t"+
		"\4\2\2\u0146\u0145\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148"+
		"\u0149\3\2\2\2\u0149\u0154\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014d\7\60"+
		"\2\2\u014c\u014b\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e"+
		"\u0150\t\5\2\2\u014f\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2"+
		"\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0140\3\2\2\2\u0153"+
		"\u014c\3\2\2\2\u0154@\3\2\2\2\u0155\u0157\t\6\2\2\u0156\u0155\3\2\2\2"+
		"\u0157\u0158\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159B\3"+
		"\2\2\2\u015a\u015c\t\7\2\2\u015b\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\b\""+
		"\3\2\u0160D\3\2\2\2\r\2\u0137\u013d\u0142\u0148\u014c\u0151\u0153\u0156"+
		"\u0158\u015d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}