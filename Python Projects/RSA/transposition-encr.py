
import string

def main():
    
    print("-This program encrypts messages using a double transposition cipher and two keys provided as input.")
    print("-Keys must be 10 characters long. Each character must be selected from the 26 letters of the alphabet.")
    print("-Each character must be used only once in a single key. Characters may repeat between the two keys.")
    
    key1 = get_key("first")
    print("First key: " + key1)
    
    key2 = get_key("second")
    print("Second key: " + key2)
    
    keys = key1 + " " + key2
    write_file("keys.txt",keys)
    print("Keys written to keys.txt")
    
    plaintext_source = input("Enter the name of the text file that contains the message to be encrypted: ")
    plaintext = read_file(plaintext_source)
    print("Message to be encrypted: " + plaintext)
    
    ciphertext1 = encrypt(plaintext, key1)
    ciphertext2 = encrypt(ciphertext1, key2)
    
    print("Encrypted cipher text: ", ciphertext2)
    write_file("AliceCipherText.txt", ciphertext2)
    print("Cipher text written to AliceCipherText.txt")
    
def get_key(key_number):
    
    valid_key = False
    
    while valid_key is False:
        key = input("Enter the " + key_number + " key: ")
        
        key = key.lower()
        key = remove_duplicates(key)
        
        if len(key) < 10:
            print("Key must be at least 10 characters long with no duplicates.")
            continue
        
        key = key[0:10]
        return key
        

def remove_duplicates(key):
    
    new_key = ""
    
    for char in key:
        if char in new_key:
            continue
        else:
            new_key = new_key + char
    
    return new_key

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

def add_x(plaintext):
    
    new_plaintext = ""
    
    for char in plaintext:
        if char is  ' ':
            new_plaintext = new_plaintext + "XX"
        elif char is '.':
            break
        else:
            new_plaintext = new_plaintext + char
    
    while True:
        if (len(new_plaintext) % 10) != 0:
            new_plaintext = new_plaintext + 'X'
        else:
            break
    
    return new_plaintext

def encrypt(plaintext, key):
    
    transpose = [None, None, None, None, None, None, None, None, None, None]
    
    for i in range(0,10):
        transpose[i] = list()
    
    plaintext = add_x(plaintext)
    transpose_index = 0
    
    for char in plaintext:
        transpose[transpose_index].append(char)
        if transpose_index == 9:
            transpose_index = 0
        else:
            transpose_index += 1
    
    ordering = get_order(key)
    ordering_index = 1
    transposition = ""

    while True:
        transpose_index = ordering.index(ordering_index)
        for element in transpose[transpose_index]:
            transposition = transposition + element
        if ordering_index == 10:
            break
        else:
            ordering_index += 1

    return transposition
    
if __name__ == "__main__":
    main()