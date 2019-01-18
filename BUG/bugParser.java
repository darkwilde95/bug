// Generated from .\bug.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class bugParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_statement = 2, RULE_expression = 3, 
		RULE_type = 4, RULE_print = 5, RULE_assignation = 6, RULE_b_if = 7, RULE_b_while = 8, 
		RULE_b_for = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "statement", "expression", "type", "print", "assignation", 
			"b_if", "b_while", "b_for"
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

	@Override
	public String getGrammarFileName() { return "bug.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public bugParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(bugParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(bugParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(bugParser.NEWLINE, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(20);
				statement();
				setState(21);
				match(NEWLINE);
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
			match(EOF);
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

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(bugParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(bugParser.NEWLINE, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(30);
				statement();
				setState(31);
				match(NEWLINE);
				}
				}
				setState(37);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public B_ifContext b_if() {
			return getRuleContext(B_ifContext.class,0);
		}
		public B_whileContext b_while() {
			return getRuleContext(B_whileContext.class,0);
		}
		public B_forContext b_for() {
			return getRuleContext(B_forContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				assignation();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				b_if();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				b_while();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				b_for();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				print();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotExprContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(bugParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(bugParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(bugParser.MINUS, 0); }
		public AddSubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegExprContext extends ExpressionContext {
		public TerminalNode MINUS() { return getToken(bugParser.MINUS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitNegExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeExprContext extends ExpressionContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitTypeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LE() { return getToken(bugParser.LE, 0); }
		public TerminalNode GE() { return getToken(bugParser.GE, 0); }
		public TerminalNode LT() { return getToken(bugParser.LT, 0); }
		public TerminalNode GT() { return getToken(bugParser.GT, 0); }
		public CompExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(bugParser.OR, 0); }
		public OrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultDivExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULT() { return getToken(bugParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(bugParser.DIV, 0); }
		public MultDivExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitMultDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqNeqExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(bugParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(bugParser.NEQ, 0); }
		public EqNeqExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitEqNeqExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(bugParser.AND, 0); }
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(46);
				match(MINUS);
				setState(47);
				expression(9);
				}
				break;
			case NOT:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(48);
				match(NOT);
				setState(49);
				expression(8);
				}
				break;
			case PL:
			case TRUE:
			case FALSE:
			case ID:
			case INT:
				{
				_localctx = new TypeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(71);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(53);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(54);
						((MultDivExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((MultDivExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(55);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(56);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(57);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(58);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new CompExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(59);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(60);
						((CompExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
							((CompExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(61);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new EqNeqExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(62);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(63);
						((EqNeqExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqNeqExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(64);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(65);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(66);
						match(AND);
						setState(67);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(69);
						match(OR);
						setState(70);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdentifierContext extends TypeContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public IdentifierContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberTypeContext extends TypeContext {
		public TerminalNode INT() { return getToken(bugParser.INT, 0); }
		public NumberTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitNumberType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentExprContext extends TypeContext {
		public TerminalNode PL() { return getToken(bugParser.PL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PR() { return getToken(bugParser.PR, 0); }
		public ParentExprContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitParentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolTypeContext extends TypeContext {
		public Token value;
		public TerminalNode TRUE() { return getToken(bugParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(bugParser.FALSE, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PL:
				_localctx = new ParentExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(PL);
				setState(77);
				expression(0);
				setState(78);
				match(PR);
				}
				break;
			case INT:
				_localctx = new NumberTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(INT);
				}
				break;
			case TRUE:
			case FALSE:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				((BoolTypeContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
					((BoolTypeContext)_localctx).value = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case ID:
				_localctx = new IdentifierContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(82);
				match(ID);
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

	public static class PrintContext extends ParserRuleContext {
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
	 
		public PrintContext() { }
		public void copyFrom(PrintContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BugPrintContext extends PrintContext {
		public TerminalNode PRINT() { return getToken(bugParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BugPrintContext(PrintContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBugPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_print);
		try {
			_localctx = new BugPrintContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(PRINT);
			setState(86);
			expression(0);
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

	public static class AssignationContext extends ParserRuleContext {
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
	 
		public AssignationContext() { }
		public void copyFrom(AssignationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BugAssignationContext extends AssignationContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BugAssignationContext(AssignationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBugAssignation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignation);
		try {
			_localctx = new BugAssignationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(ID);
			setState(89);
			match(ASSIGN);
			setState(90);
			expression(0);
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

	public static class B_ifContext extends ParserRuleContext {
		public B_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_if; }
	 
		public B_ifContext() { }
		public void copyFrom(B_ifContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BugIfContext extends B_ifContext {
		public TerminalNode IF() { return getToken(bugParser.IF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(bugParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(bugParser.NEWLINE, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode END() { return getToken(bugParser.END, 0); }
		public List<TerminalNode> ELIF() { return getTokens(bugParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(bugParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(bugParser.ELSE, 0); }
		public BugIfContext(B_ifContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBugIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_ifContext b_if() throws RecognitionException {
		B_ifContext _localctx = new B_ifContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_b_if);
		int _la;
		try {
			_localctx = new BugIfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(IF);
			setState(93);
			expression(0);
			setState(94);
			match(NEWLINE);
			setState(95);
			block();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(96);
				match(ELIF);
				setState(97);
				expression(0);
				setState(98);
				match(NEWLINE);
				setState(99);
				block();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(106);
				match(ELSE);
				setState(107);
				match(NEWLINE);
				setState(108);
				block();
				}
			}

			setState(111);
			match(END);
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

	public static class B_whileContext extends ParserRuleContext {
		public B_whileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_while; }
	 
		public B_whileContext() { }
		public void copyFrom(B_whileContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BugWhileContext extends B_whileContext {
		public TerminalNode WHILE() { return getToken(bugParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(bugParser.NEWLINE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(bugParser.END, 0); }
		public BugWhileContext(B_whileContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBugWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_whileContext b_while() throws RecognitionException {
		B_whileContext _localctx = new B_whileContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_b_while);
		try {
			_localctx = new BugWhileContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(WHILE);
			setState(114);
			expression(0);
			setState(115);
			match(NEWLINE);
			setState(116);
			block();
			setState(117);
			match(END);
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

	public static class B_forContext extends ParserRuleContext {
		public B_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_for; }
	 
		public B_forContext() { }
		public void copyFrom(B_forContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BugForContext extends B_forContext {
		public TerminalNode FOR() { return getToken(bugParser.FOR, 0); }
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public TerminalNode TO() { return getToken(bugParser.TO, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode STEP() { return getToken(bugParser.STEP, 0); }
		public TerminalNode NEWLINE() { return getToken(bugParser.NEWLINE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(bugParser.END, 0); }
		public BugForContext(B_forContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBugFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_forContext b_for() throws RecognitionException {
		B_forContext _localctx = new B_forContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_b_for);
		try {
			_localctx = new BugForContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(FOR);
			setState(120);
			assignation();
			setState(121);
			match(TO);
			setState(122);
			expression(0);
			setState(123);
			match(STEP);
			setState(124);
			expression(0);
			setState(125);
			match(NEWLINE);
			setState(126);
			block();
			setState(127);
			match(END);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u0084\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\3\3\3\3\7\3$\n"+
		"\3\f\3\16\3\'\13\3\3\4\3\4\3\4\3\4\3\4\5\4.\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5\66\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5"+
		"\6V\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\th\n\t\f\t\16\tk\13\t\3\t\3\t\3\t\5\tp\n\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\2\3"+
		"\b\f\2\4\6\b\n\f\16\20\22\24\2\7\3\2\6\7\3\2\4\5\3\2\22\25\3\2\26\27\3"+
		"\2\20\21\2\u008c\2\33\3\2\2\2\4%\3\2\2\2\6-\3\2\2\2\b\65\3\2\2\2\nU\3"+
		"\2\2\2\fW\3\2\2\2\16Z\3\2\2\2\20^\3\2\2\2\22s\3\2\2\2\24y\3\2\2\2\26\27"+
		"\5\6\4\2\27\30\7%\2\2\30\32\3\2\2\2\31\26\3\2\2\2\32\35\3\2\2\2\33\31"+
		"\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\2\2\3\37\3"+
		"\3\2\2\2 !\5\6\4\2!\"\7%\2\2\"$\3\2\2\2# \3\2\2\2$\'\3\2\2\2%#\3\2\2\2"+
		"%&\3\2\2\2&\5\3\2\2\2\'%\3\2\2\2(.\5\16\b\2).\5\20\t\2*.\5\22\n\2+.\5"+
		"\24\13\2,.\5\f\7\2-(\3\2\2\2-)\3\2\2\2-*\3\2\2\2-+\3\2\2\2-,\3\2\2\2."+
		"\7\3\2\2\2/\60\b\5\1\2\60\61\7\5\2\2\61\66\5\b\5\13\62\63\7\17\2\2\63"+
		"\66\5\b\5\n\64\66\5\n\6\2\65/\3\2\2\2\65\62\3\2\2\2\65\64\3\2\2\2\66K"+
		"\3\2\2\2\678\f\t\2\289\t\2\2\29J\5\b\5\n:;\f\b\2\2;<\t\3\2\2<J\5\b\5\t"+
		"=>\f\7\2\2>?\t\4\2\2?J\5\b\5\b@A\f\6\2\2AB\t\5\2\2BJ\5\b\5\7CD\f\5\2\2"+
		"DE\7\r\2\2EJ\5\b\5\6FG\f\4\2\2GH\7\16\2\2HJ\5\b\5\5I\67\3\2\2\2I:\3\2"+
		"\2\2I=\3\2\2\2I@\3\2\2\2IC\3\2\2\2IF\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2"+
		"\2\2L\t\3\2\2\2MK\3\2\2\2NO\7\13\2\2OP\5\b\5\2PQ\7\f\2\2QV\3\2\2\2RV\7"+
		"\"\2\2SV\t\6\2\2TV\7!\2\2UN\3\2\2\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\13"+
		"\3\2\2\2WX\7 \2\2XY\5\b\5\2Y\r\3\2\2\2Z[\7!\2\2[\\\7\3\2\2\\]\5\b\5\2"+
		"]\17\3\2\2\2^_\7\30\2\2_`\5\b\5\2`a\7%\2\2ai\5\4\3\2bc\7\32\2\2cd\5\b"+
		"\5\2de\7%\2\2ef\5\4\3\2fh\3\2\2\2gb\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2"+
		"\2\2jo\3\2\2\2ki\3\2\2\2lm\7\31\2\2mn\7%\2\2np\5\4\3\2ol\3\2\2\2op\3\2"+
		"\2\2pq\3\2\2\2qr\7\34\2\2r\21\3\2\2\2st\7\33\2\2tu\5\b\5\2uv\7%\2\2vw"+
		"\5\4\3\2wx\7\34\2\2x\23\3\2\2\2yz\7\35\2\2z{\5\16\b\2{|\7\37\2\2|}\5\b"+
		"\5\2}~\7\36\2\2~\177\5\b\5\2\177\u0080\7%\2\2\u0080\u0081\5\4\3\2\u0081"+
		"\u0082\7\34\2\2\u0082\25\3\2\2\2\13\33%-\65IKUio";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}