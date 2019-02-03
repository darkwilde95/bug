package Bug;
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
		RULE_expression_b = 4, RULE_expression_a = 5, RULE_print = 6, RULE_assignation = 7,
		RULE_array = 8, RULE_b_if = 9, RULE_b_while = 10, RULE_b_for = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "statement", "expression", "expression_b", "expression_a",
			"print", "assignation", "array", "b_if", "b_while", "b_for"
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
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(24);
				statement();
				setState(25);
				match(NEWLINE);
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32);
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
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(34);
				statement();
				setState(35);
				match(NEWLINE);
				}
				}
				setState(41);
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
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				assignation();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				b_if();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				b_while();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				b_for();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(46);
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
	public static class ExprBContext extends ExpressionContext {
		public Expression_bContext expression_b() {
			return getRuleContext(Expression_bContext.class,0);
		}
		public ExprBContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitExprB(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAContext extends ExpressionContext {
		public Expression_aContext expression_a() {
			return getRuleContext(Expression_aContext.class,0);
		}
		public ExprAContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitExprA(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprArrayContext extends ExpressionContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ExprArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitExprArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new ExprAContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				expression_a(0);
				}
				break;
			case 2:
				_localctx = new ExprBContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				expression_b(0);
				}
				break;
			case 3:
				_localctx = new ExprArrayContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(51);
				array();
				}
				break;
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

	public static class Expression_bContext extends ParserRuleContext {
		public Expression_bContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_b; }

		public Expression_bContext() { }
		public void copyFrom(Expression_bContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotExpr_bContext extends Expression_bContext {
		public TerminalNode NOT() { return getToken(bugParser.NOT, 0); }
		public Expression_bContext expression_b() {
			return getRuleContext(Expression_bContext.class,0);
		}
		public NotExpr_bContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitNotExpr_b(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqNeqExpr_aContext extends Expression_bContext {
		public Token op;
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public TerminalNode EQ() { return getToken(bugParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(bugParser.NEQ, 0); }
		public EqNeqExpr_aContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitEqNeqExpr_a(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanIdContext extends Expression_bContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public BooleanIdContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBooleanId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqNeqExpr_bContext extends Expression_bContext {
		public Token op;
		public List<Expression_bContext> expression_b() {
			return getRuleContexts(Expression_bContext.class);
		}
		public Expression_bContext expression_b(int i) {
			return getRuleContext(Expression_bContext.class,i);
		}
		public TerminalNode EQ() { return getToken(bugParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(bugParser.NEQ, 0); }
		public EqNeqExpr_bContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitEqNeqExpr_b(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanTypeContext extends Expression_bContext {
		public Token value;
		public TerminalNode TRUE() { return getToken(bugParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(bugParser.FALSE, 0); }
		public BooleanTypeContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExpr_bContext extends Expression_bContext {
		public List<Expression_bContext> expression_b() {
			return getRuleContexts(Expression_bContext.class);
		}
		public Expression_bContext expression_b(int i) {
			return getRuleContext(Expression_bContext.class,i);
		}
		public TerminalNode AND() { return getToken(bugParser.AND, 0); }
		public AndExpr_bContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAndExpr_b(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentExpr_bContext extends Expression_bContext {
		public TerminalNode PL() { return getToken(bugParser.PL, 0); }
		public Expression_bContext expression_b() {
			return getRuleContext(Expression_bContext.class,0);
		}
		public TerminalNode PR() { return getToken(bugParser.PR, 0); }
		public ParentExpr_bContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitParentExpr_b(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompExprContext extends Expression_bContext {
		public Token op;
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public TerminalNode LE() { return getToken(bugParser.LE, 0); }
		public TerminalNode GE() { return getToken(bugParser.GE, 0); }
		public TerminalNode LT() { return getToken(bugParser.LT, 0); }
		public TerminalNode GT() { return getToken(bugParser.GT, 0); }
		public CompExprContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExpr_bContext extends Expression_bContext {
		public List<Expression_bContext> expression_b() {
			return getRuleContexts(Expression_bContext.class);
		}
		public Expression_bContext expression_b(int i) {
			return getRuleContext(Expression_bContext.class,i);
		}
		public TerminalNode OR() { return getToken(bugParser.OR, 0); }
		public OrExpr_bContext(Expression_bContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitOrExpr_b(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_bContext expression_b() throws RecognitionException {
		return expression_b(0);
	}

	private Expression_bContext expression_b(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_bContext _localctx = new Expression_bContext(_ctx, _parentState);
		Expression_bContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression_b, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ParentExpr_bContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(55);
				match(PL);
				setState(56);
				expression_b(0);
				setState(57);
				match(PR);
				}
				break;
			case 2:
				{
				_localctx = new NotExpr_bContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				match(NOT);
				setState(60);
				expression_b(8);
				}
				break;
			case 3:
				{
				_localctx = new CompExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				expression_a(0);
				setState(62);
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
				setState(63);
				expression_a(0);
				}
				break;
			case 4:
				{
				_localctx = new EqNeqExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				expression_a(0);
				setState(66);
				((EqNeqExpr_aContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
					((EqNeqExpr_aContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(67);
				expression_a(0);
				}
				break;
			case 5:
				{
				_localctx = new BooleanTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(69);
				((BooleanTypeContext)_localctx).value = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
					((BooleanTypeContext)_localctx).value = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				{
				_localctx = new BooleanIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(70);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(82);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new EqNeqExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(73);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(74);
						((EqNeqExpr_bContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((EqNeqExpr_bContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(75);
						expression_b(6);
						}
						break;
					case 2:
						{
						_localctx = new AndExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(76);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(77);
						match(AND);
						setState(78);
						expression_b(5);
						}
						break;
					case 3:
						{
						_localctx = new OrExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(79);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(80);
						match(OR);
						setState(81);
						expression_b(4);
						}
						break;
					}
					}
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class Expression_aContext extends ParserRuleContext {
		public Expression_aContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_a; }

		public Expression_aContext() { }
		public void copyFrom(Expression_aContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IntegerIdContext extends Expression_aContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public IntegerIdContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitIntegerId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerTypeContext extends Expression_aContext {
		public TerminalNode INT() { return getToken(bugParser.INT, 0); }
		public IntegerTypeContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitIntegerType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentExpr_aContext extends Expression_aContext {
		public TerminalNode PL() { return getToken(bugParser.PL, 0); }
		public Expression_aContext expression_a() {
			return getRuleContext(Expression_aContext.class,0);
		}
		public TerminalNode PR() { return getToken(bugParser.PR, 0); }
		public ParentExpr_aContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitParentExpr_a(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegExpr_aContext extends Expression_aContext {
		public TerminalNode MINUS() { return getToken(bugParser.MINUS, 0); }
		public Expression_aContext expression_a() {
			return getRuleContext(Expression_aContext.class,0);
		}
		public NegExpr_aContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitNegExpr_a(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubExpr_aContext extends Expression_aContext {
		public Token op;
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(bugParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(bugParser.MINUS, 0); }
		public AddSubExpr_aContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAddSubExpr_a(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultDivExpr_aContext extends Expression_aContext {
		public Token op;
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public TerminalNode MULT() { return getToken(bugParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(bugParser.DIV, 0); }
		public MultDivExpr_aContext(Expression_aContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitMultDivExpr_a(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_aContext expression_a() throws RecognitionException {
		return expression_a(0);
	}

	private Expression_aContext expression_a(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_aContext _localctx = new Expression_aContext(_ctx, _parentState);
		Expression_aContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression_a, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PL:
				{
				_localctx = new ParentExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(88);
				match(PL);
				setState(89);
				expression_a(0);
				setState(90);
				match(PR);
				}
				break;
			case MINUS:
				{
				_localctx = new NegExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(MINUS);
				setState(93);
				expression_a(5);
				}
				break;
			case INT:
				{
				_localctx = new IntegerTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				match(INT);
				}
				break;
			case ID:
				{
				_localctx = new IntegerIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(104);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivExpr_aContext(new Expression_aContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_a);
						setState(98);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(99);
						((MultDivExpr_aContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((MultDivExpr_aContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(100);
						expression_a(5);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpr_aContext(new Expression_aContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_a);
						setState(101);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(102);
						((AddSubExpr_aContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddSubExpr_aContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(103);
						expression_a(4);
						}
						break;
					}
					}
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	public static class PrintContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(bugParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(PRINT);
			setState(110);
			expression();
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
	public static class EtoaAssignContext extends AssignationContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> SBL() { return getTokens(bugParser.SBL); }
		public TerminalNode SBL(int i) {
			return getToken(bugParser.SBL, i);
		}
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public List<TerminalNode> SBR() { return getTokens(bugParser.SBR); }
		public TerminalNode SBR(int i) {
			return getToken(bugParser.SBR, i);
		}
		public EtoaAssignContext(AssignationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitEtoaAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtoIdAssignContext extends AssignationContext {
		public List<TerminalNode> ID() { return getTokens(bugParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(bugParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public List<TerminalNode> SBL() { return getTokens(bugParser.SBL); }
		public TerminalNode SBL(int i) {
			return getToken(bugParser.SBL, i);
		}
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public List<TerminalNode> SBR() { return getTokens(bugParser.SBR); }
		public TerminalNode SBR(int i) {
			return getToken(bugParser.SBR, i);
		}
		public AtoIdAssignContext(AssignationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAtoIdAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprAssignContext extends AssignationContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprAssignContext(AssignationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitExprAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtoaAssignContext extends AssignationContext {
		public List<TerminalNode> ID() { return getTokens(bugParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(bugParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public List<TerminalNode> SBL() { return getTokens(bugParser.SBL); }
		public TerminalNode SBL(int i) {
			return getToken(bugParser.SBL, i);
		}
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public List<TerminalNode> SBR() { return getTokens(bugParser.SBR); }
		public TerminalNode SBR(int i) {
			return getToken(bugParser.SBR, i);
		}
		public AtoaAssignContext(AssignationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAtoaAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignation);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new ExprAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(ID);
				setState(113);
				match(ASSIGN);
				setState(114);
				expression();
				}
				break;
			case 2:
				_localctx = new AtoaAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				match(ID);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(116);
					match(SBL);
					setState(117);
					expression_a(0);
					setState(118);
					match(SBR);
					}
					}
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SBL );
				setState(124);
				match(ASSIGN);
				setState(125);
				match(ID);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					match(SBL);
					setState(127);
					expression_a(0);
					setState(128);
					match(SBR);
					}
					}
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SBL );
				}
				break;
			case 3:
				_localctx = new AtoIdAssignContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(ID);
				setState(135);
				match(ASSIGN);
				setState(136);
				match(ID);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(137);
					match(SBL);
					setState(138);
					expression_a(0);
					setState(139);
					match(SBR);
					}
					}
					setState(143);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SBL );
				}
				break;
			case 4:
				_localctx = new EtoaAssignContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(145);
				match(ID);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(146);
					match(SBL);
					setState(147);
					expression_a(0);
					setState(148);
					match(SBR);
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SBL );
				setState(154);
				match(ASSIGN);
				setState(155);
				expression();
				}
				break;
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

	public static class ArrayContext extends ParserRuleContext {
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }

		public ArrayContext() { }
		public void copyFrom(ArrayContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayTypeContext extends ArrayContext {
		public TerminalNode SBL() { return getToken(bugParser.SBL, 0); }
		public TerminalNode SBR() { return getToken(bugParser.SBR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(bugParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(bugParser.COMMA, i);
		}
		public ArrayTypeContext(ArrayContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayIdContext extends ArrayContext {
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public ArrayIdContext(ArrayContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitArrayId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_array);
		int _la;
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SBL:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(SBL);
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << SBL) | (1L << PL) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << ID) | (1L << INT))) != 0)) {
					{
					setState(160);
					expression();
					}
				}

				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(163);
					match(COMMA);
					setState(164);
					expression();
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(170);
				match(SBR);
				}
				break;
			case ID:
				_localctx = new ArrayIdContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
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

	public static class B_ifContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(bugParser.IF, 0); }
		public List<Expression_bContext> expression_b() {
			return getRuleContexts(Expression_bContext.class);
		}
		public Expression_bContext expression_b(int i) {
			return getRuleContext(Expression_bContext.class,i);
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
		public B_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_if; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitB_if(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_ifContext b_if() throws RecognitionException {
		B_ifContext _localctx = new B_ifContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_b_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(IF);
			setState(175);
			expression_b(0);
			setState(176);
			match(NEWLINE);
			setState(177);
			block();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(178);
				match(ELIF);
				setState(179);
				expression_b(0);
				setState(180);
				match(NEWLINE);
				setState(181);
				block();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(188);
				match(ELSE);
				setState(189);
				match(NEWLINE);
				setState(190);
				block();
				}
			}

			setState(193);
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
		public TerminalNode WHILE() { return getToken(bugParser.WHILE, 0); }
		public Expression_bContext expression_b() {
			return getRuleContext(Expression_bContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(bugParser.NEWLINE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(bugParser.END, 0); }
		public B_whileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_while; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitB_while(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_whileContext b_while() throws RecognitionException {
		B_whileContext _localctx = new B_whileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_b_while);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(WHILE);
			setState(196);
			expression_b(0);
			setState(197);
			match(NEWLINE);
			setState(198);
			block();
			setState(199);
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
		public TerminalNode FOR() { return getToken(bugParser.FOR, 0); }
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public TerminalNode TO() { return getToken(bugParser.TO, 0); }
		public List<Expression_aContext> expression_a() {
			return getRuleContexts(Expression_aContext.class);
		}
		public Expression_aContext expression_a(int i) {
			return getRuleContext(Expression_aContext.class,i);
		}
		public TerminalNode STEP() { return getToken(bugParser.STEP, 0); }
		public TerminalNode NEWLINE() { return getToken(bugParser.NEWLINE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(bugParser.END, 0); }
		public B_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_for; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitB_for(this);
			else return visitor.visitChildren(this);
		}
	}

	public final B_forContext b_for() throws RecognitionException {
		B_forContext _localctx = new B_forContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_b_for);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(FOR);
			setState(202);
			assignation();
			setState(203);
			match(TO);
			setState(204);
			expression_a(0);
			setState(205);
			match(STEP);
			setState(206);
			expression_a(0);
			setState(207);
			match(NEWLINE);
			setState(208);
			block();
			setState(209);
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
		case 4:
			return expression_b_sempred((Expression_bContext)_localctx, predIndex);
		case 5:
			return expression_a_sempred((Expression_aContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_b_sempred(Expression_bContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expression_a_sempred(Expression_aContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00d6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\3\4\5\4\62\n\4\3\5\3"+
		"\5\3\5\5\5\67\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6J\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6U\n\6"+
		"\f\6\16\6X\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7c\n\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\7\7k\n\7\f\7\16\7n\13\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\6\t{\n\t\r\t\16\t|\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u0085\n\t"+
		"\r\t\16\t\u0086\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u0090\n\t\r\t\16\t\u0091"+
		"\3\t\3\t\3\t\3\t\3\t\6\t\u0099\n\t\r\t\16\t\u009a\3\t\3\t\3\t\5\t\u00a0"+
		"\n\t\3\n\3\n\5\n\u00a4\n\n\3\n\3\n\7\n\u00a8\n\n\f\n\16\n\u00ab\13\n\3"+
		"\n\3\n\5\n\u00af\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"\u00ba\n\13\f\13\16\13\u00bd\13\13\3\13\3\13\3\13\5\13\u00c2\n\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\2\4\n\f\16\2\4\6\b\n\f\16\20\22\24\26\30\2\7\3\2\22\25\3\2\26\27"+
		"\3\2\20\21\3\2\6\7\3\2\4\5\2\u00ea\2\37\3\2\2\2\4)\3\2\2\2\6\61\3\2\2"+
		"\2\b\66\3\2\2\2\nI\3\2\2\2\fb\3\2\2\2\16o\3\2\2\2\20\u009f\3\2\2\2\22"+
		"\u00ae\3\2\2\2\24\u00b0\3\2\2\2\26\u00c5\3\2\2\2\30\u00cb\3\2\2\2\32\33"+
		"\5\6\4\2\33\34\7%\2\2\34\36\3\2\2\2\35\32\3\2\2\2\36!\3\2\2\2\37\35\3"+
		"\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\"#\7\2\2\3#\3\3\2\2\2$%\5\6"+
		"\4\2%&\7%\2\2&(\3\2\2\2\'$\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3"+
		"\2\2\2+)\3\2\2\2,\62\5\20\t\2-\62\5\24\13\2.\62\5\26\f\2/\62\5\30\r\2"+
		"\60\62\5\16\b\2\61,\3\2\2\2\61-\3\2\2\2\61.\3\2\2\2\61/\3\2\2\2\61\60"+
		"\3\2\2\2\62\7\3\2\2\2\63\67\5\f\7\2\64\67\5\n\6\2\65\67\5\22\n\2\66\63"+
		"\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\t\3\2\2\289\b\6\1\29:\7\13\2\2"+
		":;\5\n\6\2;<\7\f\2\2<J\3\2\2\2=>\7\17\2\2>J\5\n\6\n?@\5\f\7\2@A\t\2\2"+
		"\2AB\5\f\7\2BJ\3\2\2\2CD\5\f\7\2DE\t\3\2\2EF\5\f\7\2FJ\3\2\2\2GJ\t\4\2"+
		"\2HJ\7!\2\2I8\3\2\2\2I=\3\2\2\2I?\3\2\2\2IC\3\2\2\2IG\3\2\2\2IH\3\2\2"+
		"\2JV\3\2\2\2KL\f\7\2\2LM\t\3\2\2MU\5\n\6\bNO\f\6\2\2OP\7\r\2\2PU\5\n\6"+
		"\7QR\f\5\2\2RS\7\16\2\2SU\5\n\6\6TK\3\2\2\2TN\3\2\2\2TQ\3\2\2\2UX\3\2"+
		"\2\2VT\3\2\2\2VW\3\2\2\2W\13\3\2\2\2XV\3\2\2\2YZ\b\7\1\2Z[\7\13\2\2[\\"+
		"\5\f\7\2\\]\7\f\2\2]c\3\2\2\2^_\7\5\2\2_c\5\f\7\7`c\7\"\2\2ac\7!\2\2b"+
		"Y\3\2\2\2b^\3\2\2\2b`\3\2\2\2ba\3\2\2\2cl\3\2\2\2de\f\6\2\2ef\t\5\2\2"+
		"fk\5\f\7\7gh\f\5\2\2hi\t\6\2\2ik\5\f\7\6jd\3\2\2\2jg\3\2\2\2kn\3\2\2\2"+
		"lj\3\2\2\2lm\3\2\2\2m\r\3\2\2\2nl\3\2\2\2op\7 \2\2pq\5\b\5\2q\17\3\2\2"+
		"\2rs\7!\2\2st\7\3\2\2t\u00a0\5\b\5\2uz\7!\2\2vw\7\t\2\2wx\5\f\7\2xy\7"+
		"\n\2\2y{\3\2\2\2zv\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177"+
		"\7\3\2\2\177\u0084\7!\2\2\u0080\u0081\7\t\2\2\u0081\u0082\5\f\7\2\u0082"+
		"\u0083\7\n\2\2\u0083\u0085\3\2\2\2\u0084\u0080\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u00a0\3\2\2\2\u0088"+
		"\u0089\7!\2\2\u0089\u008a\7\3\2\2\u008a\u008f\7!\2\2\u008b\u008c\7\t\2"+
		"\2\u008c\u008d\5\f\7\2\u008d\u008e\7\n\2\2\u008e\u0090\3\2\2\2\u008f\u008b"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u00a0\3\2\2\2\u0093\u0098\7!\2\2\u0094\u0095\7\t\2\2\u0095\u0096\5\f"+
		"\7\2\u0096\u0097\7\n\2\2\u0097\u0099\3\2\2\2\u0098\u0094\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009d\7\3\2\2\u009d\u009e\5\b\5\2\u009e\u00a0\3\2\2\2\u009f"+
		"r\3\2\2\2\u009fu\3\2\2\2\u009f\u0088\3\2\2\2\u009f\u0093\3\2\2\2\u00a0"+
		"\21\3\2\2\2\u00a1\u00a3\7\t\2\2\u00a2\u00a4\5\b\5\2\u00a3\u00a2\3\2\2"+
		"\2\u00a3\u00a4\3\2\2\2\u00a4\u00a9\3\2\2\2\u00a5\u00a6\7\b\2\2\u00a6\u00a8"+
		"\5\b\5\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00af\7\n"+
		"\2\2\u00ad\u00af\7!\2\2\u00ae\u00a1\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\23\3\2\2\2\u00b0\u00b1\7\30\2\2\u00b1\u00b2\5\n\6\2\u00b2\u00b3\7%\2"+
		"\2\u00b3\u00bb\5\4\3\2\u00b4\u00b5\7\32\2\2\u00b5\u00b6\5\n\6\2\u00b6"+
		"\u00b7\7%\2\2\u00b7\u00b8\5\4\3\2\u00b8\u00ba\3\2\2\2\u00b9\u00b4\3\2"+
		"\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00c1\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7\31\2\2\u00bf\u00c0\7"+
		"%\2\2\u00c0\u00c2\5\4\3\2\u00c1\u00be\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c4\7\34\2\2\u00c4\25\3\2\2\2\u00c5\u00c6\7\33"+
		"\2\2\u00c6\u00c7\5\n\6\2\u00c7\u00c8\7%\2\2\u00c8\u00c9\5\4\3\2\u00c9"+
		"\u00ca\7\34\2\2\u00ca\27\3\2\2\2\u00cb\u00cc\7\35\2\2\u00cc\u00cd\5\20"+
		"\t\2\u00cd\u00ce\7\37\2\2\u00ce\u00cf\5\f\7\2\u00cf\u00d0\7\36\2\2\u00d0"+
		"\u00d1\5\f\7\2\u00d1\u00d2\7%\2\2\u00d2\u00d3\5\4\3\2\u00d3\u00d4\7\34"+
		"\2\2\u00d4\31\3\2\2\2\26\37)\61\66ITVbjl|\u0086\u0091\u009a\u009f\u00a3"+
		"\u00a9\u00ae\u00bb\u00c1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
