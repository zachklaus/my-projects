
package a2;

import java.util.ArrayList;

class Queen extends ChessPiece 
{
    public Queen(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    public String toString() 
    {
        if (this.color == Color.WHITE)
        {
            return "\u2655";
        }
        else 
        {
            return "\u265B";
        }
    }

    public ArrayList<String> legalMoves() 
    {
        return new ArrayList<String>();
    }

}