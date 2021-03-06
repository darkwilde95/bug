package Bug;
// Generated from .\bug.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class bugLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, PLUS=2, MINUS=3, MULT=4, DIV=5, COMMA=6, SBL=7, SBR=8, PL=9,
		PR=10, AND=11, OR=12, NOT=13, TRUE=14, FALSE=15, LT=16, GT=17, LE=18,
		GE=19, EQ=20, NEQ=21, IF=22, ELSE=23, ELIF=24, WHILE=25, END=26, FOR=27,
		STEP=28, TO=29, PRINT=30, ID=31, INT=32, FLOAT=33, COMMENT=34, NEWLINE=35,
		WS=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ASSIGN", "PLUS", "MINUS", "MULT", "DIV", "COMMA", "SBL", "SBR", "PL",
			"PR", "AND", "OR", "NOT", "TRUE", "FALSE", "LT", "GT", "LE", "GE", "EQ",
			"NEQ", "IF", "ELSE", "ELIF", "WHILE", "END", "FOR", "STEP", "TO", "PRINT",
			"ID", "INT", "FLOAT", "COMMENT", "NEWLINE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'+'", "'-'", "'*'", "'/'", "','", "'['", "']'", "'('",
			"')'", "'&&'", "'||'", "'!'", "'true'", "'false'", "'<'", "'>'", "'<='",
			"'>='", "'=='", "'!='", "'if'", "'else'", "'elif'", "'while '", "'end'",
			"'for'", "'step'", "'to'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ASSIGN", "PLUS", "MINUS", "MULT", "DIV", "COMMA", "SBL", "SBR",
			"PL", "PR", "AND", "OR", "NOT", "TRUE", "FALSE", "LT", "GT", "LE", "GE",
			"EQ", "NEQ", "IF", "ELSE", "ELIF", "WHILE", "END", "FOR", "STEP", "TO",
			"PRINT", "ID", "INT", "FLOAT", "COMMENT", "NEWLINE", "WS"
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


	public bugLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "bug.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u00d8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \7 \u00af\n \f \16 \u00b2\13 \3!\6!\u00b5\n!\r!\16!\u00b6\3\"\6"+
		"\"\u00ba\n\"\r\"\16\"\u00bb\3\"\3\"\6\"\u00c0\n\"\r\"\16\"\u00c1\3#\3"+
		"#\7#\u00c6\n#\f#\16#\u00c9\13#\3#\3#\3$\5$\u00ce\n$\3$\3$\3%\6%\u00d3"+
		"\n%\r%\16%\u00d4\3%\3%\2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2\7\5\2C\\aac|\6\2\62;C\\"+
		"aac|\3\2\62;\4\2\f\f\17\17\4\2\13\13\"\"\2\u00de\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2"+
		"\2\5M\3\2\2\2\7O\3\2\2\2\tQ\3\2\2\2\13S\3\2\2\2\rU\3\2\2\2\17W\3\2\2\2"+
		"\21Y\3\2\2\2\23[\3\2\2\2\25]\3\2\2\2\27_\3\2\2\2\31b\3\2\2\2\33e\3\2\2"+
		"\2\35g\3\2\2\2\37l\3\2\2\2!r\3\2\2\2#t\3\2\2\2%v\3\2\2\2\'y\3\2\2\2)|"+
		"\3\2\2\2+\177\3\2\2\2-\u0082\3\2\2\2/\u0085\3\2\2\2\61\u008a\3\2\2\2\63"+
		"\u008f\3\2\2\2\65\u0096\3\2\2\2\67\u009a\3\2\2\29\u009e\3\2\2\2;\u00a3"+
		"\3\2\2\2=\u00a6\3\2\2\2?\u00ac\3\2\2\2A\u00b4\3\2\2\2C\u00b9\3\2\2\2E"+
		"\u00c3\3\2\2\2G\u00cd\3\2\2\2I\u00d2\3\2\2\2KL\7?\2\2L\4\3\2\2\2MN\7-"+
		"\2\2N\6\3\2\2\2OP\7/\2\2P\b\3\2\2\2QR\7,\2\2R\n\3\2\2\2ST\7\61\2\2T\f"+
		"\3\2\2\2UV\7.\2\2V\16\3\2\2\2WX\7]\2\2X\20\3\2\2\2YZ\7_\2\2Z\22\3\2\2"+
		"\2[\\\7*\2\2\\\24\3\2\2\2]^\7+\2\2^\26\3\2\2\2_`\7(\2\2`a\7(\2\2a\30\3"+
		"\2\2\2bc\7~\2\2cd\7~\2\2d\32\3\2\2\2ef\7#\2\2f\34\3\2\2\2gh\7v\2\2hi\7"+
		"t\2\2ij\7w\2\2jk\7g\2\2k\36\3\2\2\2lm\7h\2\2mn\7c\2\2no\7n\2\2op\7u\2"+
		"\2pq\7g\2\2q \3\2\2\2rs\7>\2\2s\"\3\2\2\2tu\7@\2\2u$\3\2\2\2vw\7>\2\2"+
		"wx\7?\2\2x&\3\2\2\2yz\7@\2\2z{\7?\2\2{(\3\2\2\2|}\7?\2\2}~\7?\2\2~*\3"+
		"\2\2\2\177\u0080\7#\2\2\u0080\u0081\7?\2\2\u0081,\3\2\2\2\u0082\u0083"+
		"\7k\2\2\u0083\u0084\7h\2\2\u0084.\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087"+
		"\7n\2\2\u0087\u0088\7u\2\2\u0088\u0089\7g\2\2\u0089\60\3\2\2\2\u008a\u008b"+
		"\7g\2\2\u008b\u008c\7n\2\2\u008c\u008d\7k\2\2\u008d\u008e\7h\2\2\u008e"+
		"\62\3\2\2\2\u008f\u0090\7y\2\2\u0090\u0091\7j\2\2\u0091\u0092\7k\2\2\u0092"+
		"\u0093\7n\2\2\u0093\u0094\7g\2\2\u0094\u0095\7\"\2\2\u0095\64\3\2\2\2"+
		"\u0096\u0097\7g\2\2\u0097\u0098\7p\2\2\u0098\u0099\7f\2\2\u0099\66\3\2"+
		"\2\2\u009a\u009b\7h\2\2\u009b\u009c\7q\2\2\u009c\u009d\7t\2\2\u009d8\3"+
		"\2\2\2\u009e\u009f\7u\2\2\u009f\u00a0\7v\2\2\u00a0\u00a1\7g\2\2\u00a1"+
		"\u00a2\7r\2\2\u00a2:\3\2\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7q\2\2\u00a5"+
		"<\3\2\2\2\u00a6\u00a7\7r\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7k\2\2\u00a9"+
		"\u00aa\7p\2\2\u00aa\u00ab\7v\2\2\u00ab>\3\2\2\2\u00ac\u00b0\t\2\2\2\u00ad"+
		"\u00af\t\3\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2"+
		"\2\2\u00b0\u00b1\3\2\2\2\u00b1@\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5"+
		"\t\4\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7B\3\2\2\2\u00b8\u00ba\t\4\2\2\u00b9\u00b8\3\2\2\2"+
		"\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\u00bf\7\60\2\2\u00be\u00c0\t\4\2\2\u00bf\u00be\3\2\2\2"+
		"\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2D\3"+
		"\2\2\2\u00c3\u00c7\7%\2\2\u00c4\u00c6\n\5\2\2\u00c5\u00c4\3\2\2\2\u00c6"+
		"\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\b#\2\2\u00cbF\3\2\2\2\u00cc\u00ce"+
		"\7\17\2\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2"+
		"\u00cf\u00d0\7\f\2\2\u00d0H\3\2\2\2\u00d1\u00d3\t\6\2\2\u00d2\u00d1\3"+
		"\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d7\b%\2\2\u00d7J\3\2\2\2\n\2\u00b0\u00b6\u00bb"+
		"\u00c1\u00c7\u00cd\u00d4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
