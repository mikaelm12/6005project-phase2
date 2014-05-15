package BoardExpr;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import pingball.datatypes.Board;

/**
 * Testing reading in a file and outputting a board
 * 
 * Testing Strategy:
 * (A) Varying formatted files
 *      (A1) Contains a comment 
 *      (A2) Comment in file contains a keyword (i.e. 'ball', 'bumper', etc)
 *      (A3) Doesn't contain a comment
 *      (A4) Additional spaces added randomly; contains indentation
 *      (A5) An object in the field doesn't contain all the correct fields
 *      (A6) Objects declared in the file over lap each other
 * (B) Objects in the board
 *      (B1) Empty board
 *      (B2) Contains one ball
 *      (B3) Contains multiple balls
 *      (B4) Contains no ball but contains at least one gadget
 *      (B5) Contains at least one gadget
 *      (B6) Contains every possible gadget
 * (C) The given sample boards supplied by the staff
 *      (C1) sampleBoard1.pb
 *      (C2) sampleBoard2-1.pb  
 *      (C3) sampleBoard2-2.pb 
 *      (C4) sampleBoard3.pb  
 *      (C5) sampleBoard4.pb 
 * (D) Additional tests added for phase 2 //**indicates not implemented yet
 *      **(D1) Board contains spawner and portal
 *      **(D2) File does not require gravity
 */

public class FileParsingTest {

    //Tests A3, B1
    @Test
    public void testEmptyBoardNoObjects_A3_B1() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/boardA.txt"));
        String ExpectedAnswer = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
    
    
    //Tests A5
    @Test
    public void testIncorrectBoard_A5() throws Exception{
        try{
            GrammarFactory.parse(new File("src/../boards/BoardC.txt"));
        }catch(Exception e){
            /*
             * Note: Coulnd't test this without having something printed out in the
             * console because the exception is thrown by antlr 
             */
            System.err.println("Previous err message was expected for this test!");
            String expectedErrMsg = "mismatched input 'squareBumper' expecting 'y'";
            assertEquals(expectedErrMsg, e.getMessage());
        }
    }
    
    //Tests A6
    @Test
    public void testOverlappingObjects_A6(){
        try{
            GrammarFactory.parse(new File("src/../boards/boardD.txt"));
        }catch(Exception e){
            String expectedErrMsg = "Objects in same place";
            assertEquals(expectedErrMsg, e.getMessage());
        }
    }
    
  //Tests B4, B5
    @Test
    public void testEmptyBoardNoBallJustObject_B4_B5() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/BoardB.txt"));
        String ExpectedAnswer = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ". #                  .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());

    }
    
    // Tests A1, A2, A4, B2, B6, C1
    @Test
    public void testSampleBoard1_A1_A2_A4_B2_B6_C1() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/board1.txt"));
        String ExpectedAnswer = "......................\n"
                + ".*                   .\n"
                + ".                    .\n"
                + ".                   \\.\n"
                + ".                    .\n"
                + ".########|   |#####  .\n"
                + ".    O   |   |  O    .\n"
                + ".     O        O     .\n"
                + ".      O      O      .\n"
                + ".       O    O       .\n"
                + ".        |   |       .\n"
                + ".        |   |       .\n"
                + ".        \\  /        .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".====================.\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
    
    //test C2
    @Test
    public void testSampleBoard2_C2() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/board2.txt"));
        String ExpectedAnswer = "......................\n"
                + ".*                   .\n"
                + ".                    .\n"
                + ".################|   .\n"
                + ".          O     |   .\n"
                + ".           O        .\n"
                + ".            O       .\n"
                + ".             O      .\n"
                + ".              O     .\n"
                + ".               O    .\n"
                + ".                |   .\n"
                + ".                |   .\n"
                + ".                 \\  .\n"
                + ".                  \\ .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".====================.\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
    
    //Tests C3
    @Test
    public void testSampleBoard3_C3() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/board3.txt"));
        String ExpectedAnswer = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".    |###############.\n"
                + ".    |    O          .\n"
                + ".        O           .\n"
                + ".       O            .\n"
                + ".      O             .\n"
                + ".     O              .\n"
                + ".    O               .\n"
                + ".    |               .\n"
                + ".    |               .\n"
                + ".  /                 .\n"
                + ". /                  .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".====================.\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
    
    
    //Tests B3, C4
    @Test
    public void testSampleBoard4_B3_C4() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/board4.txt"));
        String ExpectedAnswer = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".    O              \\.\n"
                + ". *                  .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          |  |      .\n"
                + ".          |  |      .\n"
                + ".                    .\n"
                + ".########            .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          *         .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          ==========.\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
    
    //Tests C5
    @Test
    public void testSampleBoard5_C5() throws Exception{
        Board myBoard = GrammarFactory.parse(new File("src/../boards/board5.txt"));
        String ExpectedAnswer = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".    O              \\.\n"
                + ". *                  .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          |  |      .\n"
                + ".          |  |      .\n"
                + ".                    .\n"
                + ".####                .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          ==========.\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(ExpectedAnswer, myBoard.toString());
    }
}
