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
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, NON_ZERO_DIGIT=51, STRING=52, 
		CRLF=53, Operator=54, WS=55, COMMENT=56, LINE_COMMENT=57, NUMBER=58;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_copyData = 5, RULE_transform = 6, RULE_loadCsv = 7, 
		RULE_abort = 8, RULE_callProcess = 9, RULE_assign = 10, RULE_fbLead = 11, 
		RULE_fbFormDownload = 12, RULE_dropFile = 13, RULE_restApi = 14, RULE_expression = 15, 
		RULE_json = 16, RULE_obj = 17, RULE_pair = 18, RULE_arr = 19, RULE_jValue = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "copyData", 
			"transform", "loadCsv", "abort", "callProcess", "assign", "fbLead", "fbFormDownload", 
			"dropFile", "restApi", "expression", "json", "obj", "pair", "arr", "jValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'", 
			"'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'", 
			"'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'", 
			"'on'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", "'abort'", "'callprocess'", 
			"'with-target'", "'from-file'", "'for-every'", "'assign'", "'source'", 
			"'fblc'", "'secured-by'", "'with-key'", "'through-user'", "'for-campaign'", 
			"'into'", "'fbfd'", "'for-form'", "'dropfile'", "'in-path'", "'restapi'", 
			"'url'", "'method'", "'with headers'", "'if'", "','", "':'", "'['", "']'", 
			"'true'", "'false'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", "Operator", "WS", 
			"COMMENT", "LINE_COMMENT", "NUMBER"
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
			setState(42);
			match(T__0);
			setState(43);
			((ProcessContext)_localctx).name = match(STRING);
			setState(44);
			match(T__1);
			setState(45);
			((ProcessContext)_localctx).tryBlock = tryClause();
			setState(46);
			((ProcessContext)_localctx).catchBlock = catchClause();
			setState(47);
			((ProcessContext)_localctx).finallyBlock = finallyClause();
			setState(48);
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
			setState(50);
			match(T__3);
			setState(51);
			match(T__1);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(52);
				((TryClauseContext)_localctx).action = action();
				((TryClauseContext)_localctx).actions.add(((TryClauseContext)_localctx).action);
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
			setState(60);
			match(T__4);
			setState(61);
			match(T__1);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(62);
				((FinallyClauseContext)_localctx).action = action();
				((FinallyClauseContext)_localctx).actions.add(((FinallyClauseContext)_localctx).action);
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
			setState(70);
			match(T__5);
			setState(71);
			match(T__1);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34) | (1L << T__36) | (1L << T__38))) != 0)) {
				{
				{
				setState(72);
				((CatchClauseContext)_localctx).action = action();
				((CatchClauseContext)_localctx).actions.add(((CatchClauseContext)_localctx).action);
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
		public CopyDataContext copyData() {
			return getRuleContext(CopyDataContext.class,0);
		}
		public DropFileContext dropFile() {
			return getRuleContext(DropFileContext.class,0);
		}
		public FbFormDownloadContext fbFormDownload() {
			return getRuleContext(FbFormDownloadContext.class,0);
		}
		public FbLeadContext fbLead() {
			return getRuleContext(FbLeadContext.class,0);
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
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				{
				setState(80);
				abort();
				}
				break;
			case T__26:
				{
				setState(81);
				assign();
				}
				break;
			case T__22:
				{
				setState(82);
				callProcess();
				}
				break;
			case T__6:
				{
				setState(83);
				copyData();
				}
				break;
			case T__36:
				{
				setState(84);
				dropFile();
				}
				break;
			case T__34:
				{
				setState(85);
				fbFormDownload();
				}
				break;
			case T__28:
				{
				setState(86);
				fbLead();
				}
				break;
			case T__17:
				{
				setState(87);
				loadCsv();
				}
				break;
			case T__38:
				{
				setState(88);
				restApi();
				}
				break;
			case T__15:
				{
				setState(89);
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
		enterRule(_localctx, 10, RULE_copyData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(92);
			match(T__6);
			setState(93);
			match(T__7);
			setState(94);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(95);
			match(T__8);
			setState(96);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(97);
			match(T__9);
			setState(98);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(99);
			match(T__10);
			setState(100);
			match(T__1);
			setState(101);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(102);
			match(T__2);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(103);
				match(T__11);
				setState(104);
				((CopyDataContext)_localctx).condition = expression();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(110);
				match(T__12);
				setState(111);
				((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(117);
				match(T__13);
				setState(118);
				((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__14) {
				{
				{
				setState(124);
				match(T__14);
				setState(125);
				((CopyDataContext)_localctx).writeBatchSize = match(NON_ZERO_DIGIT);
				}
				}
				setState(130);
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
		enterRule(_localctx, 12, RULE_transform);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__15);
			setState(132);
			match(T__7);
			setState(133);
			((TransformContext)_localctx).name = match(STRING);
			setState(134);
			match(T__16);
			setState(135);
			((TransformContext)_localctx).on = match(STRING);
			setState(136);
			match(T__10);
			setState(137);
			match(T__1);
			setState(138);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(139);
			match(T__2);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(140);
				match(T__11);
				setState(141);
				((TransformContext)_localctx).condition = expression();
				}
				}
				setState(146);
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
		enterRule(_localctx, 14, RULE_loadCsv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(T__17);
			setState(148);
			match(T__7);
			setState(149);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(150);
			match(T__18);
			setState(151);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(152);
			match(T__8);
			setState(153);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(154);
			match(T__9);
			setState(155);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(156);
			match(T__19);
			setState(157);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(158);
			match(T__20);
			setState(159);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(160);
			match(T__10);
			setState(161);
			match(T__1);
			setState(162);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(163);
			match(T__2);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(164);
				match(T__11);
				setState(165);
				((LoadCsvContext)_localctx).condition = expression();
				}
				}
				setState(170);
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
		enterRule(_localctx, 16, RULE_abort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__21);
			setState(172);
			match(T__7);
			setState(173);
			((AbortContext)_localctx).name = match(STRING);
			setState(174);
			match(T__1);
			setState(175);
			((AbortContext)_localctx).value = match(STRING);
			setState(176);
			match(T__2);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(177);
				match(T__11);
				setState(178);
				((AbortContext)_localctx).condition = expression();
				}
				}
				setState(183);
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
		enterRule(_localctx, 18, RULE_callProcess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(T__22);
			setState(185);
			match(T__7);
			setState(186);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(187);
			match(T__23);
			setState(188);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(189);
			match(T__24);
			setState(190);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(191);
			match(T__10);
			setState(192);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(193);
			match(T__25);
			setState(194);
			match(T__1);
			setState(195);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(196);
			match(T__2);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(197);
				match(T__11);
				setState(198);
				((CallProcessContext)_localctx).condition = expression();
				}
				}
				setState(203);
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
		enterRule(_localctx, 20, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__26);
			setState(205);
			match(T__7);
			setState(206);
			((AssignContext)_localctx).name = match(STRING);
			setState(207);
			match(T__27);
			setState(208);
			((AssignContext)_localctx).source = match(STRING);
			setState(209);
			match(T__10);
			setState(210);
			match(T__1);
			setState(211);
			((AssignContext)_localctx).value = match(STRING);
			setState(212);
			match(T__2);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(213);
				match(T__11);
				setState(214);
				((AssignContext)_localctx).condition = expression();
				}
				}
				setState(219);
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

	public static class FbLeadContext extends ParserRuleContext {
		public Token name;
		public Token accessToken;
		public Token appSecret;
		public Token accountId;
		public Token campaignId;
		public Token target;
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
		public FbLeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fbLead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterFbLead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitFbLead(this);
		}
	}

	public final FbLeadContext fbLead() throws RecognitionException {
		FbLeadContext _localctx = new FbLeadContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fbLead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(T__28);
			setState(221);
			match(T__7);
			setState(222);
			((FbLeadContext)_localctx).name = match(STRING);
			setState(223);
			match(T__29);
			setState(224);
			((FbLeadContext)_localctx).accessToken = match(STRING);
			setState(225);
			match(T__30);
			setState(226);
			((FbLeadContext)_localctx).appSecret = match(STRING);
			setState(227);
			match(T__31);
			setState(228);
			((FbLeadContext)_localctx).accountId = match(STRING);
			setState(229);
			match(T__32);
			setState(230);
			((FbLeadContext)_localctx).campaignId = match(STRING);
			setState(231);
			match(T__33);
			setState(232);
			((FbLeadContext)_localctx).target = match(STRING);
			setState(233);
			match(T__10);
			setState(234);
			match(T__1);
			setState(235);
			((FbLeadContext)_localctx).value = match(STRING);
			setState(236);
			match(T__2);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(237);
				match(T__11);
				setState(238);
				((FbLeadContext)_localctx).condition = expression();
				}
				}
				setState(243);
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

	public static class FbFormDownloadContext extends ParserRuleContext {
		public Token name;
		public Token accessToken;
		public Token appSecret;
		public Token accountId;
		public Token formId;
		public Token target;
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
		public FbFormDownloadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fbFormDownload; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).enterFbFormDownload(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RavenListener ) ((RavenListener)listener).exitFbFormDownload(this);
		}
	}

	public final FbFormDownloadContext fbFormDownload() throws RecognitionException {
		FbFormDownloadContext _localctx = new FbFormDownloadContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_fbFormDownload);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(T__34);
			setState(245);
			match(T__7);
			setState(246);
			((FbFormDownloadContext)_localctx).name = match(STRING);
			setState(247);
			match(T__29);
			setState(248);
			((FbFormDownloadContext)_localctx).accessToken = match(STRING);
			setState(249);
			match(T__30);
			setState(250);
			((FbFormDownloadContext)_localctx).appSecret = match(STRING);
			setState(251);
			match(T__31);
			setState(252);
			((FbFormDownloadContext)_localctx).accountId = match(STRING);
			setState(253);
			match(T__35);
			setState(254);
			((FbFormDownloadContext)_localctx).formId = match(STRING);
			setState(255);
			match(T__33);
			setState(256);
			((FbFormDownloadContext)_localctx).target = match(STRING);
			setState(257);
			match(T__10);
			setState(258);
			match(T__1);
			setState(259);
			((FbFormDownloadContext)_localctx).value = match(STRING);
			setState(260);
			match(T__2);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(261);
				match(T__11);
				setState(262);
				((FbFormDownloadContext)_localctx).condition = expression();
				}
				}
				setState(267);
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
		enterRule(_localctx, 26, RULE_dropFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__36);
			setState(269);
			match(T__7);
			setState(270);
			((DropFileContext)_localctx).name = match(STRING);
			setState(271);
			match(T__37);
			setState(272);
			((DropFileContext)_localctx).target = match(STRING);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(273);
				match(T__11);
				setState(274);
				((DropFileContext)_localctx).condition = expression();
				}
				}
				setState(279);
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
		public Token url;
		public Token method;
		public JsonContext header;
		public JsonContext value;
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
		enterRule(_localctx, 28, RULE_restApi);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(T__38);
			setState(281);
			match(T__7);
			setState(282);
			((RestApiContext)_localctx).name = match(STRING);
			setState(283);
			match(T__39);
			setState(284);
			((RestApiContext)_localctx).url = match(STRING);
			setState(285);
			match(T__40);
			setState(286);
			((RestApiContext)_localctx).method = match(STRING);
			setState(287);
			match(T__41);
			setState(288);
			((RestApiContext)_localctx).header = json();
			setState(289);
			match(T__1);
			setState(290);
			((RestApiContext)_localctx).value = json();
			setState(291);
			match(T__2);
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(292);
				match(T__11);
				setState(293);
				((RestApiContext)_localctx).condition = expression();
				}
				}
				setState(298);
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
		enterRule(_localctx, 30, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(T__42);
			{
			setState(300);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(301);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(302);
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
		enterRule(_localctx, 32, RULE_json);
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				obj();
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
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
		enterRule(_localctx, 34, RULE_obj);
		int _la;
		try {
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				match(T__1);
				setState(309);
				pair();
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(310);
					match(T__43);
					setState(311);
					pair();
					}
					}
					setState(316);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(317);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				match(T__1);
				setState(320);
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
		enterRule(_localctx, 36, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(STRING);
			setState(324);
			match(T__44);
			setState(325);
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
		enterRule(_localctx, 38, RULE_arr);
		int _la;
		try {
			setState(340);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				match(T__45);
				setState(328);
				jValue();
				setState(333);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__43) {
					{
					{
					setState(329);
					match(T__43);
					setState(330);
					jValue();
					}
					}
					setState(335);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(336);
				match(T__46);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(T__45);
				setState(339);
				match(T__46);
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
		enterRule(_localctx, 40, RULE_jValue);
		try {
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(STRING);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				match(NUMBER);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				obj();
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 4);
				{
				setState(345);
				arr();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 5);
				{
				setState(346);
				match(T__47);
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 6);
				{
				setState(347);
				match(T__48);
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 7);
				{
				setState(348);
				match(T__49);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3<\u0162\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\3\3\3\3\4\3\4\3\4\7\4B\n\4\f"+
		"\4\16\4E\13\4\3\4\3\4\3\5\3\5\3\5\7\5L\n\5\f\5\16\5O\13\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6]\n\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7l\n\7\f\7\16\7o\13\7\3\7\3\7\7\7s\n\7"+
		"\f\7\16\7v\13\7\3\7\3\7\7\7z\n\7\f\7\16\7}\13\7\3\7\3\7\7\7\u0081\n\7"+
		"\f\7\16\7\u0084\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0091"+
		"\n\b\f\b\16\b\u0094\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a9\n\t\f\t\16\t\u00ac\13\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00b6\n\n\f\n\16\n\u00b9\13\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u00ca\n\13\f\13\16\13\u00cd\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\7\f\u00da\n\f\f\f\16\f\u00dd\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00f2\n\r\f\r\16"+
		"\r\u00f5\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u010a\n\16\f\16\16\16\u010d"+
		"\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0116\n\17\f\17\16\17\u0119"+
		"\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u0129\n\20\f\20\16\20\u012c\13\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\5\22\u0135\n\22\3\23\3\23\3\23\3\23\7\23\u013b\n\23\f\23\16"+
		"\23\u013e\13\23\3\23\3\23\3\23\3\23\5\23\u0144\n\23\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\7\25\u014e\n\25\f\25\16\25\u0151\13\25\3\25\3\25"+
		"\3\25\3\25\5\25\u0157\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0160"+
		"\n\26\3\26\2\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\2"+
		"\u0170\2,\3\2\2\2\4\64\3\2\2\2\6>\3\2\2\2\bH\3\2\2\2\n\\\3\2\2\2\f^\3"+
		"\2\2\2\16\u0085\3\2\2\2\20\u0095\3\2\2\2\22\u00ad\3\2\2\2\24\u00ba\3\2"+
		"\2\2\26\u00ce\3\2\2\2\30\u00de\3\2\2\2\32\u00f6\3\2\2\2\34\u010e\3\2\2"+
		"\2\36\u011a\3\2\2\2 \u012d\3\2\2\2\"\u0134\3\2\2\2$\u0143\3\2\2\2&\u0145"+
		"\3\2\2\2(\u0156\3\2\2\2*\u015f\3\2\2\2,-\7\3\2\2-.\7\66\2\2./\7\4\2\2"+
		"/\60\5\4\3\2\60\61\5\b\5\2\61\62\5\6\4\2\62\63\7\5\2\2\63\3\3\2\2\2\64"+
		"\65\7\6\2\2\659\7\4\2\2\668\5\n\6\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2"+
		"\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7\5\2\2=\5\3\2\2\2>?\7\7\2\2?C\7\4"+
		"\2\2@B\5\n\6\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2"+
		"\2\2FG\7\5\2\2G\7\3\2\2\2HI\7\b\2\2IM\7\4\2\2JL\5\n\6\2KJ\3\2\2\2LO\3"+
		"\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2PQ\7\5\2\2Q\t\3\2\2\2R]"+
		"\5\22\n\2S]\5\26\f\2T]\5\24\13\2U]\5\f\7\2V]\5\34\17\2W]\5\32\16\2X]\5"+
		"\30\r\2Y]\5\20\t\2Z]\5\36\20\2[]\5\16\b\2\\R\3\2\2\2\\S\3\2\2\2\\T\3\2"+
		"\2\2\\U\3\2\2\2\\V\3\2\2\2\\W\3\2\2\2\\X\3\2\2\2\\Y\3\2\2\2\\Z\3\2\2\2"+
		"\\[\3\2\2\2]\13\3\2\2\2^_\7\t\2\2_`\7\n\2\2`a\7\66\2\2ab\7\13\2\2bc\7"+
		"\66\2\2cd\7\f\2\2de\7\66\2\2ef\7\r\2\2fg\7\4\2\2gh\7\66\2\2hm\7\5\2\2"+
		"ij\7\16\2\2jl\5 \21\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2nt\3\2\2"+
		"\2om\3\2\2\2pq\7\17\2\2qs\7\65\2\2rp\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2"+
		"\2\2u{\3\2\2\2vt\3\2\2\2wx\7\20\2\2xz\7\65\2\2yw\3\2\2\2z}\3\2\2\2{y\3"+
		"\2\2\2{|\3\2\2\2|\u0082\3\2\2\2}{\3\2\2\2~\177\7\21\2\2\177\u0081\7\65"+
		"\2\2\u0080~\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\r\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7\22\2\2\u0086"+
		"\u0087\7\n\2\2\u0087\u0088\7\66\2\2\u0088\u0089\7\23\2\2\u0089\u008a\7"+
		"\66\2\2\u008a\u008b\7\r\2\2\u008b\u008c\7\4\2\2\u008c\u008d\7\66\2\2\u008d"+
		"\u0092\7\5\2\2\u008e\u008f\7\16\2\2\u008f\u0091\5 \21\2\u0090\u008e\3"+
		"\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\17\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\7\24\2\2\u0096\u0097\7\n\2"+
		"\2\u0097\u0098\7\66\2\2\u0098\u0099\7\25\2\2\u0099\u009a\7\66\2\2\u009a"+
		"\u009b\7\13\2\2\u009b\u009c\7\66\2\2\u009c\u009d\7\f\2\2\u009d\u009e\7"+
		"\66\2\2\u009e\u009f\7\26\2\2\u009f\u00a0\7\66\2\2\u00a0\u00a1\7\27\2\2"+
		"\u00a1\u00a2\7\66\2\2\u00a2\u00a3\7\r\2\2\u00a3\u00a4\7\4\2\2\u00a4\u00a5"+
		"\7\66\2\2\u00a5\u00aa\7\5\2\2\u00a6\u00a7\7\16\2\2\u00a7\u00a9\5 \21\2"+
		"\u00a8\u00a6\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\21\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\30\2\2\u00ae"+
		"\u00af\7\n\2\2\u00af\u00b0\7\66\2\2\u00b0\u00b1\7\4\2\2\u00b1\u00b2\7"+
		"\66\2\2\u00b2\u00b7\7\5\2\2\u00b3\u00b4\7\16\2\2\u00b4\u00b6\5 \21\2\u00b5"+
		"\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\23\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7\31\2\2\u00bb\u00bc"+
		"\7\n\2\2\u00bc\u00bd\7\66\2\2\u00bd\u00be\7\32\2\2\u00be\u00bf\7\66\2"+
		"\2\u00bf\u00c0\7\33\2\2\u00c0\u00c1\7\66\2\2\u00c1\u00c2\7\r\2\2\u00c2"+
		"\u00c3\7\66\2\2\u00c3\u00c4\7\34\2\2\u00c4\u00c5\7\4\2\2\u00c5\u00c6\7"+
		"\66\2\2\u00c6\u00cb\7\5\2\2\u00c7\u00c8\7\16\2\2\u00c8\u00ca\5 \21\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2"+
		"\2\2\u00cc\25\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\35\2\2\u00cf\u00d0"+
		"\7\n\2\2\u00d0\u00d1\7\66\2\2\u00d1\u00d2\7\36\2\2\u00d2\u00d3\7\66\2"+
		"\2\u00d3\u00d4\7\r\2\2\u00d4\u00d5\7\4\2\2\u00d5\u00d6\7\66\2\2\u00d6"+
		"\u00db\7\5\2\2\u00d7\u00d8\7\16\2\2\u00d8\u00da\5 \21\2\u00d9\u00d7\3"+
		"\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\27\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df\7\37\2\2\u00df\u00e0\7\n\2"+
		"\2\u00e0\u00e1\7\66\2\2\u00e1\u00e2\7 \2\2\u00e2\u00e3\7\66\2\2\u00e3"+
		"\u00e4\7!\2\2\u00e4\u00e5\7\66\2\2\u00e5\u00e6\7\"\2\2\u00e6\u00e7\7\66"+
		"\2\2\u00e7\u00e8\7#\2\2\u00e8\u00e9\7\66\2\2\u00e9\u00ea\7$\2\2\u00ea"+
		"\u00eb\7\66\2\2\u00eb\u00ec\7\r\2\2\u00ec\u00ed\7\4\2\2\u00ed\u00ee\7"+
		"\66\2\2\u00ee\u00f3\7\5\2\2\u00ef\u00f0\7\16\2\2\u00f0\u00f2\5 \21\2\u00f1"+
		"\u00ef\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4\31\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\7%\2\2\u00f7\u00f8"+
		"\7\n\2\2\u00f8\u00f9\7\66\2\2\u00f9\u00fa\7 \2\2\u00fa\u00fb\7\66\2\2"+
		"\u00fb\u00fc\7!\2\2\u00fc\u00fd\7\66\2\2\u00fd\u00fe\7\"\2\2\u00fe\u00ff"+
		"\7\66\2\2\u00ff\u0100\7&\2\2\u0100\u0101\7\66\2\2\u0101\u0102\7$\2\2\u0102"+
		"\u0103\7\66\2\2\u0103\u0104\7\r\2\2\u0104\u0105\7\4\2\2\u0105\u0106\7"+
		"\66\2\2\u0106\u010b\7\5\2\2\u0107\u0108\7\16\2\2\u0108\u010a\5 \21\2\u0109"+
		"\u0107\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\33\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\7\'\2\2\u010f\u0110"+
		"\7\n\2\2\u0110\u0111\7\66\2\2\u0111\u0112\7(\2\2\u0112\u0117\7\66\2\2"+
		"\u0113\u0114\7\16\2\2\u0114\u0116\5 \21\2\u0115\u0113\3\2\2\2\u0116\u0119"+
		"\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\35\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u011a\u011b\7)\2\2\u011b\u011c\7\n\2\2\u011c\u011d\7\66"+
		"\2\2\u011d\u011e\7*\2\2\u011e\u011f\7\66\2\2\u011f\u0120\7+\2\2\u0120"+
		"\u0121\7\66\2\2\u0121\u0122\7,\2\2\u0122\u0123\5\"\22\2\u0123\u0124\7"+
		"\4\2\2\u0124\u0125\5\"\22\2\u0125\u012a\7\5\2\2\u0126\u0127\7\16\2\2\u0127"+
		"\u0129\5 \21\2\u0128\u0126\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\37\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012e"+
		"\7-\2\2\u012e\u012f\7\66\2\2\u012f\u0130\78\2\2\u0130\u0131\7\66\2\2\u0131"+
		"!\3\2\2\2\u0132\u0135\5$\23\2\u0133\u0135\5(\25\2\u0134\u0132\3\2\2\2"+
		"\u0134\u0133\3\2\2\2\u0135#\3\2\2\2\u0136\u0137\7\4\2\2\u0137\u013c\5"+
		"&\24\2\u0138\u0139\7.\2\2\u0139\u013b\5&\24\2\u013a\u0138\3\2\2\2\u013b"+
		"\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2"+
		"\2\2\u013e\u013c\3\2\2\2\u013f\u0140\7\5\2\2\u0140\u0144\3\2\2\2\u0141"+
		"\u0142\7\4\2\2\u0142\u0144\7\5\2\2\u0143\u0136\3\2\2\2\u0143\u0141\3\2"+
		"\2\2\u0144%\3\2\2\2\u0145\u0146\7\66\2\2\u0146\u0147\7/\2\2\u0147\u0148"+
		"\5*\26\2\u0148\'\3\2\2\2\u0149\u014a\7\60\2\2\u014a\u014f\5*\26\2\u014b"+
		"\u014c\7.\2\2\u014c\u014e\5*\26\2\u014d\u014b\3\2\2\2\u014e\u0151\3\2"+
		"\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0152\u0153\7\61\2\2\u0153\u0157\3\2\2\2\u0154\u0155\7"+
		"\60\2\2\u0155\u0157\7\61\2\2\u0156\u0149\3\2\2\2\u0156\u0154\3\2\2\2\u0157"+
		")\3\2\2\2\u0158\u0160\7\66\2\2\u0159\u0160\7<\2\2\u015a\u0160\5$\23\2"+
		"\u015b\u0160\5(\25\2\u015c\u0160\7\62\2\2\u015d\u0160\7\63\2\2\u015e\u0160"+
		"\7\64\2\2\u015f\u0158\3\2\2\2\u015f\u0159\3\2\2\2\u015f\u015a\3\2\2\2"+
		"\u015f\u015b\3\2\2\2\u015f\u015c\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u015e"+
		"\3\2\2\2\u0160+\3\2\2\2\319CM\\mt{\u0082\u0092\u00aa\u00b7\u00cb\u00db"+
		"\u00f3\u010b\u0117\u012a\u0134\u013c\u0143\u014f\u0156\u015f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}