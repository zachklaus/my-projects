
package a2;

import java.util.ArrayList;

abstract class ChessPiece 
{
    public enum Color {WHITE,BLACK};
    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

    public ChessPiece(ChessBoard board, Color color) 
    {
      this.board = board;
      this.color = color;
    }

    public Color getColor() 
    {
        return this.color;
    }

    public String getPosition() 
    {
        String stringPosition = new String();

        switch (this.column) 
        {
            case (0): stringPosition = "a"; break;
            case (1): stringPosition = "b"; break;
            case (2): stringPosition = "c"; break;
            case (3): stringPosition = "d"; break;
            case (4): stringPosition = "e"; break;
            case (5): stringPosition = "f"; break;
            case (6): stringPosition = "g"; break;
            case (7): stringPosition = "h"; break;
            default: break;
        }
        
        stringPosition = stringPosition + Integer.toString(this.row + 1);
        return stringPosition;
    }

    public void setPosition(String position) throws IllegalPositionException 
    {
      int toRow;
      int toColumn;
      
      if (position.length() != 2) 
      {
          throw new IllegalPositionException();
      }

      switch (position.charAt(0)) 
      {
          case ('a'): toColumn = 0; break;
          case ('b'): toColumn = 1; break;
          case ('c'): toColumn = 2; break;
          case ('d'): toColumn = 3; break;
          case ('e'): toColumn = 4; break;
          case ('f'): toColumn = 5; break;
          case ('g'): toColumn = 6; break;
          case ('h'): toColumn = 7; break;
          default: throw new IllegalPositionException();
      }

      try
      {
          toRow = Character.getNumericValue(position.charAt(1)) - 1;
      } catch (Exception e) {
          throw new IllegalPositionException(); 
      }

      if (toRow < 0 || toRow > 7) 
      {
          throw new IllegalPositionException(); 
      }
      
      this.row = toRow;
      this.column = toColumn;
    
    }

    abstract public String toString();
    abstract public ArrayList<String> legalMoves();

}