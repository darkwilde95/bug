grammar bug;
import bug_tokens;

program     : (statement NEWLINE)* EOF ;

block       : (statement NEWLINE)* ;

statement   : assignation
            | b_if
            | b_while
            | b_for
            | print
            ;

expression  : MINUS expression                                      #negExpr
            | NOT expression                                        #notExpr
            | expression op=(MULT | DIV) expression                 #multDivExpr
            | expression op=(PLUS | MINUS) expression               #addSubExpr
            | expression op=(LE | GE | LT | GT) expression          #compExpr
            | expression op=(EQ | NEQ) expression                   #eqNeqExpr
            | expression AND expression                             #andExpr
            | expression OR expression                              #orExpr
            | type                                                  #typeExpr
            ;

type        : PL expression PR                                      #parentExpr
            | INT                                                   #numberType
            | value=(TRUE | FALSE)                                  #boolType
            | ID                                                    #identifier
            ;

print       : PRINT expression                                      #bugPrint
            ;

assignation : ID ASSIGN expression                                  #bugAssignation
            ;

b_if        : IF expression NEWLINE
              block
              (ELIF expression NEWLINE block)*
              (ELSE NEWLINE block)?
              END                                                   #bugIf
            ;

b_while     : WHILE expression NEWLINE
              block
              END                                                   #bugWhile
            ;

b_for       : FOR assignation TO expression STEP expression NEWLINE
              block
              END                                                   #bugFor
            ;
