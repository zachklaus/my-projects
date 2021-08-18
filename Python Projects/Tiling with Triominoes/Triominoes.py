#Author: Zachary Klausner
#Email: zachklau@rams.colostate.edu
#Class: CS220

import matplotlib.pyplot

triomino_id = 2

def main():
    
    my_list = triominoes(5, 0, 3) 
    print(my_list)


def triominoes(n, removed_row=0, removed_column=0) :

    # initialization of the board:
    board = [ [0] * 2**n for i in range(2**n) ]

    board[removed_row][removed_column] = 1
    
    corner_index = int ((2 ** n) - 1)
    
    recursive_triominoes(n, board, 0, 0, corner_index, 0, 0, corner_index, corner_index, corner_index)
    
    show_plot(board)
    
    return board


def recursive_triominoes(n, board, x0, y0, x1, y1, x2, y2, x3, y3):
    
    global first_tile_control
    global row_removed_glo
    global column_removed_glo
    
    x0 = int (x0)
    y0 = int (y0)
    x1 = int (x1)
    y1 = int (y1)
    x2 = int (x2)
    y2 = int (y2)
    x3 = int (x3)
    y3 = int (y3)  
    
    #base case
    
    if x1 - x0 == 1:
       
        tile(board, x0, y0, x1, y1, x2, y2, x3, y3)
    
    #recursive call
        
    else:
        
        place_center_tile(n,board, x0, y0, x1, y1, x2, y2, x3, y3)
        
        offset = ( 2 ** n ) / 2
        offset_2 = offset - 1
        
        s_p_1_x = x0
        s_p_1_y = y0
        s_p_2_x = x0 + offset
        s_p_2_y = y0
        s_p_3_x = x0
        s_p_3_y = y0 + offset
        s_p_4_x = x0 + offset
        s_p_4_y = y0 + offset
        
        recursive_triominoes(n - 1, board, s_p_1_x, s_p_1_y, (s_p_1_x + offset_2), s_p_1_y, s_p_1_x, (s_p_1_y + offset_2), (s_p_1_x + offset_2), (s_p_1_y + offset_2))
        recursive_triominoes(n - 1, board, s_p_2_x, s_p_2_y, (s_p_2_x + offset_2), s_p_2_y, s_p_2_x, (s_p_2_y + offset_2), (s_p_2_x + offset_2), (s_p_2_y + offset_2))                     
        recursive_triominoes(n - 1, board, s_p_3_x, s_p_3_y, (s_p_3_x + offset_2), s_p_3_y, s_p_3_x, (s_p_3_y + offset_2), (s_p_3_x + offset_2), (s_p_3_y + offset_2))                  
        recursive_triominoes(n - 1, board, s_p_4_x, s_p_4_y, (s_p_4_x + offset_2), s_p_4_y, s_p_4_x, (s_p_4_y + offset_2), (s_p_4_x + offset_2), (s_p_4_y + offset_2))
    
def tile(sub_board, x0, y0, x1, y1, x2, y2, x3, y3):
    
    global triomino_id
    
    x0 = int (x0)
    y0 = int (y0)
    x1 = int (x1)
    y1 = int (y1)
    x2 = int (x2)
    y2 = int (y2)
    x3 = int (x3)
    y3 = int (y3)
    
    num_empty = count_empties(sub_board, x0, y0, x1, y1, x2, y2, x3, y3)
    
    if num_empty < 3:
        return
    
    elif sub_board[x0][y0] > sub_board[x1][y1]:
        
        sub_board[x1][y1] = triomino_id
        sub_board[x2][y2] = triomino_id
        sub_board[x3][y3] = triomino_id
    
    elif sub_board[x1][y1] > sub_board[x0][y0]:
            
        sub_board[x0][y0] = triomino_id
        sub_board[x2][y2] = triomino_id
        sub_board[x3][y3] = triomino_id
        
    elif sub_board[x2][y2] > sub_board[x3][y3]:
            
        sub_board[x3][y3] = triomino_id
        sub_board[x1][y1] = triomino_id
        sub_board[x0][y0] = triomino_id
        
    else:
         
        sub_board[x0][y0] = triomino_id
        sub_board[x1][y1] = triomino_id
        sub_board[x2][y2]= triomino_id
        
    triomino_id += 1
    

def place_center_tile(n, board, x0, y0, x1, y1, x2, y2, x3, y3):
    
    global triomino_id
    
    x0 = int (x0)
    y0 = int (y0)
    x1 = int (x1)
    y1 = int (y1)
    x2 = int (x2)
    y2 = int (y2)
    x3 = int (x3)
    y3 = int (y3)    
    
    center_index_x = int (x0 + ((x1 - x0) / 2))
    center_index_y = int (y0 + ((y2 - y0) / 2))
    far_index = (2**n) - 1
    
    quadrant = find_quadrant(n, board, x0, y0)
    
    if quadrant == 1:
        
        board[center_index_x+1][center_index_y] = triomino_id
        board[center_index_x][center_index_y+1] = triomino_id
        board[center_index_x+1][center_index_y+1] = triomino_id
        
    elif quadrant == 3:
        
        board[center_index_x][center_index_y] = triomino_id
        board[center_index_x+1][center_index_y] = triomino_id
        board[center_index_x+1][center_index_y+1] = triomino_id        
        
    elif quadrant == 2:    
        
        board[center_index_x][center_index_y] = triomino_id
        board[center_index_x][center_index_y+1] = triomino_id
        board[center_index_x+1][center_index_y+1] = triomino_id         
     
    else:   
        
        board[center_index_x][center_index_y] = triomino_id
        board[center_index_x+1][center_index_y] = triomino_id
        board[center_index_x][center_index_y+1] = triomino_id
    
    triomino_id += 1
        

def count_empties(sub_board, x0, y0, x1, y1, x2, y2, x3, y3):
    
    num_empty = 0
    
    if sub_board[x0][y0] == 0:
        
        num_empty += 1
    
    if sub_board[x1][y1] == 0:  
        
        num_empty += 1
    
    if sub_board[x2][y2] == 0:  
        
        num_empty += 1 
        
    if sub_board[x3][y3] == 0:
        
        num_empty += 1   
    
    return num_empty        

def find_quadrant(n, board, x0, y0):
    
    x_limit = x0 + int ((2**n)) 
    y_limit = y0 + int ((2**n))
    
    x_coordinate = 0
    y_coordinate = 0
    
    for i in range(x0, x_limit):
        
        for j in range(y0, y_limit):
            
            if board[i][j] > 0:
               
                x_coordinate = i
                y_coordinate = j
                   
    if x_coordinate < x0 + (2**n) / 2 and y_coordinate < y0 + (2**n) / 2:
        return 1
    
    elif x_coordinate >= x0 + (2**n) / 2 and y_coordinate < y0 + (2**n) / 2: 
        return 2
    
    elif x_coordinate < x0 + (2**n) / 2 and y_coordinate >= y0 + (2**n) / 2: 
        return 3
    
    else: 
        return 4
   
def show_plot(X):
    
    plt = matplotlib.pyplot
    img = plt.imshow(X, interpolation='nearest')
    img.set_cmap('hot')
    plt.axis('off')
    plt.show() 
        
            
if __name__ == "__main__": main()