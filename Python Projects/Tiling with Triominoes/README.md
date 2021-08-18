# Tiling with Triominoes

- This project demonstrates the connection between induction and recursion.
- It implements a recursive algorithm  to prove the following theorem: "Let n be a positive integer. Every 2nx2n chessboard with one square removed can be tiled using L-shaped triominoes, each covering three squares at a time."
- The recursive algorithm can be summarized like this: "Base case: when n = 1, a 2x2 chessboard with one square removed can be easily tiled with a triomino that covers the three remaining squares. Recursive case: divide the board into four sub-boards. One of them will have a square removed. Put a triomino that overlaps all the remaining three sub-boards and perform recursive calls to tile the four sub-boards, with the squares covered by the additional triomino treated as removed squares for the recursive calls."
- The tiling process performed by Triominoes.py is visualized using matplotlib.