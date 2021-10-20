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
            T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, T__50 = 51, T__51 = 52,
            T__52 = 53, T__53 = 54, T__54 = 55, T__55 = 56, T__56 = 57, T__57 = 58, T__58 = 59,
            T__59 = 60, T__60 = 61, T__61 = 62, T__62 = 63, NON_ZERO_DIGIT = 64, STRING = 65,
            CRLF = 66, Operator = 67, WS = 68, COMMENT = 69, LINE_COMMENT = 70, NUMBER = 71;
    public static final int
            RULE_process = 0, RULE_tryClause = 1, RULE_finallyClause = 2, RULE_catchClause = 3,
            RULE_action = 4, RULE_attribute = 5, RULE_copyData = 6, RULE_transform = 7,
            RULE_loadCsv = 8, RULE_abort = 9, RULE_callProcess = 10, RULE_forkProcess = 11,
            RULE_spawnProcess = 12, RULE_dogLeg = 13, RULE_startProcess = 14, RULE_assign = 15,
            RULE_dropFile = 16, RULE_restApi = 17, RULE_restPart = 18, RULE_googleContactCreate = 19,
            RULE_googleCalendarCreate = 20, RULE_googleContactFetchAll = 21, RULE_expression = 22,
            RULE_json = 23, RULE_obj = 24, RULE_pair = 25, RULE_arr = 26, RULE_jValue = 27;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3I\u01f9\4\2\t\2\4" +
                    "\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
                    "\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3" +
                    "\2\3\3\3\3\3\3\7\3F\n\3\f\3\16\3I\13\3\3\3\3\3\3\4\3\4\3\4\7\4P\n\4\f" +
                    "\4\16\4S\13\4\3\4\3\4\3\5\3\5\3\5\7\5Z\n\5\f\5\16\5]\13\5\3\5\3\5\3\6" +
                    "\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6o\n\6\3\7\3\7" +
                    "\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0080\n\b\f\b" +
                    "\16\b\u0083\13\b\3\b\3\b\7\b\u0087\n\b\f\b\16\b\u008a\13\b\3\b\3\b\7\b" +
                    "\u008e\n\b\f\b\16\b\u0091\13\b\3\b\3\b\7\b\u0095\n\b\f\b\16\b\u0098\13" +
                    "\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a5\n\t\f\t\16\t" +
                    "\u00a8\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3" +
                    "\n\3\n\3\n\3\n\3\n\7\n\u00bd\n\n\f\n\16\n\u00c0\13\n\3\13\3\13\3\13\3" +
                    "\13\3\13\3\13\3\13\3\13\7\13\u00ca\n\13\f\13\16\13\u00cd\13\13\3\f\3\f" +
                    "\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00de\n\f\f\f" +
                    "\16\f\u00e1\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3" +
                    "\r\3\r\7\r\u00f2\n\r\f\r\16\r\u00f5\13\r\3\r\3\r\7\r\u00f9\n\r\f\r\16" +
                    "\r\u00fc\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0107" +
                    "\n\16\f\16\16\16\u010a\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3" +
                    "\17\3\17\3\17\7\17\u0117\n\17\f\17\16\17\u011a\13\17\3\20\3\20\3\20\3" +
                    "\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u012c" +
                    "\n\21\f\21\16\21\u012f\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0138" +
                    "\n\22\f\22\16\22\u013b\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3" +
                    "\23\3\23\3\23\3\23\7\23\u0149\n\23\f\23\16\23\u014c\13\23\3\23\3\23\7" +
                    "\23\u0150\n\23\f\23\16\23\u0153\13\23\3\23\3\23\3\23\3\23\3\23\3\23\3" +
                    "\23\7\23\u015c\n\23\f\23\16\23\u015f\13\23\3\23\3\23\3\23\7\23\u0164\n" +
                    "\23\f\23\16\23\u0167\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u0170" +
                    "\n\24\f\24\16\24\u0173\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3" +
                    "\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3" +
                    "\25\7\25\u018c\n\25\f\25\16\25\u018f\13\25\3\26\3\26\3\26\3\26\3\26\3" +
                    "\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3" +
                    "\26\3\26\7\26\u01a6\n\26\f\26\16\26\u01a9\13\26\3\27\3\27\3\27\3\27\3" +
                    "\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3" +
                    "\27\3\27\3\27\7\27\u01c0\n\27\f\27\16\27\u01c3\13\27\3\30\3\30\3\30\3" +
                    "\30\3\30\3\31\3\31\5\31\u01cc\n\31\3\32\3\32\3\32\3\32\7\32\u01d2\n\32" +
                    "\f\32\16\32\u01d5\13\32\3\32\3\32\3\32\3\32\5\32\u01db\n\32\3\33\3\33" +
                    "\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u01e5\n\34\f\34\16\34\u01e8\13\34" +
                    "\3\34\3\34\3\34\3\34\5\34\u01ee\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
                    "\5\35\u01f7\n\35\3\35\2\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"" +
                    "$&(*,.\60\62\64\668\2\2\2\u020d\2:\3\2\2\2\4B\3\2\2\2\6L\3\2\2\2\bV\3" +
                    "\2\2\2\nn\3\2\2\2\fp\3\2\2\2\16r\3\2\2\2\20\u0099\3\2\2\2\22\u00a9\3\2" +
                    "\2\2\24\u00c1\3\2\2\2\26\u00ce\3\2\2\2\30\u00e2\3\2\2\2\32\u00fd\3\2\2" +
                    "\2\34\u010b\3\2\2\2\36\u011b\3\2\2\2 \u0120\3\2\2\2\"\u0130\3\2\2\2$\u013c" +
                    "\3\2\2\2&\u0168\3\2\2\2(\u0176\3\2\2\2*\u0190\3\2\2\2,\u01aa\3\2\2\2." +
                    "\u01c4\3\2\2\2\60\u01cb\3\2\2\2\62\u01da\3\2\2\2\64\u01dc\3\2\2\2\66\u01ed" +
                    "\3\2\2\28\u01f6\3\2\2\2:;\7\3\2\2;<\7C\2\2<=\7\4\2\2=>\5\4\3\2>?\5\b\5" +
                    "\2?@\5\6\4\2@A\7\5\2\2A\3\3\2\2\2BC\7\6\2\2CG\7\4\2\2DF\5\n\6\2ED\3\2" +
                    "\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\5\2\2K\5\3" +
                    "\2\2\2LM\7\7\2\2MQ\7\4\2\2NP\5\n\6\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3" +
                    "\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\5\2\2U\7\3\2\2\2VW\7\b\2\2W[\7\4\2\2XZ" +
                    "\5\n\6\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2" +
                    "^_\7\5\2\2_\t\3\2\2\2`o\5\24\13\2ao\5 \21\2bo\5\26\f\2co\5\30\r\2do\5" +
                    "\32\16\2eo\5\34\17\2fo\5\16\b\2go\5\"\22\2ho\5\22\n\2io\5$\23\2jo\5\20" +
                    "\t\2ko\5(\25\2lo\5*\26\2mo\5,\27\2n`\3\2\2\2na\3\2\2\2nb\3\2\2\2nc\3\2" +
                    "\2\2nd\3\2\2\2ne\3\2\2\2nf\3\2\2\2ng\3\2\2\2nh\3\2\2\2ni\3\2\2\2nj\3\2" +
                    "\2\2nk\3\2\2\2nl\3\2\2\2nm\3\2\2\2o\13\3\2\2\2pq\5\36\20\2q\r\3\2\2\2" +
                    "rs\7\t\2\2st\7\n\2\2tu\7C\2\2uv\7\13\2\2vw\7C\2\2wx\7\f\2\2xy\7C\2\2y" +
                    "z\7\r\2\2z{\7\4\2\2{|\7C\2\2|\u0081\7\5\2\2}~\7\16\2\2~\u0080\5.\30\2" +
                    "\177}\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2" +
                    "\2\u0082\u0088\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\7\17\2\2\u0085" +
                    "\u0087\7B\2\2\u0086\u0084\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2" +
                    "\2\2\u0088\u0089\3\2\2\2\u0089\u008f\3\2\2\2\u008a\u0088\3\2\2\2\u008b" +
                    "\u008c\7\20\2\2\u008c\u008e\7B\2\2\u008d\u008b\3\2\2\2\u008e\u0091\3\2" +
                    "\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0096\3\2\2\2\u0091" +
                    "\u008f\3\2\2\2\u0092\u0093\7\21\2\2\u0093\u0095\7B\2\2\u0094\u0092\3\2" +
                    "\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097" +
                    "\17\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\7\22\2\2\u009a\u009b\7\n\2" +
                    "\2\u009b\u009c\7C\2\2\u009c\u009d\7\23\2\2\u009d\u009e\7C\2\2\u009e\u009f" +
                    "\7\r\2\2\u009f\u00a0\7\4\2\2\u00a0\u00a1\7C\2\2\u00a1\u00a6\7\5\2\2\u00a2" +
                    "\u00a3\7\16\2\2\u00a3\u00a5\5.\30\2\u00a4\u00a2\3\2\2\2\u00a5\u00a8\3" +
                    "\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\21\3\2\2\2\u00a8" +
                    "\u00a6\3\2\2\2\u00a9\u00aa\7\24\2\2\u00aa\u00ab\7\n\2\2\u00ab\u00ac\7" +
                    "C\2\2\u00ac\u00ad\7\25\2\2\u00ad\u00ae\7C\2\2\u00ae\u00af\7\13\2\2\u00af" +
                    "\u00b0\7C\2\2\u00b0\u00b1\7\f\2\2\u00b1\u00b2\7C\2\2\u00b2\u00b3\7\26" +
                    "\2\2\u00b3\u00b4\7C\2\2\u00b4\u00b5\7\27\2\2\u00b5\u00b6\7C\2\2\u00b6" +
                    "\u00b7\7\r\2\2\u00b7\u00b8\7\4\2\2\u00b8\u00b9\7C\2\2\u00b9\u00be\7\5" +
                    "\2\2\u00ba\u00bb\7\16\2\2\u00bb\u00bd\5.\30\2\u00bc\u00ba\3\2\2\2\u00bd" +
                    "\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\23\3\2\2" +
                    "\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\30\2\2\u00c2\u00c3\7\n\2\2\u00c3" +
                    "\u00c4\7C\2\2\u00c4\u00c5\7\4\2\2\u00c5\u00c6\7C\2\2\u00c6\u00cb\7\5\2" +
                    "\2\u00c7\u00c8\7\16\2\2\u00c8\u00ca\5.\30\2\u00c9\u00c7\3\2\2\2\u00ca" +
                    "\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\25\3\2\2" +
                    "\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7\31\2\2\u00cf\u00d0\7\n\2\2\u00d0" +
                    "\u00d1\7C\2\2\u00d1\u00d2\7\32\2\2\u00d2\u00d3\7C\2\2\u00d3\u00d4\7\33" +
                    "\2\2\u00d4\u00d5\7C\2\2\u00d5\u00d6\7\r\2\2\u00d6\u00d7\7C\2\2\u00d7\u00d8" +
                    "\7\34\2\2\u00d8\u00d9\7\4\2\2\u00d9\u00da\7C\2\2\u00da\u00df\7\5\2\2\u00db" +
                    "\u00dc\7\16\2\2\u00dc\u00de\5.\30\2\u00dd\u00db\3\2\2\2\u00de\u00e1\3" +
                    "\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\27\3\2\2\2\u00e1" +
                    "\u00df\3\2\2\2\u00e2\u00e3\7\35\2\2\u00e3\u00e4\7\n\2\2\u00e4\u00e5\7" +
                    "C\2\2\u00e5\u00e6\7\32\2\2\u00e6\u00e7\7C\2\2\u00e7\u00e8\7\33\2\2\u00e8" +
                    "\u00e9\7C\2\2\u00e9\u00ea\7\r\2\2\u00ea\u00eb\7C\2\2\u00eb\u00ec\7\34" +
                    "\2\2\u00ec\u00ed\7\4\2\2\u00ed\u00ee\7C\2\2\u00ee\u00f3\7\5\2\2\u00ef" +
                    "\u00f0\7\36\2\2\u00f0\u00f2\7C\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f5\3\2" +
                    "\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00fa\3\2\2\2\u00f5" +
                    "\u00f3\3\2\2\2\u00f6\u00f7\7\16\2\2\u00f7\u00f9\5.\30\2\u00f8\u00f6\3" +
                    "\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb" +
                    "\31\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\7\37\2\2\u00fe\u00ff\7\n\2" +
                    "\2\u00ff\u0100\7C\2\2\u0100\u0101\7\32\2\2\u0101\u0102\7C\2\2\u0102\u0103" +
                    "\7\33\2\2\u0103\u0108\7C\2\2\u0104\u0105\7\16\2\2\u0105\u0107\5.\30\2" +
                    "\u0106\u0104\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109" +
                    "\3\2\2\2\u0109\33\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\7 \2\2\u010c" +
                    "\u010d\7\n\2\2\u010d\u010e\7C\2\2\u010e\u010f\7!\2\2\u010f\u0110\7C\2" +
                    "\2\u0110\u0111\7\r\2\2\u0111\u0112\7\4\2\2\u0112\u0113\5\36\20\2\u0113" +
                    "\u0118\7\5\2\2\u0114\u0115\7\16\2\2\u0115\u0117\5.\30\2\u0116\u0114\3" +
                    "\2\2\2\u0117\u011a\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119" +
                    "\35\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u011c\7\"\2\2\u011c\u011d\7C\2\2" +
                    "\u011d\u011e\7#\2\2\u011e\u011f\7C\2\2\u011f\37\3\2\2\2\u0120\u0121\7" +
                    "$\2\2\u0121\u0122\7\n\2\2\u0122\u0123\7C\2\2\u0123\u0124\7%\2\2\u0124" +
                    "\u0125\7C\2\2\u0125\u0126\7\r\2\2\u0126\u0127\7\4\2\2\u0127\u0128\7C\2" +
                    "\2\u0128\u012d\7\5\2\2\u0129\u012a\7\16\2\2\u012a\u012c\5.\30\2\u012b" +
                    "\u0129\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2" +
                    "\2\2\u012e!\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7&\2\2\u0131\u0132" +
                    "\7\n\2\2\u0132\u0133\7C\2\2\u0133\u0134\7\'\2\2\u0134\u0139\7C\2\2\u0135" +
                    "\u0136\7\16\2\2\u0136\u0138\5.\30\2\u0137\u0135\3\2\2\2\u0138\u013b\3" +
                    "\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a#\3\2\2\2\u013b\u0139" +
                    "\3\2\2\2\u013c\u013d\7(\2\2\u013d\u013e\7\n\2\2\u013e\u013f\7C\2\2\u013f" +
                    "\u0140\7%\2\2\u0140\u0141\7C\2\2\u0141\u0142\7\r\2\2\u0142\u0143\7)\2" +
                    "\2\u0143\u0144\7C\2\2\u0144\u0145\7*\2\2\u0145\u014a\7C\2\2\u0146\u0147" +
                    "\7+\2\2\u0147\u0149\5\60\31\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2\2\2" +
                    "\u014a\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0151\3\2\2\2\u014c\u014a" +
                    "\3\2\2\2\u014d\u014e\7,\2\2\u014e\u0150\5\60\31\2\u014f\u014d\3\2\2\2" +
                    "\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154" +
                    "\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0155\7-\2\2\u0155\u0156\7\4\2\2\u0156" +
                    "\u0157\7C\2\2\u0157\u0158\7\5\2\2\u0158\u0159\3\2\2\2\u0159\u015d\7\4" +
                    "\2\2\u015a\u015c\5&\24\2\u015b\u015a\3\2\2\2\u015c\u015f\3\2\2\2\u015d" +
                    "\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0160\3\2\2\2\u015f\u015d\3\2" +
                    "\2\2\u0160\u0165\7\5\2\2\u0161\u0162\7\16\2\2\u0162\u0164\5.\30\2\u0163" +
                    "\u0161\3\2\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2" +
                    "\2\2\u0166%\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0169\7.\2\2\u0169\u016a" +
                    "\7\n\2\2\u016a\u016b\7C\2\2\u016b\u016c\7\26\2\2\u016c\u0171\7C\2\2\u016d" +
                    "\u016e\7/\2\2\u016e\u0170\7C\2\2\u016f\u016d\3\2\2\2\u0170\u0173\3\2\2" +
                    "\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\3\2\2\2\u0173\u0171" +
                    "\3\2\2\2\u0174\u0175\7\5\2\2\u0175\'\3\2\2\2\u0176\u0177\7\60\2\2\u0177" +
                    "\u0178\7\n\2\2\u0178\u0179\7C\2\2\u0179\u017a\7\61\2\2\u017a\u017b\7C" +
                    "\2\2\u017b\u017c\7\62\2\2\u017c\u017d\7C\2\2\u017d\u017e\7\63\2\2\u017e" +
                    "\u017f\7C\2\2\u017f\u0180\7\64\2\2\u0180\u0181\7C\2\2\u0181\u0182\7\65" +
                    "\2\2\u0182\u0183\7C\2\2\u0183\u0184\7\66\2\2\u0184\u0185\7C\2\2\u0185" +
                    "\u0186\7\r\2\2\u0186\u0187\7\4\2\2\u0187\u0188\7C\2\2\u0188\u018d\7\5" +
                    "\2\2\u0189\u018a\7\16\2\2\u018a\u018c\5.\30\2\u018b\u0189\3\2\2\2\u018c" +
                    "\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e)\3\2\2\2" +
                    "\u018f\u018d\3\2\2\2\u0190\u0191\7\67\2\2\u0191\u0192\7\n\2\2\u0192\u0193" +
                    "\7C\2\2\u0193\u0194\7\61\2\2\u0194\u0195\7C\2\2\u0195\u0196\7\62\2\2\u0196" +
                    "\u0197\7C\2\2\u0197\u0198\7\63\2\2\u0198\u0199\7C\2\2\u0199\u019a\7\64" +
                    "\2\2\u019a\u019b\7C\2\2\u019b\u019c\7\65\2\2\u019c\u019d\7C\2\2\u019d" +
                    "\u019e\7\66\2\2\u019e\u019f\7C\2\2\u019f\u01a0\7\r\2\2\u01a0\u01a1\7\4" +
                    "\2\2\u01a1\u01a2\7C\2\2\u01a2\u01a7\7\5\2\2\u01a3\u01a4\7\16\2\2\u01a4" +
                    "\u01a6\5.\30\2\u01a5\u01a3\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2" +
                    "\2\2\u01a7\u01a8\3\2\2\2\u01a8+\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01ab" +
                    "\78\2\2\u01ab\u01ac\7\n\2\2\u01ac\u01ad\7C\2\2\u01ad\u01ae\7\61\2\2\u01ae" +
                    "\u01af\7C\2\2\u01af\u01b0\7\62\2\2\u01b0\u01b1\7C\2\2\u01b1\u01b2\7\63" +
                    "\2\2\u01b2\u01b3\7C\2\2\u01b3\u01b4\7\64\2\2\u01b4\u01b5\7C\2\2\u01b5" +
                    "\u01b6\7\65\2\2\u01b6\u01b7\7C\2\2\u01b7\u01b8\79\2\2\u01b8\u01b9\7C\2" +
                    "\2\u01b9\u01ba\7\r\2\2\u01ba\u01bb\7\4\2\2\u01bb\u01bc\7C\2\2\u01bc\u01c1" +
                    "\7\5\2\2\u01bd\u01be\7\16\2\2\u01be\u01c0\5.\30\2\u01bf\u01bd\3\2\2\2" +
                    "\u01c0\u01c3\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2-\3" +
                    "\2\2\2\u01c3\u01c1\3\2\2\2\u01c4\u01c5\7:\2\2\u01c5\u01c6\7C\2\2\u01c6" +
                    "\u01c7\7E\2\2\u01c7\u01c8\7C\2\2\u01c8/\3\2\2\2\u01c9\u01cc\5\62\32\2" +
                    "\u01ca\u01cc\5\66\34\2\u01cb\u01c9\3\2\2\2\u01cb\u01ca\3\2\2\2\u01cc\61" +
                    "\3\2\2\2\u01cd\u01ce\7\4\2\2\u01ce\u01d3\5\64\33\2\u01cf\u01d0\7;\2\2" +
                    "\u01d0\u01d2\5\64\33\2\u01d1\u01cf\3\2\2\2\u01d2\u01d5\3\2\2\2\u01d3\u01d1" +
                    "\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d6\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d6" +
                    "\u01d7\7\5\2\2\u01d7\u01db\3\2\2\2\u01d8\u01d9\7\4\2\2\u01d9\u01db\7\5" +
                    "\2\2\u01da\u01cd\3\2\2\2\u01da\u01d8\3\2\2\2\u01db\63\3\2\2\2\u01dc\u01dd" +
                    "\7C\2\2\u01dd\u01de\7<\2\2\u01de\u01df\58\35\2\u01df\65\3\2\2\2\u01e0" +
                    "\u01e1\7=\2\2\u01e1\u01e6\58\35\2\u01e2\u01e3\7;\2\2\u01e3\u01e5\58\35" +
                    "\2\u01e4\u01e2\3\2\2\2\u01e5\u01e8\3\2\2\2\u01e6\u01e4\3\2\2\2\u01e6\u01e7" +
                    "\3\2\2\2\u01e7\u01e9\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e9\u01ea\7>\2\2\u01ea" +
                    "\u01ee\3\2\2\2\u01eb\u01ec\7=\2\2\u01ec\u01ee\7>\2\2\u01ed\u01e0\3\2\2" +
                    "\2\u01ed\u01eb\3\2\2\2\u01ee\67\3\2\2\2\u01ef\u01f7\7C\2\2\u01f0\u01f7" +
                    "\7I\2\2\u01f1\u01f7\5\62\32\2\u01f2\u01f7\5\66\34\2\u01f3\u01f7\7?\2\2" +
                    "\u01f4\u01f7\7@\2\2\u01f5\u01f7\7A\2\2\u01f6\u01ef\3\2\2\2\u01f6\u01f0" +
                    "\3\2\2\2\u01f6\u01f1\3\2\2\2\u01f6\u01f2\3\2\2\2\u01f6\u01f3\3\2\2\2\u01f6" +
                    "\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f79\3\2\2\2\"GQ[n\u0081\u0088\u008f" +
                    "\u0096\u00a6\u00be\u00cb\u00df\u00f3\u00fa\u0108\u0118\u012d\u0139\u014a" +
                    "\u0151\u015d\u0165\u0171\u018d\u01a7\u01c1\u01cb\u01d3\u01da\u01e6\u01ed" +
                    "\u01f6";
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
                "process", "tryClause", "finallyClause", "catchClause", "action", "attribute",
                "copyData", "transform", "loadCsv", "abort", "callProcess", "forkProcess",
                "spawnProcess", "dogLeg", "startProcess", "assign", "dropFile", "restApi",
                "restPart", "googleContactCreate", "googleCalendarCreate", "googleContactFetchAll",
                "expression", "json", "obj", "pair", "arr", "jValue"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'",
                "'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'",
                "'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'",
                "'on'", "'loadcsv'", "'pid'", "'with'", "'by-batch'", "'abort'", "'callprocess'",
                "'with-target'", "'from-file'", "'for-every'", "'forkprocess'", "'watermark'",
                "'spawn'", "'dogleg'", "'use-parent-context'", "'start-process'", "'with-file'",
                "'assign'", "'source'", "'dropfile'", "'in-path'", "'restapi'", "'url'",
                "'method'", "'with headers'", "'with params'", "'with body type'", "'{ part'",
                "'type as'", "'gcontact'", "'through-account'", "'secured-by'", "'with-key'",
                "'for-project'", "'on-behalf-of'", "'from-source'", "'gcalendar'", "'gcontact-fetchall'",
                "'to-target'", "'if'", "','", "':'", "'['", "']'", "'true'", "'false'",
                "'null'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", "Operator",
                "WS", "COMMENT", "LINE_COMMENT", "NUMBER"
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
                setState(56);
                match(T__0);
                setState(57);
                ((ProcessContext) _localctx).name = match(STRING);
                setState(58);
                match(T__1);
                setState(59);
                ((ProcessContext) _localctx).tryBlock = tryClause();
                setState(60);
                ((ProcessContext) _localctx).catchBlock = catchClause();
                setState(61);
                ((ProcessContext) _localctx).finallyBlock = finallyClause();
                setState(62);
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
                setState(64);
                match(T__3);
                setState(65);
                match(T__1);
                setState(69);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37) | (1L << T__45) | (1L << T__52) | (1L << T__53))) != 0)) {
                    {
                        {
                            setState(66);
                            ((TryClauseContext) _localctx).action = action();
                            ((TryClauseContext) _localctx).actions.add(((TryClauseContext) _localctx).action);
                        }
                    }
                    setState(71);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(72);
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
                setState(74);
                match(T__4);
                setState(75);
                match(T__1);
                setState(79);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37) | (1L << T__45) | (1L << T__52) | (1L << T__53))) != 0)) {
                    {
                        {
                            setState(76);
                            ((FinallyClauseContext) _localctx).action = action();
                            ((FinallyClauseContext) _localctx).actions.add(((FinallyClauseContext) _localctx).action);
                        }
                    }
                    setState(81);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(82);
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
                setState(84);
                match(T__5);
                setState(85);
                match(T__1);
                setState(89);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__15) | (1L << T__17) | (1L << T__21) | (1L << T__22) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__33) | (1L << T__35) | (1L << T__37) | (1L << T__45) | (1L << T__52) | (1L << T__53))) != 0)) {
                    {
                        {
                            setState(86);
                            ((CatchClauseContext) _localctx).action = action();
                            ((CatchClauseContext) _localctx).actions.add(((CatchClauseContext) _localctx).action);
                        }
                    }
                    setState(91);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(92);
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
                setState(108);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__21: {
                        setState(94);
                        abort();
                    }
                    break;
                    case T__33: {
                        setState(95);
                        assign();
                    }
                    break;
                    case T__22: {
                        setState(96);
                        callProcess();
                    }
                    break;
                    case T__26: {
                        setState(97);
                        forkProcess();
                    }
                    break;
                    case T__28: {
                        setState(98);
                        spawnProcess();
                    }
                    break;
                    case T__29: {
                        setState(99);
                        dogLeg();
                    }
                    break;
                    case T__6: {
                        setState(100);
                        copyData();
                    }
                    break;
                    case T__35: {
                        setState(101);
                        dropFile();
                    }
                    break;
                    case T__17: {
                        setState(102);
                        loadCsv();
                    }
                    break;
                    case T__37: {
                        setState(103);
                        restApi();
                    }
                    break;
                    case T__15: {
                        setState(104);
                        transform();
                    }
                    break;
                    case T__45: {
                        setState(105);
                        googleContactCreate();
                    }
                    break;
                    case T__52: {
                        setState(106);
                        googleCalendarCreate();
                    }
                    break;
                    case T__53: {
                        setState(107);
                        googleContactFetchAll();
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

    public final AttributeContext attribute() throws RecognitionException {
        AttributeContext _localctx = new AttributeContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_attribute);
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(110);
                    startProcess();
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

    public final StartProcessContext startProcess() throws RecognitionException {
        StartProcessContext _localctx = new StartProcessContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_startProcess);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(281);
                match(T__31);
                setState(282);
                ((StartProcessContext) _localctx).name = match(STRING);
                setState(283);
                match(T__32);
                setState(284);
                ((StartProcessContext) _localctx).target = match(STRING);
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
        enterRule(_localctx, 12, RULE_copyData);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(112);
                    match(T__6);
                    setState(113);
                    match(T__7);
                    setState(114);
                    ((CopyDataContext) _localctx).name = match(STRING);
                    setState(115);
                    match(T__8);
                    setState(116);
                    ((CopyDataContext) _localctx).source = match(STRING);
                    setState(117);
                    match(T__9);
                    setState(118);
                    ((CopyDataContext) _localctx).to = match(STRING);
                    setState(119);
                    match(T__10);
                    setState(120);
                    match(T__1);
                    setState(121);
                    ((CopyDataContext) _localctx).value = match(STRING);
                    setState(122);
                    match(T__2);
                    setState(127);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__11) {
                        {
                            {
                                setState(123);
                                match(T__11);
                                setState(124);
                                ((CopyDataContext) _localctx).condition = expression();
                            }
                        }
                        setState(129);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(134);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__12) {
                        {
                            {
                                setState(130);
                                match(T__12);
                                setState(131);
                                ((CopyDataContext) _localctx).writeThreadCount = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(136);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(141);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__13) {
                        {
                            {
                                setState(137);
                                match(T__13);
                                setState(138);
                                ((CopyDataContext) _localctx).fetchBatchSize = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(143);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(148);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__14) {
                        {
                            {
                                setState(144);
                                match(T__14);
                                setState(145);
                                ((CopyDataContext) _localctx).writeBatchSize = match(NON_ZERO_DIGIT);
                            }
                        }
                        setState(150);
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
        enterRule(_localctx, 14, RULE_transform);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(151);
                match(T__15);
                setState(152);
                match(T__7);
                setState(153);
                ((TransformContext) _localctx).name = match(STRING);
                setState(154);
                match(T__16);
                setState(155);
                ((TransformContext) _localctx).on = match(STRING);
                setState(156);
                match(T__10);
                setState(157);
                match(T__1);
                setState(158);
                ((TransformContext) _localctx).STRING = match(STRING);
                ((TransformContext) _localctx).value.add(((TransformContext) _localctx).STRING);
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
                            ((TransformContext) _localctx).condition = expression();
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

    public final LoadCsvContext loadCsv() throws RecognitionException {
        LoadCsvContext _localctx = new LoadCsvContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_loadCsv);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(167);
                match(T__17);
                setState(168);
                match(T__7);
                setState(169);
                ((LoadCsvContext) _localctx).name = match(STRING);
                setState(170);
                match(T__18);
                setState(171);
                ((LoadCsvContext) _localctx).pid = match(STRING);
                setState(172);
                match(T__8);
                setState(173);
                ((LoadCsvContext) _localctx).source = match(STRING);
                setState(174);
                match(T__9);
                setState(175);
                ((LoadCsvContext) _localctx).to = match(STRING);
                setState(176);
                match(T__19);
                setState(177);
                ((LoadCsvContext) _localctx).delim = match(STRING);
                setState(178);
                match(T__20);
                setState(179);
                ((LoadCsvContext) _localctx).limit = match(STRING);
                setState(180);
                match(T__10);
                setState(181);
                match(T__1);
                setState(182);
                ((LoadCsvContext) _localctx).value = match(STRING);
                setState(183);
                match(T__2);
                setState(188);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(184);
                            match(T__11);
                            setState(185);
                            ((LoadCsvContext) _localctx).condition = expression();
                        }
                    }
                    setState(190);
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
        enterRule(_localctx, 18, RULE_abort);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(191);
                match(T__21);
                setState(192);
                match(T__7);
                setState(193);
                ((AbortContext) _localctx).name = match(STRING);
                setState(194);
                match(T__1);
                setState(195);
                ((AbortContext) _localctx).value = match(STRING);
                setState(196);
                match(T__2);
                setState(201);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(197);
                            match(T__11);
                            setState(198);
                            ((AbortContext) _localctx).condition = expression();
                        }
                    }
                    setState(203);
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
        enterRule(_localctx, 20, RULE_callProcess);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(204);
                match(T__22);
                setState(205);
                match(T__7);
                setState(206);
                ((CallProcessContext) _localctx).name = match(STRING);
                setState(207);
                match(T__23);
                setState(208);
                ((CallProcessContext) _localctx).target = match(STRING);
                setState(209);
                match(T__24);
                setState(210);
                ((CallProcessContext) _localctx).source = match(STRING);
                setState(211);
                match(T__10);
                setState(212);
                ((CallProcessContext) _localctx).datasource = match(STRING);
                setState(213);
                match(T__25);
                setState(214);
                match(T__1);
                setState(215);
                ((CallProcessContext) _localctx).value = match(STRING);
                setState(216);
                match(T__2);
                setState(221);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(217);
                            match(T__11);
                            setState(218);
                            ((CallProcessContext) _localctx).condition = expression();
                        }
                    }
                    setState(223);
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

    public final ForkProcessContext forkProcess() throws RecognitionException {
        ForkProcessContext _localctx = new ForkProcessContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_forkProcess);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(224);
                match(T__26);
                setState(225);
                match(T__7);
                setState(226);
                ((ForkProcessContext) _localctx).name = match(STRING);
                setState(227);
                match(T__23);
                setState(228);
                ((ForkProcessContext) _localctx).target = match(STRING);
                setState(229);
                match(T__24);
                setState(230);
                ((ForkProcessContext) _localctx).source = match(STRING);
                setState(231);
                match(T__10);
                setState(232);
                ((ForkProcessContext) _localctx).datasource = match(STRING);
                setState(233);
                match(T__25);
                setState(234);
                match(T__1);
                setState(235);
                ((ForkProcessContext) _localctx).value = match(STRING);
                setState(236);
                match(T__2);
                setState(241);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__27) {
                    {
                        {
                            setState(237);
                            match(T__27);
                            setState(238);
                            ((ForkProcessContext) _localctx).forkBatchSize = match(STRING);
                        }
                    }
                    setState(243);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(248);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(244);
                            match(T__11);
                            setState(245);
                            ((ForkProcessContext) _localctx).condition = expression();
                        }
                    }
                    setState(250);
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

    public final SpawnProcessContext spawnProcess() throws RecognitionException {
        SpawnProcessContext _localctx = new SpawnProcessContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_spawnProcess);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(251);
                match(T__28);
                setState(252);
                match(T__7);
                setState(253);
                ((SpawnProcessContext) _localctx).name = match(STRING);
                setState(254);
                match(T__23);
                setState(255);
                ((SpawnProcessContext) _localctx).target = match(STRING);
                setState(256);
                match(T__24);
                setState(257);
                ((SpawnProcessContext) _localctx).source = match(STRING);
                setState(262);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(258);
                            match(T__11);
                            setState(259);
                            ((SpawnProcessContext) _localctx).condition = expression();
                        }
                    }
                    setState(264);
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

    public final DogLegContext dogLeg() throws RecognitionException {
        DogLegContext _localctx = new DogLegContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_dogLeg);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(265);
                match(T__29);
                setState(266);
                match(T__7);
                setState(267);
                ((DogLegContext) _localctx).name = match(STRING);
                setState(268);
                match(T__30);
                setState(269);
                ((DogLegContext) _localctx).inheritContext = match(STRING);
                setState(270);
                match(T__10);
                setState(271);
                match(T__1);
                setState(272);
                ((DogLegContext) _localctx).startProcess = startProcess();
                ((DogLegContext) _localctx).processList.add(((DogLegContext) _localctx).startProcess);
                setState(273);
                match(T__2);
                setState(278);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(274);
                            match(T__11);
                            setState(275);
                            ((DogLegContext) _localctx).condition = expression();
                        }
                    }
                    setState(280);
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
        enterRule(_localctx, 30, RULE_assign);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(286);
                match(T__33);
                setState(287);
                match(T__7);
                setState(288);
                ((AssignContext) _localctx).name = match(STRING);
                setState(289);
                match(T__34);
                setState(290);
                ((AssignContext) _localctx).source = match(STRING);
                setState(291);
                match(T__10);
                setState(292);
                match(T__1);
                setState(293);
                ((AssignContext) _localctx).value = match(STRING);
                setState(294);
                match(T__2);
                setState(299);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(295);
                            match(T__11);
                            setState(296);
                            ((AssignContext) _localctx).condition = expression();
                        }
                    }
                    setState(301);
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
        enterRule(_localctx, 32, RULE_dropFile);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(302);
                match(T__35);
                setState(303);
                match(T__7);
                setState(304);
                ((DropFileContext) _localctx).name = match(STRING);
                setState(305);
                match(T__36);
                setState(306);
                ((DropFileContext) _localctx).target = match(STRING);
                setState(311);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(307);
                            match(T__11);
                            setState(308);
                            ((DropFileContext) _localctx).condition = expression();
                        }
                    }
                    setState(313);
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
        enterRule(_localctx, 34, RULE_restApi);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(314);
                match(T__37);
                setState(315);
                match(T__7);
                setState(316);
                ((RestApiContext) _localctx).name = match(STRING);
                setState(317);
                match(T__34);
                setState(318);
                ((RestApiContext) _localctx).source = match(STRING);
                setState(319);
                match(T__10);
                setState(320);
                match(T__38);
                setState(321);
                ((RestApiContext) _localctx).url = match(STRING);
                setState(322);
                match(T__39);
                setState(323);
                ((RestApiContext) _localctx).method = match(STRING);
                setState(328);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__40) {
                    {
                        {
                            setState(324);
                            match(T__40);
                            setState(325);
                            ((RestApiContext) _localctx).headers = json();
                        }
                    }
                    setState(330);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(335);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__41) {
                    {
                        {
                            setState(331);
                            match(T__41);
                            setState(332);
                            ((RestApiContext) _localctx).params = json();
                        }
                    }
                    setState(337);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                {
                    setState(338);
                    match(T__42);
                    setState(339);
                    match(T__1);
                    setState(340);
                    ((RestApiContext) _localctx).bodyType = match(STRING);
                    setState(341);
                    match(T__2);
                }
                setState(343);
                match(T__1);
                setState(347);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__43) {
                    {
                        {
                            setState(344);
                            ((RestApiContext) _localctx).restPart = restPart();
                            ((RestApiContext) _localctx).value.add(((RestApiContext) _localctx).restPart);
                        }
                    }
                    setState(349);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(350);
                match(T__2);
                setState(355);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(351);
                            match(T__11);
                            setState(352);
                            ((RestApiContext) _localctx).condition = expression();
                        }
                    }
                    setState(357);
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
        enterRule(_localctx, 36, RULE_restPart);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(358);
                match(T__43);
                setState(359);
                match(T__7);
                setState(360);
                ((RestPartContext) _localctx).partName = match(STRING);
                setState(361);
                match(T__19);
                setState(362);
                ((RestPartContext) _localctx).partData = match(STRING);
                setState(367);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__44) {
                    {
                        {
                            setState(363);
                            match(T__44);
                            setState(364);
                            ((RestPartContext) _localctx).type = match(STRING);
                        }
                    }
                    setState(369);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(370);
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

    public final GoogleContactCreateContext googleContactCreate() throws RecognitionException {
        GoogleContactCreateContext _localctx = new GoogleContactCreateContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_googleContactCreate);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(372);
                match(T__45);
                setState(373);
                match(T__7);
                setState(374);
                ((GoogleContactCreateContext) _localctx).name = match(STRING);
                setState(375);
                match(T__46);
                setState(376);
                ((GoogleContactCreateContext) _localctx).account = match(STRING);
                setState(377);
                match(T__47);
                setState(378);
                ((GoogleContactCreateContext) _localctx).privateKey = match(STRING);
                setState(379);
                match(T__48);
                setState(380);
                ((GoogleContactCreateContext) _localctx).ptwelveFile = match(STRING);
                setState(381);
                match(T__49);
                setState(382);
                ((GoogleContactCreateContext) _localctx).project = match(STRING);
                setState(383);
                match(T__50);
                setState(384);
                ((GoogleContactCreateContext) _localctx).impersonatedUser = match(STRING);
                setState(385);
                match(T__51);
                setState(386);
                ((GoogleContactCreateContext) _localctx).dbSrc = match(STRING);
                setState(387);
                match(T__10);
                setState(388);
                match(T__1);
                setState(389);
                ((GoogleContactCreateContext) _localctx).value = match(STRING);
                setState(390);
                match(T__2);
                setState(395);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(391);
                            match(T__11);
                            setState(392);
                            ((GoogleContactCreateContext) _localctx).condition = expression();
                        }
                    }
                    setState(397);
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

    public final GoogleCalendarCreateContext googleCalendarCreate() throws RecognitionException {
        GoogleCalendarCreateContext _localctx = new GoogleCalendarCreateContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_googleCalendarCreate);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(398);
                match(T__52);
                setState(399);
                match(T__7);
                setState(400);
                ((GoogleCalendarCreateContext) _localctx).name = match(STRING);
                setState(401);
                match(T__46);
                setState(402);
                ((GoogleCalendarCreateContext) _localctx).account = match(STRING);
                setState(403);
                match(T__47);
                setState(404);
                ((GoogleCalendarCreateContext) _localctx).privateKey = match(STRING);
                setState(405);
                match(T__48);
                setState(406);
                ((GoogleCalendarCreateContext) _localctx).ptwelveFile = match(STRING);
                setState(407);
                match(T__49);
                setState(408);
                ((GoogleCalendarCreateContext) _localctx).project = match(STRING);
                setState(409);
                match(T__50);
                setState(410);
                ((GoogleCalendarCreateContext) _localctx).impersonatedUser = match(STRING);
                setState(411);
                match(T__51);
                setState(412);
                ((GoogleCalendarCreateContext) _localctx).dbSrc = match(STRING);
                setState(413);
                match(T__10);
                setState(414);
                match(T__1);
                setState(415);
                ((GoogleCalendarCreateContext) _localctx).value = match(STRING);
                setState(416);
                match(T__2);
                setState(421);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(417);
                            match(T__11);
                            setState(418);
                            ((GoogleCalendarCreateContext) _localctx).condition = expression();
                        }
                    }
                    setState(423);
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

    public final GoogleContactFetchAllContext googleContactFetchAll() throws RecognitionException {
        GoogleContactFetchAllContext _localctx = new GoogleContactFetchAllContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_googleContactFetchAll);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(424);
                match(T__53);
                setState(425);
                match(T__7);
                setState(426);
                ((GoogleContactFetchAllContext) _localctx).name = match(STRING);
                setState(427);
                match(T__46);
                setState(428);
                ((GoogleContactFetchAllContext) _localctx).account = match(STRING);
                setState(429);
                match(T__47);
                setState(430);
                ((GoogleContactFetchAllContext) _localctx).privateKey = match(STRING);
                setState(431);
                match(T__48);
                setState(432);
                ((GoogleContactFetchAllContext) _localctx).ptwelveFile = match(STRING);
                setState(433);
                match(T__49);
                setState(434);
                ((GoogleContactFetchAllContext) _localctx).project = match(STRING);
                setState(435);
                match(T__50);
                setState(436);
                ((GoogleContactFetchAllContext) _localctx).impersonatedUser = match(STRING);
                setState(437);
                match(T__54);
                setState(438);
                ((GoogleContactFetchAllContext) _localctx).dbSrc = match(STRING);
                setState(439);
                match(T__10);
                setState(440);
                match(T__1);
                setState(441);
                ((GoogleContactFetchAllContext) _localctx).value = match(STRING);
                setState(442);
                match(T__2);
                setState(447);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(443);
                            match(T__11);
                            setState(444);
                            ((GoogleContactFetchAllContext) _localctx).condition = expression();
                        }
                    }
                    setState(449);
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

    public final ExpressionContext expression() throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_expression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(450);
                match(T__55);
                {
                    setState(451);
                    ((ExpressionContext) _localctx).lhs = match(STRING);
                    setState(452);
                    ((ExpressionContext) _localctx).operator = match(Operator);
                    setState(453);
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
        enterRule(_localctx, 46, RULE_json);
        try {
            setState(457);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(455);
                    obj();
                }
                break;
                case T__58:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(456);
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
        enterRule(_localctx, 48, RULE_obj);
        int _la;
        try {
            setState(472);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 28, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(459);
                    match(T__1);
                    setState(460);
                    pair();
                    setState(465);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__56) {
                        {
                            {
                                setState(461);
                                match(T__56);
                                setState(462);
                                pair();
                            }
                        }
                        setState(467);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(468);
                    match(T__2);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(470);
                    match(T__1);
                    setState(471);
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
        enterRule(_localctx, 50, RULE_pair);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(474);
                match(STRING);
                setState(475);
                match(T__57);
                setState(476);
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
        enterRule(_localctx, 52, RULE_arr);
        int _la;
        try {
            setState(491);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 30, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(478);
                    match(T__58);
                    setState(479);
                    jValue();
                    setState(484);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__56) {
                        {
                            {
                                setState(480);
                                match(T__56);
                                setState(481);
                                jValue();
                            }
                        }
                        setState(486);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(487);
                    match(T__59);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(489);
                    match(T__58);
                    setState(490);
                    match(T__59);
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
        enterRule(_localctx, 54, RULE_jValue);
        try {
            setState(500);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(493);
                    match(STRING);
                }
                break;
                case NUMBER:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(494);
                    match(NUMBER);
                }
                break;
                case T__1:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(495);
                    obj();
                }
                break;
                case T__58:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(496);
                    arr();
                }
                break;
                case T__60:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(497);
                    match(T__60);
                }
                break;
                case T__61:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(498);
                    match(T__61);
                }
                break;
                case T__62:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(499);
                    match(T__62);
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

        public ForkProcessContext forkProcess() {
            return getRuleContext(ForkProcessContext.class, 0);
        }

        public SpawnProcessContext spawnProcess() {
            return getRuleContext(SpawnProcessContext.class, 0);
        }

        public DogLegContext dogLeg() {
            return getRuleContext(DogLegContext.class, 0);
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

        public GoogleContactCreateContext googleContactCreate() {
            return getRuleContext(GoogleContactCreateContext.class, 0);
        }

        public GoogleCalendarCreateContext googleCalendarCreate() {
            return getRuleContext(GoogleCalendarCreateContext.class, 0);
        }

        public GoogleContactFetchAllContext googleContactFetchAll() {
            return getRuleContext(GoogleContactFetchAllContext.class, 0);
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

    public static class AttributeContext extends ParserRuleContext {
        public AttributeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public StartProcessContext startProcess() {
            return getRuleContext(StartProcessContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_attribute;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterAttribute(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitAttribute(this);
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

    public static class ForkProcessContext extends ParserRuleContext {
        public Token name;
        public Token target;
        public Token source;
        public Token datasource;
        public Token value;
        public Token forkBatchSize;
        public ExpressionContext condition;

        public ForkProcessContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_forkProcess;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterForkProcess(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitForkProcess(this);
        }
    }

    public static class SpawnProcessContext extends ParserRuleContext {
        public Token name;
        public Token target;
        public Token source;
        public ExpressionContext condition;

        public SpawnProcessContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_spawnProcess;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterSpawnProcess(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitSpawnProcess(this);
        }
    }

    public static class DogLegContext extends ParserRuleContext {
        public Token name;
        public Token inheritContext;
        public StartProcessContext startProcess;
        public List<StartProcessContext> processList = new ArrayList<StartProcessContext>();
        public ExpressionContext condition;

        public DogLegContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(RavenParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(RavenParser.STRING, i);
        }

        public StartProcessContext startProcess() {
            return getRuleContext(StartProcessContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_dogLeg;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterDogLeg(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitDogLeg(this);
        }
    }

    public static class StartProcessContext extends ParserRuleContext {
        public Token name;
        public Token target;

        public StartProcessContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_startProcess;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterStartProcess(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitStartProcess(this);
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

    public static class GoogleContactCreateContext extends ParserRuleContext {
        public Token name;
        public Token account;
        public Token privateKey;
        public Token ptwelveFile;
        public Token project;
        public Token impersonatedUser;
        public Token dbSrc;
        public Token value;
        public ExpressionContext condition;

        public GoogleContactCreateContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_googleContactCreate;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterGoogleContactCreate(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitGoogleContactCreate(this);
        }
    }

    public static class GoogleCalendarCreateContext extends ParserRuleContext {
        public Token name;
        public Token account;
        public Token privateKey;
        public Token ptwelveFile;
        public Token project;
        public Token impersonatedUser;
        public Token dbSrc;
        public Token value;
        public ExpressionContext condition;

        public GoogleCalendarCreateContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_googleCalendarCreate;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterGoogleCalendarCreate(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitGoogleCalendarCreate(this);
        }
    }

    public static class GoogleContactFetchAllContext extends ParserRuleContext {
        public Token name;
        public Token account;
        public Token privateKey;
        public Token ptwelveFile;
        public Token project;
        public Token impersonatedUser;
        public Token dbSrc;
        public Token value;
        public ExpressionContext condition;

        public GoogleContactFetchAllContext(ParserRuleContext parent, int invokingState) {
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
            return RULE_googleContactFetchAll;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).enterGoogleContactFetchAll(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof RavenListener) ((RavenListener) listener).exitGoogleContactFetchAll(this);
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
