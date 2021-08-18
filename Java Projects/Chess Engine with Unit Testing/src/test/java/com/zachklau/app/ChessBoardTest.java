
package a2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ChessBoardTest 
{

    @Test
    void testInitialize()
    {
        ChessBoard testBoard = new ChessBoard();
        ChessBoard tempBoard = new ChessBoard();
        testBoard.initialize();
        
        try
        {
            ChessPiece testPiece = new Rook(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("a1").toString());
        
            testPiece = new Knight(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("b1").toString());

            testPiece = new Bishop(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("c1").toString());

            testPiece = new Queen(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("d1").toString());

            testPiece = new King(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("e1").toString());

            testPiece = new Bishop(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("f1").toString());

            testPiece = new Knight(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("g1").toString());

            testPiece = new Rook(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("h1").toString());
    
            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("a2").toString());
        
            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("b2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("c2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("d2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("e2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("f2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("g2").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.WHITE);
            assertEquals(testPiece.toString(), testBoard.getPiece("h2").toString());

            testPiece = new Rook(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("a8").toString());

            testPiece = new Knight(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("b8").toString());

            testPiece = new Bishop(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("c8").toString());

            testPiece = new Queen(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("d8").toString());

            testPiece = new King(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("e8").toString());

            testPiece = new Bishop(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("f8").toString());

            testPiece = new Knight(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("g8").toString());

            testPiece = new Rook(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("h8").toString());
    
            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("a7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("b7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("c7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("d7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("e7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("f7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("g7").toString());

            testPiece = new Pawn(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("h7").toString());
        } catch (Exception e) {} 
    }   

    @Test
    void testGetPieceInitial() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            ChessBoard tempBoard = new ChessBoard();

            testBoard.initialize();
            tempBoard.initialize();

            ChessPiece testPiece = new Knight(tempBoard, ChessPiece.Color.BLACK);
            assertEquals(testPiece.toString(), testBoard.getPiece("b8").toString());
        } catch (Exception e) {}
    }
    
    @Test
    void testGetPieceIllegalCharacter()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalPositionException.class, () ->  {
            testBoard.getPiece("m5");
        });
    }
    
    @Test
    void testGetPieceInvalidPositionLength()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalPositionException.class, () ->  {
            testBoard.getPiece("abc5");
        });
    }

    @Test
    void testGetPieceOutOfBounds()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalPositionException.class, () ->  {
            testBoard.getPiece("a9");
        });
    }

    @Test
    void testPlacePieceSuccessEmptySquare() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);

        assertTrue(testBoard.placePiece(testPiece, "b3"));
    }

    @Test
    void testPlacePieceSuccessOpponentOnSquare() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);

        assertTrue(testBoard.placePiece(testPiece, "c7"));
    }

    @Test
    void testPlacePieceFailureSameColorOnSquare() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);

        assertFalse(testBoard.placePiece(testPiece, "f2"));
    }

    @Test
    void testPlacePieceIllegalCharacter()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
        assertFalse(testBoard.placePiece(testPiece, "m2"));
    }
    
    @Test
    void testPlacePieceInvalidPositionLength()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
        assertFalse(testBoard.placePiece(testPiece, "aa2"));
    }

    @Test
    void testPlacePieceOutOfBounds()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        ChessPiece testPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
        
        assertFalse(testBoard.placePiece(testPiece, "a9"));
    }

    @Test 
    void testMovePawn() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("b2", "b3");
            ChessPiece expectedPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
            assertEquals(expectedPiece.toString(), testBoard.getPiece("b3").toString());
        } catch(Exception e) {}
    }

    @Test 
    void testMoveRook() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("a2", "a4");
            testBoard.move("a1", "a3");
            ChessPiece expectedPiece = new Rook(testBoard, ChessPiece.Color.WHITE);
            assertEquals(expectedPiece.toString(), testBoard.getPiece("a3").toString());
        } catch(Exception e) {}
    }

    @Test 
    void testMoveBishop() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("d2", "d3");
            testBoard.move("c1", "e3");
            ChessPiece expectedPiece = new Bishop(testBoard, ChessPiece.Color.WHITE);
            assertEquals(expectedPiece.toString(), testBoard.getPiece("e3").toString());
        } catch(Exception e) {}
    }

    @Test 
    void testPawnMoved() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("b2", "b3");
            ChessPiece expectedPiece = new Pawn(testBoard, ChessPiece.Color.WHITE);
            assertEquals(testBoard.getPiece("b2"), null);
        } catch(Exception e) {}
    }

    @Test
    void testRookMoved() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("a2", "a4");
            testBoard.move("a1", "a3");
            ChessPiece expectedPiece = new Rook(testBoard, ChessPiece.Color.WHITE);
            assertEquals(testBoard.getPiece("a1"), null);
        } catch(Exception e) {}
    }

    @Test
    void testBishopMoved() 
    {
        try 
        {
            ChessBoard testBoard = new ChessBoard();
            testBoard.initialize();
            testBoard.move("d2", "d3");
            testBoard.move("c1", "e3");
            ChessPiece expectedPiece = new Rook(testBoard, ChessPiece.Color.WHITE);
            assertEquals(testBoard.getPiece("c1"), null);
        } catch(Exception e) {}
    }

    @Test
    void testIllegalPawnMove()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a2", "d5");
        });
    }

    @Test
    void testIllegalRookMove()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a1", "d3");
        });
    }

    @Test
    void testIllegalBishopMove()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("c1", "e3");
        });
    }

    @Test
    void testIllegalKingMove()
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("e1", "e3");
        });
    }

    @Test
    void moveNonExistantPiece() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("c4", "d4");
        });
    }

    @Test
    void moveInvalidFromPositionPositionLength() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("aa2", "d4");
        });
    }

    @Test
    void moveInvalidFromPositionPositionOutOfBounds() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a9", "d4");
        });
    }

    @Test
    void moveInvalidFromPositionIllegalCharacter() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("m9", "d4");
        });
    }

    @Test
    void moveInvalidToPositionPositionLength() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a2", "dd4");
        });
    }

    @Test
    void moveInvalidToPositionPositionOutOfBounds() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a2", "d9");
        });
    }

    @Test
    void moveInvalidToPositionIllegalCharacter() 
    {
        ChessBoard testBoard = new ChessBoard();
        testBoard.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () ->  {
            testBoard.move("a2", "m4");
        });
    }
}
