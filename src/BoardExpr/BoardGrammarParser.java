// Generated from BoardGrammar.g4 by ANTLR 4.0

package BoardExpr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WHITESPACE=2, INTEGER=3, X=4, Y=5, EQUALS=6, NAMEKEY=7, XVEL=8, 
		YVEL=9, GRAVITY=10, FRICTION1=11, FRICTION2=12, TRIGGER=13, ORIENTATIONKEY=14, 
		ACTION=15, WIDTH=16, HEIGHT=17, BOARD=18, BALL=19, ABSORBER=20, FIRE=21, 
		SQUAREBUMPER=22, CIRCLEBUMPER=23, TRIANGLEBUMPER=24, LEFTFLIPPER=25, RIGHTFLIPPER=26, 
		FLOAT=27, NAME=28;
	public static final String[] tokenNames = {
		"<INVALID>", "COMMENT", "WHITESPACE", "INTEGER", "'x'", "'y'", "'='", 
		"'name'", "'xVelocity'", "'yVelocity'", "'gravity'", "'friction1'", "'friction2'", 
		"'trigger'", "'orientation'", "'action'", "'width'", "'height'", "'board'", 
		"'ball'", "'absorber'", "'fire'", "'squareBumper'", "'circleBumper'", 
		"'triangleBumper'", "'leftFlipper'", "'rightFlipper'", "FLOAT", "NAME"
	};
	public static final int
		RULE_board = 0, RULE_boardObjects = 1, RULE_boardspec = 2, RULE_ball = 3, 
		RULE_gravity = 4, RULE_friction1 = 5, RULE_friction2 = 6, RULE_bumper = 7, 
		RULE_bumpertype = 8, RULE_objectorientation = 9, RULE_flipperleft = 10, 
		RULE_flipperright = 11, RULE_absorber = 12, RULE_fire = 13, RULE_x = 14, 
		RULE_y = 15, RULE_xv = 16, RULE_yv = 17, RULE_width = 18, RULE_height = 19, 
		RULE_id = 20;
	public static final String[] ruleNames = {
		"board", "boardObjects", "boardspec", "ball", "gravity", "friction1", 
		"friction2", "bumper", "bumpertype", "objectorientation", "flipperleft", 
		"flipperright", "absorber", "fire", "x", "y", "xv", "yv", "width", "height", 
		"id"
	};

	@Override
	public String getGrammarFileName() { return "BoardGrammar.g4"; }

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

	public BoardGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardContext extends ParserRuleContext {
		public BoardObjectsContext boardObjects(int i) {
			return getRuleContext(BoardObjectsContext.class,i);
		}
		public List<BoardObjectsContext> boardObjects() {
			return getRuleContexts(BoardObjectsContext.class);
		}
		public BoardspecContext boardspec() {
			return getRuleContext(BoardspecContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BoardGrammarParser.EOF, 0); }
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); boardspec();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << ABSORBER) | (1L << FIRE) | (1L << SQUAREBUMPER) | (1L << CIRCLEBUMPER) | (1L << TRIANGLEBUMPER) | (1L << LEFTFLIPPER) | (1L << RIGHTFLIPPER))) != 0)) {
				{
				{
				setState(43); boardObjects();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49); match(EOF);
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

	public static class BoardObjectsContext extends ParserRuleContext {
		public AbsorberContext absorber() {
			return getRuleContext(AbsorberContext.class,0);
		}
		public BallContext ball() {
			return getRuleContext(BallContext.class,0);
		}
		public FlipperrightContext flipperright() {
			return getRuleContext(FlipperrightContext.class,0);
		}
		public FlipperleftContext flipperleft() {
			return getRuleContext(FlipperleftContext.class,0);
		}
		public BumperContext bumper() {
			return getRuleContext(BumperContext.class,0);
		}
		public FireContext fire() {
			return getRuleContext(FireContext.class,0);
		}
		public BoardObjectsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardObjects; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardObjects(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardObjects(this);
		}
	}

	public final BoardObjectsContext boardObjects() throws RecognitionException {
		BoardObjectsContext _localctx = new BoardObjectsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_boardObjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			switch (_input.LA(1)) {
			case BALL:
				{
				setState(51); ball();
				}
				break;
			case SQUAREBUMPER:
			case CIRCLEBUMPER:
			case TRIANGLEBUMPER:
				{
				setState(52); bumper();
				}
				break;
			case LEFTFLIPPER:
				{
				setState(53); flipperleft();
				}
				break;
			case RIGHTFLIPPER:
				{
				setState(54); flipperright();
				}
				break;
			case ABSORBER:
				{
				setState(55); absorber();
				}
				break;
			case FIRE:
				{
				setState(56); fire();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BoardspecContext extends ParserRuleContext {
		public List<Friction1Context> friction1() {
			return getRuleContexts(Friction1Context.class);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode BOARD() { return getToken(BoardGrammarParser.BOARD, 0); }
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
		public BoardspecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardspec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardspec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardspec(this);
		}
	}

	public final BoardspecContext boardspec() throws RecognitionException {
		BoardspecContext _localctx = new BoardspecContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_boardspec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(BOARD);
			setState(60); id();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==GRAVITY) {
				{
				{
				setState(61); gravity();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FRICTION1) {
				{
				{
				setState(67); friction1();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FRICTION2) {
				{
				{
				setState(73); friction2();
				}
				}
				setState(78);
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

	public static class BallContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public YvContext yv() {
			return getRuleContext(YvContext.class,0);
		}
		public TerminalNode BALL() { return getToken(BoardGrammarParser.BALL, 0); }
		public XvContext xv() {
			return getRuleContext(XvContext.class,0);
		}
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public BallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ball; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBall(this);
		}
	}

	public final BallContext ball() throws RecognitionException {
		BallContext _localctx = new BallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ball);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); match(BALL);
			setState(80); id();
			setState(81); x();
			setState(82); y();
			setState(83); xv();
			setState(84); yv();
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
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode GRAVITY() { return getToken(BoardGrammarParser.GRAVITY, 0); }
		public GravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitGravity(this);
		}
	}

	public final GravityContext gravity() throws RecognitionException {
		GravityContext _localctx = new GravityContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_gravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(GRAVITY);
			setState(87); match(EQUALS);
			setState(88); match(FLOAT);
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
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode FRICTION1() { return getToken(BoardGrammarParser.FRICTION1, 0); }
		public Friction1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFriction1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFriction1(this);
		}
	}

	public final Friction1Context friction1() throws RecognitionException {
		Friction1Context _localctx = new Friction1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_friction1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(FRICTION1);
			setState(91); match(EQUALS);
			setState(92); match(FLOAT);
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
		public TerminalNode FRICTION2() { return getToken(BoardGrammarParser.FRICTION2, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public Friction2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFriction2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFriction2(this);
		}
	}

	public final Friction2Context friction2() throws RecognitionException {
		Friction2Context _localctx = new Friction2Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_friction2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(FRICTION2);
			setState(95); match(EQUALS);
			setState(96); match(FLOAT);
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

	public static class BumperContext extends ParserRuleContext {
		public List<ObjectorientationContext> objectorientation() {
			return getRuleContexts(ObjectorientationContext.class);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ObjectorientationContext objectorientation(int i) {
			return getRuleContext(ObjectorientationContext.class,i);
		}
		public BumpertypeContext bumpertype() {
			return getRuleContext(BumpertypeContext.class,0);
		}
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public BumperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bumper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBumper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBumper(this);
		}
	}

	public final BumperContext bumper() throws RecognitionException {
		BumperContext _localctx = new BumperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bumper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); bumpertype();
			setState(99); id();
			setState(100); x();
			setState(101); y();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ORIENTATIONKEY) {
				{
				{
				setState(102); objectorientation();
				}
				}
				setState(107);
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

	public static class BumpertypeContext extends ParserRuleContext {
		public TerminalNode SQUAREBUMPER() { return getToken(BoardGrammarParser.SQUAREBUMPER, 0); }
		public TerminalNode TRIANGLEBUMPER() { return getToken(BoardGrammarParser.TRIANGLEBUMPER, 0); }
		public TerminalNode CIRCLEBUMPER() { return getToken(BoardGrammarParser.CIRCLEBUMPER, 0); }
		public BumpertypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bumpertype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBumpertype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBumpertype(this);
		}
	}

	public final BumpertypeContext bumpertype() throws RecognitionException {
		BumpertypeContext _localctx = new BumpertypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bumpertype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SQUAREBUMPER) | (1L << CIRCLEBUMPER) | (1L << TRIANGLEBUMPER))) != 0)) ) {
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

	public static class ObjectorientationContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BoardGrammarParser.INTEGER, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode ORIENTATIONKEY() { return getToken(BoardGrammarParser.ORIENTATIONKEY, 0); }
		public ObjectorientationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectorientation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterObjectorientation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitObjectorientation(this);
		}
	}

	public final ObjectorientationContext objectorientation() throws RecognitionException {
		ObjectorientationContext _localctx = new ObjectorientationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_objectorientation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(ORIENTATIONKEY);
			setState(111); match(EQUALS);
			setState(112); match(INTEGER);
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

	public static class FlipperleftContext extends ParserRuleContext {
		public ObjectorientationContext objectorientation() {
			return getRuleContext(ObjectorientationContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode LEFTFLIPPER() { return getToken(BoardGrammarParser.LEFTFLIPPER, 0); }
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public FlipperleftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flipperleft; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFlipperleft(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFlipperleft(this);
		}
	}

	public final FlipperleftContext flipperleft() throws RecognitionException {
		FlipperleftContext _localctx = new FlipperleftContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flipperleft);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); match(LEFTFLIPPER);
			setState(115); id();
			setState(116); x();
			setState(117); y();
			setState(118); objectorientation();
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

	public static class FlipperrightContext extends ParserRuleContext {
		public ObjectorientationContext objectorientation() {
			return getRuleContext(ObjectorientationContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode RIGHTFLIPPER() { return getToken(BoardGrammarParser.RIGHTFLIPPER, 0); }
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public FlipperrightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flipperright; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFlipperright(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFlipperright(this);
		}
	}

	public final FlipperrightContext flipperright() throws RecognitionException {
		FlipperrightContext _localctx = new FlipperrightContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_flipperright);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(RIGHTFLIPPER);
			setState(121); id();
			setState(122); x();
			setState(123); y();
			setState(124); objectorientation();
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

	public static class AbsorberContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public HeightContext height() {
			return getRuleContext(HeightContext.class,0);
		}
		public WidthContext width() {
			return getRuleContext(WidthContext.class,0);
		}
		public TerminalNode ABSORBER() { return getToken(BoardGrammarParser.ABSORBER, 0); }
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public AbsorberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterAbsorber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitAbsorber(this);
		}
	}

	public final AbsorberContext absorber() throws RecognitionException {
		AbsorberContext _localctx = new AbsorberContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_absorber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(ABSORBER);
			setState(127); id();
			setState(128); x();
			setState(129); y();
			setState(130); width();
			setState(131); height();
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
		public TerminalNode FIRE() { return getToken(BoardGrammarParser.FIRE, 0); }
		public List<TerminalNode> NAME() { return getTokens(BoardGrammarParser.NAME); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardGrammarParser.NAME, i);
		}
		public TerminalNode ACTION() { return getToken(BoardGrammarParser.ACTION, 0); }
		public TerminalNode TRIGGER() { return getToken(BoardGrammarParser.TRIGGER, 0); }
		public FireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fire; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFire(this);
		}
	}

	public final FireContext fire() throws RecognitionException {
		FireContext _localctx = new FireContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); match(FIRE);
			setState(134); match(TRIGGER);
			setState(135); match(EQUALS);
			setState(136); match(NAME);
			setState(137); match(ACTION);
			setState(138); match(EQUALS);
			setState(139); match(NAME);
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

	public static class XContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BoardGrammarParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode X() { return getToken(BoardGrammarParser.X, 0); }
		public XContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_x; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitX(this);
		}
	}

	public final XContext x() throws RecognitionException {
		XContext _localctx = new XContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_x);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); match(X);
			setState(142); match(EQUALS);
			setState(143);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class YContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BoardGrammarParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode Y() { return getToken(BoardGrammarParser.Y, 0); }
		public YContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_y; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitY(this);
		}
	}

	public final YContext y() throws RecognitionException {
		YContext _localctx = new YContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_y);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(Y);
			setState(146); match(EQUALS);
			setState(147);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class XvContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode XVEL() { return getToken(BoardGrammarParser.XVEL, 0); }
		public XvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterXv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitXv(this);
		}
	}

	public final XvContext xv() throws RecognitionException {
		XvContext _localctx = new XvContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_xv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); match(XVEL);
			setState(150); match(EQUALS);
			setState(151); match(FLOAT);
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

	public static class YvContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode YVEL() { return getToken(BoardGrammarParser.YVEL, 0); }
		public YvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterYv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitYv(this);
		}
	}

	public final YvContext yv() throws RecognitionException {
		YvContext _localctx = new YvContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_yv);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153); match(YVEL);
			setState(154); match(EQUALS);
			setState(155); match(FLOAT);
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
		public TerminalNode INTEGER() { return getToken(BoardGrammarParser.INTEGER, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode WIDTH() { return getToken(BoardGrammarParser.WIDTH, 0); }
		public WidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_width; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterWidth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitWidth(this);
		}
	}

	public final WidthContext width() throws RecognitionException {
		WidthContext _localctx = new WidthContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_width);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(WIDTH);
			setState(158); match(EQUALS);
			setState(159); match(INTEGER);
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
		public TerminalNode INTEGER() { return getToken(BoardGrammarParser.INTEGER, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode HEIGHT() { return getToken(BoardGrammarParser.HEIGHT, 0); }
		public HeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_height; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterHeight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitHeight(this);
		}
	}

	public final HeightContext height() throws RecognitionException {
		HeightContext _localctx = new HeightContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_height);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(HEIGHT);
			setState(162); match(EQUALS);
			setState(163); match(INTEGER);
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

	public static class IdContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public TerminalNode NAMEKEY() { return getToken(BoardGrammarParser.NAMEKEY, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165); match(NAMEKEY);
			setState(166); match(EQUALS);
			setState(167); match(NAME);
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
		"\2\3\36\u00ac\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3"+
		"\2\7\2/\n\2\f\2\16\2\62\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3<\n\3"+
		"\3\4\3\4\3\4\7\4A\n\4\f\4\16\4D\13\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\7"+
		"\4M\n\4\f\4\16\4P\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\7\tj\n\t\f\t\16\tm\13"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*"+
		"\2\5\3\30\32\4\5\5\35\35\4\5\5\35\35\u00a0\2,\3\2\2\2\4;\3\2\2\2\6=\3"+
		"\2\2\2\bQ\3\2\2\2\nX\3\2\2\2\f\\\3\2\2\2\16`\3\2\2\2\20d\3\2\2\2\22n\3"+
		"\2\2\2\24p\3\2\2\2\26t\3\2\2\2\30z\3\2\2\2\32\u0080\3\2\2\2\34\u0087\3"+
		"\2\2\2\36\u008f\3\2\2\2 \u0093\3\2\2\2\"\u0097\3\2\2\2$\u009b\3\2\2\2"+
		"&\u009f\3\2\2\2(\u00a3\3\2\2\2*\u00a7\3\2\2\2,\60\5\6\4\2-/\5\4\3\2.-"+
		"\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60\3\2"+
		"\2\2\63\64\7\1\2\2\64\3\3\2\2\2\65<\5\b\5\2\66<\5\20\t\2\67<\5\26\f\2"+
		"8<\5\30\r\29<\5\32\16\2:<\5\34\17\2;\65\3\2\2\2;\66\3\2\2\2;\67\3\2\2"+
		"\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\5\3\2\2\2=>\7\24\2\2>B\5*\26\2?A\5\n"+
		"\6\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CH\3\2\2\2DB\3\2\2\2EG\5\f"+
		"\7\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IN\3\2\2\2JH\3\2\2\2KM\5\16"+
		"\b\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\7\3\2\2\2PN\3\2\2\2QR\7"+
		"\25\2\2RS\5*\26\2ST\5\36\20\2TU\5 \21\2UV\5\"\22\2VW\5$\23\2W\t\3\2\2"+
		"\2XY\7\f\2\2YZ\7\b\2\2Z[\7\35\2\2[\13\3\2\2\2\\]\7\r\2\2]^\7\b\2\2^_\7"+
		"\35\2\2_\r\3\2\2\2`a\7\16\2\2ab\7\b\2\2bc\7\35\2\2c\17\3\2\2\2de\5\22"+
		"\n\2ef\5*\26\2fg\5\36\20\2gk\5 \21\2hj\5\24\13\2ih\3\2\2\2jm\3\2\2\2k"+
		"i\3\2\2\2kl\3\2\2\2l\21\3\2\2\2mk\3\2\2\2no\t\2\2\2o\23\3\2\2\2pq\7\20"+
		"\2\2qr\7\b\2\2rs\7\5\2\2s\25\3\2\2\2tu\7\33\2\2uv\5*\26\2vw\5\36\20\2"+
		"wx\5 \21\2xy\5\24\13\2y\27\3\2\2\2z{\7\34\2\2{|\5*\26\2|}\5\36\20\2}~"+
		"\5 \21\2~\177\5\24\13\2\177\31\3\2\2\2\u0080\u0081\7\26\2\2\u0081\u0082"+
		"\5*\26\2\u0082\u0083\5\36\20\2\u0083\u0084\5 \21\2\u0084\u0085\5&\24\2"+
		"\u0085\u0086\5(\25\2\u0086\33\3\2\2\2\u0087\u0088\7\27\2\2\u0088\u0089"+
		"\7\17\2\2\u0089\u008a\7\b\2\2\u008a\u008b\7\36\2\2\u008b\u008c\7\21\2"+
		"\2\u008c\u008d\7\b\2\2\u008d\u008e\7\36\2\2\u008e\35\3\2\2\2\u008f\u0090"+
		"\7\6\2\2\u0090\u0091\7\b\2\2\u0091\u0092\t\3\2\2\u0092\37\3\2\2\2\u0093"+
		"\u0094\7\7\2\2\u0094\u0095\7\b\2\2\u0095\u0096\t\4\2\2\u0096!\3\2\2\2"+
		"\u0097\u0098\7\n\2\2\u0098\u0099\7\b\2\2\u0099\u009a\7\35\2\2\u009a#\3"+
		"\2\2\2\u009b\u009c\7\13\2\2\u009c\u009d\7\b\2\2\u009d\u009e\7\35\2\2\u009e"+
		"%\3\2\2\2\u009f\u00a0\7\22\2\2\u00a0\u00a1\7\b\2\2\u00a1\u00a2\7\5\2\2"+
		"\u00a2\'\3\2\2\2\u00a3\u00a4\7\23\2\2\u00a4\u00a5\7\b\2\2\u00a5\u00a6"+
		"\7\5\2\2\u00a6)\3\2\2\2\u00a7\u00a8\7\t\2\2\u00a8\u00a9\7\b\2\2\u00a9"+
		"\u00aa\7\36\2\2\u00aa+\3\2\2\2\b\60;BHNk";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}