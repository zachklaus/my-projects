
package a2;

import java.util.ArrayList;

class Pawn extends ChessPiece 
{
    public Pawn(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    public String toString() 
    {
        if (this.color == Color.WHITE) 
        {
            return "\u2659";
        }
        else 
        {
            return "\u265F";
        }
    }

    public ArrayList<String> legalMoves() 
    {
        ArrayList<String> legalMoves = new ArrayList<String>();
        
        int testRow = -1;
        int testCol = -1;

        if (this.color == Color.WHITE) 
        {
            if (this.row == 1) 
            {
                testRow = this.row + 2;
                testCol = this.column;

                if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
                {
                    int[] coordinates = new int[2];
                    coordinates[0] = testRow;
                    coordinates[1] = testCol;
                    String stringPosition = convertPositionToString(coordinates);

                    try 
                    {
                        if (this.board.getPiece(stringPosition) == null)
                        {
                            legalMoves.add(stringPosition);
                        } 
                    } catch(Exception e) {}
                }
            }

            testRow = this.row + 1;
            testCol = this.column;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);
                   
                 try 
                 {
                    if (this.board.getPiece(stringPosition) == null)
                    {
                        legalMoves.add(stringPosition);
                    } 
                } catch(Exception e) {}
            }

            testRow = this.row + 1;
            testCol = this.column + 1;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);
                
                try 
                {
                    if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                    {
                        legalMoves.add(stringPosition);
                    } 
                } catch (Exception e) {} 
            }

            testRow = this.row + 1;
            testCol = this.column - 1;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);

                try 
                {
                    if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                    {
                        legalMoves.add(stringPosition);
                    }  
                } catch (Exception e) {}

        }
        
        if (this.color == Color.BLACK) 
        {
            if (this.row == 6) 
            {
                testRow = this.row - 2;
                testCol = this.column;

                if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
                {
                    int[] coordinates = new int[2];
                    coordinates[0] = testRow;
                    coordinates[1] = testCol;
                    String stringPosition = convertPositionToString(coordinates);

                    try 
                    {
                        if (this.board.getPiece(stringPosition) == null)
                        {
                            legalMoves.add(stringPosition);
                        } 
                    } catch(Exception e) {}  
                }
            }

            testRow = this.row - 1;
            testCol = this.column;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);

                 try 
                 {
                    if (this.board.getPiece(stringPosition) == null)
                    {
                        legalMoves.add(stringPosition);
                    } 
                } catch(Exception e) {}
            }

            testRow = this.row - 1;
            testCol = this.column - 1;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);

                try 
                {
                    if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                    {
                        legalMoves.add(stringPosition);
                    }
                } catch(Exception e) {}  
            }

            testRow = this.row - 1;
            testCol = this.column + 1;

            if ((testRow <= 7) && (testRow >= 0) && (testCol <= 7) && (testCol >= 0)) 
            {
                int[] coordinates = new int[2];
                coordinates[0] = testRow;
                coordinates[1] = testCol;
                String stringPosition = convertPositionToString(coordinates);

                try 
                {
                    if (this.board.getPiece(stringPosition).getColor() != this.getColor()) 
                    {
                        legalMoves.add(stringPosition);
                    }  
                } catch (Exception e) {}
            }

            }
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