
package a2;

import java.util.ArrayList;

class Knight extends ChessPiece 
{
    public Knight(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    public String toString() 
    {
        if (this.color == Color.WHITE) 
        {
            return "\u2658";
        }
        else 
        {
            return "\u265E";
        } 
    }

    public ArrayList<String> legalMoves() 
    {
        return new ArrayList<String>();
    }

}