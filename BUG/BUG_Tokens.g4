grammar bug_tokens;

// Tokens
ASSIGN  : '=' ;
PLUS    : '+' ;
MINUS   : '-' ;
MULT    : '*' ;
DIV     : '/' ;
COMMA   : ',' ;
SBL      : '[' ;
SBR      : ']' ;
PL      : '(' ;
PR      : ')' ;

AND     : '&&' ;
OR      : '||' ;
NOT     : '!' ;
TRUE    : 'true' ;
FALSE   : 'false' ;

LT      : '<' ;
GT      : '>' ;
LE      : '<=' ;
GE      : '>=' ;
EQ      : '==' ;
NEQ     : '!=' ;

IF      : 'if' ;
ELSE    : 'else' ;
ELIF    : 'elif' ;
WHILE   : 'while ' ;
END     : 'end' ;
FOR     : 'for' ;
STEP    : 'step';
TO      : 'to' ;
PRINT   : 'print' ;

ID      : [A-Za-z_][A-Za-z0-9_]* ;
INT     : [0-9]+ ;
FLOAT   : INT '.' [0-9]+ ;
COMMENT : '#' ~[\r\n]* -> skip ;

NEWLINE : '\r'? '\n' ;
WS      : [ \t]+ -> skip ;
