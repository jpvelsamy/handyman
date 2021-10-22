// Generated from Raven.g4 by ANTLR 4.9.2

package in.handyman.raven.compiler;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RavenParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, NON_ZERO_DIGIT=54, STRING=55, CRLF=56, Operator=57, WS=58, COMMENT=59, 
		LINE_COMMENT=60, NUMBER=61;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_attribute = 5, RULE_copyData = 6, RULE_transform = 7, 
		RULE_loadCsv = 8, RULE_abort = 9, RULE_callProcess = 10, RULE_forkProcess = 11, 
		RULE_spawnProcess = 12, RULE_dogLeg = 13, RULE_startProcess = 14, RULE_assign = 15, 
		RULE_dropFile = 16, RULE_restApi = 17, RULE_restPart = 18, RULE_expression = 19, 
		RULE_json = 20, RULE_obj = 21, RULE_pair = 22, RULE_arr = 23, RULE_jValue = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "attribute", 
			"copyData", "transform", "loadCsv", "abort", "callProcess", "forkProcess", 
			"spawnProcess", "dogLeg", "startProcess", "assign", "dropFile", "restApi", 
			"restPart", "expression", "json", "obj", "pair", "arr", "jValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'", 
			"'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'", 
			"'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'", 
			"'on'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", "'abort'", "'callprocess'", 
			"'with-target'", "'from-file'", "'for-every'", "'forkprocess'", "'watermark'", 
			"'spawn'", "'dogleg'", "'use-parent-context'", "'start-process'", "'with-file'", 
			"'assign'", "'source'", "'dropfile'", "'in-path'", "'restapi'", "'url'", 
			"'method'", "'with headers'", "'with params'", "'with body type'", "'{ part'", 
			"'type as'", "'if'", "','", "':'", "'['", "']'", "'true'", "'false'", 
			"'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", 
			"Operator", "WS", "COMMENT", "LINE_COMMENT", "NUMBER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Raven.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RavenParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProcessContext extends ParserRuleContext {
		public Token name;
		public TryClauseContext tryBlock;
		public CatchClauseContext catchBlock;
		public FinallyClauseContext finallyBlock;
		public TerminalNode STRING() { return getToken(RavenParser.STRING, 0); }
		public TryClauseContext tryClause() {
			return getRuleContext(TryClauseContext.class,0);
		}
		public CatchClauseContext catchClause() {
			return getRuleContext(CatchClauseContext.class,0);
		}
		public FinallyClauseContext finallyClause() {
			return getRuleContext(FinallyClauseContext.class,0);
		}
		public ProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitProcess(this);
		}
	}

	public final ProcessContext process() throws RecognitionException {
		ProcessContext _localctx = new ProcessContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_process);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__0);
			setState(51);
			((ProcessContext)_localctx).name = match(STRING);
			setState(52);
			match(T__1);
			setState(53);
			((ProcessContext)_localctx).tryBlock = tryClause();
			setState(54);
			((ProcessContext)_localctx).catchBlock = catchClause();
			setState(55);
			((ProcessContext)_localctx).finallyBlock = finallyClause();
			setState(56);
			match(T__2);
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

	public static class TryClauseContext extends ParserRuleContext {
		public ActionContext action;
		public List<ActionContext> actions = new ArrayList<ActionContext>();
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public TryClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterTryClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitTryClause(this);
		}
	}

	public final TryClauseContext tryClause() throws RecognitionException {
		TryClauseContext _localctx = new TryClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tryClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__3);
			setState(59);
			match(T__1);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37))) != 0)) {
				{
				{
				setState(60);
				((TryClauseContext)_localctx).action = action();
				((TryClauseContext)_localctx).actions.add(((TryClauseContext)_localctx).action);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(T__2);
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

	public static class FinallyClauseContext extends ParserRuleContext {
		public ActionContext action;
		public List<ActionContext> actions = new ArrayList<ActionContext>();
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public FinallyClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterFinallyClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitFinallyClause(this);
		}
	}

	public final FinallyClauseContext finallyClause() throws RecognitionException {
		FinallyClauseContext _localctx = new FinallyClauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_finallyClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__4);
			setState(69);
			match(T__1);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37))) != 0)) {
				{
				{
				setState(70);
				((FinallyClauseContext)_localctx).action = action();
				((FinallyClauseContext)_localctx).actions.add(((FinallyClauseContext)_localctx).action);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			match(T__2);
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

	public static class CatchClauseContext extends ParserRuleContext {
		public ActionContext action;
		public List<ActionContext> actions = new ArrayList<ActionContext>();
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitCatchClause(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__5);
			setState(79);
			match(T__1);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37))) != 0)) {
				{
				{
				setState(80);
				((CatchClauseContext)_localctx).action = action();
				((CatchClauseContext)_localctx).actions.add(((CatchClauseContext)_localctx).action);
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			match(T__2);
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
		public AbortContext abort() {
			return getRuleContext(AbortContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public CallProcessContext callProcess() {
			return getRuleContext(CallProcessContext.class,0);
		}
		public ForkProcessContext forkProcess() {
			return getRuleContext(ForkProcessContext.class,0);
		}
		public SpawnProcessContext spawnProcess() {
			return getRuleContext(SpawnProcessContext.class,0);
		}
		public DogLegContext dogLeg() {
			return getRuleContext(DogLegContext.class,0);
		}
		public CopyDataContext copyData() {
			return getRuleContext(CopyDataContext.class,0);
		}
		public DropFileContext dropFile() {
			return getRuleContext(DropFileContext.class,0);
		}
		public LoadCsvContext loadCsv() {
			return getRuleContext(LoadCsvContext.class,0);
		}
		public RestApiContext restApi() {
			return getRuleContext(RestApiContext.class,0);
		}
		public TransformContext transform() {
			return getRuleContext(TransformContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				{
				setState(88);
				abort();
				}
				break;
			case T__33:
				{
				setState(89);
				assign();
				}
				break;
			case T__22:
				{
				setState(90);
				callProcess();
				}
				break;
			case T__26:
				{
				setState(91);
				forkProcess();
				}
				break;
			case T__28:
				{
				setState(92);
				spawnProcess();
				}
				break;
			case T__29:
				{
				setState(93);
				dogLeg();
				}
				break;
			case T__6:
				{
				setState(94);
				copyData();
				}
				break;
			case T__35:
				{
				setState(95);
				dropFile();
				}
				break;
			case T__17:
				{
				setState(96);
				loadCsv();
				}
				break;
			case T__37:
				{
				setState(97);
				restApi();
				}
				break;
			case T__15:
				{
				setState(98);
				transform();
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

	public static class AttributeContext extends ParserRuleContext {
		public StartProcessContext startProcess() {
			return getRuleContext(StartProcessContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(101);
			startProcess();
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

	public static class CopyDataContext extends ParserRuleContext {
		public Token name;
		public Token source;
		public Token to;
		public Token value;
		public ExpressionContext condition;
		public Token writeThreadCount;
		public Token fetchBatchSize;
		public Token writeBatchSize;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> NON_ZERO_DIGIT() { return getTokens(RavenParser.NON_ZERO_DIGIT); }
		public TerminalNode NON_ZERO_DIGIT(int i) {
			return getToken(RavenParser.NON_ZERO_DIGIT, i);
		}
		public CopyDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_copyData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterCopyData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitCopyData(this);
		}
	}

	public final CopyDataContext copyData() throws RecognitionException {
		CopyDataContext _localctx = new CopyDataContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_copyData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(103);
			match(T__6);
			setState(104);
			match(T__7);
			setState(105);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(106);
			match(T__8);
			setState(107);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(108);
			match(T__9);
			setState(109);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(110);
			match(T__10);
			setState(111);
			match(T__1);
			setState(112);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(113);
			match(T__2);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(114);
				match(T__11);
				setState(115);
				((CopyDataContext)_localctx).condition = expression();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(121);
				match(T__12);
				setState(122);
				((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(128);
				match(T__13);
				setState(129);
				((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(135);
				match(T__14);
				setState(136);
				((CopyDataContext)_localctx).writeBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class TransformContext extends ParserRuleContext {
		public Token name;
		public Token on;
		public Token STRING;
		public List<Token> value = new ArrayList<Token>();
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TransformContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transform; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterTransform(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitTransform(this);
		}
	}

	public final TransformContext transform() throws RecognitionException {
		TransformContext _localctx = new TransformContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_transform);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__15);
			setState(143);
			match(T__7);
			setState(144);
			((TransformContext)_localctx).name = match(STRING);
			setState(145);
			match(T__16);
			setState(146);
			((TransformContext)_localctx).on = match(STRING);
			setState(147);
			match(T__10);
			setState(148);
			match(T__1);
			setState(149);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(150);
			match(T__2);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(151);
				match(T__11);
				setState(152);
				((TransformContext)_localctx).condition = expression();
				}
				}
				setState(157);
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

	public static class LoadCsvContext extends ParserRuleContext {
		public Token name;
		public Token pid;
		public Token source;
		public Token to;
		public Token delim;
		public Token limit;
		public Token value;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LoadCsvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadCsv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterLoadCsv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitLoadCsv(this);
		}
	}

	public final LoadCsvContext loadCsv() throws RecognitionException {
		LoadCsvContext _localctx = new LoadCsvContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_loadCsv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__17);
			setState(159);
			match(T__7);
			setState(160);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(161);
			match(T__18);
			setState(162);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(163);
			match(T__8);
			setState(164);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(165);
			match(T__9);
			setState(166);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(167);
			match(T__19);
			setState(168);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(169);
			match(T__20);
			setState(170);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(171);
			match(T__10);
			setState(172);
			match(T__1);
			setState(173);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(174);
			match(T__2);
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(175);
				match(T__11);
				setState(176);
				((LoadCsvContext)_localctx).condition = expression();
				}
				}
				setState(181);
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

	public static class AbortContext extends ParserRuleContext {
		public Token name;
		public Token value;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AbortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterAbort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitAbort(this);
		}
	}

	public final AbortContext abort() throws RecognitionException {
		AbortContext _localctx = new AbortContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_abort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(T__21);
			setState(183);
			match(T__7);
			setState(184);
			((AbortContext)_localctx).name = match(STRING);
			setState(185);
			match(T__1);
			setState(186);
			((AbortContext)_localctx).value = match(STRING);
			setState(187);
			match(T__2);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(188);
				match(T__11);
				setState(189);
				((AbortContext)_localctx).condition = expression();
				}
				}
				setState(194);
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

	public static class CallProcessContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public Token source;
		public Token datasource;
		public Token value;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CallProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callProcess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterCallProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitCallProcess(this);
		}
	}

	public final CallProcessContext callProcess() throws RecognitionException {
		CallProcessContext _localctx = new CallProcessContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_callProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(T__22);
			setState(196);
			match(T__7);
			setState(197);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(198);
			match(T__23);
			setState(199);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(200);
			match(T__24);
			setState(201);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(202);
			match(T__10);
			setState(203);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(204);
			match(T__25);
			setState(205);
			match(T__1);
			setState(206);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(207);
			match(T__2);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(208);
				match(T__11);
				setState(209);
				((CallProcessContext)_localctx).condition = expression();
				}
				}
				setState(214);
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

	public static class ForkProcessContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public Token source;
		public Token datasource;
		public Token value;
		public Token forkBatchSize;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForkProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forkProcess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterForkProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitForkProcess(this);
		}
	}

	public final ForkProcessContext forkProcess() throws RecognitionException {
		ForkProcessContext _localctx = new ForkProcessContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_forkProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__26);
			setState(216);
			match(T__7);
			setState(217);
			((ForkProcessContext)_localctx).name = match(STRING);
			setState(218);
			match(T__23);
			setState(219);
			((ForkProcessContext)_localctx).target = match(STRING);
			setState(220);
			match(T__24);
			setState(221);
			((ForkProcessContext)_localctx).source = match(STRING);
			setState(222);
			match(T__10);
			setState(223);
			((ForkProcessContext)_localctx).datasource = match(STRING);
			setState(224);
			match(T__25);
			setState(225);
			match(T__1);
			setState(226);
			((ForkProcessContext)_localctx).value = match(STRING);
			setState(227);
			match(T__2);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__27) {
				{
				{
				setState(228);
				match(T__27);
				setState(229);
				((ForkProcessContext)_localctx).forkBatchSize = match(STRING);
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(235);
				match(T__11);
				setState(236);
				((ForkProcessContext)_localctx).condition = expression();
				}
				}
				setState(241);
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

	public static class SpawnProcessContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public Token source;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SpawnProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spawnProcess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterSpawnProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitSpawnProcess(this);
		}
	}

	public final SpawnProcessContext spawnProcess() throws RecognitionException {
		SpawnProcessContext _localctx = new SpawnProcessContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_spawnProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(T__28);
			setState(243);
			match(T__7);
			setState(244);
			((SpawnProcessContext)_localctx).name = match(STRING);
			setState(245);
			match(T__23);
			setState(246);
			((SpawnProcessContext)_localctx).target = match(STRING);
			setState(247);
			match(T__24);
			setState(248);
			((SpawnProcessContext)_localctx).source = match(STRING);
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(249);
				match(T__11);
				setState(250);
				((SpawnProcessContext)_localctx).condition = expression();
				}
				}
				setState(255);
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

	public static class DogLegContext extends ParserRuleContext {
		public Token name;
		public Token inheritContext;
		public StartProcessContext startProcess;
		public List<StartProcessContext> processList = new ArrayList<StartProcessContext>();
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public StartProcessContext startProcess() {
			return getRuleContext(StartProcessContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DogLegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dogLeg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterDogLeg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitDogLeg(this);
		}
	}

	public final DogLegContext dogLeg() throws RecognitionException {
		DogLegContext _localctx = new DogLegContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dogLeg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__29);
			setState(257);
			match(T__7);
			setState(258);
			((DogLegContext)_localctx).name = match(STRING);
			setState(259);
			match(T__30);
			setState(260);
			((DogLegContext)_localctx).inheritContext = match(STRING);
			setState(261);
			match(T__10);
			setState(262);
			match(T__1);
			setState(263);
			((DogLegContext)_localctx).startProcess = startProcess();
			((DogLegContext)_localctx).processList.add(((DogLegContext)_localctx).startProcess);
			setState(264);
			match(T__2);
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(265);
				match(T__11);
				setState(266);
				((DogLegContext)_localctx).condition = expression();
				}
				}
				setState(271);
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

	public static class StartProcessContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public StartProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startProcess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterStartProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitStartProcess(this);
		}
	}

	public final StartProcessContext startProcess() throws RecognitionException {
		StartProcessContext _localctx = new StartProcessContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_startProcess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(T__31);
			setState(273);
			((StartProcessContext)_localctx).name = match(STRING);
			setState(274);
			match(T__32);
			setState(275);
			((StartProcessContext)_localctx).target = match(STRING);
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

	public static class AssignContext extends ParserRuleContext {
		public Token name;
		public Token source;
		public Token value;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitAssign(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__33);
			setState(278);
			match(T__7);
			setState(279);
			((AssignContext)_localctx).name = match(STRING);
			setState(280);
			match(T__34);
			setState(281);
			((AssignContext)_localctx).source = match(STRING);
			setState(282);
			match(T__10);
			setState(283);
			match(T__1);
			setState(284);
			((AssignContext)_localctx).value = match(STRING);
			setState(285);
			match(T__2);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(286);
				match(T__11);
				setState(287);
				((AssignContext)_localctx).condition = expression();
				}
				}
				setState(292);
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

	public static class DropFileContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DropFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterDropFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitDropFile(this);
		}
	}

	public final DropFileContext dropFile() throws RecognitionException {
		DropFileContext _localctx = new DropFileContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dropFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__35);
			setState(294);
			match(T__7);
			setState(295);
			((DropFileContext)_localctx).name = match(STRING);
			setState(296);
			match(T__36);
			setState(297);
			((DropFileContext)_localctx).target = match(STRING);
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(298);
				match(T__11);
				setState(299);
				((DropFileContext)_localctx).condition = expression();
				}
				}
				setState(304);
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

	public static class RestApiContext extends ParserRuleContext {
		public Token name;
		public Token source;
		public Token url;
		public Token method;
		public JsonContext headers;
		public JsonContext params;
		public Token bodyType;
		public RestPartContext restPart;
		public List<RestPartContext> value = new ArrayList<RestPartContext>();
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<JsonContext> json() {
			return getRuleContexts(JsonContext.class);
		}
		public JsonContext json(int i) {
			return getRuleContext(JsonContext.class,i);
		}
		public List<RestPartContext> restPart() {
			return getRuleContexts(RestPartContext.class);
		}
		public RestPartContext restPart(int i) {
			return getRuleContext(RestPartContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RestApiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restApi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterRestApi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitRestApi(this);
		}
	}

	public final RestApiContext restApi() throws RecognitionException {
		RestApiContext _localctx = new RestApiContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_restApi);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(T__37);
			setState(306);
			match(T__7);
			setState(307);
			((RestApiContext)_localctx).name = match(STRING);
			setState(308);
			match(T__34);
			setState(309);
			((RestApiContext)_localctx).source = match(STRING);
			setState(310);
			match(T__10);
			setState(311);
			match(T__38);
			setState(312);
			((RestApiContext)_localctx).url = match(STRING);
			setState(313);
			match(T__39);
			setState(314);
			((RestApiContext)_localctx).method = match(STRING);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(315);
				match(T__40);
				setState(316);
				((RestApiContext)_localctx).headers = json();
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__41) {
				{
				{
				setState(322);
				match(T__41);
				setState(323);
				((RestApiContext)_localctx).params = json();
				}
				}
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(329);
			match(T__42);
			setState(330);
			match(T__1);
			setState(331);
			((RestApiContext)_localctx).bodyType = match(STRING);
			setState(332);
			match(T__2);
			}
			setState(334);
			match(T__1);
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__43) {
				{
				{
				setState(335);
				((RestApiContext)_localctx).restPart = restPart();
				((RestApiContext)_localctx).value.add(((RestApiContext)_localctx).restPart);
				}
				}
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(341);
			match(T__2);
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(342);
				match(T__11);
				setState(343);
				((RestApiContext)_localctx).condition = expression();
				}
				}
				setState(348);
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

	public static class RestPartContext extends ParserRuleContext {
		public Token partName;
		public Token partData;
		public Token type;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public RestPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterRestPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitRestPart(this);
		}
	}

	public final RestPartContext restPart() throws RecognitionException {
		RestPartContext _localctx = new RestPartContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_restPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(T__43);
			setState(350);
			match(T__7);
			setState(351);
			((RestPartContext)_localctx).partName = match(STRING);
			setState(352);
			match(T__19);
			setState(353);
			((RestPartContext)_localctx).partData = match(STRING);
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__44) {
				{
				{
				setState(354);
				match(T__44);
				setState(355);
				((RestPartContext)_localctx).type = match(STRING);
				}
				}
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(361);
			match(T__2);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Token lhs;
		public Token operator;
		public Token rhs;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public TerminalNode Operator() { return getToken(RavenParser.Operator, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(T__45);
			{
			setState(364);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(365);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(366);
			((ExpressionContext)_localctx).rhs = match(STRING);
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

	public static class JsonContext extends ParserRuleContext {
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ArrContext arr() {
			return getRuleContext(ArrContext.class,0);
		}
		public JsonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterJson(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitJson(this);
		}
	}

	public final JsonContext json() throws RecognitionException {
		JsonContext _localctx = new JsonContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_json);
		try {
			setState(370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(368);
				obj();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				arr();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ObjContext extends ParserRuleContext {
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_obj);
		int _la;
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				match(T__1);
				setState(373);
				pair();
				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__46) {
					{
					{
					setState(374);
					match(T__46);
					setState(375);
					pair();
					}
					}
					setState(380);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(381);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				match(T__1);
				setState(384);
				match(T__2);
				}
				break;
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

	public static class PairContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(RavenParser.STRING, 0); }
		public JValueContext jValue() {
			return getRuleContext(JValueContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(STRING);
			setState(388);
			match(T__47);
			setState(389);
			jValue();
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

	public static class ArrContext extends ParserRuleContext {
		public List<JValueContext> jValue() {
			return getRuleContexts(JValueContext.class);
		}
		public JValueContext jValue(int i) {
			return getRuleContext(JValueContext.class,i);
		}
		public ArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterArr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitArr(this);
		}
	}

	public final ArrContext arr() throws RecognitionException {
		ArrContext _localctx = new ArrContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arr);
		int _la;
		try {
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(T__48);
				setState(392);
				jValue();
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__46) {
					{
					{
					setState(393);
					match(T__46);
					setState(394);
					jValue();
					}
					}
					setState(399);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(400);
				match(T__49);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				match(T__48);
				setState(403);
				match(T__49);
				}
				break;
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

	public static class JValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(RavenParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(RavenParser.NUMBER, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ArrContext arr() {
			return getRuleContext(ArrContext.class,0);
		}
		public JValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterJValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitJValue(this);
		}
	}

	public final JValueContext jValue() throws RecognitionException {
		JValueContext _localctx = new JValueContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_jValue);
		try {
			setState(413);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(407);
				match(NUMBER);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(408);
				obj();
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 4);
				{
				setState(409);
				arr();
				}
				break;
			case T__50:
				enterOuterAlt(_localctx, 5);
				{
				setState(410);
				match(T__50);
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 6);
				{
				setState(411);
				match(T__51);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 7);
				{
				setState(412);
				match(T__52);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u01a2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3@\n\3\f\3\16"+
		"\3C\13\3\3\3\3\3\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\3\5\3\5\3"+
		"\5\7\5T\n\5\f\5\16\5W\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6f\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\bw\n\b\f\b\16\bz\13\b\3\b\3\b\7\b~\n\b\f\b\16\b\u0081\13\b"+
		"\3\b\3\b\7\b\u0085\n\b\f\b\16\b\u0088\13\b\3\b\3\b\7\b\u008c\n\b\f\b\16"+
		"\b\u008f\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009c\n"+
		"\t\f\t\16\t\u009f\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b4\n\n\f\n\16\n\u00b7\13\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c1\n\13\f\13\16\13\u00c4\13"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d5"+
		"\n\f\f\f\16\f\u00d8\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\7\r\u00e9\n\r\f\r\16\r\u00ec\13\r\3\r\3\r\7\r\u00f0\n\r"+
		"\f\r\16\r\u00f3\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16"+
		"\u00fe\n\16\f\16\16\16\u0101\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\7\17\u010e\n\17\f\17\16\17\u0111\13\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\7\21\u0123\n\21\f\21\16\21\u0126\13\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\7\22\u012f\n\22\f\22\16\22\u0132\13\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0140\n\23\f\23\16\23\u0143\13"+
		"\23\3\23\3\23\7\23\u0147\n\23\f\23\16\23\u014a\13\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\7\23\u0153\n\23\f\23\16\23\u0156\13\23\3\23\3\23\3"+
		"\23\7\23\u015b\n\23\f\23\16\23\u015e\13\23\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\7\24\u0167\n\24\f\24\16\24\u016a\13\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\5\26\u0175\n\26\3\27\3\27\3\27\3\27\7\27\u017b"+
		"\n\27\f\27\16\27\u017e\13\27\3\27\3\27\3\27\3\27\5\27\u0184\n\27\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u018e\n\31\f\31\16\31\u0191\13"+
		"\31\3\31\3\31\3\31\3\31\5\31\u0197\n\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\5\32\u01a0\n\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\2\2\2\u01b3\2\64\3\2\2\2\4<\3\2\2\2\6F\3\2\2\2\bP\3"+
		"\2\2\2\ne\3\2\2\2\fg\3\2\2\2\16i\3\2\2\2\20\u0090\3\2\2\2\22\u00a0\3\2"+
		"\2\2\24\u00b8\3\2\2\2\26\u00c5\3\2\2\2\30\u00d9\3\2\2\2\32\u00f4\3\2\2"+
		"\2\34\u0102\3\2\2\2\36\u0112\3\2\2\2 \u0117\3\2\2\2\"\u0127\3\2\2\2$\u0133"+
		"\3\2\2\2&\u015f\3\2\2\2(\u016d\3\2\2\2*\u0174\3\2\2\2,\u0183\3\2\2\2."+
		"\u0185\3\2\2\2\60\u0196\3\2\2\2\62\u019f\3\2\2\2\64\65\7\3\2\2\65\66\7"+
		"9\2\2\66\67\7\4\2\2\678\5\4\3\289\5\b\5\29:\5\6\4\2:;\7\5\2\2;\3\3\2\2"+
		"\2<=\7\6\2\2=A\7\4\2\2>@\5\n\6\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2"+
		"\2BD\3\2\2\2CA\3\2\2\2DE\7\5\2\2E\5\3\2\2\2FG\7\7\2\2GK\7\4\2\2HJ\5\n"+
		"\6\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7\5"+
		"\2\2O\7\3\2\2\2PQ\7\b\2\2QU\7\4\2\2RT\5\n\6\2SR\3\2\2\2TW\3\2\2\2US\3"+
		"\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2XY\7\5\2\2Y\t\3\2\2\2Zf\5\24\13\2"+
		"[f\5 \21\2\\f\5\26\f\2]f\5\30\r\2^f\5\32\16\2_f\5\34\17\2`f\5\16\b\2a"+
		"f\5\"\22\2bf\5\22\n\2cf\5$\23\2df\5\20\t\2eZ\3\2\2\2e[\3\2\2\2e\\\3\2"+
		"\2\2e]\3\2\2\2e^\3\2\2\2e_\3\2\2\2e`\3\2\2\2ea\3\2\2\2eb\3\2\2\2ec\3\2"+
		"\2\2ed\3\2\2\2f\13\3\2\2\2gh\5\36\20\2h\r\3\2\2\2ij\7\t\2\2jk\7\n\2\2"+
		"kl\79\2\2lm\7\13\2\2mn\79\2\2no\7\f\2\2op\79\2\2pq\7\r\2\2qr\7\4\2\2r"+
		"s\79\2\2sx\7\5\2\2tu\7\16\2\2uw\5(\25\2vt\3\2\2\2wz\3\2\2\2xv\3\2\2\2"+
		"xy\3\2\2\2y\177\3\2\2\2zx\3\2\2\2{|\7\17\2\2|~\78\2\2}{\3\2\2\2~\u0081"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0086\3\2\2\2\u0081\177"+
		"\3\2\2\2\u0082\u0083\7\20\2\2\u0083\u0085\78\2\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008d\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0089\u008a\7\21\2\2\u008a\u008c\78\2\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\17\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7\22\2\2\u0091\u0092"+
		"\7\n\2\2\u0092\u0093\79\2\2\u0093\u0094\7\23\2\2\u0094\u0095\79\2\2\u0095"+
		"\u0096\7\r\2\2\u0096\u0097\7\4\2\2\u0097\u0098\79\2\2\u0098\u009d\7\5"+
		"\2\2\u0099\u009a\7\16\2\2\u009a\u009c\5(\25\2\u009b\u0099\3\2\2\2\u009c"+
		"\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\21\3\2\2"+
		"\2\u009f\u009d\3\2\2\2\u00a0\u00a1\7\24\2\2\u00a1\u00a2\7\n\2\2\u00a2"+
		"\u00a3\79\2\2\u00a3\u00a4\7\25\2\2\u00a4\u00a5\79\2\2\u00a5\u00a6\7\13"+
		"\2\2\u00a6\u00a7\79\2\2\u00a7\u00a8\7\f\2\2\u00a8\u00a9\79\2\2\u00a9\u00aa"+
		"\7\26\2\2\u00aa\u00ab\79\2\2\u00ab\u00ac\7\27\2\2\u00ac\u00ad\79\2\2\u00ad"+
		"\u00ae\7\r\2\2\u00ae\u00af\7\4\2\2\u00af\u00b0\79\2\2\u00b0\u00b5\7\5"+
		"\2\2\u00b1\u00b2\7\16\2\2\u00b2\u00b4\5(\25\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\23\3\2\2"+
		"\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\7\30\2\2\u00b9\u00ba\7\n\2\2\u00ba"+
		"\u00bb\79\2\2\u00bb\u00bc\7\4\2\2\u00bc\u00bd\79\2\2\u00bd\u00c2\7\5\2"+
		"\2\u00be\u00bf\7\16\2\2\u00bf\u00c1\5(\25\2\u00c0\u00be\3\2\2\2\u00c1"+
		"\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\25\3\2\2"+
		"\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\31\2\2\u00c6\u00c7\7\n\2\2\u00c7"+
		"\u00c8\79\2\2\u00c8\u00c9\7\32\2\2\u00c9\u00ca\79\2\2\u00ca\u00cb\7\33"+
		"\2\2\u00cb\u00cc\79\2\2\u00cc\u00cd\7\r\2\2\u00cd\u00ce\79\2\2\u00ce\u00cf"+
		"\7\34\2\2\u00cf\u00d0\7\4\2\2\u00d0\u00d1\79\2\2\u00d1\u00d6\7\5\2\2\u00d2"+
		"\u00d3\7\16\2\2\u00d3\u00d5\5(\25\2\u00d4\u00d2\3\2\2\2\u00d5\u00d8\3"+
		"\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\27\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d9\u00da\7\35\2\2\u00da\u00db\7\n\2\2\u00db\u00dc\7"+
		"9\2\2\u00dc\u00dd\7\32\2\2\u00dd\u00de\79\2\2\u00de\u00df\7\33\2\2\u00df"+
		"\u00e0\79\2\2\u00e0\u00e1\7\r\2\2\u00e1\u00e2\79\2\2\u00e2\u00e3\7\34"+
		"\2\2\u00e3\u00e4\7\4\2\2\u00e4\u00e5\79\2\2\u00e5\u00ea\7\5\2\2\u00e6"+
		"\u00e7\7\36\2\2\u00e7\u00e9\79\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2"+
		"\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f1\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\7\16\2\2\u00ee\u00f0\5(\25\2\u00ef\u00ed\3"+
		"\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\31\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7\37\2\2\u00f5\u00f6\7\n\2"+
		"\2\u00f6\u00f7\79\2\2\u00f7\u00f8\7\32\2\2\u00f8\u00f9\79\2\2\u00f9\u00fa"+
		"\7\33\2\2\u00fa\u00ff\79\2\2\u00fb\u00fc\7\16\2\2\u00fc\u00fe\5(\25\2"+
		"\u00fd\u00fb\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\33\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7 \2\2\u0103"+
		"\u0104\7\n\2\2\u0104\u0105\79\2\2\u0105\u0106\7!\2\2\u0106\u0107\79\2"+
		"\2\u0107\u0108\7\r\2\2\u0108\u0109\7\4\2\2\u0109\u010a\5\36\20\2\u010a"+
		"\u010f\7\5\2\2\u010b\u010c\7\16\2\2\u010c\u010e\5(\25\2\u010d\u010b\3"+
		"\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\35\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\"\2\2\u0113\u0114\79\2\2"+
		"\u0114\u0115\7#\2\2\u0115\u0116\79\2\2\u0116\37\3\2\2\2\u0117\u0118\7"+
		"$\2\2\u0118\u0119\7\n\2\2\u0119\u011a\79\2\2\u011a\u011b\7%\2\2\u011b"+
		"\u011c\79\2\2\u011c\u011d\7\r\2\2\u011d\u011e\7\4\2\2\u011e\u011f\79\2"+
		"\2\u011f\u0124\7\5\2\2\u0120\u0121\7\16\2\2\u0121\u0123\5(\25\2\u0122"+
		"\u0120\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125!\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7&\2\2\u0128\u0129"+
		"\7\n\2\2\u0129\u012a\79\2\2\u012a\u012b\7\'\2\2\u012b\u0130\79\2\2\u012c"+
		"\u012d\7\16\2\2\u012d\u012f\5(\25\2\u012e\u012c\3\2\2\2\u012f\u0132\3"+
		"\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131#\3\2\2\2\u0132\u0130"+
		"\3\2\2\2\u0133\u0134\7(\2\2\u0134\u0135\7\n\2\2\u0135\u0136\79\2\2\u0136"+
		"\u0137\7%\2\2\u0137\u0138\79\2\2\u0138\u0139\7\r\2\2\u0139\u013a\7)\2"+
		"\2\u013a\u013b\79\2\2\u013b\u013c\7*\2\2\u013c\u0141\79\2\2\u013d\u013e"+
		"\7+\2\2\u013e\u0140\5*\26\2\u013f\u013d\3\2\2\2\u0140\u0143\3\2\2\2\u0141"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0148\3\2\2\2\u0143\u0141\3\2"+
		"\2\2\u0144\u0145\7,\2\2\u0145\u0147\5*\26\2\u0146\u0144\3\2\2\2\u0147"+
		"\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014b\3\2"+
		"\2\2\u014a\u0148\3\2\2\2\u014b\u014c\7-\2\2\u014c\u014d\7\4\2\2\u014d"+
		"\u014e\79\2\2\u014e\u014f\7\5\2\2\u014f\u0150\3\2\2\2\u0150\u0154\7\4"+
		"\2\2\u0151\u0153\5&\24\2\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2\u0154"+
		"\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154\3\2"+
		"\2\2\u0157\u015c\7\5\2\2\u0158\u0159\7\16\2\2\u0159\u015b\5(\25\2\u015a"+
		"\u0158\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2"+
		"\2\2\u015d%\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0160\7.\2\2\u0160\u0161"+
		"\7\n\2\2\u0161\u0162\79\2\2\u0162\u0163\7\26\2\2\u0163\u0168\79\2\2\u0164"+
		"\u0165\7/\2\2\u0165\u0167\79\2\2\u0166\u0164\3\2\2\2\u0167\u016a\3\2\2"+
		"\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2\2\2\u016a\u0168"+
		"\3\2\2\2\u016b\u016c\7\5\2\2\u016c\'\3\2\2\2\u016d\u016e\7\60\2\2\u016e"+
		"\u016f\79\2\2\u016f\u0170\7;\2\2\u0170\u0171\79\2\2\u0171)\3\2\2\2\u0172"+
		"\u0175\5,\27\2\u0173\u0175\5\60\31\2\u0174\u0172\3\2\2\2\u0174\u0173\3"+
		"\2\2\2\u0175+\3\2\2\2\u0176\u0177\7\4\2\2\u0177\u017c\5.\30\2\u0178\u0179"+
		"\7\61\2\2\u0179\u017b\5.\30\2\u017a\u0178\3\2\2\2\u017b\u017e\3\2\2\2"+
		"\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c"+
		"\3\2\2\2\u017f\u0180\7\5\2\2\u0180\u0184\3\2\2\2\u0181\u0182\7\4\2\2\u0182"+
		"\u0184\7\5\2\2\u0183\u0176\3\2\2\2\u0183\u0181\3\2\2\2\u0184-\3\2\2\2"+
		"\u0185\u0186\79\2\2\u0186\u0187\7\62\2\2\u0187\u0188\5\62\32\2\u0188/"+
		"\3\2\2\2\u0189\u018a\7\63\2\2\u018a\u018f\5\62\32\2\u018b\u018c\7\61\2"+
		"\2\u018c\u018e\5\62\32\2\u018d\u018b\3\2\2\2\u018e\u0191\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u018f\3\2"+
		"\2\2\u0192\u0193\7\64\2\2\u0193\u0197\3\2\2\2\u0194\u0195\7\63\2\2\u0195"+
		"\u0197\7\64\2\2\u0196\u0189\3\2\2\2\u0196\u0194\3\2\2\2\u0197\61\3\2\2"+
		"\2\u0198\u01a0\79\2\2\u0199\u01a0\7?\2\2\u019a\u01a0\5,\27\2\u019b\u01a0"+
		"\5\60\31\2\u019c\u01a0\7\65\2\2\u019d\u01a0\7\66\2\2\u019e\u01a0\7\67"+
		"\2\2\u019f\u0198\3\2\2\2\u019f\u0199\3\2\2\2\u019f\u019a\3\2\2\2\u019f"+
		"\u019b\3\2\2\2\u019f\u019c\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u019e\3\2"+
		"\2\2\u01a0\63\3\2\2\2\37AKUex\177\u0086\u008d\u009d\u00b5\u00c2\u00d6"+
		"\u00ea\u00f1\u00ff\u010f\u0124\u0130\u0141\u0148\u0154\u015c\u0168\u0174"+
		"\u017c\u0183\u018f\u0196\u019f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}