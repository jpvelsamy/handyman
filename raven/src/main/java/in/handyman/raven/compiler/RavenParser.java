// Generated from Raven.g4 by ANTLR 4.9.2

package in.handyman.raven.compiler;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RavenParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
            T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
            T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
            T__45 = 46, NON_ZERO_DIGIT = 47, STRING = 48, CRLF = 49, Operator = 50, WS = 51, COMMENT = 52,
            LINE_COMMENT = 53, NUMBER = 54;
    public static final int
            RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3,
            RULE_action = 4, RULE_copyData = 5, RULE_transform = 6, RULE_loadCsv = 7,
            RULE_abort = 8, RULE_callProcess = 9, RULE_assign = 10, RULE_dropFile = 11,
            RULE_restApi = 12, RULE_restPart = 13, RULE_expression = 14, RULE_json = 15,
            RULE_obj = 16, RULE_pair = 17, RULE_arr = 18, RULE_jValue = 19;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u0155\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3" +
                    "\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\3\4\3\4\3\4\7\4@\n\4\f\4\16\4C" +
                    "\13\4\3\4\3\4\3\5\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\5\3\5\3\6\3\6\3\6" +
                    "\3\6\3\6\3\6\3\6\3\6\5\6Y\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7" +
                    "\3\7\3\7\3\7\7\7h\n\7\f\7\16\7k\13\7\3\7\3\7\7\7o\n\7\f\7\16\7r\13\7\3" +
                    "\7\3\7\7\7v\n\7\f\7\16\7y\13\7\3\7\3\7\7\7}\n\7\f\7\16\7\u0080\13\7\3" +
                    "\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u008d\n\b\f\b\16\b\u0090" +
                    "\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t" +
                    "\3\t\3\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13\t\3\n\3\n\3\n\3\n\3\n\3\n" +
                    "\3\n\3\n\7\n\u00b2\n\n\f\n\16\n\u00b5\13\n\3\13\3\13\3\13\3\13\3\13\3" +
                    "\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00c6\n\13\f\13" +
                    "\16\13\u00c9\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00d6" +
                    "\n\f\f\f\16\f\u00d9\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00e2\n\r\f\r" +
                    "\16\r\u00e5\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
                    "\3\16\7\16\u00f3\n\16\f\16\16\16\u00f6\13\16\3\16\3\16\7\16\u00fa\n\16" +
                    "\f\16\16\16\u00fd\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0106" +
                    "\n\16\f\16\16\16\u0109\13\16\3\16\3\16\3\16\7\16\u010e\n\16\f\16\16\16" +
                    "\u0111\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u011a\n\17\f\17\16" +
                    "\17\u011d\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\5\21\u0128" +
                    "\n\21\3\22\3\22\3\22\3\22\7\22\u012e\n\22\f\22\16\22\u0131\13\22\3\22" +
                    "\3\22\3\22\3\22\5\22\u0137\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24" +
                    "\7\24\u0141\n\24\f\24\16\24\u0144\13\24\3\24\3\24\3\24\3\24\5\24\u014a" +
                    "\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0153\n\25\3\25\2\2\26\2" +
                    "\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\2\2\u0164\2*\3\2\2\2\4\62" +
                    "\3\2\2\2\6<\3\2\2\2\bF\3\2\2\2\nX\3\2\2\2\fZ\3\2\2\2\16\u0081\3\2\2\2" +
                    "\20\u0091\3\2\2\2\22\u00a9\3\2\2\2\24\u00b6\3\2\2\2\26\u00ca\3\2\2\2\30" +
                    "\u00da\3\2\2\2\32\u00e6\3\2\2\2\34\u0112\3\2\2\2\36\u0120\3\2\2\2 \u0127" +
                    "\3\2\2\2\"\u0136\3\2\2\2$\u0138\3\2\2\2&\u0149\3\2\2\2(\u0152\3\2\2\2" +
                    "*+\7\3\2\2+,\7\62\2\2,-\7\4\2\2-.\5\4\3\2./\5\b\5\2/\60\5\6\4\2\60\61" +
                    "\7\5\2\2\61\3\3\2\2\2\62\63\7\6\2\2\63\67\7\4\2\2\64\66\5\n\6\2\65\64" +
                    "\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:" +
                    ";\7\5\2\2;\5\3\2\2\2<=\7\7\2\2=A\7\4\2\2>@\5\n\6\2?>\3\2\2\2@C\3\2\2\2" +
                    "A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\5\2\2E\7\3\2\2\2FG\7\b\2" +
                    "\2GK\7\4\2\2HJ\5\n\6\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2" +
                    "\2MK\3\2\2\2NO\7\5\2\2O\t\3\2\2\2PY\5\22\n\2QY\5\26\f\2RY\5\24\13\2SY" +
                    "\5\f\7\2TY\5\30\r\2UY\5\20\t\2VY\5\32\16\2WY\5\16\b\2XP\3\2\2\2XQ\3\2" +
                    "\2\2XR\3\2\2\2XS\3\2\2\2XT\3\2\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\13\3" +
                    "\2\2\2Z[\7\t\2\2[\\\7\n\2\2\\]\7\62\2\2]^\7\13\2\2^_\7\62\2\2_`\7\f\2" +
                    "\2`a\7\62\2\2ab\7\r\2\2bc\7\4\2\2cd\7\62\2\2di\7\5\2\2ef\7\16\2\2fh\5" +
                    "\36\20\2ge\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jp\3\2\2\2ki\3\2\2\2l" +
                    "m\7\17\2\2mo\7\61\2\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qw\3\2\2" +
                    "\2rp\3\2\2\2st\7\20\2\2tv\7\61\2\2us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2" +
                    "\2\2x~\3\2\2\2yw\3\2\2\2z{\7\21\2\2{}\7\61\2\2|z\3\2\2\2}\u0080\3\2\2" +
                    "\2~|\3\2\2\2~\177\3\2\2\2\177\r\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\22" +
                    "\2\2\u0082\u0083\7\n\2\2\u0083\u0084\7\62\2\2\u0084\u0085\7\23\2\2\u0085" +
                    "\u0086\7\62\2\2\u0086\u0087\7\r\2\2\u0087\u0088\7\4\2\2\u0088\u0089\7" +
                    "\62\2\2\u0089\u008e\7\5\2\2\u008a\u008b\7\16\2\2\u008b\u008d\5\36\20\2" +
                    "\u008c\u008a\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f" +
                    "\3\2\2\2\u008f\17\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7\24\2\2\u0092" +
                    "\u0093\7\n\2\2\u0093\u0094\7\62\2\2\u0094\u0095\7\25\2\2\u0095\u0096\7" +
                    "\62\2\2\u0096\u0097\7\13\2\2\u0097\u0098\7\62\2\2\u0098\u0099\7\f\2\2" +
                    "\u0099\u009a\7\62\2\2\u009a\u009b\7\26\2\2\u009b\u009c\7\62\2\2\u009c" +
                    "\u009d\7\27\2\2\u009d\u009e\7\62\2\2\u009e\u009f\7\r\2\2\u009f\u00a0\7" +
                    "\4\2\2\u00a0\u00a1\7\62\2\2\u00a1\u00a6\7\5\2\2\u00a2\u00a3\7\16\2\2\u00a3" +
                    "\u00a5\5\36\20\2\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3" +
                    "\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\21\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9" +
                    "\u00aa\7\30\2\2\u00aa\u00ab\7\n\2\2\u00ab\u00ac\7\62\2\2\u00ac\u00ad\7" +
                    "\4\2\2\u00ad\u00ae\7\62\2\2\u00ae\u00b3\7\5\2\2\u00af\u00b0\7\16\2\2\u00b0" +
                    "\u00b2\5\36\20\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3" +
                    "\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\23\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6" +
                    "\u00b7\7\31\2\2\u00b7\u00b8\7\n\2\2\u00b8\u00b9\7\62\2\2\u00b9\u00ba\7" +
                    "\32\2\2\u00ba\u00bb\7\62\2\2\u00bb\u00bc\7\33\2\2\u00bc\u00bd\7\62\2\2" +
                    "\u00bd\u00be\7\r\2\2\u00be\u00bf\7\62\2\2\u00bf\u00c0\7\34\2\2\u00c0\u00c1" +
                    "\7\4\2\2\u00c1\u00c2\7\62\2\2\u00c2\u00c7\7\5\2\2\u00c3\u00c4\7\16\2\2" +
                    "\u00c4\u00c6\5\36\20\2\u00c5\u00c3\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5" +
                    "\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\25\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca" +
                    "\u00cb\7\35\2\2\u00cb\u00cc\7\n\2\2\u00cc\u00cd\7\62\2\2\u00cd\u00ce\7" +
                    "\36\2\2\u00ce\u00cf\7\62\2\2\u00cf\u00d0\7\r\2\2\u00d0\u00d1\7\4\2\2\u00d1" +
                    "\u00d2\7\62\2\2\u00d2\u00d7\7\5\2\2\u00d3\u00d4\7\16\2\2\u00d4\u00d6\5" +
                    "\36\20\2\u00d5\u00d3\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7" +
                    "\u00d8\3\2\2\2\u00d8\27\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7\37\2" +
                    "\2\u00db\u00dc\7\n\2\2\u00dc\u00dd\7\62\2\2\u00dd\u00de\7 \2\2\u00de\u00e3" +
                    "\7\62\2\2\u00df\u00e0\7\16\2\2\u00e0\u00e2\5\36\20\2\u00e1\u00df\3\2\2" +
                    "\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\31" +
                    "\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7!\2\2\u00e7\u00e8\7\n\2\2\u00e8" +
                    "\u00e9\7\62\2\2\u00e9\u00ea\7\36\2\2\u00ea\u00eb\7\62\2\2\u00eb\u00ec" +
                    "\7\r\2\2\u00ec\u00ed\7\"\2\2\u00ed\u00ee\7\62\2\2\u00ee\u00ef\7#\2\2\u00ef" +
                    "\u00f4\7\62\2\2\u00f0\u00f1\7$\2\2\u00f1\u00f3\5 \21\2\u00f2\u00f0\3\2" +
                    "\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5" +
                    "\u00fb\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\7%\2\2\u00f8\u00fa\5 \21" +
                    "\2\u00f9\u00f7\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc" +
                    "\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7&\2\2\u00ff" +
                    "\u0100\7\4\2\2\u0100\u0101\7\62\2\2\u0101\u0102\7\5\2\2\u0102\u0103\3" +
                    "\2\2\2\u0103\u0107\7\4\2\2\u0104\u0106\5\34\17\2\u0105\u0104\3\2\2\2\u0106" +
                    "\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2" +
                    "\2\2\u0109\u0107\3\2\2\2\u010a\u010f\7\5\2\2\u010b\u010c\7\16\2\2\u010c" +
                    "\u010e\5\36\20\2\u010d\u010b\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3" +
                    "\2\2\2\u010f\u0110\3\2\2\2\u0110\33\3\2\2\2\u0111\u010f\3\2\2\2\u0112" +
                    "\u0113\7\'\2\2\u0113\u0114\7\n\2\2\u0114\u0115\7\62\2\2\u0115\u0116\7" +
                    "\26\2\2\u0116\u011b\7\62\2\2\u0117\u0118\7(\2\2\u0118\u011a\7\62\2\2\u0119" +
                    "\u0117\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2" +
                    "\2\2\u011c\u011e\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u011f\7\5\2\2\u011f" +
                    "\35\3\2\2\2\u0120\u0121\7)\2\2\u0121\u0122\7\62\2\2\u0122\u0123\7\64\2" +
                    "\2\u0123\u0124\7\62\2\2\u0124\37\3\2\2\2\u0125\u0128\5\"\22\2\u0126\u0128" +
                    "\5&\24\2\u0127\u0125\3\2\2\2\u0127\u0126\3\2\2\2\u0128!\3\2\2\2\u0129" +
                    "\u012a\7\4\2\2\u012a\u012f\5$\23\2\u012b\u012c\7*\2\2\u012c\u012e\5$\23" +
                    "\2\u012d\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130" +
                    "\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7\5\2\2\u0133" +
                    "\u0137\3\2\2\2\u0134\u0135\7\4\2\2\u0135\u0137\7\5\2\2\u0136\u0129\3\2" +
                    "\2\2\u0136\u0134\3\2\2\2\u0137#\3\2\2\2\u0138\u0139\7\62\2\2\u0139\u013a" +
                    "\7+\2\2\u013a\u013b\5(\25\2\u013b%\3\2\2\2\u013c\u013d\7,\2\2\u013d\u0142" +
                    "\5(\25\2\u013e\u013f\7*\2\2\u013f\u0141\5(\25\2\u0140\u013e\3\2\2\2\u0141" +
                    "\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2" +
                    "\2\2\u0144\u0142\3\2\2\2\u0145\u0146\7-\2\2\u0146\u014a\3\2\2\2\u0147" +
                    "\u0148\7,\2\2\u0148\u014a\7-\2\2\u0149\u013c\3\2\2\2\u0149\u0147\3\2\2" +
                    "\2\u014a\'\3\2\2\2\u014b\u0153\7\62\2\2\u014c\u0153\78\2\2\u014d\u0153" +
                    "\5\"\22\2\u014e\u0153\5&\24\2\u014f\u0153\7.\2\2\u0150\u0153\7/\2\2\u0151" +
                    "\u0153\7\60\2\2\u0152\u014b\3\2\2\2\u0152\u014c\3\2\2\2\u0152\u014d\3" +
                    "\2\2\2\u0152\u014e\3\2\2\2\u0152\u014f\3\2\2\2\u0152\u0150\3\2\2\2\u0152" +
                    "\u0151\3\2\2\2\u0153)\3\2\2\2\33\67AKXipw~\u008e\u00a6\u00b3\u00c7\u00d7" +
                    "\u00e3\u00f4\u00fb\u0107\u010f\u011b\u0127\u012f\u0136\u0142\u0149\u0152";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION);
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

    public RavenParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "process", "tryClause", "finallyClause", "catchClause", "action", "copyData",
                "transform", "loadCsv", "abort", "callProcess", "assign", "dropFile",
                "restApi", "restPart", "expression", "json", "obj", "pair", "arr", "jValue"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'",
                "'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'",
                "'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'",
                "'on'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", "'abort'", "'callprocess'",
                "'with-target'", "'from-file'", "'for-every'", "'assign'", "'source'",
                "'dropfile'", "'in-path'", "'restapi'", "'url'", "'method'", "'with headers'",
                "'with params'", "'with body type'", "'{ part'", "'type as'", "'if'",
                "','", "':'", "'['", "']'", "'true'", "'false'", "'null'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, "NON_ZERO_DIGIT",
                "STRING", "CRLF", "Operator", "WS", "COMMENT", "LINE_COMMENT", "NUMBER"
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
        return "Raven.g4";
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

    public final ProcessContext process() throws RecognitionException {
        ProcessContext _localctx = new ProcessContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_process);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(40);
                match(T__0);
                setState(41);
                ((ProcessContext) _localctx).name = match(STRING);
                setState(42);
                match(T__1);
                setState(43);
                ((ProcessContext) _localctx).tryBlock = tryClause();
                setState(44);
                ((ProcessContext) _localctx).catchBlock = catchClause();
                setState(45);
                ((ProcessContext) _localctx).finallyBlock = finallyClause();
                setState(46);
                match(T__2);
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

    public final TryClauseContext tryClause() throws RecognitionException {
        TryClauseContext _localctx = new TryClauseContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_tryClause);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(48);
                match(T__3);
                setState(49);
                match(T__1);
                setState(53);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__30))) != 0)) {
                    {
                        {
                            setState(50);
                            ((TryClauseContext) _localctx).action = action();
                            ((TryClauseContext) _localctx).actions.add(((TryClauseContext) _localctx).action);
                        }
                    }
                    setState(55);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(56);
                match(T__2);
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

    public final FinallyClauseContext finallyClause() throws RecognitionException {
        FinallyClauseContext _localctx = new FinallyClauseContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_finallyClause);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(58);
                match(T__4);
                setState(59);
                match(T__1);
                setState(63);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__30))) != 0)) {
                    {
                        {
                            setState(60);
                            ((FinallyClauseContext) _localctx).action = action();
                            ((FinallyClauseContext) _localctx).actions.add(((FinallyClauseContext) _localctx).action);
                        }
                    }
                    setState(65);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(66);
                match(T__2);
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

    public final CatchClauseContext catchClause() throws RecognitionException {
        CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_catchClause);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(68);
                match(T__5);
                setState(69);
                match(T__1);
                setState(73);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__30))) != 0)) {
                    {
                        {
                            setState(70);
                            ((CatchClauseContext) _localctx).action = action();
                            ((CatchClauseContext) _localctx).actions.add(((CatchClauseContext) _localctx).action);
                        }
                    }
                    setState(75);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(76);
                match(T__2);
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

    public final ActionContext action() throws RecognitionException {
        ActionContext _localctx = new ActionContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_action);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(86);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__21: {
                        setState(78);
                        abort();
                    }
                    break;
                    case T__26: {
                        setState(79);
                        assign();
                    }
                    break;
                    case T__22: {
                        setState(80);
                        callProcess();
                    }
                    break;
                    case T__6: {
                        setState(81);
                        copyData();
                    }
                    break;
                    case T__28: {
                        setState(82);
                        dropFile();
                    }
                    break;
                    case T__17: {
                        setState(83);
                        loadCsv();
                    }
                    break;
                    case T__30: {
                        setState(84);
                        restApi();
                    }
                    break;
                    case T__15: {
                        setState(85);
                        transform();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
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

    public final CopyDataContext copyData() throws RecognitionException {
        CopyDataContext _localctx = new CopyDataContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_copyData);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(88);
                    match(T__6);
                    setState(89);
                    match(T__7);
                    setState(90);
                    ((CopyDataContext) _localctx).name = match(STRING);
                    setState(91);
                    match(T__8);
                    setState(92);
                    ((CopyDataContext) _localctx).source = match(STRING);
                    setState(93);
                    match(T__9);
                    setState(94);
                    ((CopyDataContext) _localctx).to = match(STRING);
                    setState(95);
                    match(T__10);
                    setState(96);
                    match(T__1);
                    setState(97);
                    ((CopyDataContext) _localctx).value = match(STRING);
                    setState(98);
                    match(T__2);
                    setState(103);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__11) {
                        {
                            {
                                setState(99);
                                match(T__11);
                                setState(100);
                                ((CopyDataContext) _localctx).condition = expression();
                            }
                        }
                        setState(105);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(110);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__12) {
                        {
                            {
                                setState(106);
                                match(T__12);
                                setState(107);
                                ((CopyDataContext) _localctx).writeThreadCount = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(112);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(117);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__13) {
                        {
                            {
                                setState(113);
                                match(T__13);
                                setState(114);
                                ((CopyDataContext) _localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(119);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(124);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__14) {
                        {
                            {
                                setState(120);
                                match(T__14);
                                setState(121);
                                ((CopyDataContext) _localctx).writeBatchSize = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(126);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
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

    public final TransformContext transform() throws RecognitionException {
        TransformContext _localctx = new TransformContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_transform);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(127);
                match(T__15);
                setState(128);
                match(T__7);
                setState(129);
                ((TransformContext) _localctx).name = match(STRING);
                setState(130);
                match(T__16);
                setState(131);
                ((TransformContext) _localctx).on = match(STRING);
                setState(132);
                match(T__10);
                setState(133);
                match(T__1);
                setState(134);
                ((TransformContext) _localctx).STRING = match(STRING);
                ((TransformContext) _localctx).value.add(((TransformContext) _localctx).STRING);
                setState(135);
                match(T__2);
                setState(140);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(136);
                            match(T__11);
                            setState(137);
                            ((TransformContext) _localctx).condition = expression();
                        }
                    }
                    setState(142);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final LoadCsvContext loadCsv() throws RecognitionException {
        LoadCsvContext _localctx = new LoadCsvContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_loadCsv);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(143);
                match(T__17);
                setState(144);
                match(T__7);
                setState(145);
                ((LoadCsvContext) _localctx).name = match(STRING);
                setState(146);
                match(T__18);
                setState(147);
                ((LoadCsvContext) _localctx).pid = match(STRING);
                setState(148);
                match(T__8);
                setState(149);
                ((LoadCsvContext) _localctx).source = match(STRING);
                setState(150);
                match(T__9);
                setState(151);
                ((LoadCsvContext) _localctx).to = match(STRING);
                setState(152);
                match(T__19);
                setState(153);
                ((LoadCsvContext) _localctx).delim = match(STRING);
                setState(154);
                match(T__20);
                setState(155);
                ((LoadCsvContext) _localctx).limit = match(STRING);
                setState(156);
                match(T__10);
                setState(157);
                match(T__1);
                setState(158);
                ((LoadCsvContext) _localctx).value = match(STRING);
                setState(159);
                match(T__2);
                setState(164);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(160);
                            match(T__11);
                            setState(161);
                            ((LoadCsvContext) _localctx).condition = expression();
                        }
                    }
                    setState(166);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final AbortContext abort() throws RecognitionException {
        AbortContext _localctx = new AbortContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_abort);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(167);
                match(T__21);
                setState(168);
                match(T__7);
                setState(169);
                ((AbortContext) _localctx).name = match(STRING);
                setState(170);
                match(T__1);
                setState(171);
                ((AbortContext) _localctx).value = match(STRING);
                setState(172);
                match(T__2);
                setState(177);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(173);
                            match(T__11);
                            setState(174);
                            ((AbortContext) _localctx).condition = expression();
                        }
                    }
                    setState(179);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final CallProcessContext callProcess() throws RecognitionException {
        CallProcessContext _localctx = new CallProcessContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_callProcess);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(180);
                match(T__22);
                setState(181);
                match(T__7);
                setState(182);
                ((CallProcessContext) _localctx).name = match(STRING);
                setState(183);
                match(T__23);
                setState(184);
                ((CallProcessContext) _localctx).target = match(STRING);
                setState(185);
                match(T__24);
                setState(186);
                ((CallProcessContext) _localctx).source = match(STRING);
                setState(187);
                match(T__10);
                setState(188);
                ((CallProcessContext) _localctx).datasource = match(STRING);
                setState(189);
                match(T__25);
                setState(190);
                match(T__1);
                setState(191);
                ((CallProcessContext) _localctx).value = match(STRING);
                setState(192);
                match(T__2);
                setState(197);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(193);
                            match(T__11);
                            setState(194);
                            ((CallProcessContext) _localctx).condition = expression();
                        }
                    }
                    setState(199);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final AssignContext assign() throws RecognitionException {
        AssignContext _localctx = new AssignContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_assign);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(200);
                match(T__26);
                setState(201);
                match(T__7);
                setState(202);
                ((AssignContext) _localctx).name = match(STRING);
                setState(203);
                match(T__27);
                setState(204);
                ((AssignContext) _localctx).source = match(STRING);
                setState(205);
                match(T__10);
                setState(206);
                match(T__1);
                setState(207);
                ((AssignContext) _localctx).value = match(STRING);
                setState(208);
                match(T__2);
                setState(213);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(209);
                            match(T__11);
                            setState(210);
                            ((AssignContext) _localctx).condition = expression();
                        }
                    }
                    setState(215);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final DropFileContext dropFile() throws RecognitionException {
        DropFileContext _localctx = new DropFileContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_dropFile);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(216);
                match(T__28);
                setState(217);
                match(T__7);
                setState(218);
                ((DropFileContext) _localctx).name = match(STRING);
                setState(219);
                match(T__29);
                setState(220);
                ((DropFileContext) _localctx).target = match(STRING);
                setState(225);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(221);
                            match(T__11);
                            setState(222);
                            ((DropFileContext) _localctx).condition = expression();
                        }
                    }
                    setState(227);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final RestApiContext restApi() throws RecognitionException {
        RestApiContext _localctx = new RestApiContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_restApi);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(228);
                match(T__30);
                setState(229);
                match(T__7);
                setState(230);
                ((RestApiContext) _localctx).name = match(STRING);
                setState(231);
                match(T__27);
                setState(232);
                ((RestApiContext) _localctx).source = match(STRING);
                setState(233);
                match(T__10);
                setState(234);
                match(T__31);
                setState(235);
                ((RestApiContext) _localctx).url = match(STRING);
                setState(236);
                match(T__32);
                setState(237);
                ((RestApiContext) _localctx).method = match(STRING);
                setState(242);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__33) {
                    {
                        {
                            setState(238);
                            match(T__33);
                            setState(239);
                            ((RestApiContext) _localctx).headers = json();
                        }
                    }
                    setState(244);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(249);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__34) {
                    {
                        {
                            setState(245);
                            match(T__34);
                            setState(246);
                            ((RestApiContext) _localctx).params = json();
                        }
                    }
                    setState(251);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                {
                    setState(252);
                    match(T__35);
                    setState(253);
                    match(T__1);
                    setState(254);
                    ((RestApiContext) _localctx).bodyType = match(STRING);
                    setState(255);
                    match(T__2);
                }
                setState(257);
                match(T__1);
                setState(261);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__36) {
                    {
                        {
                            setState(258);
                            ((RestApiContext) _localctx).restPart = restPart();
                            ((RestApiContext) _localctx).value.add(((RestApiContext) _localctx).restPart);
                        }
                    }
                    setState(263);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(264);
                match(T__2);
                setState(269);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(265);
                            match(T__11);
                            setState(266);
                            ((RestApiContext) _localctx).condition = expression();
                        }
                    }
                    setState(271);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
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

    public final RestPartContext restPart() throws RecognitionException {
        RestPartContext _localctx = new RestPartContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_restPart);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(272);
                match(T__36);
                setState(273);
                match(T__7);
                setState(274);
                ((RestPartContext) _localctx).partName = match(STRING);
                setState(275);
                match(T__19);
                setState(276);
                ((RestPartContext) _localctx).partData = match(STRING);
                setState(281);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__37) {
                    {
                        {
                            setState(277);
                            match(T__37);
                            setState(278);
                            ((RestPartContext) _localctx).type = match(STRING);
                        }
                    }
                    setState(283);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(284);
                match(T__2);
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

    public final ExpressionContext expression() throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_expression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(286);
                match(T__38);
                {
                    setState(287);
                    ((ExpressionContext) _localctx).lhs = match(STRING);
                    setState(288);
                    ((ExpressionContext) _localctx).operator = match(Operator);
                    setState(289);
                    ((ExpressionContext) _localctx).rhs = match(STRING);
                }
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

    public final JsonContext json() throws RecognitionException {
        JsonContext _localctx = new JsonContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_json);
        try {
            setState(293);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(291);
                    obj();
                }
                break;
                case T__41:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(292);
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
        enterRule(_localctx, 32, RULE_obj);
        int _la;
        try {
            setState(308);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 21, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(295);
                    match(T__1);
                    setState(296);
                    pair();
                    setState(301);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__39) {
                        {
                            {
                                setState(297);
                                match(T__39);
                                setState(298);
                                pair();
                            }
                        }
                        setState(303);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(304);
                    match(T__2);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(306);
                    match(T__1);
                    setState(307);
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
        enterRule(_localctx, 34, RULE_pair);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(310);
                match(STRING);
                setState(311);
                match(T__40);
                setState(312);
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
        enterRule(_localctx, 36, RULE_arr);
        int _la;
        try {
            setState(327);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(314);
                    match(T__41);
                    setState(315);
                    jValue();
                    setState(320);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__39) {
                        {
                            {
                                setState(316);
                                match(T__39);
                                setState(317);
                                jValue();
                            }
                        }
                        setState(322);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(323);
                    match(T__42);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(325);
                    match(T__41);
                    setState(326);
                    match(T__42);
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
        enterRule(_localctx, 38, RULE_jValue);
        try {
            setState(336);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(329);
                    match(STRING);
                }
                break;
                case NUMBER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(330);
                    match(NUMBER);
                }
                break;
                case T__1:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(331);
                    obj();
                }
                break;
                case T__41:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(332);
                    arr();
                }
                break;
                case T__43:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(333);
                    match(T__43);
                }
                break;
                case T__44:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(334);
                    match(T__44);
                }
                break;
                case T__45:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(335);
                    match(T__45);
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

    public static class ProcessContext extends ParserRuleContext {
        public Token name;
        public TryClauseContext tryBlock;
        public CatchClauseContext catchBlock;
        public FinallyClauseContext finallyBlock;

        public ProcessContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(RavenParser.STRING, 0);
        }

        public TryClauseContext tryClause() {
            return getRuleContext(TryClauseContext.class, 0);
        }

        public CatchClauseContext catchClause() {
            return getRuleContext(CatchClauseContext.class, 0);
        }

        public FinallyClauseContext finallyClause() {
            return getRuleContext(FinallyClauseContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_process;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterProcess(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitProcess(this);
        }
    }

    public static class TryClauseContext extends ParserRuleContext {
        public ActionContext action;
        public List<ActionContext> actions = new ArrayList<ActionContext>();

        public TryClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ActionContext> action() {
            return getRuleContexts(ActionContext.class);
        }

        public ActionContext action(int i) {
            return getRuleContext(ActionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tryClause;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterTryClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitTryClause(this);
        }
    }

    public static class FinallyClauseContext extends ParserRuleContext {
        public ActionContext action;
        public List<ActionContext> actions = new ArrayList<ActionContext>();

        public FinallyClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ActionContext> action() {
            return getRuleContexts(ActionContext.class);
        }

        public ActionContext action(int i) {
            return getRuleContext(ActionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_finallyClause;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterFinallyClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitFinallyClause(this);
        }
    }

    public static class CatchClauseContext extends ParserRuleContext {
        public ActionContext action;
        public List<ActionContext> actions = new ArrayList<ActionContext>();

        public CatchClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ActionContext> action() {
            return getRuleContexts(ActionContext.class);
        }

        public ActionContext action(int i) {
            return getRuleContext(ActionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_catchClause;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterCatchClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitCatchClause(this);
        }
    }

    public static class ActionContext extends ParserRuleContext {
        public ActionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public AbortContext abort() {
            return getRuleContext(AbortContext.class, 0);
        }

        public AssignContext assign() {
            return getRuleContext(AssignContext.class, 0);
        }

        public CallProcessContext callProcess() {
            return getRuleContext(CallProcessContext.class, 0);
        }

        public CopyDataContext copyData() {
            return getRuleContext(CopyDataContext.class, 0);
        }

        public DropFileContext dropFile() {
            return getRuleContext(DropFileContext.class, 0);
        }

        public LoadCsvContext loadCsv() {
            return getRuleContext(LoadCsvContext.class, 0);
        }

        public RestApiContext restApi() {
            return getRuleContext(RestApiContext.class, 0);
        }

        public TransformContext transform() {
            return getRuleContext(TransformContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_action;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterAction(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitAction(this);
        }
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

        public CopyDataContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public List<TerminalNode> NON_ZERO_DIGIT() {
            return getTokens(RavenParser.NON_ZERO_DIGIT);
        }

        public TerminalNode NON_ZERO_DIGIT(int i) {
            return getToken(RavenParser.NON_ZERO_DIGIT, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_copyData;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterCopyData(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitCopyData(this);
        }
    }

    public static class TransformContext extends ParserRuleContext {
        public Token name;
        public Token on;
        public Token STRING;
        public List<Token> value = new ArrayList<Token>();
        public ExpressionContext condition;

        public TransformContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_transform;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterTransform(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitTransform(this);
        }
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

        public LoadCsvContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_loadCsv;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterLoadCsv(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitLoadCsv(this);
        }
    }

    public static class AbortContext extends ParserRuleContext {
        public Token name;
        public Token value;
        public ExpressionContext condition;

        public AbortContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_abort;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterAbort(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitAbort(this);
        }
    }

    public static class CallProcessContext extends ParserRuleContext {
        public Token name;
        public Token target;
        public Token source;
        public Token datasource;
        public Token value;
        public ExpressionContext condition;

        public CallProcessContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_callProcess;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterCallProcess(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitCallProcess(this);
        }
    }

    public static class AssignContext extends ParserRuleContext {
        public Token name;
        public Token source;
        public Token value;
        public ExpressionContext condition;

        public AssignContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assign;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterAssign(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitAssign(this);
        }
    }

    public static class DropFileContext extends ParserRuleContext {
        public Token name;
        public Token target;
        public ExpressionContext condition;

        public DropFileContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_dropFile;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterDropFile(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitDropFile(this);
        }
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

        public RestApiContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public List<JsonContext> json() {
            return getRuleContexts(JsonContext.class);
        }

        public JsonContext json(int i) {
            return getRuleContext(JsonContext.class, i);
        }

        public List<RestPartContext> restPart() {
            return getRuleContexts(RestPartContext.class);
        }

        public RestPartContext restPart(int i) {
            return getRuleContext(RestPartContext.class, i);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_restApi;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterRestApi(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitRestApi(this);
        }
    }

    public static class RestPartContext extends ParserRuleContext {
        public Token partName;
        public Token partData;
        public Token type;

        public RestPartContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_restPart;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterRestPart(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitRestPart(this);
        }
    }

    public static class ExpressionContext extends ParserRuleContext {
        public Token lhs;
        public Token operator;
        public Token rhs;

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public TerminalNode Operator() {
            return getToken(RavenParser.Operator, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitExpression(this);
        }
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
            if (listener instanceof RavenListener) ((RavenListener) listener).enterJson(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitJson(this);
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
            if (listener instanceof RavenListener) ((RavenListener) listener).enterObj(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitObj(this);
        }
    }

    public static class PairContext extends ParserRuleContext {
        public PairContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(RavenParser.STRING, 0);
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
            if (listener instanceof RavenListener) ((RavenListener) listener).enterPair(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitPair(this);
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
            if (listener instanceof RavenListener) ((RavenListener) listener).enterArr(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitArr(this);
        }
    }

    public static class JValueContext extends ParserRuleContext {
        public JValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(RavenParser.STRING, 0);
        }

        public TerminalNode NUMBER() {
            return getToken(RavenParser.NUMBER, 0);
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
            if (listener instanceof RavenListener) ((RavenListener) listener).enterJValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitJValue(this);
        }
    }
}
