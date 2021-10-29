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
		T__52=53, T__53=54, NON_ZERO_DIGIT=55, STRING=56, CRLF=57, Operator=58, 
		WS=59, COMMENT=60, LINE_COMMENT=61, NUMBER=62;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_attribute = 5, RULE_copyData = 6, RULE_transform = 7, 
		RULE_transform2 = 8, RULE_loadCsv = 9, RULE_abort = 10, RULE_callProcess = 11, 
		RULE_forkProcess = 12, RULE_spawnProcess = 13, RULE_dogLeg = 14, RULE_startProcess = 15, 
		RULE_assign = 16, RULE_dropFile = 17, RULE_restApi = 18, RULE_restPart = 19, 
		RULE_expression = 20, RULE_json = 21, RULE_obj = 22, RULE_pair = 23, RULE_arr = 24, 
		RULE_jValue = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "attribute", 
			"copyData", "transform", "transform2", "loadCsv", "abort", "callProcess", 
			"forkProcess", "spawnProcess", "dogLeg", "startProcess", "assign", "dropFile", 
			"restApi", "restPart", "expression", "json", "obj", "pair", "arr", "jValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'", 
			"'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'", 
			"'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'", 
			"'on'", "'transform2'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", 
			"'abort'", "'callprocess'", "'with-target'", "'from-file'", "'for-every'", 
			"'forkprocess'", "'watermark'", "'spawn'", "'dogleg'", "'use-parent-context'", 
			"'start-process'", "'with-file'", "'assign'", "'source'", "'dropfile'", 
			"'in-path'", "'restapi'", "'url'", "'method'", "'with headers'", "'with params'", 
			"'with body type'", "'{ part'", "'type as'", "'if'", "','", "':'", "'['", 
			"']'", "'true'", "'false'", "'null'"
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
			setState(52);
			match(T__0);
			setState(53);
			((ProcessContext)_localctx).name = match(STRING);
			setState(54);
			match(T__1);
			setState(55);
			((ProcessContext)_localctx).tryBlock = tryClause();
			setState(56);
			((ProcessContext)_localctx).catchBlock = catchClause();
			setState(57);
			((ProcessContext)_localctx).finallyBlock = finallyClause();
			setState(58);
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
			setState(60);
			match(T__3);
			setState(61);
			match(T__1);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(62);
				((TryClauseContext)_localctx).action = action();
				((TryClauseContext)_localctx).actions.add(((TryClauseContext)_localctx).action);
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
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
			setState(70);
			match(T__4);
			setState(71);
			match(T__1);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(72);
				((FinallyClauseContext)_localctx).action = action();
				((FinallyClauseContext)_localctx).actions.add(((FinallyClauseContext)_localctx).action);
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
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
			setState(80);
			match(T__5);
			setState(81);
			match(T__1);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__18) | (1L << T__22) | (1L << T__23) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(82);
				((CatchClauseContext)_localctx).action = action();
				((CatchClauseContext)_localctx).actions.add(((CatchClauseContext)_localctx).action);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
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
		public Transform2Context transform2() {
			return getRuleContext(Transform2Context.class,0);
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
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				{
				setState(90);
				abort();
				}
				break;
			case T__34:
				{
				setState(91);
				assign();
				}
				break;
			case T__23:
				{
				setState(92);
				callProcess();
				}
				break;
			case T__27:
				{
				setState(93);
				forkProcess();
				}
				break;
			case T__29:
				{
				setState(94);
				spawnProcess();
				}
				break;
			case T__30:
				{
				setState(95);
				dogLeg();
				}
				break;
			case T__6:
				{
				setState(96);
				copyData();
				}
				break;
			case T__36:
				{
				setState(97);
				dropFile();
				}
				break;
			case T__18:
				{
				setState(98);
				loadCsv();
				}
				break;
			case T__38:
				{
				setState(99);
				restApi();
				}
				break;
			case T__15:
				{
				setState(100);
				transform();
				}
				break;
			case T__17:
				{
				setState(101);
				transform2();
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
			setState(104);
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
			setState(106);
			match(T__6);
			setState(107);
			match(T__7);
			setState(108);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(109);
			match(T__8);
			setState(110);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(111);
			match(T__9);
			setState(112);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(113);
			match(T__10);
			setState(114);
			match(T__1);
			setState(115);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(116);
			match(T__2);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(117);
				match(T__11);
				setState(118);
				((CopyDataContext)_localctx).condition = expression();
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(124);
				match(T__12);
				setState(125);
				((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(131);
				match(T__13);
				setState(132);
				((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(138);
				match(T__14);
				setState(139);
				((CopyDataContext)_localctx).writeBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(144);
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
			setState(145);
			match(T__15);
			setState(146);
			match(T__7);
			setState(147);
			((TransformContext)_localctx).name = match(STRING);
			setState(148);
			match(T__16);
			setState(149);
			((TransformContext)_localctx).on = match(STRING);
			setState(150);
			match(T__10);
			setState(151);
			match(T__1);
			setState(152);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(153);
			match(T__2);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(154);
				match(T__11);
				setState(155);
				((TransformContext)_localctx).condition = expression();
				}
				}
				setState(160);
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

	public static class Transform2Context extends ParserRuleContext {
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
		public Transform2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transform2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterTransform2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitTransform2(this);
		}
	}

	public final Transform2Context transform2() throws RecognitionException {
		Transform2Context _localctx = new Transform2Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_transform2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__17);
			setState(162);
			match(T__7);
			setState(163);
			((Transform2Context)_localctx).name = match(STRING);
			setState(164);
			match(T__16);
			setState(165);
			((Transform2Context)_localctx).on = match(STRING);
			setState(166);
			match(T__10);
			setState(167);
			match(T__1);
			setState(168);
			((Transform2Context)_localctx).STRING = match(STRING);
			((Transform2Context)_localctx).value.add(((Transform2Context)_localctx).STRING);
			setState(169);
			match(T__2);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(170);
				match(T__11);
				setState(171);
				((Transform2Context)_localctx).condition = expression();
				}
				}
				setState(176);
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
		enterRule(_localctx, 18, RULE_loadCsv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__18);
			setState(178);
			match(T__7);
			setState(179);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(180);
			match(T__19);
			setState(181);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(182);
			match(T__8);
			setState(183);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(184);
			match(T__9);
			setState(185);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(186);
			match(T__20);
			setState(187);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(188);
			match(T__21);
			setState(189);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(190);
			match(T__10);
			setState(191);
			match(T__1);
			setState(192);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(193);
			match(T__2);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(194);
				match(T__11);
				setState(195);
				((LoadCsvContext)_localctx).condition = expression();
				}
				}
				setState(200);
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
		enterRule(_localctx, 20, RULE_abort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(T__22);
			setState(202);
			match(T__7);
			setState(203);
			((AbortContext)_localctx).name = match(STRING);
			setState(204);
			match(T__1);
			setState(205);
			((AbortContext)_localctx).value = match(STRING);
			setState(206);
			match(T__2);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(207);
				match(T__11);
				setState(208);
				((AbortContext)_localctx).condition = expression();
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
		enterRule(_localctx, 22, RULE_callProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(T__23);
			setState(215);
			match(T__7);
			setState(216);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(217);
			match(T__24);
			setState(218);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(219);
			match(T__25);
			setState(220);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(221);
			match(T__10);
			setState(222);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(223);
			match(T__26);
			setState(224);
			match(T__1);
			setState(225);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(226);
			match(T__2);
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(227);
				match(T__11);
				setState(228);
				((CallProcessContext)_localctx).condition = expression();
				}
				}
				setState(233);
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
		enterRule(_localctx, 24, RULE_forkProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(T__27);
			setState(235);
			match(T__7);
			setState(236);
			((ForkProcessContext)_localctx).name = match(STRING);
			setState(237);
			match(T__24);
			setState(238);
			((ForkProcessContext)_localctx).target = match(STRING);
			setState(239);
			match(T__25);
			setState(240);
			((ForkProcessContext)_localctx).source = match(STRING);
			setState(241);
			match(T__10);
			setState(242);
			((ForkProcessContext)_localctx).datasource = match(STRING);
			setState(243);
			match(T__26);
			setState(244);
			match(T__1);
			setState(245);
			((ForkProcessContext)_localctx).value = match(STRING);
			setState(246);
			match(T__2);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(247);
				match(T__28);
				setState(248);
				((ForkProcessContext)_localctx).forkBatchSize = match(STRING);
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(254);
				match(T__11);
				setState(255);
				((ForkProcessContext)_localctx).condition = expression();
				}
				}
				setState(260);
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
		enterRule(_localctx, 26, RULE_spawnProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(T__29);
			setState(262);
			match(T__7);
			setState(263);
			((SpawnProcessContext)_localctx).name = match(STRING);
			setState(264);
			match(T__24);
			setState(265);
			((SpawnProcessContext)_localctx).target = match(STRING);
			setState(266);
			match(T__25);
			setState(267);
			((SpawnProcessContext)_localctx).source = match(STRING);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(268);
				match(T__11);
				setState(269);
				((SpawnProcessContext)_localctx).condition = expression();
				}
				}
				setState(274);
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
		enterRule(_localctx, 28, RULE_dogLeg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(T__30);
			setState(276);
			match(T__7);
			setState(277);
			((DogLegContext)_localctx).name = match(STRING);
			setState(278);
			match(T__31);
			setState(279);
			((DogLegContext)_localctx).inheritContext = match(STRING);
			setState(280);
			match(T__10);
			setState(281);
			match(T__1);
			setState(282);
			((DogLegContext)_localctx).startProcess = startProcess();
			((DogLegContext)_localctx).processList.add(((DogLegContext)_localctx).startProcess);
			setState(283);
			match(T__2);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(284);
				match(T__11);
				setState(285);
				((DogLegContext)_localctx).condition = expression();
				}
				}
				setState(290);
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
		enterRule(_localctx, 30, RULE_startProcess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__32);
			setState(292);
			((StartProcessContext)_localctx).name = match(STRING);
			setState(293);
			match(T__33);
			setState(294);
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
		enterRule(_localctx, 32, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(T__34);
			setState(297);
			match(T__7);
			setState(298);
			((AssignContext)_localctx).name = match(STRING);
			setState(299);
			match(T__35);
			setState(300);
			((AssignContext)_localctx).source = match(STRING);
			setState(301);
			match(T__10);
			setState(302);
			match(T__1);
			setState(303);
			((AssignContext)_localctx).value = match(STRING);
			setState(304);
			match(T__2);
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(305);
				match(T__11);
				setState(306);
				((AssignContext)_localctx).condition = expression();
				}
				}
				setState(311);
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
		enterRule(_localctx, 34, RULE_dropFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__36);
			setState(313);
			match(T__7);
			setState(314);
			((DropFileContext)_localctx).name = match(STRING);
			setState(315);
			match(T__37);
			setState(316);
			((DropFileContext)_localctx).target = match(STRING);
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(317);
				match(T__11);
				setState(318);
				((DropFileContext)_localctx).condition = expression();
				}
				}
				setState(323);
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
		enterRule(_localctx, 36, RULE_restApi);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(T__38);
			setState(325);
			match(T__7);
			setState(326);
			((RestApiContext)_localctx).name = match(STRING);
			setState(327);
			match(T__35);
			setState(328);
			((RestApiContext)_localctx).source = match(STRING);
			setState(329);
			match(T__10);
			setState(330);
			match(T__39);
			setState(331);
			((RestApiContext)_localctx).url = match(STRING);
			setState(332);
			match(T__40);
			setState(333);
			((RestApiContext)_localctx).method = match(STRING);
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__41) {
				{
				{
				setState(334);
				match(T__41);
				setState(335);
				((RestApiContext)_localctx).headers = json();
				}
				}
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(341);
				match(T__42);
				setState(342);
				((RestApiContext)_localctx).params = json();
				}
				}
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(348);
			match(T__43);
			setState(349);
			match(T__1);
			setState(350);
			((RestApiContext)_localctx).bodyType = match(STRING);
			setState(351);
			match(T__2);
			}
			setState(353);
			match(T__1);
			setState(357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__44) {
				{
				{
				setState(354);
				((RestApiContext)_localctx).restPart = restPart();
				((RestApiContext)_localctx).value.add(((RestApiContext)_localctx).restPart);
				}
				}
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(360);
			match(T__2);
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(361);
				match(T__11);
				setState(362);
				((RestApiContext)_localctx).condition = expression();
				}
				}
				setState(367);
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
		enterRule(_localctx, 38, RULE_restPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(T__44);
			setState(369);
			match(T__7);
			setState(370);
			((RestPartContext)_localctx).partName = match(STRING);
			setState(371);
			match(T__20);
			setState(372);
			((RestPartContext)_localctx).partData = match(STRING);
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__45) {
				{
				{
				setState(373);
				match(T__45);
				setState(374);
				((RestPartContext)_localctx).type = match(STRING);
				}
				}
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(380);
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
		enterRule(_localctx, 40, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(T__46);
			{
			setState(383);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(384);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(385);
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
		enterRule(_localctx, 42, RULE_json);
		try {
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				obj();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
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
		enterRule(_localctx, 44, RULE_obj);
		int _la;
		try {
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				match(T__1);
				setState(392);
				pair();
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__47) {
					{
					{
					setState(393);
					match(T__47);
					setState(394);
					pair();
					}
					}
					setState(399);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(400);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				match(T__1);
				setState(403);
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
		enterRule(_localctx, 46, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(STRING);
			setState(407);
			match(T__48);
			setState(408);
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
		enterRule(_localctx, 48, RULE_arr);
		int _la;
		try {
			setState(423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(410);
				match(T__49);
				setState(411);
				jValue();
				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__47) {
					{
					{
					setState(412);
					match(T__47);
					setState(413);
					jValue();
					}
					}
					setState(418);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(419);
				match(T__50);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(421);
				match(T__49);
				setState(422);
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
		enterRule(_localctx, 50, RULE_jValue);
		try {
			setState(432);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(426);
				match(NUMBER);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(427);
				obj();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 4);
				{
				setState(428);
				arr();
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 5);
				{
				setState(429);
				match(T__51);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 6);
				{
				setState(430);
				match(T__52);
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 7);
				{
				setState(431);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3@\u01b5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3B"+
		"\n\3\f\3\16\3E\13\3\3\3\3\3\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\4\3"+
		"\4\3\5\3\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6i\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\7\bz\n\b\f\b\16\b}\13\b\3\b\3\b\7\b\u0081\n\b"+
		"\f\b\16\b\u0084\13\b\3\b\3\b\7\b\u0088\n\b\f\b\16\b\u008b\13\b\3\b\3\b"+
		"\7\b\u008f\n\b\f\b\16\b\u0092\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\7\t\u009f\n\t\f\t\16\t\u00a2\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\n\u00af\n\n\f\n\16\n\u00b2\13\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00c7\n\13\f\13\16\13\u00ca\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\7\f\u00d4\n\f\f\f\16\f\u00d7\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00e8\n\r\f\r\16\r\u00eb\13\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u00fc\n\16\f\16\16\16\u00ff\13\16\3\16\3\16\7\16\u0103\n\16\f\16"+
		"\16\16\u0106\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0111"+
		"\n\17\f\17\16\17\u0114\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\7\20\u0121\n\20\f\20\16\20\u0124\13\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0136"+
		"\n\22\f\22\16\22\u0139\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0142"+
		"\n\23\f\23\16\23\u0145\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\7\24\u0153\n\24\f\24\16\24\u0156\13\24\3\24\3\24\7"+
		"\24\u015a\n\24\f\24\16\24\u015d\13\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\7\24\u0166\n\24\f\24\16\24\u0169\13\24\3\24\3\24\3\24\7\24\u016e\n"+
		"\24\f\24\16\24\u0171\13\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u017a"+
		"\n\25\f\25\16\25\u017d\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3"+
		"\27\5\27\u0188\n\27\3\30\3\30\3\30\3\30\7\30\u018e\n\30\f\30\16\30\u0191"+
		"\13\30\3\30\3\30\3\30\3\30\5\30\u0197\n\30\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\7\32\u01a1\n\32\f\32\16\32\u01a4\13\32\3\32\3\32\3\32\3"+
		"\32\5\32\u01aa\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u01b3\n\33"+
		"\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\2\2\2\u01c7\2\66\3\2\2\2\4>\3\2\2\2\6H\3\2\2\2\bR\3\2\2\2\nh\3\2\2\2"+
		"\fj\3\2\2\2\16l\3\2\2\2\20\u0093\3\2\2\2\22\u00a3\3\2\2\2\24\u00b3\3\2"+
		"\2\2\26\u00cb\3\2\2\2\30\u00d8\3\2\2\2\32\u00ec\3\2\2\2\34\u0107\3\2\2"+
		"\2\36\u0115\3\2\2\2 \u0125\3\2\2\2\"\u012a\3\2\2\2$\u013a\3\2\2\2&\u0146"+
		"\3\2\2\2(\u0172\3\2\2\2*\u0180\3\2\2\2,\u0187\3\2\2\2.\u0196\3\2\2\2\60"+
		"\u0198\3\2\2\2\62\u01a9\3\2\2\2\64\u01b2\3\2\2\2\66\67\7\3\2\2\678\7:"+
		"\2\289\7\4\2\29:\5\4\3\2:;\5\b\5\2;<\5\6\4\2<=\7\5\2\2=\3\3\2\2\2>?\7"+
		"\6\2\2?C\7\4\2\2@B\5\n\6\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3"+
		"\2\2\2EC\3\2\2\2FG\7\5\2\2G\5\3\2\2\2HI\7\7\2\2IM\7\4\2\2JL\5\n\6\2KJ"+
		"\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\7\5\2\2Q"+
		"\7\3\2\2\2RS\7\b\2\2SW\7\4\2\2TV\5\n\6\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2"+
		"WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\5\2\2[\t\3\2\2\2\\i\5\26\f\2]i\5\""+
		"\22\2^i\5\30\r\2_i\5\32\16\2`i\5\34\17\2ai\5\36\20\2bi\5\16\b\2ci\5$\23"+
		"\2di\5\24\13\2ei\5&\24\2fi\5\20\t\2gi\5\22\n\2h\\\3\2\2\2h]\3\2\2\2h^"+
		"\3\2\2\2h_\3\2\2\2h`\3\2\2\2ha\3\2\2\2hb\3\2\2\2hc\3\2\2\2hd\3\2\2\2h"+
		"e\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\13\3\2\2\2jk\5 \21\2k\r\3\2\2\2lm\7\t\2"+
		"\2mn\7\n\2\2no\7:\2\2op\7\13\2\2pq\7:\2\2qr\7\f\2\2rs\7:\2\2st\7\r\2\2"+
		"tu\7\4\2\2uv\7:\2\2v{\7\5\2\2wx\7\16\2\2xz\5*\26\2yw\3\2\2\2z}\3\2\2\2"+
		"{y\3\2\2\2{|\3\2\2\2|\u0082\3\2\2\2}{\3\2\2\2~\177\7\17\2\2\177\u0081"+
		"\79\2\2\u0080~\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0089\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7\20\2\2"+
		"\u0086\u0088\79\2\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0090\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u008d\7\21\2\2\u008d\u008f\79\2\2\u008e\u008c\3\2\2\2\u008f\u0092\3\2"+
		"\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\17\3\2\2\2\u0092\u0090"+
		"\3\2\2\2\u0093\u0094\7\22\2\2\u0094\u0095\7\n\2\2\u0095\u0096\7:\2\2\u0096"+
		"\u0097\7\23\2\2\u0097\u0098\7:\2\2\u0098\u0099\7\r\2\2\u0099\u009a\7\4"+
		"\2\2\u009a\u009b\7:\2\2\u009b\u00a0\7\5\2\2\u009c\u009d\7\16\2\2\u009d"+
		"\u009f\5*\26\2\u009e\u009c\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\21\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4"+
		"\7\24\2\2\u00a4\u00a5\7\n\2\2\u00a5\u00a6\7:\2\2\u00a6\u00a7\7\23\2\2"+
		"\u00a7\u00a8\7:\2\2\u00a8\u00a9\7\r\2\2\u00a9\u00aa\7\4\2\2\u00aa\u00ab"+
		"\7:\2\2\u00ab\u00b0\7\5\2\2\u00ac\u00ad\7\16\2\2\u00ad\u00af\5*\26\2\u00ae"+
		"\u00ac\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2"+
		"\2\2\u00b1\23\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7\25\2\2\u00b4\u00b5"+
		"\7\n\2\2\u00b5\u00b6\7:\2\2\u00b6\u00b7\7\26\2\2\u00b7\u00b8\7:\2\2\u00b8"+
		"\u00b9\7\13\2\2\u00b9\u00ba\7:\2\2\u00ba\u00bb\7\f\2\2\u00bb\u00bc\7:"+
		"\2\2\u00bc\u00bd\7\27\2\2\u00bd\u00be\7:\2\2\u00be\u00bf\7\30\2\2\u00bf"+
		"\u00c0\7:\2\2\u00c0\u00c1\7\r\2\2\u00c1\u00c2\7\4\2\2\u00c2\u00c3\7:\2"+
		"\2\u00c3\u00c8\7\5\2\2\u00c4\u00c5\7\16\2\2\u00c5\u00c7\5*\26\2\u00c6"+
		"\u00c4\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\25\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\7\31\2\2\u00cc\u00cd"+
		"\7\n\2\2\u00cd\u00ce\7:\2\2\u00ce\u00cf\7\4\2\2\u00cf\u00d0\7:\2\2\u00d0"+
		"\u00d5\7\5\2\2\u00d1\u00d2\7\16\2\2\u00d2\u00d4\5*\26\2\u00d3\u00d1\3"+
		"\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\27\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7\32\2\2\u00d9\u00da\7\n\2"+
		"\2\u00da\u00db\7:\2\2\u00db\u00dc\7\33\2\2\u00dc\u00dd\7:\2\2\u00dd\u00de"+
		"\7\34\2\2\u00de\u00df\7:\2\2\u00df\u00e0\7\r\2\2\u00e0\u00e1\7:\2\2\u00e1"+
		"\u00e2\7\35\2\2\u00e2\u00e3\7\4\2\2\u00e3\u00e4\7:\2\2\u00e4\u00e9\7\5"+
		"\2\2\u00e5\u00e6\7\16\2\2\u00e6\u00e8\5*\26\2\u00e7\u00e5\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\31\3\2\2"+
		"\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\7\36\2\2\u00ed\u00ee\7\n\2\2\u00ee"+
		"\u00ef\7:\2\2\u00ef\u00f0\7\33\2\2\u00f0\u00f1\7:\2\2\u00f1\u00f2\7\34"+
		"\2\2\u00f2\u00f3\7:\2\2\u00f3\u00f4\7\r\2\2\u00f4\u00f5\7:\2\2\u00f5\u00f6"+
		"\7\35\2\2\u00f6\u00f7\7\4\2\2\u00f7\u00f8\7:\2\2\u00f8\u00fd\7\5\2\2\u00f9"+
		"\u00fa\7\37\2\2\u00fa\u00fc\7:\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00ff\3\2"+
		"\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0104\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0101\7\16\2\2\u0101\u0103\5*\26\2\u0102\u0100\3"+
		"\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
		"\33\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7 \2\2\u0108\u0109\7\n\2\2"+
		"\u0109\u010a\7:\2\2\u010a\u010b\7\33\2\2\u010b\u010c\7:\2\2\u010c\u010d"+
		"\7\34\2\2\u010d\u0112\7:\2\2\u010e\u010f\7\16\2\2\u010f\u0111\5*\26\2"+
		"\u0110\u010e\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113\35\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0116\7!\2\2\u0116"+
		"\u0117\7\n\2\2\u0117\u0118\7:\2\2\u0118\u0119\7\"\2\2\u0119\u011a\7:\2"+
		"\2\u011a\u011b\7\r\2\2\u011b\u011c\7\4\2\2\u011c\u011d\5 \21\2\u011d\u0122"+
		"\7\5\2\2\u011e\u011f\7\16\2\2\u011f\u0121\5*\26\2\u0120\u011e\3\2\2\2"+
		"\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\37"+
		"\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\7#\2\2\u0126\u0127\7:\2\2\u0127"+
		"\u0128\7$\2\2\u0128\u0129\7:\2\2\u0129!\3\2\2\2\u012a\u012b\7%\2\2\u012b"+
		"\u012c\7\n\2\2\u012c\u012d\7:\2\2\u012d\u012e\7&\2\2\u012e\u012f\7:\2"+
		"\2\u012f\u0130\7\r\2\2\u0130\u0131\7\4\2\2\u0131\u0132\7:\2\2\u0132\u0137"+
		"\7\5\2\2\u0133\u0134\7\16\2\2\u0134\u0136\5*\26\2\u0135\u0133\3\2\2\2"+
		"\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138#\3"+
		"\2\2\2\u0139\u0137\3\2\2\2\u013a\u013b\7\'\2\2\u013b\u013c\7\n\2\2\u013c"+
		"\u013d\7:\2\2\u013d\u013e\7(\2\2\u013e\u0143\7:\2\2\u013f\u0140\7\16\2"+
		"\2\u0140\u0142\5*\26\2\u0141\u013f\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141"+
		"\3\2\2\2\u0143\u0144\3\2\2\2\u0144%\3\2\2\2\u0145\u0143\3\2\2\2\u0146"+
		"\u0147\7)\2\2\u0147\u0148\7\n\2\2\u0148\u0149\7:\2\2\u0149\u014a\7&\2"+
		"\2\u014a\u014b\7:\2\2\u014b\u014c\7\r\2\2\u014c\u014d\7*\2\2\u014d\u014e"+
		"\7:\2\2\u014e\u014f\7+\2\2\u014f\u0154\7:\2\2\u0150\u0151\7,\2\2\u0151"+
		"\u0153\5,\27\2\u0152\u0150\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2"+
		"\2\2\u0154\u0155\3\2\2\2\u0155\u015b\3\2\2\2\u0156\u0154\3\2\2\2\u0157"+
		"\u0158\7-\2\2\u0158\u015a\5,\27\2\u0159\u0157\3\2\2\2\u015a\u015d\3\2"+
		"\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015e\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015e\u015f\7.\2\2\u015f\u0160\7\4\2\2\u0160\u0161\7:\2"+
		"\2\u0161\u0162\7\5\2\2\u0162\u0163\3\2\2\2\u0163\u0167\7\4\2\2\u0164\u0166"+
		"\5(\25\2\u0165\u0164\3\2\2\2\u0166\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0167"+
		"\u0168\3\2\2\2\u0168\u016a\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u016f\7\5"+
		"\2\2\u016b\u016c\7\16\2\2\u016c\u016e\5*\26\2\u016d\u016b\3\2\2\2\u016e"+
		"\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\'\3\2\2\2"+
		"\u0171\u016f\3\2\2\2\u0172\u0173\7/\2\2\u0173\u0174\7\n\2\2\u0174\u0175"+
		"\7:\2\2\u0175\u0176\7\27\2\2\u0176\u017b\7:\2\2\u0177\u0178\7\60\2\2\u0178"+
		"\u017a\7:\2\2\u0179\u0177\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u017b\3\2\2\2\u017e"+
		"\u017f\7\5\2\2\u017f)\3\2\2\2\u0180\u0181\7\61\2\2\u0181\u0182\7:\2\2"+
		"\u0182\u0183\7<\2\2\u0183\u0184\7:\2\2\u0184+\3\2\2\2\u0185\u0188\5.\30"+
		"\2\u0186\u0188\5\62\32\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2\2\u0188"+
		"-\3\2\2\2\u0189\u018a\7\4\2\2\u018a\u018f\5\60\31\2\u018b\u018c\7\62\2"+
		"\2\u018c\u018e\5\60\31\2\u018d\u018b\3\2\2\2\u018e\u0191\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u018f\3\2"+
		"\2\2\u0192\u0193\7\5\2\2\u0193\u0197\3\2\2\2\u0194\u0195\7\4\2\2\u0195"+
		"\u0197\7\5\2\2\u0196\u0189\3\2\2\2\u0196\u0194\3\2\2\2\u0197/\3\2\2\2"+
		"\u0198\u0199\7:\2\2\u0199\u019a\7\63\2\2\u019a\u019b\5\64\33\2\u019b\61"+
		"\3\2\2\2\u019c\u019d\7\64\2\2\u019d\u01a2\5\64\33\2\u019e\u019f\7\62\2"+
		"\2\u019f\u01a1\5\64\33\2\u01a0\u019e\3\2\2\2\u01a1\u01a4\3\2\2\2\u01a2"+
		"\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4\u01a2\3\2"+
		"\2\2\u01a5\u01a6\7\65\2\2\u01a6\u01aa\3\2\2\2\u01a7\u01a8\7\64\2\2\u01a8"+
		"\u01aa\7\65\2\2\u01a9\u019c\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\63\3\2\2"+
		"\2\u01ab\u01b3\7:\2\2\u01ac\u01b3\7@\2\2\u01ad\u01b3\5.\30\2\u01ae\u01b3"+
		"\5\62\32\2\u01af\u01b3\7\66\2\2\u01b0\u01b3\7\67\2\2\u01b1\u01b3\78\2"+
		"\2\u01b2\u01ab\3\2\2\2\u01b2\u01ac\3\2\2\2\u01b2\u01ad\3\2\2\2\u01b2\u01ae"+
		"\3\2\2\2\u01b2\u01af\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b2\u01b1\3\2\2\2\u01b3"+
		"\65\3\2\2\2 CMWh{\u0082\u0089\u0090\u00a0\u00b0\u00c8\u00d5\u00e9\u00fd"+
		"\u0104\u0112\u0122\u0137\u0143\u0154\u015b\u0167\u016f\u017b\u0187\u018f"+
		"\u0196\u01a2\u01a9\u01b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}