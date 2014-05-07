grammar Grammar;

// This puts a Java package statement at the top of the output Java files.
@header {
package pingball;
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
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */

boardInfo : board (object | COMMENT | fire)* EOF; 

board : 'board' objectName gravity* ( friction1 | friction2)*;
gravity : 'gravity' equalSign NUM;
friction1: 'friction1' equalSign NUM;
friction2:  'friction2' equalSign NUM;

object  : objectType objectName xLoc yLoc (xVelocity yVelocity | orientation | width height)*;         // match keyword hello followed by an identifier
objectType: 'ball' | 'squareBumper' | 'circleBumper' | 'triangleBumper' | 'leftFlipper' | 'rightFlipper' | 'absorber';
objectName: 'name' equalSign ID+;
xLoc: 'x' equalSign NUM;
yLoc: 'y' equalSign NUM;
xVelocity: 'xVelocity' equalSign NUM;
yVelocity: 'yVelocity' equalSign NUM;
orientation: 'orientation' equalSign NUM;
width: 'width' equalSign NUM;
height: 'height' equalSign NUM;

fire: FIRE TRIGGER equalSign trigger ACTION equalSign action;
trigger: ID;
action: ID;

equalSign: '=';
FIRE: 'fire';
TRIGGER: 'trigger';
ACTION: 'action';
COMMENT: '#' ~('\r' | '\n')* -> skip;

NUM: '-'?([0-9]+'.'[0-9]*|'.'?[0-9]+);

ID : ([a-z] | [A-Z] | [0-9] | '_')+; //any combination of lower or uppercase letters and numbers

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
