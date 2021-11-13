// Generated from Raven.g4 by ANTLR 4.9.3

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
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

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
		T__52=53, T__53=54, NON_ZERO_DIGIT=55, STRING=56, CRLF=57, Operator=58, 
		WS=59, COMMENT=60, LINE_COMMENT=61, NUMBER=62;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_multitude = 5, RULE_copyData = 6, RULE_transform = 7, 
		RULE_loadCsv = 8, RULE_abort = 9, RULE_callProcess = 10, RULE_forkProcess = 11, 
		RULE_spawnProcess = 12, RULE_dogLeg = 13, RULE_startProcess = 14, RULE_assign = 15, 
		RULE_dropFile = 16, RULE_restApi = 17, RULE_restPart = 18, RULE_expression = 19, 
		RULE_json = 20, RULE_obj = 21, RULE_pair = 22, RULE_arr = 23, RULE_jValue = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "multitude", 
			"copyData", "transform", "loadCsv", "abort", "callProcess", "forkProcess", 
			"spawnProcess", "dogLeg", "startProcess", "assign", "dropFile", "restApi", 
			"restPart", "expression", "json", "obj", "pair", "arr", "jValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'multitude'", 
			"'as'", "'on'", "'using'", "'on-condition'", "'fielding'", "'copydata'", 
			"'from'", "'to'", "'with-fetch-batch-size'", "'with-write-batch-size'", 
			"'transform'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", "'abort'", 
			"'callprocess'", "'with-target'", "'from-file'", "'for-every'", "'forkprocess'", 
			"'watermark'", "'spawn'", "'dogleg'", "'use-parent-context'", "'start-process'", 
			"'with-file'", "'assign'", "'source'", "'dropfile'", "'in-path'", "'restapi'", 
			"'url'", "'method'", "'with headers'", "'with params'", "'with body type'", 
			"'{ part'", "'type as'", "'if'", "','", "':'", "'['", "']'", "'true'", 
			"'false'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NON_ZERO_DIGIT", "STRING", 
			"CRLF", "Operator", "WS", "COMMENT", "LINE_COMMENT", "NUMBER"
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__12) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__12) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__12) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
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
		public MultitudeContext multitude() {
			return getRuleContext(MultitudeContext.class,0);
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
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				{
				setState(88);
				abort();
				}
				break;
			case T__34:
				{
				setState(89);
				assign();
				}
				break;
			case T__23:
				{
				setState(90);
				callProcess();
				}
				break;
			case T__27:
				{
				setState(91);
				forkProcess();
				}
				break;
			case T__29:
				{
				setState(92);
				spawnProcess();
				}
				break;
			case T__30:
				{
				setState(93);
				dogLeg();
				}
				break;
			case T__12:
				{
				setState(94);
				copyData();
				}
				break;
			case T__36:
				{
				setState(95);
				dropFile();
				}
				break;
			case T__18:
				{
				setState(96);
				loadCsv();
				}
				break;
			case T__38:
				{
				setState(97);
				restApi();
				}
				break;
			case T__17:
				{
				setState(98);
				transform();
				}
				break;
			case T__6:
				{
				setState(99);
				multitude();
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

	public static class MultitudeContext extends ParserRuleContext {
		public Token name;
		public Token on;
		public ActionContext action;
		public List<ActionContext> actions = new ArrayList<ActionContext>();
		public ExpressionContext condition;
		public Token writeThreadCount;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
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
		public MultitudeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multitude; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterMultitude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitMultitude(this);
		}
	}

	public final MultitudeContext multitude() throws RecognitionException {
		MultitudeContext _localctx = new MultitudeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_multitude);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__6);
			setState(103);
			match(T__7);
			setState(104);
			((MultitudeContext)_localctx).name = match(STRING);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(105);
				match(T__8);
				setState(106);
				((MultitudeContext)_localctx).on = match(STRING);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			match(T__9);
			setState(113);
			match(T__1);
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__12) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(114);
				((MultitudeContext)_localctx).action = action();
				((MultitudeContext)_localctx).actions.add(((MultitudeContext)_localctx).action);
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
			match(T__2);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(121);
				match(T__10);
				setState(122);
				((MultitudeContext)_localctx).condition = expression();
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(128);
				match(T__11);
				setState(129);
				((MultitudeContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
				}
				}
				setState(134);
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
			setState(135);
			match(T__12);
			setState(136);
			match(T__7);
			setState(137);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(138);
			match(T__13);
			setState(139);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(140);
			match(T__14);
			setState(141);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(142);
			match(T__9);
			setState(143);
			match(T__1);
			setState(144);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(145);
			match(T__2);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(146);
				match(T__10);
				setState(147);
				((CopyDataContext)_localctx).condition = expression();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(153);
				match(T__11);
				setState(154);
				((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(160);
				match(T__15);
				setState(161);
				((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(167);
				match(T__16);
				setState(168);
				((CopyDataContext)_localctx).writeBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(173);
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
			setState(174);
			match(T__17);
			setState(175);
			match(T__7);
			setState(176);
			((TransformContext)_localctx).name = match(STRING);
			setState(177);
			match(T__8);
			setState(178);
			((TransformContext)_localctx).on = match(STRING);
			setState(179);
			match(T__9);
			setState(180);
			match(T__1);
			setState(181);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(182);
			match(T__2);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(183);
				match(T__10);
				setState(184);
				((TransformContext)_localctx).condition = expression();
				}
				}
				setState(189);
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
			setState(190);
			match(T__18);
			setState(191);
			match(T__7);
			setState(192);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(193);
			match(T__19);
			setState(194);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(195);
			match(T__13);
			setState(196);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(197);
			match(T__14);
			setState(198);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(199);
			match(T__20);
			setState(200);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(201);
			match(T__21);
			setState(202);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(203);
			match(T__9);
			setState(204);
			match(T__1);
			setState(205);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(206);
			match(T__2);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(207);
				match(T__10);
				setState(208);
				((LoadCsvContext)_localctx).condition = expression();
				}
				}
				setState(213);
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
			setState(214);
			match(T__22);
			setState(215);
			match(T__7);
			setState(216);
			((AbortContext)_localctx).name = match(STRING);
			setState(217);
			match(T__1);
			setState(218);
			((AbortContext)_localctx).value = match(STRING);
			setState(219);
			match(T__2);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(220);
				match(T__10);
				setState(221);
				((AbortContext)_localctx).condition = expression();
				}
				}
				setState(226);
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
			setState(227);
			match(T__23);
			setState(228);
			match(T__7);
			setState(229);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(230);
			match(T__24);
			setState(231);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(232);
			match(T__25);
			setState(233);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(234);
			match(T__9);
			setState(235);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(236);
			match(T__26);
			setState(237);
			match(T__1);
			setState(238);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(239);
			match(T__2);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(240);
				match(T__10);
				setState(241);
				((CallProcessContext)_localctx).condition = expression();
				}
				}
				setState(246);
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
			setState(247);
			match(T__27);
			setState(248);
			match(T__7);
			setState(249);
			((ForkProcessContext)_localctx).name = match(STRING);
			setState(250);
			match(T__24);
			setState(251);
			((ForkProcessContext)_localctx).target = match(STRING);
			setState(252);
			match(T__25);
			setState(253);
			((ForkProcessContext)_localctx).source = match(STRING);
			setState(254);
			match(T__9);
			setState(255);
			((ForkProcessContext)_localctx).datasource = match(STRING);
			setState(256);
			match(T__26);
			setState(257);
			match(T__1);
			setState(258);
			((ForkProcessContext)_localctx).value = match(STRING);
			setState(259);
			match(T__2);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(260);
				match(T__28);
				setState(261);
				((ForkProcessContext)_localctx).forkBatchSize = match(STRING);
				}
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(267);
				match(T__10);
				setState(268);
				((ForkProcessContext)_localctx).condition = expression();
				}
				}
				setState(273);
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
			setState(274);
			match(T__29);
			setState(275);
			match(T__7);
			setState(276);
			((SpawnProcessContext)_localctx).name = match(STRING);
			setState(277);
			match(T__24);
			setState(278);
			((SpawnProcessContext)_localctx).target = match(STRING);
			setState(279);
			match(T__25);
			setState(280);
			((SpawnProcessContext)_localctx).source = match(STRING);
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(281);
				match(T__10);
				setState(282);
				((SpawnProcessContext)_localctx).condition = expression();
				}
				}
				setState(287);
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
			setState(288);
			match(T__30);
			setState(289);
			match(T__7);
			setState(290);
			((DogLegContext)_localctx).name = match(STRING);
			setState(291);
			match(T__31);
			setState(292);
			((DogLegContext)_localctx).inheritContext = match(STRING);
			setState(293);
			match(T__9);
			setState(294);
			match(T__1);
			setState(295);
			((DogLegContext)_localctx).startProcess = startProcess();
			((DogLegContext)_localctx).processList.add(((DogLegContext)_localctx).startProcess);
			setState(296);
			match(T__2);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(297);
				match(T__10);
				setState(298);
				((DogLegContext)_localctx).condition = expression();
				}
				}
				setState(303);
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
			setState(304);
			match(T__32);
			setState(305);
			((StartProcessContext)_localctx).name = match(STRING);
			setState(306);
			match(T__33);
			setState(307);
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
			setState(309);
			match(T__34);
			setState(310);
			match(T__7);
			setState(311);
			((AssignContext)_localctx).name = match(STRING);
			setState(312);
			match(T__35);
			setState(313);
			((AssignContext)_localctx).source = match(STRING);
			setState(314);
			match(T__9);
			setState(315);
			match(T__1);
			setState(316);
			((AssignContext)_localctx).value = match(STRING);
			setState(317);
			match(T__2);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(318);
				match(T__10);
				setState(319);
				((AssignContext)_localctx).condition = expression();
				}
				}
				setState(324);
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
			setState(325);
			match(T__36);
			setState(326);
			match(T__7);
			setState(327);
			((DropFileContext)_localctx).name = match(STRING);
			setState(328);
			match(T__37);
			setState(329);
			((DropFileContext)_localctx).target = match(STRING);
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(330);
				match(T__10);
				setState(331);
				((DropFileContext)_localctx).condition = expression();
				}
				}
				setState(336);
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
			setState(337);
			match(T__38);
			setState(338);
			match(T__7);
			setState(339);
			((RestApiContext)_localctx).name = match(STRING);
			setState(340);
			match(T__35);
			setState(341);
			((RestApiContext)_localctx).source = match(STRING);
			setState(342);
			match(T__9);
			setState(343);
			match(T__39);
			setState(344);
			((RestApiContext)_localctx).url = match(STRING);
			setState(345);
			match(T__40);
			setState(346);
			((RestApiContext)_localctx).method = match(STRING);
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__41) {
				{
				{
				setState(347);
				match(T__41);
				setState(348);
				((RestApiContext)_localctx).headers = json();
				}
				}
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(354);
				match(T__42);
				setState(355);
				((RestApiContext)_localctx).params = json();
				}
				}
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(361);
			match(T__43);
			setState(362);
			match(T__1);
			setState(363);
			((RestApiContext)_localctx).bodyType = match(STRING);
			setState(364);
			match(T__2);
			}
			setState(366);
			match(T__1);
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__44) {
				{
				{
				setState(367);
				((RestApiContext)_localctx).restPart = restPart();
				((RestApiContext)_localctx).value.add(((RestApiContext)_localctx).restPart);
				}
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(373);
			match(T__2);
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(374);
				match(T__10);
				setState(375);
				((RestApiContext)_localctx).condition = expression();
				}
				}
				setState(380);
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
			setState(381);
			match(T__44);
			setState(382);
			match(T__7);
			setState(383);
			((RestPartContext)_localctx).partName = match(STRING);
			setState(384);
			match(T__20);
			setState(385);
			((RestPartContext)_localctx).partData = match(STRING);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__45) {
				{
				{
				setState(386);
				match(T__45);
				setState(387);
				((RestPartContext)_localctx).type = match(STRING);
				}
				}
				setState(392);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(393);
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
			setState(395);
			match(T__46);
			{
			setState(396);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(397);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(398);
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
			setState(402);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				obj();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(401);
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
			setState(417);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(404);
				match(T__1);
				setState(405);
				pair();
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__47) {
					{
					{
					setState(406);
					match(T__47);
					setState(407);
					pair();
					}
					}
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(413);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(415);
				match(T__1);
				setState(416);
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
			setState(419);
			match(STRING);
			setState(420);
			match(T__48);
			setState(421);
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
			setState(436);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(423);
				match(T__49);
				setState(424);
				jValue();
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__47) {
					{
					{
					setState(425);
					match(T__47);
					setState(426);
					jValue();
					}
					}
					setState(431);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(432);
				match(T__50);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(434);
				match(T__49);
				setState(435);
				match(T__50);
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
			setState(445);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(439);
				match(NUMBER);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(440);
				obj();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 4);
				{
				setState(441);
				arr();
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 5);
				{
				setState(442);
				match(T__51);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 6);
				{
				setState(443);
				match(T__52);
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 7);
				{
				setState(444);
				match(T__53);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u01c2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3@\n\3\f\3\16"+
		"\3C\13\3\3\3\3\3\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\3\5\3\5\3"+
		"\5\7\5T\n\5\f\5\16\5W\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6g\n\6\3\7\3\7\3\7\3\7\3\7\7\7n\n\7\f\7\16\7q\13\7\3\7"+
		"\3\7\3\7\7\7v\n\7\f\7\16\7y\13\7\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13"+
		"\7\3\7\3\7\7\7\u0085\n\7\f\7\16\7\u0088\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0097\n\b\f\b\16\b\u009a\13\b\3\b\3\b\7"+
		"\b\u009e\n\b\f\b\16\b\u00a1\13\b\3\b\3\b\7\b\u00a5\n\b\f\b\16\b\u00a8"+
		"\13\b\3\b\3\b\7\b\u00ac\n\b\f\b\16\b\u00af\13\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\7\t\u00bc\n\t\f\t\16\t\u00bf\13\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00d4"+
		"\n\n\f\n\16\n\u00d7\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u00e1\n\13\f\13\16\13\u00e4\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00f5\n\f\f\f\16\f\u00f8\13\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0109\n\r\f\r\16"+
		"\r\u010c\13\r\3\r\3\r\7\r\u0110\n\r\f\r\16\r\u0113\13\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u011e\n\16\f\16\16\16\u0121\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u012e\n\17"+
		"\f\17\16\17\u0131\13\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0143\n\21\f\21\16\21\u0146\13"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u014f\n\22\f\22\16\22\u0152"+
		"\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u0160\n\23\f\23\16\23\u0163\13\23\3\23\3\23\7\23\u0167\n\23\f\23\16\23"+
		"\u016a\13\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0173\n\23\f\23\16"+
		"\23\u0176\13\23\3\23\3\23\3\23\7\23\u017b\n\23\f\23\16\23\u017e\13\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0187\n\24\f\24\16\24\u018a\13"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\5\26\u0195\n\26\3\27"+
		"\3\27\3\27\3\27\7\27\u019b\n\27\f\27\16\27\u019e\13\27\3\27\3\27\3\27"+
		"\3\27\5\27\u01a4\n\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u01ae"+
		"\n\31\f\31\16\31\u01b1\13\31\3\31\3\31\3\31\3\31\5\31\u01b7\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u01c0\n\32\3\32\2\2\33\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\2\2\u01d8\2\64\3\2\2\2\4"+
		"<\3\2\2\2\6F\3\2\2\2\bP\3\2\2\2\nf\3\2\2\2\fh\3\2\2\2\16\u0089\3\2\2\2"+
		"\20\u00b0\3\2\2\2\22\u00c0\3\2\2\2\24\u00d8\3\2\2\2\26\u00e5\3\2\2\2\30"+
		"\u00f9\3\2\2\2\32\u0114\3\2\2\2\34\u0122\3\2\2\2\36\u0132\3\2\2\2 \u0137"+
		"\3\2\2\2\"\u0147\3\2\2\2$\u0153\3\2\2\2&\u017f\3\2\2\2(\u018d\3\2\2\2"+
		"*\u0194\3\2\2\2,\u01a3\3\2\2\2.\u01a5\3\2\2\2\60\u01b6\3\2\2\2\62\u01bf"+
		"\3\2\2\2\64\65\7\3\2\2\65\66\7:\2\2\66\67\7\4\2\2\678\5\4\3\289\5\b\5"+
		"\29:\5\6\4\2:;\7\5\2\2;\3\3\2\2\2<=\7\6\2\2=A\7\4\2\2>@\5\n\6\2?>\3\2"+
		"\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\5\2\2E\5\3"+
		"\2\2\2FG\7\7\2\2GK\7\4\2\2HJ\5\n\6\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3"+
		"\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7\5\2\2O\7\3\2\2\2PQ\7\b\2\2QU\7\4\2\2RT"+
		"\5\n\6\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2X"+
		"Y\7\5\2\2Y\t\3\2\2\2Zg\5\24\13\2[g\5 \21\2\\g\5\26\f\2]g\5\30\r\2^g\5"+
		"\32\16\2_g\5\34\17\2`g\5\16\b\2ag\5\"\22\2bg\5\22\n\2cg\5$\23\2dg\5\20"+
		"\t\2eg\5\f\7\2fZ\3\2\2\2f[\3\2\2\2f\\\3\2\2\2f]\3\2\2\2f^\3\2\2\2f_\3"+
		"\2\2\2f`\3\2\2\2fa\3\2\2\2fb\3\2\2\2fc\3\2\2\2fd\3\2\2\2fe\3\2\2\2g\13"+
		"\3\2\2\2hi\7\t\2\2ij\7\n\2\2jo\7:\2\2kl\7\13\2\2ln\7:\2\2mk\3\2\2\2nq"+
		"\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7\f\2\2sw\7\4\2\2t"+
		"v\5\n\6\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2"+
		"z\177\7\5\2\2{|\7\r\2\2|~\5(\25\2}{\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0086\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083"+
		"\7\16\2\2\u0083\u0085\79\2\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2\u0086"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\r\3\2\2\2\u0088\u0086\3\2\2\2"+
		"\u0089\u008a\7\17\2\2\u008a\u008b\7\n\2\2\u008b\u008c\7:\2\2\u008c\u008d"+
		"\7\20\2\2\u008d\u008e\7:\2\2\u008e\u008f\7\21\2\2\u008f\u0090\7:\2\2\u0090"+
		"\u0091\7\f\2\2\u0091\u0092\7\4\2\2\u0092\u0093\7:\2\2\u0093\u0098\7\5"+
		"\2\2\u0094\u0095\7\r\2\2\u0095\u0097\5(\25\2\u0096\u0094\3\2\2\2\u0097"+
		"\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009f\3\2"+
		"\2\2\u009a\u0098\3\2\2\2\u009b\u009c\7\16\2\2\u009c\u009e\79\2\2\u009d"+
		"\u009b\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\u00a6\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\7\22\2\2\u00a3"+
		"\u00a5\79\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00ad\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00aa\7\23\2\2\u00aa\u00ac\79\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00af\3\2"+
		"\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\17\3\2\2\2\u00af\u00ad"+
		"\3\2\2\2\u00b0\u00b1\7\24\2\2\u00b1\u00b2\7\n\2\2\u00b2\u00b3\7:\2\2\u00b3"+
		"\u00b4\7\13\2\2\u00b4\u00b5\7:\2\2\u00b5\u00b6\7\f\2\2\u00b6\u00b7\7\4"+
		"\2\2\u00b7\u00b8\7:\2\2\u00b8\u00bd\7\5\2\2\u00b9\u00ba\7\r\2\2\u00ba"+
		"\u00bc\5(\25\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\21\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1"+
		"\7\25\2\2\u00c1\u00c2\7\n\2\2\u00c2\u00c3\7:\2\2\u00c3\u00c4\7\26\2\2"+
		"\u00c4\u00c5\7:\2\2\u00c5\u00c6\7\20\2\2\u00c6\u00c7\7:\2\2\u00c7\u00c8"+
		"\7\21\2\2\u00c8\u00c9\7:\2\2\u00c9\u00ca\7\27\2\2\u00ca\u00cb\7:\2\2\u00cb"+
		"\u00cc\7\30\2\2\u00cc\u00cd\7:\2\2\u00cd\u00ce\7\f\2\2\u00ce\u00cf\7\4"+
		"\2\2\u00cf\u00d0\7:\2\2\u00d0\u00d5\7\5\2\2\u00d1\u00d2\7\r\2\2\u00d2"+
		"\u00d4\5(\25\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\23\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9"+
		"\7\31\2\2\u00d9\u00da\7\n\2\2\u00da\u00db\7:\2\2\u00db\u00dc\7\4\2\2\u00dc"+
		"\u00dd\7:\2\2\u00dd\u00e2\7\5\2\2\u00de\u00df\7\r\2\2\u00df\u00e1\5(\25"+
		"\2\u00e0\u00de\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3\25\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7\32\2\2\u00e6"+
		"\u00e7\7\n\2\2\u00e7\u00e8\7:\2\2\u00e8\u00e9\7\33\2\2\u00e9\u00ea\7:"+
		"\2\2\u00ea\u00eb\7\34\2\2\u00eb\u00ec\7:\2\2\u00ec\u00ed\7\f\2\2\u00ed"+
		"\u00ee\7:\2\2\u00ee\u00ef\7\35\2\2\u00ef\u00f0\7\4\2\2\u00f0\u00f1\7:"+
		"\2\2\u00f1\u00f6\7\5\2\2\u00f2\u00f3\7\r\2\2\u00f3\u00f5\5(\25\2\u00f4"+
		"\u00f2\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\27\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\7\36\2\2\u00fa\u00fb"+
		"\7\n\2\2\u00fb\u00fc\7:\2\2\u00fc\u00fd\7\33\2\2\u00fd\u00fe\7:\2\2\u00fe"+
		"\u00ff\7\34\2\2\u00ff\u0100\7:\2\2\u0100\u0101\7\f\2\2\u0101\u0102\7:"+
		"\2\2\u0102\u0103\7\35\2\2\u0103\u0104\7\4\2\2\u0104\u0105\7:\2\2\u0105"+
		"\u010a\7\5\2\2\u0106\u0107\7\37\2\2\u0107\u0109\7:\2\2\u0108\u0106\3\2"+
		"\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u0111\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010e\7\r\2\2\u010e\u0110\5("+
		"\25\2\u010f\u010d\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\31\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0115\7 \2\2"+
		"\u0115\u0116\7\n\2\2\u0116\u0117\7:\2\2\u0117\u0118\7\33\2\2\u0118\u0119"+
		"\7:\2\2\u0119\u011a\7\34\2\2\u011a\u011f\7:\2\2\u011b\u011c\7\r\2\2\u011c"+
		"\u011e\5(\25\2\u011d\u011b\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2"+
		"\2\2\u011f\u0120\3\2\2\2\u0120\33\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123"+
		"\7!\2\2\u0123\u0124\7\n\2\2\u0124\u0125\7:\2\2\u0125\u0126\7\"\2\2\u0126"+
		"\u0127\7:\2\2\u0127\u0128\7\f\2\2\u0128\u0129\7\4\2\2\u0129\u012a\5\36"+
		"\20\2\u012a\u012f\7\5\2\2\u012b\u012c\7\r\2\2\u012c\u012e\5(\25\2\u012d"+
		"\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2"+
		"\2\2\u0130\35\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7#\2\2\u0133\u0134"+
		"\7:\2\2\u0134\u0135\7$\2\2\u0135\u0136\7:\2\2\u0136\37\3\2\2\2\u0137\u0138"+
		"\7%\2\2\u0138\u0139\7\n\2\2\u0139\u013a\7:\2\2\u013a\u013b\7&\2\2\u013b"+
		"\u013c\7:\2\2\u013c\u013d\7\f\2\2\u013d\u013e\7\4\2\2\u013e\u013f\7:\2"+
		"\2\u013f\u0144\7\5\2\2\u0140\u0141\7\r\2\2\u0141\u0143\5(\25\2\u0142\u0140"+
		"\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"!\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\7\'\2\2\u0148\u0149\7\n\2\2"+
		"\u0149\u014a\7:\2\2\u014a\u014b\7(\2\2\u014b\u0150\7:\2\2\u014c\u014d"+
		"\7\r\2\2\u014d\u014f\5(\25\2\u014e\u014c\3\2\2\2\u014f\u0152\3\2\2\2\u0150"+
		"\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151#\3\2\2\2\u0152\u0150\3\2\2\2"+
		"\u0153\u0154\7)\2\2\u0154\u0155\7\n\2\2\u0155\u0156\7:\2\2\u0156\u0157"+
		"\7&\2\2\u0157\u0158\7:\2\2\u0158\u0159\7\f\2\2\u0159\u015a\7*\2\2\u015a"+
		"\u015b\7:\2\2\u015b\u015c\7+\2\2\u015c\u0161\7:\2\2\u015d\u015e\7,\2\2"+
		"\u015e\u0160\5*\26\2\u015f\u015d\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f"+
		"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0168\3\2\2\2\u0163\u0161\3\2\2\2\u0164"+
		"\u0165\7-\2\2\u0165\u0167\5*\26\2\u0166\u0164\3\2\2\2\u0167\u016a\3\2"+
		"\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016b\u016c\7.\2\2\u016c\u016d\7\4\2\2\u016d\u016e\7:\2"+
		"\2\u016e\u016f\7\5\2\2\u016f\u0170\3\2\2\2\u0170\u0174\7\4\2\2\u0171\u0173"+
		"\5&\24\2\u0172\u0171\3\2\2\2\u0173\u0176\3\2\2\2\u0174\u0172\3\2\2\2\u0174"+
		"\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u017c\7\5"+
		"\2\2\u0178\u0179\7\r\2\2\u0179\u017b\5(\25\2\u017a\u0178\3\2\2\2\u017b"+
		"\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d%\3\2\2\2"+
		"\u017e\u017c\3\2\2\2\u017f\u0180\7/\2\2\u0180\u0181\7\n\2\2\u0181\u0182"+
		"\7:\2\2\u0182\u0183\7\27\2\2\u0183\u0188\7:\2\2\u0184\u0185\7\60\2\2\u0185"+
		"\u0187\7:\2\2\u0186\u0184\3\2\2\2\u0187\u018a\3\2\2\2\u0188\u0186\3\2"+
		"\2\2\u0188\u0189\3\2\2\2\u0189\u018b\3\2\2\2\u018a\u0188\3\2\2\2\u018b"+
		"\u018c\7\5\2\2\u018c\'\3\2\2\2\u018d\u018e\7\61\2\2\u018e\u018f\7:\2\2"+
		"\u018f\u0190\7<\2\2\u0190\u0191\7:\2\2\u0191)\3\2\2\2\u0192\u0195\5,\27"+
		"\2\u0193\u0195\5\60\31\2\u0194\u0192\3\2\2\2\u0194\u0193\3\2\2\2\u0195"+
		"+\3\2\2\2\u0196\u0197\7\4\2\2\u0197\u019c\5.\30\2\u0198\u0199\7\62\2\2"+
		"\u0199\u019b\5.\30\2\u019a\u0198\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a"+
		"\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f\3\2\2\2\u019e\u019c\3\2\2\2\u019f"+
		"\u01a0\7\5\2\2\u01a0\u01a4\3\2\2\2\u01a1\u01a2\7\4\2\2\u01a2\u01a4\7\5"+
		"\2\2\u01a3\u0196\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4-\3\2\2\2\u01a5\u01a6"+
		"\7:\2\2\u01a6\u01a7\7\63\2\2\u01a7\u01a8\5\62\32\2\u01a8/\3\2\2\2\u01a9"+
		"\u01aa\7\64\2\2\u01aa\u01af\5\62\32\2\u01ab\u01ac\7\62\2\2\u01ac\u01ae"+
		"\5\62\32\2\u01ad\u01ab\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2"+
		"\u01af\u01b0\3\2\2\2\u01b0\u01b2\3\2\2\2\u01b1\u01af\3\2\2\2\u01b2\u01b3"+
		"\7\65\2\2\u01b3\u01b7\3\2\2\2\u01b4\u01b5\7\64\2\2\u01b5\u01b7\7\65\2"+
		"\2\u01b6\u01a9\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b7\61\3\2\2\2\u01b8\u01c0"+
		"\7:\2\2\u01b9\u01c0\7@\2\2\u01ba\u01c0\5,\27\2\u01bb\u01c0\5\60\31\2\u01bc"+
		"\u01c0\7\66\2\2\u01bd\u01c0\7\67\2\2\u01be\u01c0\78\2\2\u01bf\u01b8\3"+
		"\2\2\2\u01bf\u01b9\3\2\2\2\u01bf\u01ba\3\2\2\2\u01bf\u01bb\3\2\2\2\u01bf"+
		"\u01bc\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0\63\3\2\2"+
		"\2#AKUfow\177\u0086\u0098\u009f\u00a6\u00ad\u00bd\u00d5\u00e2\u00f6\u010a"+
		"\u0111\u011f\u012f\u0144\u0150\u0161\u0168\u0174\u017c\u0188\u0194\u019c"+
		"\u01a3\u01af\u01b6\u01bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}