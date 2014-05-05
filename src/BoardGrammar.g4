//Our Board Grammar

grammar BoardGrammar;

// This puts a Java package statement at the top of the output Java files.
@header {
package BoardExpr;
}

// This adds code to the generated lexer and parser.
@members {
    /**
     * Call this method to have the lexer or parser throw a RuntimeException if
     * it encounters an error.
     */
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 * *** ANTLR requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */

COMMENT : ('#' (~'\n')* '\n') -> skip;
WHITESPACE : [ \t\r\n,]+ -> skip;

INTEGER : [0-9]+; 
X : 'x';
Y : 'y';
EQUALS : '=';
NAMEKEY : 'name';
XVEL: 'xVelocity';
YVEL :'yVelocity';
GRAVITY :'gravity';
FRICTION1 :'friction1';
FRICTION2 :'friction2';
TRIGGER : 'trigger';
ORIENTATIONKEY : 'orientation';
ACTION : 'action';
WIDTH : 'width';
HEIGHT : 'height';






BOARD : 'board';
BALL :'ball';
ABSORBER : 'absorber';
FIRE : 'fire';




// Bumpers
SQUAREBUMPER : 'squareBumper';
CIRCLEBUMPER : 'circleBumper';
TRIANGLEBUMPER :'triangleBumper';


// Flippers
LEFTFLIPPER : 'leftFlipper';
RIGHTFLIPPER :'rightFlipper';



FLOAT : ('-')?[0-9]+('.'[0-9]+)?;
NAME : [A-Za-z_][A-Za-z_0-9.,]*; 

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */

board : boardspec boardObjects*   EOF;
boardObjects : (ball|  bumper| flipperleft| flipperright| absorber | fire) ;
boardspec : BOARD id gravity* friction1* friction2*  ;



ball :  BALL  id x y xv yv;
gravity : GRAVITY EQUALS FLOAT;
friction1:  FRICTION1 EQUALS FLOAT;
friction2: FRICTION2 EQUALS FLOAT;

bumper : bumpertype id x y objectorientation*;
bumpertype : (SQUAREBUMPER | CIRCLEBUMPER| TRIANGLEBUMPER);

objectorientation: ORIENTATIONKEY EQUALS INTEGER;

flipperleft : LEFTFLIPPER id x y objectorientation;  //Not sure if they need to be different
flipperright : RIGHTFLIPPER id x y objectorientation;

absorber : ABSORBER id x y width height;

fire : FIRE TRIGGER EQUALS NAME ACTION EQUALS NAME;


x : X EQUALS  (INTEGER|FLOAT);
y : Y EQUALS (INTEGER|FLOAT);
xv: XVEL EQUALS FLOAT;
yv : YVEL EQUALS FLOAT;


width : WIDTH EQUALS INTEGER;
height : HEIGHT EQUALS INTEGER;
id : NAMEKEY EQUALS NAME;








