
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ChessPieceTest 
{
    @Test 
    void testGetColorWhite()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Knight(testBoard, ChessPiece.Color.WHITE);
        assertEquals(ChessPiece.Color.WHITE, testPiece.getColor());
    }

    @Test 
    void testGetColorBlack()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Knight(testBoard, ChessPiece.Color.BLACK);
        assertEquals(ChessPiece.Color.BLACK, testPiece.getColor());
    }

    @Test 
    void testGetPositionPawn() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
            testPiece.setPosition("a3");
            assertEquals("a3",testPiece.getPosition());
        } catch(Exception e) {}
    }

    @Test 
    void testGetPositionRook() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = testBoard.getPiece("a1");
            assertEquals("a1",testPiece.getPosition());
        } catch(Exception e) {}
    }

    @Test 
    void testGetPositionMovedRook() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("a2","a4");
            testBoard.move("a1","a3");
            ChessPiece testPiece = testBoard.getPiece("a3");
            assertEquals("a3",testPiece.getPosition());
        } catch(Exception e) {}
    }

    @Test 
    void testSetPositionPawn() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = testBoard.getPiece("a2");
            testPiece.setPosition("a3");
            assertEquals("a3",testPiece.getPosition());
        } catch (Exception e) {}
    }

    @Test void testSetPositionIllegalCharacter() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = testBoard.getPiece("a2");
            Assertions.assertThrows(IllegalPositionException.class, () ->  {
                testPiece.setPosition("m5");
            });
        } catch (Exception e) {}
    }

    @Test void testSetPositionTooLongPosition() 
    {   
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = testBoard.getPiece("a2");
            Assertions.assertThrows(IllegalPositionException.class, () ->  {
                testPiece.setPosition("aa5");
            });
        } catch(Exception e) {}
    }
    
    @Test void testSetPositionOutOfBounds() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            ChessPiece testPiece = testBoard.getPiece("a2");
            Assertions.assertThrows(IllegalPositionException.class, () ->  {
                testPiece.setPosition("a9");
            });
        } catch (Exception e) {}
    }
    
}
