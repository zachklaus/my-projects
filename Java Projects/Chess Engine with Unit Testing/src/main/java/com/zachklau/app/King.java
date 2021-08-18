
package a2;

import java.util.ArrayList;

class King extends ChessPiece 
{
    public King(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    public String toString() 
    {
        if (this.color == Color.WHITE) 
        {
            return "\u2654";
        }
        else 
        {
            return "\u265A";
        }
    }

    public ArrayList<String> legalMoves() 
    {
        ArrayList<String> legalMoves = new ArrayList<String>();

        int[] coordinates;
        String stringPosition;
        
        int testRow = this.row + 1;
        int testCol = this.column + 1;
        
        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
                {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row - 1;
        testCol = this.column + 1;

        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);    
                }
            } catch (Exception e) {} 
        }

        testRow = this.row + 1;
        testCol = this.column - 1;

        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row - 1;
        testCol = this.column - 1;

        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row;
        testCol = this.column + 1;

        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row - 1;
        testCol = this.column;

        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row;
        testCol = this.column - 1;
  
        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        testRow = this.row + 1;
        testCol = this.column;
  
        if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0))
        { 
            coordinates = new int[2];
            coordinates[0] = testRow;
            coordinates[1] = testCol;
            stringPosition = convertPositionToString(coordinates);

            try 
            {
                if (this.board.getPiece(stringPosition) == null)
                {
                    legalMoves.add(stringPosition);
                }
                else if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                {
                    legalMoves.add(stringPosition);
                }
            } catch (Exception e) {} 
        }

        return legalMoves;

    }
    
    private String convertPositionToString(int[] position) 
    {
        int row = position[0];
        int column = position[1];

        String stringPosition = "";
        
        switch (column) 
        {
            case (0): stringPosition.concat("a"); break;
            case (1): stringPosition.concat("b"); break;
            case (2): stringPosition.concat("c"); break;
            case (3): stringPosition.concat("d"); break;
            case (4): stringPosition.concat("e"); break;
            case (5): stringPosition.concat("f"); break;
            case (6): stringPosition.concat("g"); break;
            case (7): stringPosition.concat("h"); break;
            default: break;
        }
        
        stringPosition.concat(Integer.toString(row + 1));
        return stringPosition;

    }

}