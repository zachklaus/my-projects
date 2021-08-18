
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class KingTest 
{
    @Test 
    void TestKingStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testKing = new King(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2654", testKing.toString());
    }

    @Test 
    void TestKingStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testKing = new King(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265A", testKing.toString());
    }

    @Test 
    void TestKingLegalMoves()
    {   try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("e2", "e4");
            testBoard.move("e4", "e5");
            testBoard.move("e5", "e6");
            testBoard.move("e1", "e2");
            testBoard.move("e2", "e3");
            testBoard.move("e3", "f3");
            testBoard.move("f3", "f4");
            testBoard.move("f4", "f5");
            testBoard.move("f5", "f6");
            ChessPiece testKing = testBoard.getPiece("c3");
            ArrayList<String> expectedMoves = new ArrayList<String>();
            expectedMoves.add("f7");
            expectedMoves.add("e7");
            expectedMoves.add("g7");
            expectedMoves.add("e6");
            expectedMoves.add("g6");
            expectedMoves.add("g5");
            expectedMoves.add("f5");
            expectedMoves.add("e5");
            assertEquals(expectedMoves, testKing.legalMoves());
        } catch(Exception e) {}
    }
}