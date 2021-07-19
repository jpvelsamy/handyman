// Generated from Raven.g4 by ANTLR 4.9.2

package in.handyman.raven.compiler;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RavenLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, NON_ZERO_DIGIT=28, STRING=29, CRLF=30, Operator=31, 
		WS=32, COMMENT=33, LINE_COMMENT=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "DIGIT", "NON_Z_DIGIT", "TWODIGIT", "LETTER", "StringCharacters", 
			"StringCharacter", "NON_ZERO_DIGIT", "STRING", "CRLF", "Operator", "WS", 
			"COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'", 
			"'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'", 
			"'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'", 
			"'on'", "'loadcsv'", "'with'", "'abort'", "'callprocess'", "'with-target'", 
			"'from-file'", "'for-every'", "'assign'", "'source'", "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
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


	public RavenLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Raven.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u0179\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\6!\u0135\n"+
		"!\r!\16!\u0136\3\"\3\"\3#\6#\u013c\n#\r#\16#\u013d\3$\3$\5$\u0142\n$\3"+
		"$\3$\3%\5%\u0147\n%\3%\3%\5%\u014b\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\5&\u0158\n&\3\'\6\'\u015b\n\'\r\'\16\'\u015c\3\'\3\'\3(\3(\3(\3(\7("+
		"\u0165\n(\f(\16(\u0168\13(\3(\3(\3(\3(\3(\3)\3)\3)\3)\7)\u0173\n)\f)\16"+
		")\u0176\13)\3)\3)\3\u0166\2*\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\2;\2=\2?\2A\2C\2E\36G\37I K!M\"O#Q$\3\2\t\3\2\62"+
		";\3\2\63;\4\2C\\c|\4\2$$^^\4\2>>@@\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2"+
		"\u017c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3"+
		"S\3\2\2\2\5[\3\2\2\2\7]\3\2\2\2\t_\3\2\2\2\13c\3\2\2\2\rk\3\2\2\2\17q"+
		"\3\2\2\2\21z\3\2\2\2\23}\3\2\2\2\25\u0082\3\2\2\2\27\u0085\3\2\2\2\31"+
		"\u008b\3\2\2\2\33\u0098\3\2\2\2\35\u00a1\3\2\2\2\37\u00b7\3\2\2\2!\u00cd"+
		"\3\2\2\2#\u00d7\3\2\2\2%\u00da\3\2\2\2\'\u00e2\3\2\2\2)\u00e7\3\2\2\2"+
		"+\u00ed\3\2\2\2-\u00f9\3\2\2\2/\u0105\3\2\2\2\61\u010f\3\2\2\2\63\u0119"+
		"\3\2\2\2\65\u0120\3\2\2\2\67\u0127\3\2\2\29\u012a\3\2\2\2;\u012c\3\2\2"+
		"\2=\u012e\3\2\2\2?\u0131\3\2\2\2A\u0134\3\2\2\2C\u0138\3\2\2\2E\u013b"+
		"\3\2\2\2G\u013f\3\2\2\2I\u014a\3\2\2\2K\u0157\3\2\2\2M\u015a\3\2\2\2O"+
		"\u0160\3\2\2\2Q\u016e\3\2\2\2ST\7r\2\2TU\7t\2\2UV\7q\2\2VW\7e\2\2WX\7"+
		"g\2\2XY\7u\2\2YZ\7u\2\2Z\4\3\2\2\2[\\\7}\2\2\\\6\3\2\2\2]^\7\177\2\2^"+
		"\b\3\2\2\2_`\7v\2\2`a\7t\2\2ab\7{\2\2b\n\3\2\2\2cd\7h\2\2de\7k\2\2ef\7"+
		"p\2\2fg\7c\2\2gh\7n\2\2hi\7n\2\2ij\7{\2\2j\f\3\2\2\2kl\7e\2\2lm\7c\2\2"+
		"mn\7v\2\2no\7e\2\2op\7j\2\2p\16\3\2\2\2qr\7e\2\2rs\7q\2\2st\7r\2\2tu\7"+
		"{\2\2uv\7f\2\2vw\7c\2\2wx\7v\2\2xy\7c\2\2y\20\3\2\2\2z{\7c\2\2{|\7u\2"+
		"\2|\22\3\2\2\2}~\7h\2\2~\177\7t\2\2\177\u0080\7q\2\2\u0080\u0081\7o\2"+
		"\2\u0081\24\3\2\2\2\u0082\u0083\7v\2\2\u0083\u0084\7q\2\2\u0084\26\3\2"+
		"\2\2\u0085\u0086\7w\2\2\u0086\u0087\7u\2\2\u0087\u0088\7k\2\2\u0088\u0089"+
		"\7p\2\2\u0089\u008a\7i\2\2\u008a\30\3\2\2\2\u008b\u008c\7q\2\2\u008c\u008d"+
		"\7p\2\2\u008d\u008e\7/\2\2\u008e\u008f\7e\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7p\2\2\u0091\u0092\7f\2\2\u0092\u0093\7k\2\2\u0093\u0094\7v\2\2"+
		"\u0094\u0095\7k\2\2\u0095\u0096\7q\2\2\u0096\u0097\7p\2\2\u0097\32\3\2"+
		"\2\2\u0098\u0099\7h\2\2\u0099\u009a\7k\2\2\u009a\u009b\7g\2\2\u009b\u009c"+
		"\7n\2\2\u009c\u009d\7f\2\2\u009d\u009e\7k\2\2\u009e\u009f\7p\2\2\u009f"+
		"\u00a0\7i\2\2\u00a0\34\3\2\2\2\u00a1\u00a2\7y\2\2\u00a2\u00a3\7k\2\2\u00a3"+
		"\u00a4\7v\2\2\u00a4\u00a5\7j\2\2\u00a5\u00a6\7/\2\2\u00a6\u00a7\7h\2\2"+
		"\u00a7\u00a8\7g\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab"+
		"\7j\2\2\u00ab\u00ac\7/\2\2\u00ac\u00ad\7d\2\2\u00ad\u00ae\7c\2\2\u00ae"+
		"\u00af\7v\2\2\u00af\u00b0\7e\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2\7/\2\2"+
		"\u00b2\u00b3\7u\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7|\2\2\u00b5\u00b6"+
		"\7g\2\2\u00b6\36\3\2\2\2\u00b7\u00b8\7y\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba"+
		"\7v\2\2\u00ba\u00bb\7j\2\2\u00bb\u00bc\7/\2\2\u00bc\u00bd\7y\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7g\2\2"+
		"\u00c1\u00c2\7/\2\2\u00c2\u00c3\7d\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5"+
		"\7v\2\2\u00c5\u00c6\7e\2\2\u00c6\u00c7\7j\2\2\u00c7\u00c8\7/\2\2\u00c8"+
		"\u00c9\7u\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7|\2\2\u00cb\u00cc\7g\2\2"+
		"\u00cc \3\2\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7c\2"+
		"\2\u00d0\u00d1\7p\2\2\u00d1\u00d2\7u\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4"+
		"\7q\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7o\2\2\u00d6\"\3\2\2\2\u00d7\u00d8"+
		"\7q\2\2\u00d8\u00d9\7p\2\2\u00d9$\3\2\2\2\u00da\u00db\7n\2\2\u00db\u00dc"+
		"\7q\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7f\2\2\u00de\u00df\7e\2\2\u00df"+
		"\u00e0\7u\2\2\u00e0\u00e1\7x\2\2\u00e1&\3\2\2\2\u00e2\u00e3\7y\2\2\u00e3"+
		"\u00e4\7k\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6\7j\2\2\u00e6(\3\2\2\2\u00e7"+
		"\u00e8\7c\2\2\u00e8\u00e9\7d\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7t\2\2"+
		"\u00eb\u00ec\7v\2\2\u00ec*\3\2\2\2\u00ed\u00ee\7e\2\2\u00ee\u00ef\7c\2"+
		"\2\u00ef\u00f0\7n\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7r\2\2\u00f2\u00f3"+
		"\7t\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7e\2\2\u00f5\u00f6\7g\2\2\u00f6"+
		"\u00f7\7u\2\2\u00f7\u00f8\7u\2\2\u00f8,\3\2\2\2\u00f9\u00fa\7y\2\2\u00fa"+
		"\u00fb\7k\2\2\u00fb\u00fc\7v\2\2\u00fc\u00fd\7j\2\2\u00fd\u00fe\7/\2\2"+
		"\u00fe\u00ff\7v\2\2\u00ff\u0100\7c\2\2\u0100\u0101\7t\2\2\u0101\u0102"+
		"\7i\2\2\u0102\u0103\7g\2\2\u0103\u0104\7v\2\2\u0104.\3\2\2\2\u0105\u0106"+
		"\7h\2\2\u0106\u0107\7t\2\2\u0107\u0108\7q\2\2\u0108\u0109\7o\2\2\u0109"+
		"\u010a\7/\2\2\u010a\u010b\7h\2\2\u010b\u010c\7k\2\2\u010c\u010d\7n\2\2"+
		"\u010d\u010e\7g\2\2\u010e\60\3\2\2\2\u010f\u0110\7h\2\2\u0110\u0111\7"+
		"q\2\2\u0111\u0112\7t\2\2\u0112\u0113\7/\2\2\u0113\u0114\7g\2\2\u0114\u0115"+
		"\7x\2\2\u0115\u0116\7g\2\2\u0116\u0117\7t\2\2\u0117\u0118\7{\2\2\u0118"+
		"\62\3\2\2\2\u0119\u011a\7c\2\2\u011a\u011b\7u\2\2\u011b\u011c\7u\2\2\u011c"+
		"\u011d\7k\2\2\u011d\u011e\7i\2\2\u011e\u011f\7p\2\2\u011f\64\3\2\2\2\u0120"+
		"\u0121\7u\2\2\u0121\u0122\7q\2\2\u0122\u0123\7w\2\2\u0123\u0124\7t\2\2"+
		"\u0124\u0125\7e\2\2\u0125\u0126\7g\2\2\u0126\66\3\2\2\2\u0127\u0128\7"+
		"k\2\2\u0128\u0129\7h\2\2\u01298\3\2\2\2\u012a\u012b\t\2\2\2\u012b:\3\2"+
		"\2\2\u012c\u012d\t\3\2\2\u012d<\3\2\2\2\u012e\u012f\59\35\2\u012f\u0130"+
		"\59\35\2\u0130>\3\2\2\2\u0131\u0132\t\4\2\2\u0132@\3\2\2\2\u0133\u0135"+
		"\5C\"\2\u0134\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137B\3\2\2\2\u0138\u0139\n\5\2\2\u0139D\3\2\2\2\u013a"+
		"\u013c\5;\36\2\u013b\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2"+
		"\2\2\u013d\u013e\3\2\2\2\u013eF\3\2\2\2\u013f\u0141\7$\2\2\u0140\u0142"+
		"\5A!\2\u0141\u0140\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u0144\7$\2\2\u0144H\3\2\2\2\u0145\u0147\7\17\2\2\u0146\u0145\3\2\2\2"+
		"\u0146\u0147\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014b\7\f\2\2\u0149\u014b"+
		"\7\17\2\2\u014a\u0146\3\2\2\2\u014a\u0149\3\2\2\2\u014bJ\3\2\2\2\u014c"+
		"\u0158\t\6\2\2\u014d\u014e\7?\2\2\u014e\u0158\7?\2\2\u014f\u0150\7e\2"+
		"\2\u0150\u0151\7q\2\2\u0151\u0152\7p\2\2\u0152\u0153\7v\2\2\u0153\u0154"+
		"\7c\2\2\u0154\u0155\7k\2\2\u0155\u0156\7p\2\2\u0156\u0158\7u\2\2\u0157"+
		"\u014c\3\2\2\2\u0157\u014d\3\2\2\2\u0157\u014f\3\2\2\2\u0158L\3\2\2\2"+
		"\u0159\u015b\t\7\2\2\u015a\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015a"+
		"\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\b\'\2\2\u015f"+
		"N\3\2\2\2\u0160\u0161\7\61\2\2\u0161\u0162\7,\2\2\u0162\u0166\3\2\2\2"+
		"\u0163\u0165\13\2\2\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0166\u0164\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u0166\3\2\2\2\u0169"+
		"\u016a\7,\2\2\u016a\u016b\7\61\2\2\u016b\u016c\3\2\2\2\u016c\u016d\b("+
		"\2\2\u016dP\3\2\2\2\u016e\u016f\7\61\2\2\u016f\u0170\7\61\2\2\u0170\u0174"+
		"\3\2\2\2\u0171\u0173\n\b\2\2\u0172\u0171\3\2\2\2\u0173\u0176\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176\u0174\3\2"+
		"\2\2\u0177\u0178\b)\2\2\u0178R\3\2\2\2\f\2\u0136\u013d\u0141\u0146\u014a"+
		"\u0157\u015c\u0166\u0174\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}