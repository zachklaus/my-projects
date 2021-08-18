
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class QueenTest 
{
    @Test 
    void TestQueenStringWhite()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
        assertEquals("\u2655", testQueen.toString());
    }

    @Test 
    void TestQueenStringBlack()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testQueen = new Queen(testBoard, ChessPiece.Color.BLACK);
        assertEquals("\u265B", testQueen.toString());
    }

    @Test 
    void TestQueenLegalMoves()
    {   
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testQueen = new Queen(testBoard, ChessPiece.Color.WHITE);
        ArrayList<String> expectedMoves = new ArrayList<String>();
        assertEquals(expectedMoves, testQueen.legalMoves());
    }
}