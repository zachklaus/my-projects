import string

def main():
    
    print("-This program decrypts cipher text from a double transposition cipher.")
    print("-The program is provided two keys from unwrappedKeys.txt and cipher text from AliceCipherText.txt")
    
    keys = get_keys()
    print("Keys to be used: " + keys[0] + ", " + keys[1])
    
    ciphertext = read_file("AliceCipherText.txt")
    print("Cipher text to be decrypted: " + ciphertext)
    
    plaintext1 = decrypt(ciphertext, keys[1])
    plaintext2 = decrypt(plaintext1, keys[0])
    
    plaintext_final = remove_xx(plaintext2)
    
    print("Decrypted message: " + plaintext_final)
    write_file("decryptedMessage.txt", plaintext_final)
    print("Decrypted message written to decryptedMessage.txt")

def get_keys():
    
    key_text = read_file("unwrappedKeys.txt")
    keys = [None, None]
    key1 = ""
    key2 = ""
    
    for char in key_text:
        if char is ' ':
            break
        else:
            key1 += char
    
    for char in key_text[key_text.index(' '):]:
        if char is ' ':
            continue
        else:
            key2 += char
    
    keys[0] = key1
    keys[1] = key2
    
    return keys
      
def read_file(filename):
    
    file = open(filename, "r")
    if file.mode == "r":
        plaintext = file.read()
    
    new_plaintext = ""
    
    for char in plaintext:
        if char is '\n' or char is '.':
            continue
        else:
            new_plaintext = new_plaintext + char
    
    file.close()
    return new_plaintext

def write_file(filename, contents):
    
    file = open(filename, "w")
    file.write(contents)
    file.close()
    
def get_order(key):
    
    ordering = list()
    for i in range(0,10):
        ordering.append(0)
        
    for i in range(0,10):
        ordering[i] = string.ascii_lowercase.index(key[i]) + 1
    
    finished = False
    ordering_temp = ordering[:]
    rank = 1
    while finished is False:
        minimum = min(ordering_temp)
        index = ordering.index(minimum)
        ordering[index] = rank
        rank = rank + 1
        ordering_temp.remove(minimum)
        if not ordering_temp:
            finished = True
          
    return ordering

def remove_xx(plaintext):
    
    new_text = ""
    for i in range(0, len(plaintext)):
        if i + 1 >= len(plaintext):
            break
        elif (plaintext[i] == 'X' and plaintext[i+1] == 'X') or (plaintext[i] == 'X' and plaintext[i-1] == 'X'):
            if new_text[len(new_text)-1] != ' ':
                new_text = new_text + ' '
            continue
        else:
            new_text = new_text + plaintext[i]
    
    new_text = new_text.strip()
    return new_text

def decrypt(plaintext, key):
    
    transpose = [None, None, None, None, None, None, None, None, None, None]
    
    for i in range(0,10):
        transpose[i] = list()
    
    transpose_depth =  int(len(plaintext) / 10)
    
    ordering = get_order(key)
    ordering_place = 1
    
    temp_matrix = [None, None, None, None, None, None, None, None, None, None]
    
    for i in range(0,10):
        temp_matrix[i] = list()
        
    text_index = 0
    temp_matrix_index = 0
    
    for text_index in range(0, len(plaintext)):
        if len(temp_matrix[temp_matrix_index]) == transpose_depth:
            temp_matrix_index = temp_matrix_index + 1
        
        temp_matrix[temp_matrix_index].append(plaintext[text_index])
        text_index = text_index + 1 
    
    for char_list in temp_matrix:
        transpose_index = ordering.index(ordering_place)
        transpose[transpose_index] = char_list
        if ordering_place == 10:
            break
        else:
            ordering_place = ordering_place + 1
    
    decrypted = ""
    
    i = 0
    j = 0
    
    while True:
        if len(decrypted) == transpose_depth * 10:
            break
        if (i > 9) and (j > transpose_depth-1):
            continue
        else:
            decrypted = decrypted + transpose[i][j]
        if (i==9):
            i = 0
            j = j+1
        else:
            i = i + 1
        
    return decrypted
    
if __name__ == "__main__":
    main()