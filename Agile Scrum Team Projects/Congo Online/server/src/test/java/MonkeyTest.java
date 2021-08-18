package Game;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import javax.annotation.processing.SupportedAnnotationTypes;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
  This class contains tests for the Monkey piece in Congo.
 */

public class MonkeyTest {
    GameBoard congoGame;

    // Setup to be done before every test in TestPlan
    @Before
    public void initialize() {
        congoGame = new GameBoard();
        congoGame.initialize();
    }

    @Test
    public void testMonkeyMove() throws Exception {
        /* Start with initial board */
        MonkeyPiece monkey1 = (MonkeyPiece) congoGame.getGamePiece(0,1);
        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        movesRow.add(1);
        movesCol.add(0);

        /* jump to 1,0 which should be blocked*/
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
        movesCol.set(0,1);  /* try jumping to 1,1 which should also be blocked by a pawn */
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
        movesCol.set(0,2);  /* try jumping to 1,2 which is again blocked by a pawn */
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
        movesRow.set(0,2); /* try jumping to 2,2 which is not a straight line move */
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);

        /* move the opponent's zebra to 1,2 */
        congoGame.movePiece(6,6,1,2);  /* move zebra from 6,6 to 1,2 so it is capturable with a jump */
        /* make first move in sequence -> 2,3 */
        movesRow.set(0,2);
        movesCol.set(0,3);
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == true);
        congoGame.movePiece(6,0,1,4);  /* move opponent's giraffe to 1,4 */
        /* will not be capturable because crocodile is blocking */
        movesRow.add(0);  /* add jump to 0,5, but this should be blocked */
        movesCol.add(5);  /* Now have 2 moves in our sequence */
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
        /* now move the crocodile and allow the jump */
        congoGame.movePiece(0,5,1,6);  /* move crocodile from 0,5 to 1,6 so monkey can capture opponent's giraffe */
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == true);
    }

    @Test
    public void testMonkeyCantJumpTwice() throws Exception {
        /* Start with initialized board */
        /* tests to make sure we block move if piece is jumped twice */
        congoGame.movePiece(0,1,2,1);  /* initialize player's monkey to 2,1 */
        congoGame.movePiece(6,2,3,2);  /* initialize opponent's elephant to 3,2 */
        congoGame.movePiece(6,4,2,4);  /* initialize opponent's elephant to 2,4 */
        congoGame.movePiece(6,1,4,2);  /* initialize opponent's monkey to 4,2 */

        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        /* perform valid double jump (2,1) to (4,3) to (4,1) */
        movesRow.add(4);
        movesCol.add(3);
        movesRow.add(4);
        movesCol.add(1);
        MonkeyPiece monkey1 = (MonkeyPiece) congoGame.getGamePiece(2,1);
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == true);
        /* now try and do an illegal move by jumping the first elephant again to get to the second elephant */
        /* add jumps to (2,3) to (2,5) */
        movesRow.add(2);
        movesCol.add(3);
        movesRow.add(2);
        movesCol.add(5);
        assertTrue(monkey1.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
    }

    @Test
    public void testMonkeySingleJump() throws Exception {
        /* Start with initial board */
        /* tests to make sure monkey from player 1 can jump opponent's pawn and capture it */
        congoGame.movePiece(5,2, 1,2);  /* move player 2's pawn to 1,2 */
        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        /* perform valid single jump move (0,1) to (2,3) */
        /* first square (0,1) will be determined by monkey's location in MonkeyPiece */
        /* next location is add to array that holds sequence of moves */
        movesRow.add(2);
        movesCol.add(3);

        GamePiece monkey = congoGame.getGamePiece(0,1);
        GamePiece pawn = congoGame.getGamePiece(1,2);
        monkey.performMove(movesRow, movesCol, congoGame);  /* this should jump and capture the pawn */
        assertTrue(congoGame.getGamePiece(0,1) == null);  /* monkey has moved and left square empty */
        assertTrue(congoGame.getGamePiece(2,3) instanceof MonkeyPiece);  /* monkey is now in square 2,3 */
        assertTrue(congoGame.getGamePiece(1,2) == null);  /* pawn has been captured */
    }


    @Test
    public void testMonkeyDoubleJumpDrown() throws Exception {
        /* Start with initial board */
        /* tests to make sure monkey captures 2 pieces along river but drowns at end of the turn */
        GamePiece monkey = congoGame.getGamePiece(6,1);
        GamePiece pawn1 = congoGame.getGamePiece(1,2);
        GamePiece pawn2 = congoGame.getGamePiece(1,4);

        congoGame.movePiece(monkey, 3, 1);  /* move player 2's monkey to 3,1 - in the river */
        congoGame.movePiece(pawn1, 3, 2);   /* move player 1's pawn into the river */
        congoGame.movePiece(pawn2, 3, 4);   /* move player 1's pawn into the river */

        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        /* perform valid double jump move (3,1) to (3,3) to (3,5) */
        /* first square (3,1) will be determined by monkey's location in MonkeyPiece */
        /* next location is add to array that holds sequence of moves */
        movesRow.add(3);
        movesCol.add(3);
        movesRow.add(3);
        movesCol.add(5);

        monkey.performMove(movesRow, movesCol, congoGame);  /* this should jump and capture 2 pieces */
        assertTrue(congoGame.getGamePiece(3,1) == null);  /* monkey has moved and left square empty */
        assertTrue(congoGame.getGamePiece(3,5) == null);  /* monkey should have drown */
        assertTrue(congoGame.getGamePiece(3,2) == null);  /* pawn1 has been captured */
        assertTrue(congoGame.getGamePiece(3,4) == null);  /* pawn2 has been captured */
    }

    @Test
    public void testMonkeyCantCaptureAdjacentSquare() throws Exception {
        /* Start with initial board */
        MonkeyPiece monkey = (MonkeyPiece) congoGame.getGamePiece(6, 1);
        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        movesRow.add(5);
        movesCol.add(0);

        /* move to 5,0 which should be blocked by this player's pawn */
        /* Can't capture it's own piece */
        assertTrue(monkey.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);
        /* move the opponent's elephant to 5,2 */
        congoGame.movePiece(0,2,5,2);  /* move elephant from 0,2 to 5,2 so it is capturable with a jump but not a 1 step move */

        movesRow.set(0,5);
        movesCol.set(0,2);
        /* Can't capture opponent's elephant */
        assertTrue(monkey.ValidateMove(movesRow,movesCol,congoGame.getBoard()) == false);

        /* now move pawn forward so monkey can move */
        GamePiece pawn = congoGame.getGamePiece(5, 1);
        movesRow.set(0,4);
        movesCol.set(0,1);
        pawn.performMove(movesRow, movesCol, congoGame);  /* this should move pawn forward 1 step */
        /* now monkey can move forward to empty space */
        movesRow.set(0,5);
        movesCol.set(0,1);
        assertTrue(monkey.ValidateMove(movesRow, movesCol, congoGame.getBoard()));
        monkey.performMove(movesRow, movesCol, congoGame);
        assertTrue(congoGame.getGamePiece(6,1) == null);  /* monkey has moved and left square empty */
        assertTrue(congoGame.getGamePiece(5,1) instanceof MonkeyPiece);  /* monkey is now in square 5,1 */
        assertTrue(congoGame.getGamePiece(4,1) instanceof PawnPiece);  /* pawn has been moved */
    }

    @Test(expected = Exception.class)
    public void testMonkeyCantJumpSamePlayersPiece() throws Exception {
        /* Start with initial board */
        MonkeyPiece monkey = (MonkeyPiece) congoGame.getGamePiece(6, 1);
        ArrayList<Integer> movesRow = new ArrayList<Integer>();
        ArrayList<Integer> movesCol = new ArrayList<Integer>();
        movesRow.add(4);
        movesCol.add(3);

        monkey.performMove(movesRow, movesCol, congoGame);
    }
}
