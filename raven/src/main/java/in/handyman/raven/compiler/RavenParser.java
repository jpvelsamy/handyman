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
		T__38=39, NON_ZERO_DIGIT=40, STRING=41, CRLF=42, Operator=43, WS=44, COMMENT=45, 
		LINE_COMMENT=46;
	public static final int
		RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3, 
		RULE_action = 4, RULE_copyData = 5, RULE_transform = 6, RULE_loadCsv = 7, 
		RULE_abort = 8, RULE_callProcess = 9, RULE_assign = 10, RULE_fbLead = 11, 
		RULE_fbFormDownload = 12, RULE_dropFile = 13, RULE_expression = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"process", "tryClause", "finallyClause", "catchClause", "action", "copyData", 
			"transform", "loadCsv", "abort", "callProcess", "assign", "fbLead", "fbFormDownload", 
			"dropFile", "expression"
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
			"'into'", "'fbfd'", "'for-form'", "'dropfile'", "'in-path'", "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", "Operator", 
			"WS", "COMMENT", "LINE_COMMENT"
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
			setState(30);
			match(T__0);
			setState(31);
			((ProcessContext)_localctx).name = match(STRING);
			setState(32);
			match(T__1);
			setState(33);
			((ProcessContext)_localctx).tryBlock = tryClause();
			setState(34);
			((ProcessContext)_localctx).catchBlock = catchClause();
			setState(35);
			((ProcessContext)_localctx).finallyBlock = finallyClause();
			setState(36);
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
			setState(38);
			match(T__3);
			setState(39);
			match(T__1);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(40);
				((TryClauseContext)_localctx).action = action();
				((TryClauseContext)_localctx).actions.add(((TryClauseContext)_localctx).action);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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
			setState(48);
			match(T__4);
			setState(49);
			match(T__1);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(50);
				((FinallyClauseContext)_localctx).action = action();
				((FinallyClauseContext)_localctx).actions.add(((FinallyClauseContext)_localctx).action);
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
			setState(58);
			match(T__5);
			setState(59);
			match(T__1);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__34))) != 0)) {
				{
				{
				setState(60);
				((CatchClauseContext)_localctx).action = action();
				((CatchClauseContext)_localctx).actions.add(((CatchClauseContext)_localctx).action);
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
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				{
				setState(68);
				abort();
				}
				break;
			case T__26:
				{
				setState(69);
				assign();
				}
				break;
			case T__22:
				{
				setState(70);
				callProcess();
				}
				break;
			case T__6:
				{
				setState(71);
				copyData();
				}
				break;
			case T__34:
				{
				setState(72);
				fbFormDownload();
				}
				break;
			case T__28:
				{
				setState(73);
				fbLead();
				}
				break;
			case T__17:
				{
				setState(74);
				loadCsv();
				}
				break;
			case T__15:
				{
				setState(75);
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
			setState(78);
			match(T__6);
			setState(79);
			match(T__7);
			setState(80);
			((CopyDataContext)_localctx).name = match(STRING);
			setState(81);
			match(T__8);
			setState(82);
			((CopyDataContext)_localctx).source = match(STRING);
			setState(83);
			match(T__9);
			setState(84);
			((CopyDataContext)_localctx).to = match(STRING);
			setState(85);
			match(T__10);
			setState(86);
			match(T__1);
			setState(87);
			((CopyDataContext)_localctx).value = match(STRING);
			setState(88);
			match(T__2);
			setState(89);
			match(T__11);
			setState(90);
			((CopyDataContext)_localctx).condition = expression();
			setState(91);
			match(T__12);
			setState(92);
			((CopyDataContext)_localctx).writeThreadCount = match(NON_ZERO_DIGIT);
			setState(93);
			match(T__13);
			setState(94);
			((CopyDataContext)_localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
			setState(95);
			match(T__14);
			setState(96);
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
			setState(98);
			match(T__15);
			setState(99);
			match(T__7);
			setState(100);
			((TransformContext)_localctx).name = match(STRING);
			setState(101);
			match(T__16);
			setState(102);
			((TransformContext)_localctx).on = match(STRING);
			setState(103);
			match(T__10);
			setState(104);
			match(T__1);
			setState(105);
			((TransformContext)_localctx).STRING = match(STRING);
			((TransformContext)_localctx).value.add(((TransformContext)_localctx).STRING);
			setState(106);
			match(T__2);
			setState(107);
			match(T__11);
			setState(108);
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
			setState(110);
			match(T__17);
			setState(111);
			match(T__7);
			setState(112);
			((LoadCsvContext)_localctx).name = match(STRING);
			setState(113);
			match(T__18);
			setState(114);
			((LoadCsvContext)_localctx).pid = match(STRING);
			setState(115);
			match(T__8);
			setState(116);
			((LoadCsvContext)_localctx).source = match(STRING);
			setState(117);
			match(T__9);
			setState(118);
			((LoadCsvContext)_localctx).to = match(STRING);
			setState(119);
			match(T__19);
			setState(120);
			((LoadCsvContext)_localctx).delim = match(STRING);
			setState(121);
			match(T__20);
			setState(122);
			((LoadCsvContext)_localctx).limit = match(STRING);
			setState(123);
			match(T__10);
			setState(124);
			match(T__1);
			setState(125);
			((LoadCsvContext)_localctx).value = match(STRING);
			setState(126);
			match(T__2);
			setState(127);
			match(T__11);
			setState(128);
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
			setState(130);
			match(T__21);
			setState(131);
			match(T__7);
			setState(132);
			((AbortContext)_localctx).name = match(STRING);
			setState(133);
			match(T__1);
			setState(134);
			((AbortContext)_localctx).value = match(STRING);
			setState(135);
			match(T__2);
			setState(136);
			match(T__11);
			setState(137);
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
			setState(139);
			match(T__22);
			setState(140);
			match(T__7);
			setState(141);
			((CallProcessContext)_localctx).name = match(STRING);
			setState(142);
			match(T__23);
			setState(143);
			((CallProcessContext)_localctx).target = match(STRING);
			setState(144);
			match(T__24);
			setState(145);
			((CallProcessContext)_localctx).source = match(STRING);
			setState(146);
			match(T__10);
			setState(147);
			((CallProcessContext)_localctx).datasource = match(STRING);
			setState(148);
			match(T__25);
			setState(149);
			match(T__1);
			setState(150);
			((CallProcessContext)_localctx).value = match(STRING);
			setState(151);
			match(T__2);
			setState(152);
			match(T__11);
			setState(153);
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
			setState(155);
			match(T__26);
			setState(156);
			match(T__7);
			setState(157);
			((AssignContext)_localctx).name = match(STRING);
			setState(158);
			match(T__27);
			setState(159);
			((AssignContext)_localctx).source = match(STRING);
			setState(160);
			match(T__10);
			setState(161);
			match(T__1);
			setState(162);
			((AssignContext)_localctx).value = match(STRING);
			setState(163);
			match(T__2);
			setState(164);
			match(T__11);
			setState(165);
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
			setState(167);
			match(T__28);
			setState(168);
			match(T__7);
			setState(169);
			((FbLeadContext)_localctx).name = match(STRING);
			setState(170);
			match(T__29);
			setState(171);
			((FbLeadContext)_localctx).accessToken = match(STRING);
			setState(172);
			match(T__30);
			setState(173);
			((FbLeadContext)_localctx).appSecret = match(STRING);
			setState(174);
			match(T__31);
			setState(175);
			((FbLeadContext)_localctx).accountId = match(STRING);
			setState(176);
			match(T__32);
			setState(177);
			((FbLeadContext)_localctx).campaignId = match(STRING);
			setState(178);
			match(T__33);
			setState(179);
			((FbLeadContext)_localctx).target = match(STRING);
			setState(180);
			match(T__10);
			setState(181);
			match(T__1);
			setState(182);
			((FbLeadContext)_localctx).value = match(STRING);
			setState(183);
			match(T__2);
			setState(184);
			match(T__11);
			setState(185);
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
			setState(187);
			match(T__34);
			setState(188);
			match(T__7);
			setState(189);
			((FbFormDownloadContext)_localctx).name = match(STRING);
			setState(190);
			match(T__29);
			setState(191);
			((FbFormDownloadContext)_localctx).accessToken = match(STRING);
			setState(192);
			match(T__30);
			setState(193);
			((FbFormDownloadContext)_localctx).appSecret = match(STRING);
			setState(194);
			match(T__31);
			setState(195);
			((FbFormDownloadContext)_localctx).accountId = match(STRING);
			setState(196);
			match(T__35);
			setState(197);
			((FbFormDownloadContext)_localctx).formId = match(STRING);
			setState(198);
			match(T__33);
			setState(199);
			((FbFormDownloadContext)_localctx).target = match(STRING);
			setState(200);
			match(T__10);
			setState(201);
			match(T__1);
			setState(202);
			((FbFormDownloadContext)_localctx).value = match(STRING);
			setState(203);
			match(T__2);
			setState(204);
			match(T__11);
			setState(205);
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

	public static class DropFileContext extends ParserRuleContext {
		public Token name;
		public Token target;
		public ExpressionContext condition;
		public List<TerminalNode> STRING() { return getTokens(RavenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(RavenParser.STRING, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(T__36);
			setState(208);
			match(T__7);
			setState(209);
			((DropFileContext)_localctx).name = match(STRING);
			setState(210);
			match(T__37);
			setState(211);
			((DropFileContext)_localctx).target = match(STRING);
			setState(212);
			match(T__11);
			setState(213);
			((DropFileContext)_localctx).condition = expression();
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
		enterRule(_localctx, 28, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__38);
			{
			setState(216);
			((ExpressionContext)_localctx).lhs = match(STRING);
			setState(217);
			((ExpressionContext)_localctx).operator = match(Operator);
			setState(218);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u00df\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\3\4\3\4\3\4"+
		"\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\7\5@\n\5\f\5\16\5C\13\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36\2\2\2\u00d9\2 \3\2\2\2\4(\3\2\2\2\6\62\3\2\2\2\b"+
		"<\3\2\2\2\nN\3\2\2\2\fP\3\2\2\2\16d\3\2\2\2\20p\3\2\2\2\22\u0084\3\2\2"+
		"\2\24\u008d\3\2\2\2\26\u009d\3\2\2\2\30\u00a9\3\2\2\2\32\u00bd\3\2\2\2"+
		"\34\u00d1\3\2\2\2\36\u00d9\3\2\2\2 !\7\3\2\2!\"\7+\2\2\"#\7\4\2\2#$\5"+
		"\4\3\2$%\5\b\5\2%&\5\6\4\2&\'\7\5\2\2\'\3\3\2\2\2()\7\6\2\2)-\7\4\2\2"+
		"*,\5\n\6\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2"+
		"\2\60\61\7\5\2\2\61\5\3\2\2\2\62\63\7\7\2\2\63\67\7\4\2\2\64\66\5\n\6"+
		"\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3"+
		"\2\2\2:;\7\5\2\2;\7\3\2\2\2<=\7\b\2\2=A\7\4\2\2>@\5\n\6\2?>\3\2\2\2@C"+
		"\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\5\2\2E\t\3\2\2\2"+
		"FO\5\22\n\2GO\5\26\f\2HO\5\24\13\2IO\5\f\7\2JO\5\32\16\2KO\5\30\r\2LO"+
		"\5\20\t\2MO\5\16\b\2NF\3\2\2\2NG\3\2\2\2NH\3\2\2\2NI\3\2\2\2NJ\3\2\2\2"+
		"NK\3\2\2\2NL\3\2\2\2NM\3\2\2\2O\13\3\2\2\2PQ\7\t\2\2QR\7\n\2\2RS\7+\2"+
		"\2ST\7\13\2\2TU\7+\2\2UV\7\f\2\2VW\7+\2\2WX\7\r\2\2XY\7\4\2\2YZ\7+\2\2"+
		"Z[\7\5\2\2[\\\7\16\2\2\\]\5\36\20\2]^\7\17\2\2^_\7*\2\2_`\7\20\2\2`a\7"+
		"*\2\2ab\7\21\2\2bc\7*\2\2c\r\3\2\2\2de\7\22\2\2ef\7\n\2\2fg\7+\2\2gh\7"+
		"\23\2\2hi\7+\2\2ij\7\r\2\2jk\7\4\2\2kl\7+\2\2lm\7\5\2\2mn\7\16\2\2no\5"+
		"\36\20\2o\17\3\2\2\2pq\7\24\2\2qr\7\n\2\2rs\7+\2\2st\7\25\2\2tu\7+\2\2"+
		"uv\7\13\2\2vw\7+\2\2wx\7\f\2\2xy\7+\2\2yz\7\26\2\2z{\7+\2\2{|\7\27\2\2"+
		"|}\7+\2\2}~\7\r\2\2~\177\7\4\2\2\177\u0080\7+\2\2\u0080\u0081\7\5\2\2"+
		"\u0081\u0082\7\16\2\2\u0082\u0083\5\36\20\2\u0083\21\3\2\2\2\u0084\u0085"+
		"\7\30\2\2\u0085\u0086\7\n\2\2\u0086\u0087\7+\2\2\u0087\u0088\7\4\2\2\u0088"+
		"\u0089\7+\2\2\u0089\u008a\7\5\2\2\u008a\u008b\7\16\2\2\u008b\u008c\5\36"+
		"\20\2\u008c\23\3\2\2\2\u008d\u008e\7\31\2\2\u008e\u008f\7\n\2\2\u008f"+
		"\u0090\7+\2\2\u0090\u0091\7\32\2\2\u0091\u0092\7+\2\2\u0092\u0093\7\33"+
		"\2\2\u0093\u0094\7+\2\2\u0094\u0095\7\r\2\2\u0095\u0096\7+\2\2\u0096\u0097"+
		"\7\34\2\2\u0097\u0098\7\4\2\2\u0098\u0099\7+\2\2\u0099\u009a\7\5\2\2\u009a"+
		"\u009b\7\16\2\2\u009b\u009c\5\36\20\2\u009c\25\3\2\2\2\u009d\u009e\7\35"+
		"\2\2\u009e\u009f\7\n\2\2\u009f\u00a0\7+\2\2\u00a0\u00a1\7\36\2\2\u00a1"+
		"\u00a2\7+\2\2\u00a2\u00a3\7\r\2\2\u00a3\u00a4\7\4\2\2\u00a4\u00a5\7+\2"+
		"\2\u00a5\u00a6\7\5\2\2\u00a6\u00a7\7\16\2\2\u00a7\u00a8\5\36\20\2\u00a8"+
		"\27\3\2\2\2\u00a9\u00aa\7\37\2\2\u00aa\u00ab\7\n\2\2\u00ab\u00ac\7+\2"+
		"\2\u00ac\u00ad\7 \2\2\u00ad\u00ae\7+\2\2\u00ae\u00af\7!\2\2\u00af\u00b0"+
		"\7+\2\2\u00b0\u00b1\7\"\2\2\u00b1\u00b2\7+\2\2\u00b2\u00b3\7#\2\2\u00b3"+
		"\u00b4\7+\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7+\2\2\u00b6\u00b7\7\r\2"+
		"\2\u00b7\u00b8\7\4\2\2\u00b8\u00b9\7+\2\2\u00b9\u00ba\7\5\2\2\u00ba\u00bb"+
		"\7\16\2\2\u00bb\u00bc\5\36\20\2\u00bc\31\3\2\2\2\u00bd\u00be\7%\2\2\u00be"+
		"\u00bf\7\n\2\2\u00bf\u00c0\7+\2\2\u00c0\u00c1\7 \2\2\u00c1\u00c2\7+\2"+
		"\2\u00c2\u00c3\7!\2\2\u00c3\u00c4\7+\2\2\u00c4\u00c5\7\"\2\2\u00c5\u00c6"+
		"\7+\2\2\u00c6\u00c7\7&\2\2\u00c7\u00c8\7+\2\2\u00c8\u00c9\7$\2\2\u00c9"+
		"\u00ca\7+\2\2\u00ca\u00cb\7\r\2\2\u00cb\u00cc\7\4\2\2\u00cc\u00cd\7+\2"+
		"\2\u00cd\u00ce\7\5\2\2\u00ce\u00cf\7\16\2\2\u00cf\u00d0\5\36\20\2\u00d0"+
		"\33\3\2\2\2\u00d1\u00d2\7\'\2\2\u00d2\u00d3\7\n\2\2\u00d3\u00d4\7+\2\2"+
		"\u00d4\u00d5\7(\2\2\u00d5\u00d6\7+\2\2\u00d6\u00d7\7\16\2\2\u00d7\u00d8"+
		"\5\36\20\2\u00d8\35\3\2\2\2\u00d9\u00da\7)\2\2\u00da\u00db\7+\2\2\u00db"+
		"\u00dc\7-\2\2\u00dc\u00dd\7+\2\2\u00dd\37\3\2\2\2\6-\67AN";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}