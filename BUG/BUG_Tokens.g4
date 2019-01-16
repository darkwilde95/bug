grammar BUG_Tokens;

// Tokens
ASSIGN  : '=' ;
PLUS    : '+' ;
MINUS   : '-' ;
MULT    : '*' ;
DIV     : '/' ;
LP      : '(' ;
RP      : ')' ;
// **mod : %

AND     : '&&' ;
OR      : '||' ;
NOT     : '!' ;
TRUE    : 'true' ;
FALSE   : 'false' ;
INT     : '-'? [0-9]+ ;
DECIMAL : INT '.' [0-9]+ ;

LT      : '<' ;
GT      : '>' ;
LE      : '<=' ;
GE      : '>=' ;
EQ      : '==' ;
DIFF    : '!=' ;
//**NEG: !

IF      : 'if' ;
ELSE    : 'else' ;
ELIF    : 'elif' ;
WHILE   : 'while ' ;
END     : 'end' ;
FOR     : 'for' ;
STEP    : 'step';
TO      : 'to' ;
ID      : [A-Za-z][A-Za-z_0-9]* ;

NEWLINE : '\r'? '\n' ;
WS      : [ \t]+ -> skip ;
