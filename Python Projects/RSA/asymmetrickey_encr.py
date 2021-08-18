from subprocess import Popen, PIPE, STDOUT
import sys

def main():
    
    print("-This program takes as input two symmetric keys from keys.txt and a public key from publicKey.txt")
    print("-The public key is used to encrypt (wrap) the symmetric keys using RSA encryption")
    keys = get_keys()
    print("Keys to be wrapped: " + keys[0] + ", " + keys[1])
    public_key = get_public_key()
    print("Public key to be used: e = " + public_key[0] + ", n = " + public_key[1])
    print("Wrapping keys...")
    ascii_keys = convert_to_ascii(keys)
    wrapped_keys = wrap_keys(ascii_keys, public_key)
    write_keys("wrappedKeys.txt", wrapped_keys)
    print("Wrapped keys written to wrappedKeys.txt")
    
def wrap_keys(ascii_keys, public_key):
    
    wrapped_keys = [None, None, 0]
    wrapped_keys[0] = list()
    wrapped_keys[1] = list()
    wrapped_keys[2] = list()
    
    for char in ascii_keys[0]:
        wrapped1 = call_bc(char, public_key[0], '^')
        wrapped2 = call_bc(wrapped1, public_key[1], '%')
        wrapped_keys[0].append(wrapped2)
    
    for char in ascii_keys[1]:
        wrapped1 = call_bc(char, public_key[0], '^')
        wrapped2 = call_bc(wrapped1, public_key[1], '%') 
        wrapped_keys[1].append(wrapped2)
    
    space = ' '
    wrapped_space1 = call_bc(ord(space), public_key[0], '^')
    wrapped_space2 = call_bc(wrapped_space1, public_key[1], '%')
    wrapped_keys[2].append(wrapped_space2)
    
    return wrapped_keys
  
def convert_to_ascii(keys):
    
    ascii_keys = [None, None]
    ascii_keys[0] = list()
    ascii_keys[1] = list()
    
    for char in keys[0]:
        integer_form = ord(char)
        ascii_keys[0].append(integer_form)
    
    for char in keys[1]:
        integer_form = ord(char)
        ascii_keys[1].append(integer_form)
    
    return ascii_keys
    
def get_public_key():
    
    public_key_text =  read_file("publicKey.txt")
    public_key = [None, None]
    e = ""
    n = ""
    
    for char in public_key_text:
        if char is ',':
            break
        else:
            e += char
    
    for char in public_key_text[public_key_text.index(' '):]:
        if char is ' ':
            continue
        else:
            n += char
    
    public_key[0] = e
    public_key[1] = n
    
    return public_key

def get_keys():
    
    key_text = read_file("keys.txt")
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

def write_keys(filename, wrapped_keys):
    
    file = open(filename, "w")
    
    line1 = ""
    line2 = ""
    line3 = ""
    
    for integer in wrapped_keys[0][:-1]:
        line1 += integer
        line1 += ' '
    line1 += wrapped_keys[0][-1]
    
    line2 = wrapped_keys[2][0]
    
    for integer in wrapped_keys[1][:-1]:
        line3 += integer
        line3 += ' '
    line3 += wrapped_keys[1][-1]    
    
    file.write(line1 + '\n')
    file.write(line2 + '\n')
    file.write(line3)
    
    file.close()

def call_bc(a, b, op):
    p = Popen(['bc'], stdout=PIPE, stdin=PIPE, stderr=PIPE)
    encode_a_b  = "{} {} {}\n".format(a,op,b).encode('utf-8')
    stdout_data = p.communicate(input=encode_a_b)[0]
    return stdout_data.decode('utf-8')[:-1]

if __name__ == "__main__":
    main()