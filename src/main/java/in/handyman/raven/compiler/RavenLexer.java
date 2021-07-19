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
		T__24=25, NON_ZERO_DIGIT=26, STRING=27, CRLF=28, Operator=29, WS=30, COMMENT=31, 
		LINE_COMMENT=32;
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
			"DIGIT", "NON_Z_DIGIT", "TWODIGIT", "LETTER", "StringCharacters", "StringCharacter", 
			"NON_ZERO_DIGIT", "STRING", "CRLF", "Operator", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'process'", "'{'", "'}'", "'try'", "'finally'", "'catch'", "'copydata'", 
			"'as'", "'from'", "'to'", "'using'", "'on-condition'", "'fielding'", 
			"'with-fetch-batch-size'", "'with-write-batch-size'", "'transform'", 
			"'on'", "'loadcsv'", "'with'", "'abort'", "'callprocess'", "'with-target'", 
			"'from-file'", "'for-every'", "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u0167\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\6\37\u0123\n\37"+
		"\r\37\16\37\u0124\3 \3 \3!\6!\u012a\n!\r!\16!\u012b\3\"\3\"\5\"\u0130"+
		"\n\"\3\"\3\"\3#\5#\u0135\n#\3#\3#\5#\u0139\n#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\5$\u0146\n$\3%\6%\u0149\n%\r%\16%\u014a\3%\3%\3&\3&\3&\3&\7"+
		"&\u0153\n&\f&\16&\u0156\13&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\7\'\u0161\n"+
		"\'\f\'\16\'\u0164\13\'\3\'\3\'\3\u0154\2(\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\2\67\29\2;\2=\2?\2A\34C\35E\36G\37I K!M\"\3"+
		"\2\t\3\2\62;\3\2\63;\4\2C\\c|\4\2$$^^\4\2>>@@\5\2\13\f\16\17\"\"\4\2\f"+
		"\f\17\17\2\u016a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3O\3\2\2\2\5W\3\2\2"+
		"\2\7Y\3\2\2\2\t[\3\2\2\2\13_\3\2\2\2\rg\3\2\2\2\17m\3\2\2\2\21v\3\2\2"+
		"\2\23y\3\2\2\2\25~\3\2\2\2\27\u0081\3\2\2\2\31\u0087\3\2\2\2\33\u0094"+
		"\3\2\2\2\35\u009d\3\2\2\2\37\u00b3\3\2\2\2!\u00c9\3\2\2\2#\u00d3\3\2\2"+
		"\2%\u00d6\3\2\2\2\'\u00de\3\2\2\2)\u00e3\3\2\2\2+\u00e9\3\2\2\2-\u00f5"+
		"\3\2\2\2/\u0101\3\2\2\2\61\u010b\3\2\2\2\63\u0115\3\2\2\2\65\u0118\3\2"+
		"\2\2\67\u011a\3\2\2\29\u011c\3\2\2\2;\u011f\3\2\2\2=\u0122\3\2\2\2?\u0126"+
		"\3\2\2\2A\u0129\3\2\2\2C\u012d\3\2\2\2E\u0138\3\2\2\2G\u0145\3\2\2\2I"+
		"\u0148\3\2\2\2K\u014e\3\2\2\2M\u015c\3\2\2\2OP\7r\2\2PQ\7t\2\2QR\7q\2"+
		"\2RS\7e\2\2ST\7g\2\2TU\7u\2\2UV\7u\2\2V\4\3\2\2\2WX\7}\2\2X\6\3\2\2\2"+
		"YZ\7\177\2\2Z\b\3\2\2\2[\\\7v\2\2\\]\7t\2\2]^\7{\2\2^\n\3\2\2\2_`\7h\2"+
		"\2`a\7k\2\2ab\7p\2\2bc\7c\2\2cd\7n\2\2de\7n\2\2ef\7{\2\2f\f\3\2\2\2gh"+
		"\7e\2\2hi\7c\2\2ij\7v\2\2jk\7e\2\2kl\7j\2\2l\16\3\2\2\2mn\7e\2\2no\7q"+
		"\2\2op\7r\2\2pq\7{\2\2qr\7f\2\2rs\7c\2\2st\7v\2\2tu\7c\2\2u\20\3\2\2\2"+
		"vw\7c\2\2wx\7u\2\2x\22\3\2\2\2yz\7h\2\2z{\7t\2\2{|\7q\2\2|}\7o\2\2}\24"+
		"\3\2\2\2~\177\7v\2\2\177\u0080\7q\2\2\u0080\26\3\2\2\2\u0081\u0082\7w"+
		"\2\2\u0082\u0083\7u\2\2\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086"+
		"\7i\2\2\u0086\30\3\2\2\2\u0087\u0088\7q\2\2\u0088\u0089\7p\2\2\u0089\u008a"+
		"\7/\2\2\u008a\u008b\7e\2\2\u008b\u008c\7q\2\2\u008c\u008d\7p\2\2\u008d"+
		"\u008e\7f\2\2\u008e\u008f\7k\2\2\u008f\u0090\7v\2\2\u0090\u0091\7k\2\2"+
		"\u0091\u0092\7q\2\2\u0092\u0093\7p\2\2\u0093\32\3\2\2\2\u0094\u0095\7"+
		"h\2\2\u0095\u0096\7k\2\2\u0096\u0097\7g\2\2\u0097\u0098\7n\2\2\u0098\u0099"+
		"\7f\2\2\u0099\u009a\7k\2\2\u009a\u009b\7p\2\2\u009b\u009c\7i\2\2\u009c"+
		"\34\3\2\2\2\u009d\u009e\7y\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7v\2\2\u00a0"+
		"\u00a1\7j\2\2\u00a1\u00a2\7/\2\2\u00a2\u00a3\7h\2\2\u00a3\u00a4\7g\2\2"+
		"\u00a4\u00a5\7v\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7\7j\2\2\u00a7\u00a8"+
		"\7/\2\2\u00a8\u00a9\7d\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7v\2\2\u00ab"+
		"\u00ac\7e\2\2\u00ac\u00ad\7j\2\2\u00ad\u00ae\7/\2\2\u00ae\u00af\7u\2\2"+
		"\u00af\u00b0\7k\2\2\u00b0\u00b1\7|\2\2\u00b1\u00b2\7g\2\2\u00b2\36\3\2"+
		"\2\2\u00b3\u00b4\7y\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7"+
		"\7j\2\2\u00b7\u00b8\7/\2\2\u00b8\u00b9\7y\2\2\u00b9\u00ba\7t\2\2\u00ba"+
		"\u00bb\7k\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7/\2\2"+
		"\u00be\u00bf\7d\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2"+
		"\7e\2\2\u00c2\u00c3\7j\2\2\u00c3\u00c4\7/\2\2\u00c4\u00c5\7u\2\2\u00c5"+
		"\u00c6\7k\2\2\u00c6\u00c7\7|\2\2\u00c7\u00c8\7g\2\2\u00c8 \3\2\2\2\u00c9"+
		"\u00ca\7v\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7p\2\2"+
		"\u00cd\u00ce\7u\2\2\u00ce\u00cf\7h\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1"+
		"\7t\2\2\u00d1\u00d2\7o\2\2\u00d2\"\3\2\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5"+
		"\7p\2\2\u00d5$\3\2\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9"+
		"\7c\2\2\u00d9\u00da\7f\2\2\u00da\u00db\7e\2\2\u00db\u00dc\7u\2\2\u00dc"+
		"\u00dd\7x\2\2\u00dd&\3\2\2\2\u00de\u00df\7y\2\2\u00df\u00e0\7k\2\2\u00e0"+
		"\u00e1\7v\2\2\u00e1\u00e2\7j\2\2\u00e2(\3\2\2\2\u00e3\u00e4\7c\2\2\u00e4"+
		"\u00e5\7d\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7v\2\2"+
		"\u00e8*\3\2\2\2\u00e9\u00ea\7e\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7n\2"+
		"\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7r\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0"+
		"\7q\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7u\2\2\u00f3"+
		"\u00f4\7u\2\2\u00f4,\3\2\2\2\u00f5\u00f6\7y\2\2\u00f6\u00f7\7k\2\2\u00f7"+
		"\u00f8\7v\2\2\u00f8\u00f9\7j\2\2\u00f9\u00fa\7/\2\2\u00fa\u00fb\7v\2\2"+
		"\u00fb\u00fc\7c\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7i\2\2\u00fe\u00ff"+
		"\7g\2\2\u00ff\u0100\7v\2\2\u0100.\3\2\2\2\u0101\u0102\7h\2\2\u0102\u0103"+
		"\7t\2\2\u0103\u0104\7q\2\2\u0104\u0105\7o\2\2\u0105\u0106\7/\2\2\u0106"+
		"\u0107\7h\2\2\u0107\u0108\7k\2\2\u0108\u0109\7n\2\2\u0109\u010a\7g\2\2"+
		"\u010a\60\3\2\2\2\u010b\u010c\7h\2\2\u010c\u010d\7q\2\2\u010d\u010e\7"+
		"t\2\2\u010e\u010f\7/\2\2\u010f\u0110\7g\2\2\u0110\u0111\7x\2\2\u0111\u0112"+
		"\7g\2\2\u0112\u0113\7t\2\2\u0113\u0114\7{\2\2\u0114\62\3\2\2\2\u0115\u0116"+
		"\7k\2\2\u0116\u0117\7h\2\2\u0117\64\3\2\2\2\u0118\u0119\t\2\2\2\u0119"+
		"\66\3\2\2\2\u011a\u011b\t\3\2\2\u011b8\3\2\2\2\u011c\u011d\5\65\33\2\u011d"+
		"\u011e\5\65\33\2\u011e:\3\2\2\2\u011f\u0120\t\4\2\2\u0120<\3\2\2\2\u0121"+
		"\u0123\5? \2\u0122\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2"+
		"\2\u0124\u0125\3\2\2\2\u0125>\3\2\2\2\u0126\u0127\n\5\2\2\u0127@\3\2\2"+
		"\2\u0128\u012a\5\67\34\2\u0129\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012cB\3\2\2\2\u012d\u012f\7$\2\2\u012e"+
		"\u0130\5=\37\2\u012f\u012e\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2"+
		"\2\2\u0131\u0132\7$\2\2\u0132D\3\2\2\2\u0133\u0135\7\17\2\2\u0134\u0133"+
		"\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0139\7\f\2\2\u0137"+
		"\u0139\7\17\2\2\u0138\u0134\3\2\2\2\u0138\u0137\3\2\2\2\u0139F\3\2\2\2"+
		"\u013a\u0146\t\6\2\2\u013b\u013c\7?\2\2\u013c\u0146\7?\2\2\u013d\u013e"+
		"\7e\2\2\u013e\u013f\7q\2\2\u013f\u0140\7p\2\2\u0140\u0141\7v\2\2\u0141"+
		"\u0142\7c\2\2\u0142\u0143\7k\2\2\u0143\u0144\7p\2\2\u0144\u0146\7u\2\2"+
		"\u0145\u013a\3\2\2\2\u0145\u013b\3\2\2\2\u0145\u013d\3\2\2\2\u0146H\3"+
		"\2\2\2\u0147\u0149\t\7\2\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\b%"+
		"\2\2\u014dJ\3\2\2\2\u014e\u014f\7\61\2\2\u014f\u0150\7,\2\2\u0150\u0154"+
		"\3\2\2\2\u0151\u0153\13\2\2\2\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2"+
		"\u0154\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154"+
		"\3\2\2\2\u0157\u0158\7,\2\2\u0158\u0159\7\61\2\2\u0159\u015a\3\2\2\2\u015a"+
		"\u015b\b&\2\2\u015bL\3\2\2\2\u015c\u015d\7\61\2\2\u015d\u015e\7\61\2\2"+
		"\u015e\u0162\3\2\2\2\u015f\u0161\n\b\2\2\u0160\u015f\3\2\2\2\u0161\u0164"+
		"\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0165\u0166\b\'\2\2\u0166N\3\2\2\2\f\2\u0124\u012b\u012f"+
		"\u0134\u0138\u0145\u014a\u0154\u0162\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}