// Generated from JSON.g4 by ANTLR 4.9.3

package in.handyman.raven.compiler;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JSONParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            STRING = 10, NUMBER = 11, WS = 12;
    public static final int
            RULE_json = 0, RULE_obj = 1, RULE_pair = 2, RULE_arr = 3, RULE_jValue = 4;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16<\4\2\t\2\4\3\t" +
                    "\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\5\2\17\n\2\3\3\3\3\3\3\3\3\7\3\25\n" +
                    "\3\f\3\16\3\30\13\3\3\3\3\3\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\5\3\5" +
                    "\3\5\3\5\7\5(\n\5\f\5\16\5+\13\5\3\5\3\5\3\5\3\5\5\5\61\n\5\3\6\3\6\3" +
                    "\6\3\6\3\6\3\6\3\6\5\6:\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2A\2\16\3\2\2\2\4" +
                    "\35\3\2\2\2\6\37\3\2\2\2\b\60\3\2\2\2\n9\3\2\2\2\f\17\5\4\3\2\r\17\5\b" +
                    "\5\2\16\f\3\2\2\2\16\r\3\2\2\2\17\3\3\2\2\2\20\21\7\3\2\2\21\26\5\6\4" +
                    "\2\22\23\7\4\2\2\23\25\5\6\4\2\24\22\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2" +
                    "\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\5\2\2\32\36\3\2\2" +
                    "\2\33\34\7\3\2\2\34\36\7\5\2\2\35\20\3\2\2\2\35\33\3\2\2\2\36\5\3\2\2" +
                    "\2\37 \7\f\2\2 !\7\6\2\2!\"\5\n\6\2\"\7\3\2\2\2#$\7\7\2\2$)\5\n\6\2%&" +
                    "\7\4\2\2&(\5\n\6\2\'%\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2" +
                    "+)\3\2\2\2,-\7\b\2\2-\61\3\2\2\2./\7\7\2\2/\61\7\b\2\2\60#\3\2\2\2\60" +
                    ".\3\2\2\2\61\t\3\2\2\2\62:\7\f\2\2\63:\7\r\2\2\64:\5\4\3\2\65:\5\b\5\2" +
                    "\66:\7\t\2\2\67:\7\n\2\28:\7\13\2\29\62\3\2\2\29\63\3\2\2\29\64\3\2\2" +
                    "\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\298\3\2\2\2:\13\3\2\2\2\b\16\26\35" +
                    ")\609";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION);
    }

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

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public JSONParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "json", "obj", "pair", "arr", "jValue"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'{'", "','", "'}'", "':'", "'['", "']'", "'true'", "'false'",
                "'null'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, "STRING",
                "NUMBER", "WS"
        };
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
    public String getGrammarFileName() {
        return "JSON.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final JsonContext json() throws RecognitionException {
        JsonContext _localctx = new JsonContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_json);
        try {
            setState(12);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__0:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(10);
                    obj();
                }
                break;
                case T__4:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(11);
                    arr();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ObjContext obj() throws RecognitionException {
        ObjContext _localctx = new ObjContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_obj);
        int _la;
        try {
            setState(27);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(14);
                    match(T__0);
                    setState(15);
                    pair();
                    setState(20);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__1) {
                        {
                            {
                                setState(16);
                                match(T__1);
                                setState(17);
                                pair();
                            }
                        }
                        setState(22);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(23);
                    match(T__2);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(25);
                    match(T__0);
                    setState(26);
                    match(T__2);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PairContext pair() throws RecognitionException {
        PairContext _localctx = new PairContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_pair);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(29);
                match(STRING);
                setState(30);
                match(T__3);
                setState(31);
                jValue();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrContext arr() throws RecognitionException {
        ArrContext _localctx = new ArrContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_arr);
        int _la;
        try {
            setState(46);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(33);
                    match(T__4);
                    setState(34);
                    jValue();
                    setState(39);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__1) {
                        {
                            {
                                setState(35);
                                match(T__1);
                                setState(36);
                                jValue();
                            }
                        }
                        setState(41);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(42);
                    match(T__5);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(44);
                    match(T__4);
                    setState(45);
                    match(T__5);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final JValueContext jValue() throws RecognitionException {
        JValueContext _localctx = new JValueContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_jValue);
        try {
            setState(55);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(48);
                    match(STRING);
                }
                break;
                case NUMBER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(49);
                    match(NUMBER);
                }
                break;
                case T__0:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(50);
                    obj();
                }
                break;
                case T__4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(51);
                    arr();
                }
                break;
                case T__6:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(52);
                    match(T__6);
                }
                break;
                case T__7:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(53);
                    match(T__7);
                }
                break;
                case T__8:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(54);
                    match(T__8);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class JsonContext extends ParserRuleContext {
        public JsonContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ObjContext obj() {
            return getRuleContext(ObjContext.class, 0);
        }

        public ArrContext arr() {
            return getRuleContext(ArrContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_json;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).enterJson(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).exitJson(this);
        }
    }

    public static class ObjContext extends ParserRuleContext {
        public ObjContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<PairContext> pair() {
            return getRuleContexts(PairContext.class);
        }

        public PairContext pair(int i) {
            return getRuleContext(PairContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_obj;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).enterObj(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).exitObj(this);
        }
    }

    public static class PairContext extends ParserRuleContext {
        public PairContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(JSONParser.STRING, 0);
        }

        public JValueContext jValue() {
            return getRuleContext(JValueContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pair;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).enterPair(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).exitPair(this);
        }
    }

    public static class ArrContext extends ParserRuleContext {
        public ArrContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<JValueContext> jValue() {
            return getRuleContexts(JValueContext.class);
        }

        public JValueContext jValue(int i) {
            return getRuleContext(JValueContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).enterArr(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).exitArr(this);
        }
    }

    public static class JValueContext extends ParserRuleContext {
        public JValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(JSONParser.STRING, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(JSONParser.NUMBER, 0);
        }

        public ObjContext obj() {
            return getRuleContext(ObjContext.class, 0);
        }

        public ArrContext arr() {
            return getRuleContext(ArrContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_jValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).enterJValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof JSONListener) ((JSONListener) listener).exitJValue(this);
        }
    }
}
