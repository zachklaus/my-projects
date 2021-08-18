
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class RookTest 
{
    @Test 
    void TestRookStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testRook = new Rook(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2656", testRook.toString());
    }

    @Test 
    void TestRookStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testRook = new Rook(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265C", testRook.toString());
    }

    @Test 
    void TestRookLegalMoves()
    {   try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("a2", "a4");
            testBoard.move("a1", "a3");
            testBoard.move("a3", "c3");
            ChessPiece testRook = testBoard.getPiece("c3");
            ArrayList<String> expectedMoves = new ArrayList<String>();
            expectedMoves.add("b3");
            expectedMoves.add("a3");
            expectedMoves.add("d3");
            expectedMoves.add("e3");
            expectedMoves.add("f3");
            expectedMoves.add("g3");
            expectedMoves.add("h3");
            expectedMoves.add("c4");
            expectedMoves.add("c5");
            expectedMoves.add("c6");
            expectedMoves.add("c7");
            assertEquals(expectedMoves, testRook.legalMoves());
        } catch(Exception e) {}
    }
}