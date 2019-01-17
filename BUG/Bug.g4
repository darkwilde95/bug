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

expression  : MINUS expression
            | NOT expression
            | expression op=(MULT | DIV) expression
            | expression op=(PLUS | MINUS) expression
            | expression op=(LE | GE | LT | GT) expression
            | expression op=(EQ | NEQ) expression
            | expression AND expression
            | expression OR expression
            | type
            ;

type        : PL expression PR
            | (INT | FLOAT)
            | (TRUE | FALSE)
            | ID
            ;

print       : PRINT expression ;

assignation : ID ASSIGN expression ;

b_if        : IF expression NEWLINE
              block
              (ELIF expression NEWLINE block)*
              (ELSE NEWLINE block)?
              END
            ;

b_while     : WHILE expression NEWLINE
              block
              END;

b_for       : FOR assignation TO expression STEP expression NEWLINE
              block
              END
            ;
