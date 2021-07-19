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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, NON_ZERO_DIGIT=30, STRING=31, 
		CRLF=32, Operator=33, WS=34, COMMENT=35, LINE_COMMENT=36;
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
			"T__25", "T__26", "T__27", "T__28", "DIGIT", "NON_Z_DIGIT", "TWODIGIT", 
			"LETTER", "StringCharacters", "StringCharacter", "NON_ZERO_DIGIT", "STRING", 
			"CRLF", "Operator", "WS", "COMMENT", "LINE_COMMENT"
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
			"'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "NON_ZERO_DIGIT", "STRING", "CRLF", 
			"Operator", "WS", "COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u018a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3#\6#\u0146\n"+
		"#\r#\16#\u0147\3$\3$\3%\6%\u014d\n%\r%\16%\u014e\3&\3&\5&\u0153\n&\3&"+
		"\3&\3\'\5\'\u0158\n\'\3\'\3\'\5\'\u015c\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3(\3(\5(\u0169\n(\3)\6)\u016c\n)\r)\16)\u016d\3)\3)\3*\3*\3*\3*\7*\u0176"+
		"\n*\f*\16*\u0179\13*\3*\3*\3*\3*\3*\3+\3+\3+\3+\7+\u0184\n+\f+\16+\u0187"+
		"\13+\3+\3+\3\u0177\2,\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37=\2?\2A\2C\2E\2G\2I K!M\"O#Q$S%U&\3\2\t\3\2\62;\3"+
		"\2\63;\4\2C\\c|\4\2$$^^\4\2>>@@\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u018d"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2"+
		"\2\2U\3\2\2\2\3W\3\2\2\2\5_\3\2\2\2\7a\3\2\2\2\tc\3\2\2\2\13g\3\2\2\2"+
		"\ro\3\2\2\2\17u\3\2\2\2\21~\3\2\2\2\23\u0081\3\2\2\2\25\u0086\3\2\2\2"+
		"\27\u0089\3\2\2\2\31\u008f\3\2\2\2\33\u009c\3\2\2\2\35\u00a5\3\2\2\2\37"+
		"\u00bb\3\2\2\2!\u00d1\3\2\2\2#\u00db\3\2\2\2%\u00de\3\2\2\2\'\u00e6\3"+
		"\2\2\2)\u00ea\3\2\2\2+\u00ef\3\2\2\2-\u00f8\3\2\2\2/\u00fe\3\2\2\2\61"+
		"\u010a\3\2\2\2\63\u0116\3\2\2\2\65\u0120\3\2\2\2\67\u012a\3\2\2\29\u0131"+
		"\3\2\2\2;\u0138\3\2\2\2=\u013b\3\2\2\2?\u013d\3\2\2\2A\u013f\3\2\2\2C"+
		"\u0142\3\2\2\2E\u0145\3\2\2\2G\u0149\3\2\2\2I\u014c\3\2\2\2K\u0150\3\2"+
		"\2\2M\u015b\3\2\2\2O\u0168\3\2\2\2Q\u016b\3\2\2\2S\u0171\3\2\2\2U\u017f"+
		"\3\2\2\2WX\7r\2\2XY\7t\2\2YZ\7q\2\2Z[\7e\2\2[\\\7g\2\2\\]\7u\2\2]^\7u"+
		"\2\2^\4\3\2\2\2_`\7}\2\2`\6\3\2\2\2ab\7\177\2\2b\b\3\2\2\2cd\7v\2\2de"+
		"\7t\2\2ef\7{\2\2f\n\3\2\2\2gh\7h\2\2hi\7k\2\2ij\7p\2\2jk\7c\2\2kl\7n\2"+
		"\2lm\7n\2\2mn\7{\2\2n\f\3\2\2\2op\7e\2\2pq\7c\2\2qr\7v\2\2rs\7e\2\2st"+
		"\7j\2\2t\16\3\2\2\2uv\7e\2\2vw\7q\2\2wx\7r\2\2xy\7{\2\2yz\7f\2\2z{\7c"+
		"\2\2{|\7v\2\2|}\7c\2\2}\20\3\2\2\2~\177\7c\2\2\177\u0080\7u\2\2\u0080"+
		"\22\3\2\2\2\u0081\u0082\7h\2\2\u0082\u0083\7t\2\2\u0083\u0084\7q\2\2\u0084"+
		"\u0085\7o\2\2\u0085\24\3\2\2\2\u0086\u0087\7v\2\2\u0087\u0088\7q\2\2\u0088"+
		"\26\3\2\2\2\u0089\u008a\7w\2\2\u008a\u008b\7u\2\2\u008b\u008c\7k\2\2\u008c"+
		"\u008d\7p\2\2\u008d\u008e\7i\2\2\u008e\30\3\2\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7p\2\2\u0091\u0092\7/\2\2\u0092\u0093\7e\2\2\u0093\u0094\7q\2\2"+
		"\u0094\u0095\7p\2\2\u0095\u0096\7f\2\2\u0096\u0097\7k\2\2\u0097\u0098"+
		"\7v\2\2\u0098\u0099\7k\2\2\u0099\u009a\7q\2\2\u009a\u009b\7p\2\2\u009b"+
		"\32\3\2\2\2\u009c\u009d\7h\2\2\u009d\u009e\7k\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7n\2\2\u00a0\u00a1\7f\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7p\2\2"+
		"\u00a3\u00a4\7i\2\2\u00a4\34\3\2\2\2\u00a5\u00a6\7y\2\2\u00a6\u00a7\7"+
		"k\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa\7/\2\2\u00aa\u00ab"+
		"\7h\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7e\2\2\u00ae"+
		"\u00af\7j\2\2\u00af\u00b0\7/\2\2\u00b0\u00b1\7d\2\2\u00b1\u00b2\7c\2\2"+
		"\u00b2\u00b3\7v\2\2\u00b3\u00b4\7e\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6"+
		"\7/\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7|\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\36\3\2\2\2\u00bb\u00bc\7y\2\2\u00bc\u00bd\7k\2\2\u00bd"+
		"\u00be\7v\2\2\u00be\u00bf\7j\2\2\u00bf\u00c0\7/\2\2\u00c0\u00c1\7y\2\2"+
		"\u00c1\u00c2\7t\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5"+
		"\7g\2\2\u00c5\u00c6\7/\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8\7c\2\2\u00c8"+
		"\u00c9\7v\2\2\u00c9\u00ca\7e\2\2\u00ca\u00cb\7j\2\2\u00cb\u00cc\7/\2\2"+
		"\u00cc\u00cd\7u\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7|\2\2\u00cf\u00d0"+
		"\7g\2\2\u00d0 \3\2\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4"+
		"\7c\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7h\2\2\u00d7"+
		"\u00d8\7q\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7o\2\2\u00da\"\3\2\2\2\u00db"+
		"\u00dc\7q\2\2\u00dc\u00dd\7p\2\2\u00dd$\3\2\2\2\u00de\u00df\7n\2\2\u00df"+
		"\u00e0\7q\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2\7f\2\2\u00e2\u00e3\7e\2\2"+
		"\u00e3\u00e4\7u\2\2\u00e4\u00e5\7x\2\2\u00e5&\3\2\2\2\u00e6\u00e7\7r\2"+
		"\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7f\2\2\u00e9(\3\2\2\2\u00ea\u00eb\7"+
		"y\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7j\2\2\u00ee*"+
		"\3\2\2\2\u00ef\u00f0\7d\2\2\u00f0\u00f1\7{\2\2\u00f1\u00f2\7/\2\2\u00f2"+
		"\u00f3\7d\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7v\2\2\u00f5\u00f6\7e\2\2"+
		"\u00f6\u00f7\7j\2\2\u00f7,\3\2\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa\7d\2"+
		"\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7v\2\2\u00fd.\3\2"+
		"\2\2\u00fe\u00ff\7e\2\2\u00ff\u0100\7c\2\2\u0100\u0101\7n\2\2\u0101\u0102"+
		"\7n\2\2\u0102\u0103\7r\2\2\u0103\u0104\7t\2\2\u0104\u0105\7q\2\2\u0105"+
		"\u0106\7e\2\2\u0106\u0107\7g\2\2\u0107\u0108\7u\2\2\u0108\u0109\7u\2\2"+
		"\u0109\60\3\2\2\2\u010a\u010b\7y\2\2\u010b\u010c\7k\2\2\u010c\u010d\7"+
		"v\2\2\u010d\u010e\7j\2\2\u010e\u010f\7/\2\2\u010f\u0110\7v\2\2\u0110\u0111"+
		"\7c\2\2\u0111\u0112\7t\2\2\u0112\u0113\7i\2\2\u0113\u0114\7g\2\2\u0114"+
		"\u0115\7v\2\2\u0115\62\3\2\2\2\u0116\u0117\7h\2\2\u0117\u0118\7t\2\2\u0118"+
		"\u0119\7q\2\2\u0119\u011a\7o\2\2\u011a\u011b\7/\2\2\u011b\u011c\7h\2\2"+
		"\u011c\u011d\7k\2\2\u011d\u011e\7n\2\2\u011e\u011f\7g\2\2\u011f\64\3\2"+
		"\2\2\u0120\u0121\7h\2\2\u0121\u0122\7q\2\2\u0122\u0123\7t\2\2\u0123\u0124"+
		"\7/\2\2\u0124\u0125\7g\2\2\u0125\u0126\7x\2\2\u0126\u0127\7g\2\2\u0127"+
		"\u0128\7t\2\2\u0128\u0129\7{\2\2\u0129\66\3\2\2\2\u012a\u012b\7c\2\2\u012b"+
		"\u012c\7u\2\2\u012c\u012d\7u\2\2\u012d\u012e\7k\2\2\u012e\u012f\7i\2\2"+
		"\u012f\u0130\7p\2\2\u01308\3\2\2\2\u0131\u0132\7u\2\2\u0132\u0133\7q\2"+
		"\2\u0133\u0134\7w\2\2\u0134\u0135\7t\2\2\u0135\u0136\7e\2\2\u0136\u0137"+
		"\7g\2\2\u0137:\3\2\2\2\u0138\u0139\7k\2\2\u0139\u013a\7h\2\2\u013a<\3"+
		"\2\2\2\u013b\u013c\t\2\2\2\u013c>\3\2\2\2\u013d\u013e\t\3\2\2\u013e@\3"+
		"\2\2\2\u013f\u0140\5=\37\2\u0140\u0141\5=\37\2\u0141B\3\2\2\2\u0142\u0143"+
		"\t\4\2\2\u0143D\3\2\2\2\u0144\u0146\5G$\2\u0145\u0144\3\2\2\2\u0146\u0147"+
		"\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148F\3\2\2\2\u0149"+
		"\u014a\n\5\2\2\u014aH\3\2\2\2\u014b\u014d\5? \2\u014c\u014b\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014fJ\3\2\2\2"+
		"\u0150\u0152\7$\2\2\u0151\u0153\5E#\2\u0152\u0151\3\2\2\2\u0152\u0153"+
		"\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\7$\2\2\u0155L\3\2\2\2\u0156\u0158"+
		"\7\17\2\2\u0157\u0156\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u0159\3\2\2\2"+
		"\u0159\u015c\7\f\2\2\u015a\u015c\7\17\2\2\u015b\u0157\3\2\2\2\u015b\u015a"+
		"\3\2\2\2\u015cN\3\2\2\2\u015d\u0169\t\6\2\2\u015e\u015f\7?\2\2\u015f\u0169"+
		"\7?\2\2\u0160\u0161\7e\2\2\u0161\u0162\7q\2\2\u0162\u0163\7p\2\2\u0163"+
		"\u0164\7v\2\2\u0164\u0165\7c\2\2\u0165\u0166\7k\2\2\u0166\u0167\7p\2\2"+
		"\u0167\u0169\7u\2\2\u0168\u015d\3\2\2\2\u0168\u015e\3\2\2\2\u0168\u0160"+
		"\3\2\2\2\u0169P\3\2\2\2\u016a\u016c\t\7\2\2\u016b\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u0170\b)\2\2\u0170R\3\2\2\2\u0171\u0172\7\61\2\2\u0172\u0173"+
		"\7,\2\2\u0173\u0177\3\2\2\2\u0174\u0176\13\2\2\2\u0175\u0174\3\2\2\2\u0176"+
		"\u0179\3\2\2\2\u0177\u0178\3\2\2\2\u0177\u0175\3\2\2\2\u0178\u017a\3\2"+
		"\2\2\u0179\u0177\3\2\2\2\u017a\u017b\7,\2\2\u017b\u017c\7\61\2\2\u017c"+
		"\u017d\3\2\2\2\u017d\u017e\b*\2\2\u017eT\3\2\2\2\u017f\u0180\7\61\2\2"+
		"\u0180\u0181\7\61\2\2\u0181\u0185\3\2\2\2\u0182\u0184\n\b\2\2\u0183\u0182"+
		"\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186"+
		"\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\b+\2\2\u0189V\3\2\2\2\f"+
		"\2\u0147\u014e\u0152\u0157\u015b\u0168\u016d\u0177\u0185\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}