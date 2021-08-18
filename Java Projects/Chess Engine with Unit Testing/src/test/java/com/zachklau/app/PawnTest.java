
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class PawnTest 
{
    @Test 
    void TestPawnStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPawn = new Pawn(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2659", testPawn.toString());
    }

    @Test 
    void TestPawnStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPawn = new Pawn(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265F", testPawn.toString());
    }

    @Test 
    void TestPawnLegalMoves()
    {   try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("d2", "d3");
            testBoard.move("d3", "d4");
            testBoard.move("e7", "e6");
            testBoard.move("e6", "e5");
            ChessPiece testWhitePawn = testBoard.getPiece("d4");
            ChessPiece testBlackPawn = testBoard.getPiece("e5");
            ArrayList<String> expectedWhiteMoves = new ArrayList<String>();
            expectedWhiteMoves.add("e5");
            expectedWhiteMoves.add("d5");
            ArrayList<String> expectedBlackMoves = new ArrayList<String>();
            expectedBlackMoves.add("d4");
            expectedBlackMoves.add("e4");
            assertEquals(expectedWhiteMoves, testWhitePawn.legalMoves());
            assertEquals(expectedBlackMoves, testBlackPawn.legalMoves());
        } catch(Exception e) {}
    }
}