// Generated from Grammar.g4 by ANTLR 4.0

package BoardExpr2;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__20=1, T__19=2, T__18=3, T__17=4, T__16=5, T__15=6, T__14=7, T__13=8, 
		T__12=9, T__11=10, T__10=11, T__9=12, T__8=13, T__7=14, T__6=15, T__5=16, 
		T__4=17, T__3=18, T__2=19, T__1=20, T__0=21, KEYUP=22, KEYDOWN=23, FIRE=24, 
		TRIGGER=25, ACTION=26, COMMENT=27, NUM=28, ID=29, WS=30;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'name'", "'friction1'", "'gravity'", "'board'", 
		"'ball'", "'xVelocity'", "'orientation'", "'='", "'height'", "'x'", "'y'", 
		"'triangleBumper'", "'key'", "'absorber'", "'squareBumper'", "'leftFlipper'", 
		"'circleBumper'", "'friction2'", "'width'", "'rightFlipper'", "'keyup'", 
		"'keydown'", "'fire'", "'trigger'", "'action'", "COMMENT", "NUM", "ID", 
		"WS"
	};
	public static final int
		RULE_boardInfo = 0, RULE_board = 1, RULE_gravity = 2, RULE_friction1 = 3, 
		RULE_friction2 = 4, RULE_object = 5, RULE_objectType = 6, RULE_objectName = 7, 
		RULE_xLoc = 8, RULE_yLoc = 9, RULE_xVelocity = 10, RULE_yVelocity = 11, 
		RULE_orientation = 12, RULE_width = 13, RULE_height = 14, RULE_fire = 15, 
		RULE_trigger = 16, RULE_action = 17, RULE_keys = 18, RULE_keyCmd = 19, 
		RULE_key = 20, RULE_equalSign = 21;
	public static final String[] ruleNames = {
		"boardInfo", "board", "gravity", "friction1", "friction2", "object", "objectType", 
		"objectName", "xLoc", "yLoc", "xVelocity", "yVelocity", "orientation", 
		"width", "height", "fire", "trigger", "action", "keys", "keyCmd", "key", 
		"equalSign"
	};

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardInfoContext extends ParserRuleContext {
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public FireContext fire(int i) {
			return getRuleContext(FireContext.class,i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(GrammarParser.COMMENT); }
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public TerminalNode COMMENT(int i) {
			return getToken(GrammarParser.COMMENT, i);
		}
		public BoardContext board() {
			return getRuleContext(BoardContext.class,0);
		}
		public List<FireContext> fire() {
			return getRuleContexts(FireContext.class);
		}
		public BoardInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBoardInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBoardInfo(this);
		}
	}

	public final BoardInfoContext boardInfo() throws RecognitionException {
		BoardInfoContext _localctx = new BoardInfoContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_boardInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); board();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 13) | (1L << 15) | (1L << 16) | (1L << 17) | (1L << 18) | (1L << 21) | (1L << FIRE) | (1L << COMMENT))) != 0)) {
				{
				setState(48);
				switch (_input.LA(1)) {
				case 6:
				case 13:
				case 15:
				case 16:
				case 17:
				case 18:
				case 21:
					{
					setState(45); object();
					}
					break;
				case COMMENT:
					{
					setState(46); match(COMMENT);
					}
					break;
				case FIRE:
					{
					setState(47); fire();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardContext extends ParserRuleContext {
		public List<Friction1Context> friction1() {
			return getRuleContexts(Friction1Context.class);
		}
		public ObjectNameContext objectName() {
			return getRuleContext(ObjectNameContext.class,0);
		}
		public Friction2Context friction2(int i) {
			return getRuleContext(Friction2Context.class,i);
		}
		public Friction1Context friction1(int i) {
			return getRuleContext(Friction1Context.class,i);
		}
		public GravityContext gravity(int i) {
			return getRuleContext(GravityContext.class,i);
		}
		public List<Friction2Context> friction2() {
			return getRuleContexts(Friction2Context.class);
		}
		public List<GravityContext> gravity() {
			return getRuleContexts(GravityContext.class);
		}
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(5);
			setState(56); objectName();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(57); gravity();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3 || _la==19) {
				{
				setState(65);
				switch (_input.LA(1)) {
				case 3:
					{
					setState(63); friction1();
					}
					break;
				case 19:
					{
					setState(64); friction2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GravityContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public GravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGravity(this);
		}
	}

	public final GravityContext gravity() throws RecognitionException {
		GravityContext _localctx = new GravityContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_gravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(4);
			setState(71); equalSign();
			setState(72); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction1Context extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public Friction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFriction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFriction1(this);
		}
	}

	public final Friction1Context friction1() throws RecognitionException {
		Friction1Context _localctx = new Friction1Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_friction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); match(3);
			setState(75); equalSign();
			setState(76); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction2Context extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public Friction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFriction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFriction2(this);
		}
	}

	public final Friction2Context friction2() throws RecognitionException {
		Friction2Context _localctx = new Friction2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_friction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); match(19);
			setState(79); equalSign();
			setState(80); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public List<OrientationContext> orientation() {
			return getRuleContexts(OrientationContext.class);
		}
		public YLocContext yLoc() {
			return getRuleContext(YLocContext.class,0);
		}
		public List<HeightContext> height() {
			return getRuleContexts(HeightContext.class);
		}
		public XLocContext xLoc() {
			return getRuleContext(XLocContext.class,0);
		}
		public ObjectNameContext objectName() {
			return getRuleContext(ObjectNameContext.class,0);
		}
		public List<WidthContext> width() {
			return getRuleContexts(WidthContext.class);
		}
		public List<YVelocityContext> yVelocity() {
			return getRuleContexts(YVelocityContext.class);
		}
		public XVelocityContext xVelocity(int i) {
			return getRuleContext(XVelocityContext.class,i);
		}
		public HeightContext height(int i) {
			return getRuleContext(HeightContext.class,i);
		}
		public OrientationContext orientation(int i) {
			return getRuleContext(OrientationContext.class,i);
		}
		public List<XVelocityContext> xVelocity() {
			return getRuleContexts(XVelocityContext.class);
		}
		public WidthContext width(int i) {
			return getRuleContext(WidthContext.class,i);
		}
		public YVelocityContext yVelocity(int i) {
			return getRuleContext(YVelocityContext.class,i);
		}
		public ObjectTypeContext objectType() {
			return getRuleContext(ObjectTypeContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); objectType();
			setState(83); objectName();
			setState(84); xLoc();
			setState(85); yLoc();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 8) | (1L << 20))) != 0)) {
				{
				setState(93);
				switch (_input.LA(1)) {
				case 7:
					{
					setState(86); xVelocity();
					setState(87); yVelocity();
					}
					break;
				case 8:
					{
					setState(89); orientation();
					}
					break;
				case 20:
					{
					setState(90); width();
					setState(91); height();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectTypeContext extends ParserRuleContext {
		public ObjectTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterObjectType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitObjectType(this);
		}
	}

	public final ObjectTypeContext objectType() throws RecognitionException {
		ObjectTypeContext _localctx = new ObjectTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_objectType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 13) | (1L << 15) | (1L << 16) | (1L << 17) | (1L << 18) | (1L << 21))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectNameContext extends ParserRuleContext {
		public TerminalNode ID(int i) {
			return getToken(GrammarParser.ID, i);
		}
		public List<TerminalNode> ID() { return getTokens(GrammarParser.ID); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public ObjectNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterObjectName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitObjectName(this);
		}
	}

	public final ObjectNameContext objectName() throws RecognitionException {
		ObjectNameContext _localctx = new ObjectNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_objectName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(2);
			setState(101); equalSign();
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102); match(ID);
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XLocContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public XLocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xLoc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterXLoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitXLoc(this);
		}
	}

	public final XLocContext xLoc() throws RecognitionException {
		XLocContext _localctx = new XLocContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_xLoc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); match(11);
			setState(108); equalSign();
			setState(109); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YLocContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public YLocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yLoc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterYLoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitYLoc(this);
		}
	}

	public final YLocContext yLoc() throws RecognitionException {
		YLocContext _localctx = new YLocContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_yLoc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(12);
			setState(112); equalSign();
			setState(113); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XVelocityContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public XVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterXVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitXVelocity(this);
		}
	}

	public final XVelocityContext xVelocity() throws RecognitionException {
		XVelocityContext _localctx = new XVelocityContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_xVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(7);
			setState(116); equalSign();
			setState(117); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YVelocityContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public YVelocityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yVelocity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterYVelocity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitYVelocity(this);
		}
	}

	public final YVelocityContext yVelocity() throws RecognitionException {
		YVelocityContext _localctx = new YVelocityContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_yVelocity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); match(1);
			setState(120); equalSign();
			setState(121); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrientationContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public OrientationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orientation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOrientation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOrientation(this);
		}
	}

	public final OrientationContext orientation() throws RecognitionException {
		OrientationContext _localctx = new OrientationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_orientation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(8);
			setState(124); equalSign();
			setState(125); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidthContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public WidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_width; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitWidth(this);
		}
	}

	public final WidthContext width() throws RecognitionException {
		WidthContext _localctx = new WidthContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_width);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(20);
			setState(128); equalSign();
			setState(129); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeightContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarParser.NUM, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public HeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_height; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterHeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitHeight(this);
		}
	}

	public final HeightContext height() throws RecognitionException {
		HeightContext _localctx = new HeightContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_height);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); match(10);
			setState(132); equalSign();
			setState(133); match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FireContext extends ParserRuleContext {
		public TriggerContext trigger() {
			return getRuleContext(TriggerContext.class,0);
		}
		public TerminalNode FIRE() { return getToken(GrammarParser.FIRE, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public FireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fire; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFire(this);
		}
	}

	public final FireContext fire() throws RecognitionException {
		FireContext _localctx = new FireContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(FIRE);
			setState(136); trigger();
			setState(137); action();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriggerContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public TerminalNode TRIGGER() { return getToken(GrammarParser.TRIGGER, 0); }
		public TriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trigger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTrigger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTrigger(this);
		}
	}

	public final TriggerContext trigger() throws RecognitionException {
		TriggerContext _localctx = new TriggerContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_trigger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(TRIGGER);
			setState(140); equalSign();
			setState(141); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public TerminalNode ACTION() { return getToken(GrammarParser.ACTION, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(ACTION);
			setState(144); equalSign();
			setState(145); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeysContext extends ParserRuleContext {
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public KeyCmdContext keyCmd() {
			return getRuleContext(KeyCmdContext.class,0);
		}
		public KeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKeys(this);
		}
	}

	public final KeysContext keys() throws RecognitionException {
		KeysContext _localctx = new KeysContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_keys);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); keyCmd();
			setState(148); key();
			setState(149); action();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyCmdContext extends ParserRuleContext {
		public TerminalNode KEYDOWN() { return getToken(GrammarParser.KEYDOWN, 0); }
		public TerminalNode KEYUP() { return getToken(GrammarParser.KEYUP, 0); }
		public KeyCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyCmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKeyCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKeyCmd(this);
		}
	}

	public final KeyCmdContext keyCmd() throws RecognitionException {
		KeyCmdContext _localctx = new KeyCmdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_keyCmd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_la = _input.LA(1);
			if ( !(_la==KEYUP || _la==KEYDOWN) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitKey(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153); match(14);
			setState(154); equalSign();
			setState(155); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualSignContext extends ParserRuleContext {
		public EqualSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterEqualSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitEqualSign(this);
		}
	}

	public final EqualSignContext equalSign() throws RecognitionException {
		EqualSignContext _localctx = new EqualSignContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_equalSign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3 \u00a2\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\3\2\3\2\3\2\3\2\7\2\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\7\3="+
		"\n\3\f\3\16\3@\13\3\3\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\7\7`\n\7\f\7\16\7c\13\7\3\b\3\b\3\t\3\t\3\t\6\tj\n\t\r\t\16\tk\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\2\30\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,\2\4\6\b\b\17\17\21\24\27\27\3\30\31\u0095\2.\3"+
		"\2\2\2\49\3\2\2\2\6H\3\2\2\2\bL\3\2\2\2\nP\3\2\2\2\fT\3\2\2\2\16d\3\2"+
		"\2\2\20f\3\2\2\2\22m\3\2\2\2\24q\3\2\2\2\26u\3\2\2\2\30y\3\2\2\2\32}\3"+
		"\2\2\2\34\u0081\3\2\2\2\36\u0085\3\2\2\2 \u0089\3\2\2\2\"\u008d\3\2\2"+
		"\2$\u0091\3\2\2\2&\u0095\3\2\2\2(\u0099\3\2\2\2*\u009b\3\2\2\2,\u009f"+
		"\3\2\2\2.\64\5\4\3\2/\63\5\f\7\2\60\63\7\35\2\2\61\63\5 \21\2\62/\3\2"+
		"\2\2\62\60\3\2\2\2\62\61\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2"+
		"\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7\1\2\28\3\3\2\2\29:\7\7\2\2:>\5"+
		"\20\t\2;=\5\6\4\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?E\3\2\2\2@>"+
		"\3\2\2\2AD\5\b\5\2BD\5\n\6\2CA\3\2\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2E"+
		"F\3\2\2\2F\5\3\2\2\2GE\3\2\2\2HI\7\6\2\2IJ\5,\27\2JK\7\36\2\2K\7\3\2\2"+
		"\2LM\7\5\2\2MN\5,\27\2NO\7\36\2\2O\t\3\2\2\2PQ\7\25\2\2QR\5,\27\2RS\7"+
		"\36\2\2S\13\3\2\2\2TU\5\16\b\2UV\5\20\t\2VW\5\22\n\2Wa\5\24\13\2XY\5\26"+
		"\f\2YZ\5\30\r\2Z`\3\2\2\2[`\5\32\16\2\\]\5\34\17\2]^\5\36\20\2^`\3\2\2"+
		"\2_X\3\2\2\2_[\3\2\2\2_\\\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\r\3\2"+
		"\2\2ca\3\2\2\2de\t\2\2\2e\17\3\2\2\2fg\7\4\2\2gi\5,\27\2hj\7\37\2\2ih"+
		"\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\21\3\2\2\2mn\7\r\2\2no\5,\27\2"+
		"op\7\36\2\2p\23\3\2\2\2qr\7\16\2\2rs\5,\27\2st\7\36\2\2t\25\3\2\2\2uv"+
		"\7\t\2\2vw\5,\27\2wx\7\36\2\2x\27\3\2\2\2yz\7\3\2\2z{\5,\27\2{|\7\36\2"+
		"\2|\31\3\2\2\2}~\7\n\2\2~\177\5,\27\2\177\u0080\7\36\2\2\u0080\33\3\2"+
		"\2\2\u0081\u0082\7\26\2\2\u0082\u0083\5,\27\2\u0083\u0084\7\36\2\2\u0084"+
		"\35\3\2\2\2\u0085\u0086\7\f\2\2\u0086\u0087\5,\27\2\u0087\u0088\7\36\2"+
		"\2\u0088\37\3\2\2\2\u0089\u008a\7\32\2\2\u008a\u008b\5\"\22\2\u008b\u008c"+
		"\5$\23\2\u008c!\3\2\2\2\u008d\u008e\7\33\2\2\u008e\u008f\5,\27\2\u008f"+
		"\u0090\7\37\2\2\u0090#\3\2\2\2\u0091\u0092\7\34\2\2\u0092\u0093\5,\27"+
		"\2\u0093\u0094\7\37\2\2\u0094%\3\2\2\2\u0095\u0096\5(\25\2\u0096\u0097"+
		"\5*\26\2\u0097\u0098\5$\23\2\u0098\'\3\2\2\2\u0099\u009a\t\3\2\2\u009a"+
		")\3\2\2\2\u009b\u009c\7\20\2\2\u009c\u009d\5,\27\2\u009d\u009e\7\37\2"+
		"\2\u009e+\3\2\2\2\u009f\u00a0\7\13\2\2\u00a0-\3\2\2\2\n\62\64>CE_ak";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}