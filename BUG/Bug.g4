grammar Bug;
import BUG_Tokens;

prog        :	(instruc NEWLINE)+ ;

instruc     : assignation
            | b_if
            | b_while
            | b_for
            ;

number      : INT | DECIMAL ;

expr        : expr op=('*'|'/') expr
            | expr op=('+'|'-') expr
            | number
            | ID
            | LP expr RP
            ;

// -----------------------------------------------------------------------------

assignation : ID ASSIGN number
            | ID ASSIGN '-'? ID
            | ID ASSIGN FALSE
            | ID ASSIGN TRUE
            | ID ASSIGN expr
            ;

operand     : ID | INT | DECIMAL ;

bool        : TRUE | FALSE | ID ;

comp        : LT | GT | LE | GE | EQ | DIFF ;

condition   : operand comp operand
            | bool
            | condition AND condition
            | condition OR condition
            | LP condition RP
            ;

b_if        : IF condition NEWLINE
              (instruc NEWLINE)+
              (ELIF condition NEWLINE instruc NEWLINE)*
              (ELSE NEWLINE instruc NEWLINE)?
              END
            ;

b_while     : WHILE condition NEWLINE
              (instruc NEWLINE)+
              END;

b_for       : FOR ID ASSIGN expr TO expr STEP expr NEWLINE
              (instruc NEWLINE)+
              END
            ;
