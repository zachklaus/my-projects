
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class KnightTest 
{
    @Test 
    void TestKnightStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testKnight = new Knight(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2658", testKnight.toString());
    }

    @Test 
    void TestKnightStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testKnight = new Knight(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265E", testKnight.toString());
    }

    @Test 
    void TestKnightLegalMoves()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testKnight = new Knight(testBoard, ChessPiece.Color.WHITE);
        ArrayList<String> expectedMoves = new ArrayList<String>();
        assertEquals(expectedMoves, testKnight.legalMoves());
    }
}