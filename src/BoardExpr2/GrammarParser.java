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
		T__22=1, T__21=2, T__20=3, T__19=4, T__18=5, T__17=6, T__16=7, T__15=8, 
		T__14=9, T__13=10, T__12=11, T__11=12, T__10=13, T__9=14, T__8=15, T__7=16, 
		T__6=17, T__5=18, T__4=19, T__3=20, T__2=21, T__1=22, T__0=23, KEYUP=24, 
		KEYDOWN=25, FIRE=26, TRIGGER=27, ACTION=28, PORTAL=29, COMMENT=30, NUM=31, 
		ID=32, WS=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'name'", "'friction1'", "'gravity'", "'otherPortal'", 
		"'board'", "'ball'", "'xVelocity'", "'orientation'", "'='", "'height'", 
		"'otherBoard'", "'x'", "'y'", "'triangleBumper'", "'key'", "'absorber'", 
		"'squareBumper'", "'leftFlipper'", "'circleBumper'", "'friction2'", "'width'", 
		"'rightFlipper'", "'keyup'", "'keydown'", "'fire'", "'trigger'", "'action'", 
		"'portal'", "COMMENT", "NUM", "ID", "WS"
	};
	public static final int
		RULE_boardInfo = 0, RULE_board = 1, RULE_gravity = 2, RULE_friction1 = 3, 
		RULE_friction2 = 4, RULE_object = 5, RULE_objectType = 6, RULE_objectName = 7, 
		RULE_xLoc = 8, RULE_yLoc = 9, RULE_xVelocity = 10, RULE_yVelocity = 11, 
		RULE_orientation = 12, RULE_width = 13, RULE_height = 14, RULE_fire = 15, 
		RULE_trigger = 16, RULE_action = 17, RULE_keys = 18, RULE_keyCmd = 19, 
		RULE_key = 20, RULE_portal = 21, RULE_otherBoard = 22, RULE_otherPortal = 23, 
		RULE_equalSign = 24;
	public static final String[] ruleNames = {
		"boardInfo", "board", "gravity", "friction1", "friction2", "object", "objectType", 
		"objectName", "xLoc", "yLoc", "xVelocity", "yVelocity", "orientation", 
		"width", "height", "fire", "trigger", "action", "keys", "keyCmd", "key", 
		"portal", "otherBoard", "otherPortal", "equalSign"
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
		public List<KeysContext> keys() {
			return getRuleContexts(KeysContext.class);
		}
		public List<PortalContext> portal() {
			return getRuleContexts(PortalContext.class);
		}
		public FireContext fire(int i) {
			return getRuleContext(FireContext.class,i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(GrammarParser.COMMENT); }
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public PortalContext portal(int i) {
			return getRuleContext(PortalContext.class,i);
		}
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public KeysContext keys(int i) {
			return getRuleContext(KeysContext.class,i);
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
			setState(50); board();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 15) | (1L << 17) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 23) | (1L << KEYUP) | (1L << KEYDOWN) | (1L << FIRE) | (1L << PORTAL) | (1L << COMMENT))) != 0)) {
				{
				setState(56);
				switch (_input.LA(1)) {
				case 7:
				case 15:
				case 17:
				case 18:
				case 19:
				case 20:
				case 23:
					{
					setState(51); object();
					}
					break;
				case COMMENT:
					{
					setState(52); match(COMMENT);
					}
					break;
				case FIRE:
					{
					setState(53); fire();
					}
					break;
				case KEYUP:
				case KEYDOWN:
					{
					setState(54); keys();
					}
					break;
				case PORTAL:
					{
					setState(55); portal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61); match(EOF);
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
			setState(63); match(6);
			setState(64); objectName();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(65); gravity();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==3 || _la==21) {
				{
				setState(73);
				switch (_input.LA(1)) {
				case 3:
					{
					setState(71); friction1();
					}
					break;
				case 21:
					{
					setState(72); friction2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(77);
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
			setState(78); match(4);
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
			setState(82); match(3);
			setState(83); equalSign();
			setState(84); match(NUM);
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
			setState(86); match(21);
			setState(87); equalSign();
			setState(88); match(NUM);
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
			setState(90); objectType();
			setState(91); objectName();
			setState(92); xLoc();
			setState(93); yLoc();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << 9) | (1L << 22))) != 0)) {
				{
				setState(101);
				switch (_input.LA(1)) {
				case 8:
					{
					setState(94); xVelocity();
					setState(95); yVelocity();
					}
					break;
				case 9:
					{
					setState(97); orientation();
					}
					break;
				case 22:
					{
					setState(98); width();
					setState(99); height();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(105);
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
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 15) | (1L << 17) | (1L << 18) | (1L << 19) | (1L << 20) | (1L << 23))) != 0)) ) {
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
			setState(108); match(2);
			setState(109); equalSign();
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110); match(ID);
				}
				}
				setState(113); 
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
			setState(115); match(13);
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
			setState(119); match(14);
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
			setState(127); match(1);
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
			setState(131); match(9);
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
			setState(135); match(22);
			setState(136); equalSign();
			setState(137); match(NUM);
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
			setState(139); match(11);
			setState(140); equalSign();
			setState(141); match(NUM);
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
			setState(143); match(FIRE);
			setState(144); trigger();
			setState(145); action();
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
			setState(147); match(TRIGGER);
			setState(148); equalSign();
			setState(149); match(ID);
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
			setState(151); match(ACTION);
			setState(152); equalSign();
			setState(153); match(ID);
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
			setState(155); keyCmd();
			setState(156); key();
			setState(157); action();
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
			setState(159);
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
			setState(161); match(16);
			setState(162); equalSign();
			setState(163); match(ID);
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

	public static class PortalContext extends ParserRuleContext {
		public YLocContext yLoc() {
			return getRuleContext(YLocContext.class,0);
		}
		public OtherBoardContext otherBoard(int i) {
			return getRuleContext(OtherBoardContext.class,i);
		}
		public OtherPortalContext otherPortal() {
			return getRuleContext(OtherPortalContext.class,0);
		}
		public List<OtherBoardContext> otherBoard() {
			return getRuleContexts(OtherBoardContext.class);
		}
		public XLocContext xLoc() {
			return getRuleContext(XLocContext.class,0);
		}
		public ObjectNameContext objectName() {
			return getRuleContext(ObjectNameContext.class,0);
		}
		public TerminalNode PORTAL() { return getToken(GrammarParser.PORTAL, 0); }
		public PortalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPortal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPortal(this);
		}
	}

	public final PortalContext portal() throws RecognitionException {
		PortalContext _localctx = new PortalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_portal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(PORTAL);
			setState(166); objectName();
			setState(167); xLoc();
			setState(168); yLoc();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==12) {
				{
				{
				setState(169); otherBoard();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(175); otherPortal();
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

	public static class OtherBoardContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public OtherBoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherBoard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOtherBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOtherBoard(this);
		}
	}

	public final OtherBoardContext otherBoard() throws RecognitionException {
		OtherBoardContext _localctx = new OtherBoardContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_otherBoard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177); match(12);
			setState(178); equalSign();
			setState(179); match(ID);
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

	public static class OtherPortalContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarParser.ID, 0); }
		public EqualSignContext equalSign() {
			return getRuleContext(EqualSignContext.class,0);
		}
		public OtherPortalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherPortal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterOtherPortal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitOtherPortal(this);
		}
	}

	public final OtherPortalContext otherPortal() throws RecognitionException {
		OtherPortalContext _localctx = new OtherPortalContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_otherPortal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); match(5);
			setState(182); equalSign();
			setState(183); match(ID);
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
		enterRule(_localctx, 48, RULE_equalSign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185); match(10);
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
		"\2\3#\u00be\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\7\2;\n\2\f\2\16"+
		"\2>\13\2\3\2\3\2\3\3\3\3\3\3\7\3E\n\3\f\3\16\3H\13\3\3\3\3\3\7\3L\n\3"+
		"\f\3\16\3O\13\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7h\n\7\f\7\16\7k\13\7\3\b\3\b"+
		"\3\t\3\t\3\t\6\tr\n\t\r\t\16\ts\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\7\27\u00ad\n\27\f\27\16\27\u00b0\13\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\2\33\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\4\6\t\t\21\21\23\26\31\31\3"+
		"\32\33\u00b1\2\64\3\2\2\2\4A\3\2\2\2\6P\3\2\2\2\bT\3\2\2\2\nX\3\2\2\2"+
		"\f\\\3\2\2\2\16l\3\2\2\2\20n\3\2\2\2\22u\3\2\2\2\24y\3\2\2\2\26}\3\2\2"+
		"\2\30\u0081\3\2\2\2\32\u0085\3\2\2\2\34\u0089\3\2\2\2\36\u008d\3\2\2\2"+
		" \u0091\3\2\2\2\"\u0095\3\2\2\2$\u0099\3\2\2\2&\u009d\3\2\2\2(\u00a1\3"+
		"\2\2\2*\u00a3\3\2\2\2,\u00a7\3\2\2\2.\u00b3\3\2\2\2\60\u00b7\3\2\2\2\62"+
		"\u00bb\3\2\2\2\64<\5\4\3\2\65;\5\f\7\2\66;\7 \2\2\67;\5 \21\28;\5&\24"+
		"\29;\5,\27\2:\65\3\2\2\2:\66\3\2\2\2:\67\3\2\2\2:8\3\2\2\2:9\3\2\2\2;"+
		">\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\1\2\2@\3\3\2\2\2"+
		"AB\7\b\2\2BF\5\20\t\2CE\5\6\4\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2"+
		"\2GM\3\2\2\2HF\3\2\2\2IL\5\b\5\2JL\5\n\6\2KI\3\2\2\2KJ\3\2\2\2LO\3\2\2"+
		"\2MK\3\2\2\2MN\3\2\2\2N\5\3\2\2\2OM\3\2\2\2PQ\7\6\2\2QR\5\62\32\2RS\7"+
		"!\2\2S\7\3\2\2\2TU\7\5\2\2UV\5\62\32\2VW\7!\2\2W\t\3\2\2\2XY\7\27\2\2"+
		"YZ\5\62\32\2Z[\7!\2\2[\13\3\2\2\2\\]\5\16\b\2]^\5\20\t\2^_\5\22\n\2_i"+
		"\5\24\13\2`a\5\26\f\2ab\5\30\r\2bh\3\2\2\2ch\5\32\16\2de\5\34\17\2ef\5"+
		"\36\20\2fh\3\2\2\2g`\3\2\2\2gc\3\2\2\2gd\3\2\2\2hk\3\2\2\2ig\3\2\2\2i"+
		"j\3\2\2\2j\r\3\2\2\2ki\3\2\2\2lm\t\2\2\2m\17\3\2\2\2no\7\4\2\2oq\5\62"+
		"\32\2pr\7\"\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2t\21\3\2\2\2uv"+
		"\7\17\2\2vw\5\62\32\2wx\7!\2\2x\23\3\2\2\2yz\7\20\2\2z{\5\62\32\2{|\7"+
		"!\2\2|\25\3\2\2\2}~\7\n\2\2~\177\5\62\32\2\177\u0080\7!\2\2\u0080\27\3"+
		"\2\2\2\u0081\u0082\7\3\2\2\u0082\u0083\5\62\32\2\u0083\u0084\7!\2\2\u0084"+
		"\31\3\2\2\2\u0085\u0086\7\13\2\2\u0086\u0087\5\62\32\2\u0087\u0088\7!"+
		"\2\2\u0088\33\3\2\2\2\u0089\u008a\7\30\2\2\u008a\u008b\5\62\32\2\u008b"+
		"\u008c\7!\2\2\u008c\35\3\2\2\2\u008d\u008e\7\r\2\2\u008e\u008f\5\62\32"+
		"\2\u008f\u0090\7!\2\2\u0090\37\3\2\2\2\u0091\u0092\7\34\2\2\u0092\u0093"+
		"\5\"\22\2\u0093\u0094\5$\23\2\u0094!\3\2\2\2\u0095\u0096\7\35\2\2\u0096"+
		"\u0097\5\62\32\2\u0097\u0098\7\"\2\2\u0098#\3\2\2\2\u0099\u009a\7\36\2"+
		"\2\u009a\u009b\5\62\32\2\u009b\u009c\7\"\2\2\u009c%\3\2\2\2\u009d\u009e"+
		"\5(\25\2\u009e\u009f\5*\26\2\u009f\u00a0\5$\23\2\u00a0\'\3\2\2\2\u00a1"+
		"\u00a2\t\3\2\2\u00a2)\3\2\2\2\u00a3\u00a4\7\22\2\2\u00a4\u00a5\5\62\32"+
		"\2\u00a5\u00a6\7\"\2\2\u00a6+\3\2\2\2\u00a7\u00a8\7\37\2\2\u00a8\u00a9"+
		"\5\20\t\2\u00a9\u00aa\5\22\n\2\u00aa\u00ae\5\24\13\2\u00ab\u00ad\5.\30"+
		"\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af"+
		"\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\5\60\31\2"+
		"\u00b2-\3\2\2\2\u00b3\u00b4\7\16\2\2\u00b4\u00b5\5\62\32\2\u00b5\u00b6"+
		"\7\"\2\2\u00b6/\3\2\2\2\u00b7\u00b8\7\7\2\2\u00b8\u00b9\5\62\32\2\u00b9"+
		"\u00ba\7\"\2\2\u00ba\61\3\2\2\2\u00bb\u00bc\7\f\2\2\u00bc\63\3\2\2\2\13"+
		":<FKMgis\u00ae";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}