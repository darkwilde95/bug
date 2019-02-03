grammar bug;
import bug_tokens;

program       : (statement NEWLINE)* EOF ;

block         : (statement NEWLINE)* ;

statement     : assignation
              | b_if
              | b_while
              | b_for
              | print
              ;

expression    : expression_a                                            #exprA
              | expression_b                                            #exprB
              | array                                                   #exprArray
              ;

expression_b  : PL expression_b PR                                      #parentExpr_b
              | NOT expression_b                                        #notExpr_b
              | expression_a op=(LE | GE | LT | GT) expression_a        #compExpr
              | expression_a op=(EQ | NEQ) expression_a                 #eqNeqExpr_a
              | expression_b op=(EQ | NEQ) expression_b                 #eqNeqExpr_b
              | expression_b AND expression_b                           #andExpr_b
              | expression_b OR expression_b                            #orExpr_b
              | value=(TRUE | FALSE)                                    #booleanType
              | ID                                                      #booleanId
              ;

expression_a  : PL expression_a PR                                      #parentExpr_a
              | MINUS expression_a                                      #negExpr_a
              | expression_a op=(MULT | DIV) expression_a               #multDivExpr_a
              | expression_a op=(PLUS | MINUS) expression_a             #addSubExpr_a
              | INT                                                     #integerType
              | ID                                                      #integerId
              ;

print         : PRINT expression
              ;


assignation   : ID ASSIGN expression                                          #exprAssign
              | ID (SBL expression_a SBR)+ ASSIGN ID (SBL expression_a SBR)+  #atoaAssign
              | ID ASSIGN ID (SBL expression_a SBR)+                          #atoIdAssign
              | ID (SBL expression_a SBR)+ ASSIGN expression                  #etoaAssign
              ;

array         : SBL (expression)? (COMMA expression)* SBR               #arrayType
              | ID                                                      #arrayId
              ;

b_if          : IF expression_b NEWLINE
                block
                (ELIF expression_b NEWLINE block)*
                (ELSE NEWLINE block)?
                END
              ;

b_while       : WHILE expression_b NEWLINE
                block
                END
              ;

b_for         : FOR assignation TO expression_a STEP expression_a NEWLINE
                block
                END
              ;
