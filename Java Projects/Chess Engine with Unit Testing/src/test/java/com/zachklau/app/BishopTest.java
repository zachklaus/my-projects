
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class BishopTest 
{
    @Test 
    void TestBishopStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testBishop = new Bishop(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2657", testBishop.toString());
    }

    @Test 
    void TestBishopStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testBishop = new Bishop(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265D", testBishop.toString());
    }

    @Test 
    void TestBishopLegalMoves()
    {   try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("d2", "d3");
            testBoard.move("c1", "e3");
            ChessPiece testBishop = testBoard.getPiece("e3");
            ArrayList<String> expectedMoves = new ArrayList<String>();
            expectedMoves.add("d4");
            expectedMoves.add("c5");
            expectedMoves.add("b6");
            expectedMoves.add("a7");
            expectedMoves.add("f4");
            expectedMoves.add("g5");
            expectedMoves.add("h6");
            assertEquals(expectedMoves, testBishop.legalMoves());
        } catch(Exception e) {}
    }
}