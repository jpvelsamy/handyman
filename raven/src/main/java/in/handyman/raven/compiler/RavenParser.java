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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, NON_ZERO_DIGIT=38, 
		STRING=39, CRLF=40, Operator=41, WS=42, COMMENT=43, LINE_COMMENT=44;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_copyData = 5, RULE_transform = 6, RULE_loadCsv = 7, 
		RULE_abort = 8, RULE_callProcess = 9, RULE_assign = 10, RULE_fbLead = 11, 
		RULE_fbFormDownload = 12, RULE_expression = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "copyData", 
			"transform", "loadCsv", "abort", "callProcess", "assign", "fbLead", "fbFormDownload", 
			"expression"
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
			"'into'", "'fbfd'", "'for-form'", "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", "Operator", "WS", "COMMENT", 
			"LINE_COMMENT"
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
			setState(28);
			match(T__0);
			setState(29);
			((ProcessContext)_localctx).name = match(STRING);
			setState(30);
			match(T__1);
			setState(31);
			((ProcessContext)_localctx).tryBlock = tryClause();
			setState(32);
			((ProcessContext)_localctx).catchBlock = catchClause();
			setState(33);
			((ProcessContext)_localctx).finallyBlock = finallyClause();
			setState(34);
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
			setState(36);
			match(T__3);
			setState(37);
			match(T__1);
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(38);
				((TryClauseContext)_localctx).action = action();
				((TryClauseContext)_localctx).actions.add(((TryClauseContext)_localctx).action);
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
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
			setState(46);
			match(T__4);
			setState(47);
			match(T__1);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(48);
				((FinallyClauseContext)_localctx).action = action();
				((FinallyClauseContext)_localctx).actions.add(((FinallyClauseContext)_localctx).action);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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
			setState(56);
			match(T__5);
			setState(57);
			match(T__1);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(58);
				((CatchClauseContext)_localctx).action = action();
				((CatchClauseContext)_localctx).actions.add(((CatchClauseContext)_localctx).action);
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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
		public FbFormDownloadContext fbFormDownload() {
			return getRuleContext(FbFormDownloadContext.class,0);
		}
		public FbLeadContext fbLead() {
			return getRuleContext(FbLeadContext.class,0);
		}
		public LoadCsvContext loadCsv() {
			return getRuleContext(LoadCsvContext.class,0);
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
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				{
				setState(66);
				abort();
				}
				break;
			case T__26:
				{
				setState(67);
				assign();
				}
				break;
			case T__22:
				{
				setState(68);
				callProcess();
				}
				break;
			case T__6:
				{
				setState(69);
				copyData();
				}
				break;
			case T__34:
				{
				setState(70);
				fbFormDownload();
				}
				break;
			case T__28:
				{
				setState(71);
				fbLead();
				}
				break;
			case T__17:
				{
				setState(72);
				loadCsv();
				}
				break;
			case T__15:
				{
				setState(73);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(76);
			match(T__6);
			setState(77);
			match(T__7);
			setState(78);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(79);
			match(T__8);
			setState(80);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(81);
			match(T__9);
			setState(82);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(83);
			match(T__10);
			setState(84);
			match(T__1);
			setState(85);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(86);
			match(T__2);
			setState(87);
			match(T__11);
			setState(88);
			((CopyDataContext)_localctx).condition = expression();
			setState(89);
			match(T__12);
			setState(90);
			((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
			setState(91);
			match(T__13);
			setState(92);
			((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
			setState(93);
			match(T__14);
			setState(94);
			((CopyDataContext)_localctx).writeBatchSize = match(NON_ZERO_DIGIT);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__15);
			setState(97);
			match(T__7);
			setState(98);
			((TransformContext)_localctx).name = match(STRING);
			setState(99);
			match(T__16);
			setState(100);
			((TransformContext)_localctx).on = match(STRING);
			setState(101);
			match(T__10);
			setState(102);
			match(T__1);
			setState(103);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(104);
			match(T__2);
			setState(105);
			match(T__11);
			setState(106);
			((TransformContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__17);
			setState(109);
			match(T__7);
			setState(110);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(111);
			match(T__18);
			setState(112);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(113);
			match(T__8);
			setState(114);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(115);
			match(T__9);
			setState(116);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(117);
			match(T__19);
			setState(118);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(119);
			match(T__20);
			setState(120);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(121);
			match(T__10);
			setState(122);
			match(T__1);
			setState(123);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(124);
			match(T__2);
			setState(125);
			match(T__11);
			setState(126);
			((LoadCsvContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(T__21);
			setState(129);
			match(T__7);
			setState(130);
			((AbortContext)_localctx).name = match(STRING);
			setState(131);
			match(T__1);
			setState(132);
			((AbortContext)_localctx).value = match(STRING);
			setState(133);
			match(T__2);
			setState(134);
			match(T__11);
			setState(135);
			((AbortContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__22);
			setState(138);
			match(T__7);
			setState(139);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(140);
			match(T__23);
			setState(141);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(142);
			match(T__24);
			setState(143);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(144);
			match(T__10);
			setState(145);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(146);
			match(T__25);
			setState(147);
			match(T__1);
			setState(148);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(149);
			match(T__2);
			setState(150);
			match(T__11);
			setState(151);
			((CallProcessContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__26);
			setState(154);
			match(T__7);
			setState(155);
			((AssignContext)_localctx).name = match(STRING);
			setState(156);
			match(T__27);
			setState(157);
			((AssignContext)_localctx).source = match(STRING);
			setState(158);
			match(T__10);
			setState(159);
			match(T__1);
			setState(160);
			((AssignContext)_localctx).value = match(STRING);
			setState(161);
			match(T__2);
			setState(162);
			match(T__11);
			setState(163);
			((AssignContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(T__28);
			setState(166);
			match(T__7);
			setState(167);
			((FbLeadContext)_localctx).name = match(STRING);
			setState(168);
			match(T__29);
			setState(169);
			((FbLeadContext)_localctx).accessToken = match(STRING);
			setState(170);
			match(T__30);
			setState(171);
			((FbLeadContext)_localctx).appSecret = match(STRING);
			setState(172);
			match(T__31);
			setState(173);
			((FbLeadContext)_localctx).accountId = match(STRING);
			setState(174);
			match(T__32);
			setState(175);
			((FbLeadContext)_localctx).campaignId = match(STRING);
			setState(176);
			match(T__33);
			setState(177);
			((FbLeadContext)_localctx).target = match(STRING);
			setState(178);
			match(T__10);
			setState(179);
			match(T__1);
			setState(180);
			((FbLeadContext)_localctx).value = match(STRING);
			setState(181);
			match(T__2);
			setState(182);
			match(T__11);
			setState(183);
			((FbLeadContext)_localctx).condition = expression();
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(T__34);
			setState(186);
			match(T__7);
			setState(187);
			((FbFormDownloadContext)_localctx).name = match(STRING);
			setState(188);
			match(T__29);
			setState(189);
			((FbFormDownloadContext)_localctx).accessToken = match(STRING);
			setState(190);
			match(T__30);
			setState(191);
			((FbFormDownloadContext)_localctx).appSecret = match(STRING);
			setState(192);
			match(T__31);
			setState(193);
			((FbFormDownloadContext)_localctx).accountId = match(STRING);
			setState(194);
			match(T__35);
			setState(195);
			((FbFormDownloadContext)_localctx).formId = match(STRING);
			setState(196);
			match(T__33);
			setState(197);
			((FbFormDownloadContext)_localctx).target = match(STRING);
			setState(198);
			match(T__10);
			setState(199);
			match(T__1);
			setState(200);
			((FbFormDownloadContext)_localctx).value = match(STRING);
			setState(201);
			match(T__2);
			setState(202);
			match(T__11);
			setState(203);
			((FbFormDownloadContext)_localctx).condition = expression();
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
		enterRule(_localctx, 26, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__36);
			{
			setState(206);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(207);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(208);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u00d5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\3\3\3\3\4\3\4\3\4\7\4\64\n\4"+
		"\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\2\2"+
		"\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\2\u00d0\2\36\3\2\2\2\4&\3"+
		"\2\2\2\6\60\3\2\2\2\b:\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16b\3\2\2\2\20n\3"+
		"\2\2\2\22\u0082\3\2\2\2\24\u008b\3\2\2\2\26\u009b\3\2\2\2\30\u00a7\3\2"+
		"\2\2\32\u00bb\3\2\2\2\34\u00cf\3\2\2\2\36\37\7\3\2\2\37 \7)\2\2 !\7\4"+
		"\2\2!\"\5\4\3\2\"#\5\b\5\2#$\5\6\4\2$%\7\5\2\2%\3\3\2\2\2&\'\7\6\2\2\'"+
		"+\7\4\2\2(*\5\n\6\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2"+
		"-+\3\2\2\2./\7\5\2\2/\5\3\2\2\2\60\61\7\7\2\2\61\65\7\4\2\2\62\64\5\n"+
		"\6\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2"+
		"\2\67\65\3\2\2\289\7\5\2\29\7\3\2\2\2:;\7\b\2\2;?\7\4\2\2<>\5\n\6\2=<"+
		"\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\5\2\2C"+
		"\t\3\2\2\2DM\5\22\n\2EM\5\26\f\2FM\5\24\13\2GM\5\f\7\2HM\5\32\16\2IM\5"+
		"\30\r\2JM\5\20\t\2KM\5\16\b\2LD\3\2\2\2LE\3\2\2\2LF\3\2\2\2LG\3\2\2\2"+
		"LH\3\2\2\2LI\3\2\2\2LJ\3\2\2\2LK\3\2\2\2M\13\3\2\2\2NO\7\t\2\2OP\7\n\2"+
		"\2PQ\7)\2\2QR\7\13\2\2RS\7)\2\2ST\7\f\2\2TU\7)\2\2UV\7\r\2\2VW\7\4\2\2"+
		"WX\7)\2\2XY\7\5\2\2YZ\7\16\2\2Z[\5\34\17\2[\\\7\17\2\2\\]\7(\2\2]^\7\20"+
		"\2\2^_\7(\2\2_`\7\21\2\2`a\7(\2\2a\r\3\2\2\2bc\7\22\2\2cd\7\n\2\2de\7"+
		")\2\2ef\7\23\2\2fg\7)\2\2gh\7\r\2\2hi\7\4\2\2ij\7)\2\2jk\7\5\2\2kl\7\16"+
		"\2\2lm\5\34\17\2m\17\3\2\2\2no\7\24\2\2op\7\n\2\2pq\7)\2\2qr\7\25\2\2"+
		"rs\7)\2\2st\7\13\2\2tu\7)\2\2uv\7\f\2\2vw\7)\2\2wx\7\26\2\2xy\7)\2\2y"+
		"z\7\27\2\2z{\7)\2\2{|\7\r\2\2|}\7\4\2\2}~\7)\2\2~\177\7\5\2\2\177\u0080"+
		"\7\16\2\2\u0080\u0081\5\34\17\2\u0081\21\3\2\2\2\u0082\u0083\7\30\2\2"+
		"\u0083\u0084\7\n\2\2\u0084\u0085\7)\2\2\u0085\u0086\7\4\2\2\u0086\u0087"+
		"\7)\2\2\u0087\u0088\7\5\2\2\u0088\u0089\7\16\2\2\u0089\u008a\5\34\17\2"+
		"\u008a\23\3\2\2\2\u008b\u008c\7\31\2\2\u008c\u008d\7\n\2\2\u008d\u008e"+
		"\7)\2\2\u008e\u008f\7\32\2\2\u008f\u0090\7)\2\2\u0090\u0091\7\33\2\2\u0091"+
		"\u0092\7)\2\2\u0092\u0093\7\r\2\2\u0093\u0094\7)\2\2\u0094\u0095\7\34"+
		"\2\2\u0095\u0096\7\4\2\2\u0096\u0097\7)\2\2\u0097\u0098\7\5\2\2\u0098"+
		"\u0099\7\16\2\2\u0099\u009a\5\34\17\2\u009a\25\3\2\2\2\u009b\u009c\7\35"+
		"\2\2\u009c\u009d\7\n\2\2\u009d\u009e\7)\2\2\u009e\u009f\7\36\2\2\u009f"+
		"\u00a0\7)\2\2\u00a0\u00a1\7\r\2\2\u00a1\u00a2\7\4\2\2\u00a2\u00a3\7)\2"+
		"\2\u00a3\u00a4\7\5\2\2\u00a4\u00a5\7\16\2\2\u00a5\u00a6\5\34\17\2\u00a6"+
		"\27\3\2\2\2\u00a7\u00a8\7\37\2\2\u00a8\u00a9\7\n\2\2\u00a9\u00aa\7)\2"+
		"\2\u00aa\u00ab\7 \2\2\u00ab\u00ac\7)\2\2\u00ac\u00ad\7!\2\2\u00ad\u00ae"+
		"\7)\2\2\u00ae\u00af\7\"\2\2\u00af\u00b0\7)\2\2\u00b0\u00b1\7#\2\2\u00b1"+
		"\u00b2\7)\2\2\u00b2\u00b3\7$\2\2\u00b3\u00b4\7)\2\2\u00b4\u00b5\7\r\2"+
		"\2\u00b5\u00b6\7\4\2\2\u00b6\u00b7\7)\2\2\u00b7\u00b8\7\5\2\2\u00b8\u00b9"+
		"\7\16\2\2\u00b9\u00ba\5\34\17\2\u00ba\31\3\2\2\2\u00bb\u00bc\7%\2\2\u00bc"+
		"\u00bd\7\n\2\2\u00bd\u00be\7)\2\2\u00be\u00bf\7 \2\2\u00bf\u00c0\7)\2"+
		"\2\u00c0\u00c1\7!\2\2\u00c1\u00c2\7)\2\2\u00c2\u00c3\7\"\2\2\u00c3\u00c4"+
		"\7)\2\2\u00c4\u00c5\7&\2\2\u00c5\u00c6\7)\2\2\u00c6\u00c7\7$\2\2\u00c7"+
		"\u00c8\7)\2\2\u00c8\u00c9\7\r\2\2\u00c9\u00ca\7\4\2\2\u00ca\u00cb\7)\2"+
		"\2\u00cb\u00cc\7\5\2\2\u00cc\u00cd\7\16\2\2\u00cd\u00ce\5\34\17\2\u00ce"+
		"\33\3\2\2\2\u00cf\u00d0\7\'\2\2\u00d0\u00d1\7)\2\2\u00d1\u00d2\7+\2\2"+
		"\u00d2\u00d3\7)\2\2\u00d3\35\3\2\2\2\6+\65?L";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}