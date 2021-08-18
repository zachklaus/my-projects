
package a2;

import java.util.ArrayList;

class ChessBoard
{
  
  private ChessPiece[][] board;

  public ChessBoard()
  {
      this.board = new ChessPiece[8][8];
  }

  public void initialize() 
  {
    this.board[0][0] = new Rook(this, ChessPiece.Color.WHITE);
    this.board[0][1] = new Knight(this, ChessPiece.Color.WHITE);
    this.board[0][2] = new Bishop(this, ChessPiece.Color.WHITE);
    this.board[0][3] = new Queen(this, ChessPiece.Color.WHITE);
    this.board[0][4] = new King(this, ChessPiece.Color.WHITE);
    this.board[0][5] = new Bishop(this, ChessPiece.Color.WHITE);
    this.board[0][6] = new Knight(this, ChessPiece.Color.WHITE);
    this.board[0][7] = new Rook(this, ChessPiece.Color.WHITE);
    
    this.board[1][0] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][1] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][2] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][3] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][4] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][5] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][6] = new Pawn(this, ChessPiece.Color.WHITE);
    this.board[1][7] = new Pawn(this, ChessPiece.Color.WHITE);

    this.board[7][0] = new Rook(this, ChessPiece.Color.BLACK);
    this.board[7][1] = new Knight(this, ChessPiece.Color.BLACK);
    this.board[7][2] = new Bishop(this, ChessPiece.Color.BLACK);
    this.board[7][3] = new Queen(this, ChessPiece.Color.BLACK);
    this.board[7][4] = new King(this, ChessPiece.Color.BLACK);
    this.board[7][5] = new Bishop(this, ChessPiece.Color.BLACK);
    this.board[7][6] = new Knight(this, ChessPiece.Color.BLACK);
    this.board[7][7] = new Rook(this, ChessPiece.Color.BLACK);
    
    this.board[6][0] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][1] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][2] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][3] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][4] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][5] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][6] = new Pawn(this, ChessPiece.Color.BLACK);
    this.board[6][7] = new Pawn(this, ChessPiece.Color.BLACK);

  }

  public ChessPiece getPiece(String position) throws IllegalPositionException 
  {
    
      int[] rowAndColumn;

      try {
          rowAndColumn = getPosition(position);
      } catch (IllegalPositionException e) {
          throw e;
      }

      return this.board[rowAndColumn[0]][rowAndColumn[1]];

  }

  public boolean placePiece(ChessPiece piece, String position) 
  {

      ChessPiece moveToPiece;

      try {
          moveToPiece = getPiece(position);
      } catch(Exception e) {
          return false;
      }

      if ((moveToPiece != null) && (moveToPiece.getColor() == piece.getColor()))
      {
          return false;
      }

      moveToPiece = null;
      try 
      {
          piece.setPosition(position);
      } catch (Exception e) {}
      return true;

  }

  public void move(String fromPosition, String toPosition) throws IllegalMoveException 
  {
      ChessPiece fromPositionPiece;

      try {
          fromPositionPiece = getPiece(fromPosition);
      } catch(Exception e) {
          throw new IllegalMoveException();
      }
      
      if (fromPositionPiece == null) 
      {
          throw new IllegalMoveException();
      }

      ChessPiece toPositionPiece;

      try {
          toPositionPiece = getPiece(toPosition);
      } catch(Exception e) {
          throw new IllegalMoveException();
      }

      if ((toPositionPiece != null) && (toPositionPiece.getColor() == fromPositionPiece.getColor()))
      {
          throw new IllegalMoveException();
      }
      
      ArrayList<String> legalMoves = fromPositionPiece.legalMoves();

      if (!legalMoves.contains(toPosition)) 
      {
          throw new IllegalMoveException();
      }
      
      int[] rowAndColumnFrom = new int[2];
      int[] rowAndColumnTo = new int[2];
      try 
      {
          rowAndColumnFrom = getPosition(fromPosition);
          rowAndColumnTo = getPosition(toPosition);
      }catch(Exception e) {}

      this.board[rowAndColumnFrom[0]][rowAndColumnFrom[1]] = null;
      this.board[rowAndColumnTo[0]][rowAndColumnTo[1]] = fromPositionPiece;
      
      try 
      {
        this.placePiece(this.getPiece(fromPosition), toPosition);
      } catch (Exception e) {}
  }

  public String toString()
  {    
      String chess="";    
      String upperLeft = "\u250C";    
      String upperRight = "\u2510";    
      String horizontalLine = "\u2500";    
      String horizontal3 = horizontalLine + "\u3000" + horizontalLine;    
      String verticalLine = "\u2502";    
      String upperT = "\u252C";    
      String bottomLeft = "\u2514";    
      String bottomRight = "\u2518";    
      String bottomT = "\u2534";    
      String plus = "\u253C";    
      String leftT = "\u251C";    
      String rightT = "\u2524";    
      String topLine = upperLeft;    
      for (int i = 0; i<7; i++)
      {        
          topLine += horizontal3 + upperT;    
      }    
      topLine += horizontal3 + upperRight;    
      String bottomLine = bottomLeft;    
      for (int i = 0; i<7; i++)
      {        
          bottomLine += horizontal3 + bottomT;    
      }    
      bottomLine += horizontal3 + bottomRight;    
      chess+=topLine + "\n";    
      for (int row = 7; row >=0; row--)
      {        
          String midLine = "";        
          for (int col = 0; col < 8; col++)
          {            
              if(board[row][col]==null) 
              {                
                  midLine += verticalLine + " \u3000 ";            
              } 
              else {
                  midLine += verticalLine + " "+board[row][col]+" ";
              }        
          }        
          midLine += verticalLine;        
          String midLine2 = leftT;        
          for (int i = 0; i<7; i++)
          {            
              midLine2 += horizontal3 + plus;        
          }        
          midLine2 += horizontal3 + rightT;        
          chess+=midLine+ "\n";        
          if(row>=1)            
            chess+=midLine2+ "\n";    
        }    
        chess+=bottomLine;    
        return chess;
    }
    
    private int[] getPosition(String position) throws IllegalPositionException 
    {
      int[] rowAndColumn = new int[2];
      int row;
      int column;
      
      if (position.length() != 2) 
      {
          throw new IllegalPositionException();
      }

      switch (position.charAt(0)) 
      {
          case ('a'): column = 0; break;
          case ('b'): column = 1; break;
          case ('c'): column = 2; break;
          case ('d'): column = 3; break;
          case ('e'): column = 4; break;
          case ('f'): column = 5; break;
          case ('g'): column = 6; break;
          case ('h'): column = 7; break;
          default: throw new IllegalPositionException();
      }

      try
      {
          row = Character.getNumericValue(position.charAt(1)) - 1;
      } catch (Exception e) {
          throw new IllegalPositionException(); 
      }

      if (row < 0 || row > 7) 
      {
          throw new IllegalPositionException(); 
      }

      rowAndColumn[0] = row;
      rowAndColumn[1] = column;
      
      return rowAndColumn;

    }
}