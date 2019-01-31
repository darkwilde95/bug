package bug;
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
		RULE_b_if = 8, RULE_b_while = 9, RULE_b_for = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "statement", "expression", "expression_b", "expression_a", 
			"print", "assignation", "b_if", "b_while", "b_for"
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
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(22);
				statement();
				setState(23);
				match(NEWLINE);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
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
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(32);
				statement();
				setState(33);
				match(NEWLINE);
				}
				}
				setState(39);
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
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				assignation();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				b_if();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				b_while();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				b_for();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(44);
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
		public Expression_aContext expression_a() {
			return getRuleContext(Expression_aContext.class,0);
		}
		public Expression_bContext expression_b() {
			return getRuleContext(Expression_bContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				expression_a(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				expression_b(0);
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
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ParentExpr_bContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(52);
				match(PL);
				setState(53);
				expression_b(0);
				setState(54);
				match(PR);
				}
				break;
			case 2:
				{
				_localctx = new NotExpr_bContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				match(NOT);
				setState(57);
				expression_b(8);
				}
				break;
			case 3:
				{
				_localctx = new CompExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(58);
				expression_a(0);
				setState(59);
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
				setState(60);
				expression_a(0);
				}
				break;
			case 4:
				{
				_localctx = new EqNeqExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62);
				expression_a(0);
				setState(63);
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
				setState(64);
				expression_a(0);
				}
				break;
			case 5:
				{
				_localctx = new BooleanTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(66);
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
				setState(67);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(79);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new EqNeqExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(70);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(71);
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
						setState(72);
						expression_b(6);
						}
						break;
					case 2:
						{
						_localctx = new AndExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(73);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(74);
						match(AND);
						setState(75);
						expression_b(5);
						}
						break;
					case 3:
						{
						_localctx = new OrExpr_bContext(new Expression_bContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_b);
						setState(76);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(77);
						match(OR);
						setState(78);
						expression_b(4);
						}
						break;
					}
					} 
				}
				setState(83);
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
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PL:
				{
				_localctx = new ParentExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(85);
				match(PL);
				setState(86);
				expression_a(0);
				setState(87);
				match(PR);
				}
				break;
			case MINUS:
				{
				_localctx = new NegExpr_aContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(89);
				match(MINUS);
				setState(90);
				expression_a(5);
				}
				break;
			case INT:
				{
				_localctx = new IntegerTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(91);
				match(INT);
				}
				break;
			case ID:
				{
				_localctx = new IntegerIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(103);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(101);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivExpr_aContext(new Expression_aContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_a);
						setState(95);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(96);
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
						setState(97);
						expression_a(5);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExpr_aContext(new Expression_aContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression_a);
						setState(98);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(99);
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
						setState(100);
						expression_a(4);
						}
						break;
					}
					} 
				}
				setState(105);
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
			setState(106);
			match(PRINT);
			setState(107);
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
		public TerminalNode ID() { return getToken(bugParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(bugParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bugVisitor ) return ((bugVisitor<? extends T>)visitor).visitAssignation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(ID);
			setState(110);
			match(ASSIGN);
			setState(111);
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

	public static class B_ifContext extends ParserRuleContext {
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
		enterRule(_localctx, 16, RULE_b_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(IF);
			setState(114);
			expression();
			setState(115);
			match(NEWLINE);
			setState(116);
			block();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(117);
				match(ELIF);
				setState(118);
				expression();
				setState(119);
				match(NEWLINE);
				setState(120);
				block();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(127);
				match(ELSE);
				setState(128);
				match(NEWLINE);
				setState(129);
				block();
				}
			}

			setState(132);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 18, RULE_b_while);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(WHILE);
			setState(135);
			expression();
			setState(136);
			match(NEWLINE);
			setState(137);
			block();
			setState(138);
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
		enterRule(_localctx, 20, RULE_b_for);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(FOR);
			setState(141);
			assignation();
			setState(142);
			match(TO);
			setState(143);
			expression();
			setState(144);
			match(STEP);
			setState(145);
			expression();
			setState(146);
			match(NEWLINE);
			setState(147);
			block();
			setState(148);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u0099\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3\3\3\3\3"+
		"\3\7\3&\n\3\f\3\16\3)\13\3\3\4\3\4\3\4\3\4\3\4\5\4\60\n\4\3\5\3\5\5\5"+
		"\64\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6G\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6R\n\6\f\6\16\6"+
		"U\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7`\n\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\7\7h\n\7\f\7\16\7k\13\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\7\n}\n\n\f\n\16\n\u0080\13\n\3\n\3\n\3\n\5\n"+
		"\u0085\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\2\4\n\f\r\2\4\6\b\n\f\16\20\22\24\26\2\7\3\2\22"+
		"\25\3\2\26\27\3\2\20\21\3\2\6\7\3\2\4\5\2\u00a3\2\35\3\2\2\2\4\'\3\2\2"+
		"\2\6/\3\2\2\2\b\63\3\2\2\2\nF\3\2\2\2\f_\3\2\2\2\16l\3\2\2\2\20o\3\2\2"+
		"\2\22s\3\2\2\2\24\u0088\3\2\2\2\26\u008e\3\2\2\2\30\31\5\6\4\2\31\32\7"+
		"%\2\2\32\34\3\2\2\2\33\30\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3"+
		"\2\2\2\36 \3\2\2\2\37\35\3\2\2\2 !\7\2\2\3!\3\3\2\2\2\"#\5\6\4\2#$\7%"+
		"\2\2$&\3\2\2\2%\"\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)"+
		"\'\3\2\2\2*\60\5\20\t\2+\60\5\22\n\2,\60\5\24\13\2-\60\5\26\f\2.\60\5"+
		"\16\b\2/*\3\2\2\2/+\3\2\2\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\7\3\2\2\2"+
		"\61\64\5\f\7\2\62\64\5\n\6\2\63\61\3\2\2\2\63\62\3\2\2\2\64\t\3\2\2\2"+
		"\65\66\b\6\1\2\66\67\7\13\2\2\678\5\n\6\289\7\f\2\29G\3\2\2\2:;\7\17\2"+
		"\2;G\5\n\6\n<=\5\f\7\2=>\t\2\2\2>?\5\f\7\2?G\3\2\2\2@A\5\f\7\2AB\t\3\2"+
		"\2BC\5\f\7\2CG\3\2\2\2DG\t\4\2\2EG\7!\2\2F\65\3\2\2\2F:\3\2\2\2F<\3\2"+
		"\2\2F@\3\2\2\2FD\3\2\2\2FE\3\2\2\2GS\3\2\2\2HI\f\7\2\2IJ\t\3\2\2JR\5\n"+
		"\6\bKL\f\6\2\2LM\7\r\2\2MR\5\n\6\7NO\f\5\2\2OP\7\16\2\2PR\5\n\6\6QH\3"+
		"\2\2\2QK\3\2\2\2QN\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\13\3\2\2\2U"+
		"S\3\2\2\2VW\b\7\1\2WX\7\13\2\2XY\5\f\7\2YZ\7\f\2\2Z`\3\2\2\2[\\\7\5\2"+
		"\2\\`\5\f\7\7]`\7\"\2\2^`\7!\2\2_V\3\2\2\2_[\3\2\2\2_]\3\2\2\2_^\3\2\2"+
		"\2`i\3\2\2\2ab\f\6\2\2bc\t\5\2\2ch\5\f\7\7de\f\5\2\2ef\t\6\2\2fh\5\f\7"+
		"\6ga\3\2\2\2gd\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\r\3\2\2\2ki\3\2"+
		"\2\2lm\7 \2\2mn\5\b\5\2n\17\3\2\2\2op\7!\2\2pq\7\3\2\2qr\5\b\5\2r\21\3"+
		"\2\2\2st\7\30\2\2tu\5\b\5\2uv\7%\2\2v~\5\4\3\2wx\7\32\2\2xy\5\b\5\2yz"+
		"\7%\2\2z{\5\4\3\2{}\3\2\2\2|w\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3"+
		"\2\2\2\177\u0084\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\31\2\2\u0082\u0083"+
		"\7%\2\2\u0083\u0085\5\4\3\2\u0084\u0081\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u0087\7\34\2\2\u0087\23\3\2\2\2\u0088\u0089\7\33"+
		"\2\2\u0089\u008a\5\b\5\2\u008a\u008b\7%\2\2\u008b\u008c\5\4\3\2\u008c"+
		"\u008d\7\34\2\2\u008d\25\3\2\2\2\u008e\u008f\7\35\2\2\u008f\u0090\5\20"+
		"\t\2\u0090\u0091\7\37\2\2\u0091\u0092\5\b\5\2\u0092\u0093\7\36\2\2\u0093"+
		"\u0094\5\b\5\2\u0094\u0095\7%\2\2\u0095\u0096\5\4\3\2\u0096\u0097\7\34"+
		"\2\2\u0097\27\3\2\2\2\16\35\'/\63FQS_gi~\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}