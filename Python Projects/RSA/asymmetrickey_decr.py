from subprocess import Popen, PIPE, STDOUT
import sys

def main():
    
    print("-This program takes as input two encrypted (wrapped) symmetric keys from wrappedKeys.txt and a private key from privateKey.txt")
    print("-The private key is used to decrypt (unwrap) the symmetric keys using RSA decryption")    
    private_key = get_private_key()
    print("Private key to be used: d = " + private_key[0] + ", n = " + private_key[1])
    wrapped_keys = get_wrapped_keys()
    print("Unwrapping keys...")
    unwrapped_keys = unwrap_keys(wrapped_keys, private_key)
    final_unwrapped = convert_to_char(unwrapped_keys)
    print("Unwrapped Keys: " + final_unwrapped)
    write_unwrapped_keys("unwrappedKeys.txt", final_unwrapped)
    print("Unwrapped Keys written to unwrappedKeys.txt")
    
def unwrap_keys(wrapped_keys, private_key):
    
    unwrapped_keys = [None, None, None]
    unwrapped_keys[0] = list()
    unwrapped_keys[1] = list()
    unwrapped_keys[2] = list()
    
    for integer in wrapped_keys[0]:
        unwrapped1 = call_bc(integer, private_key[0], '^')
        unwrapped2 = call_bc(unwrapped1, private_key[1], '%')
        unwrapped_keys[0].append(unwrapped2)
    
    for integer in wrapped_keys[1]:
        unwrapped1 = call_bc(integer, private_key[0], '^')
        unwrapped2 = call_bc(unwrapped1, private_key[1], '%')
        unwrapped_keys[1].append(unwrapped2)
    
    for integer in wrapped_keys[2]:
        unwrapped1 = call_bc(integer, private_key[0], '^')
        unwrapped2 = call_bc(unwrapped1, private_key[1], '%')
        unwrapped_keys[2].append(unwrapped2)
    
    return unwrapped_keys
  
def convert_to_char(unwrapped_keys):
    
    final_unwrapped = ""
    
    for a_list in unwrapped_keys:
        for integer in a_list:
            temp = int(integer)
            final_unwrapped += chr(temp)
    
    return final_unwrapped
    
def get_private_key():
    
    private_key_text =  read_file("privateKey.txt")
    private_key = [None, None]
    d = ""
    n = ""
    
    for char in private_key_text:
        if char is ',':
            break
        else:
            d += char
    
    for char in private_key_text[private_key_text.index(' '):]:
        if char is ' ':
            continue
        else:
            n += char
    
    private_key[0] = d
    private_key[1] = n
    
    return private_key

def get_wrapped_keys():
    
    with open("wrappedKeys.txt" , 'r') as file:
        wrapped_key_lines = file.readlines() 
    
    if wrapped_key_lines[2][-1] is not '\n':
        wrapped_key_lines[2] += '\n'
    
    wrapped_line1 = list()
    element1 = ""
    for char in wrapped_key_lines[0]:
        if char is '\n':
            wrapped_line1.append(element1)
            element1 = ""
        elif char is ' ':
            wrapped_line1.append(element1)
            element1 = ""
            continue
        else:
            element1 += char
    
    wrapped_line2 = list()
    element3 = ""
    for char in wrapped_key_lines[1]:
        if char is '\n':
            break
        else:
            element3 += char
    wrapped_line2.append(element3)
    
    wrapped_line3 = list()
    element2 = ""
    for char in wrapped_key_lines[2]:
        if char is '\n':
            wrapped_line3.append(element2)
            element2 = ""
        elif char is ' ':
            wrapped_line3.append(element2)
            element2 = ""
            continue
        else:
            element2 += char
    
    wrapped_final = [None, None, None]
    wrapped_final[0] = wrapped_line1
    wrapped_final[1] = wrapped_line2
    wrapped_final[2] = wrapped_line3
    
    return wrapped_final
      
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

def write_unwrapped_keys(filename, unwrapped_keys):
    file = open(filename, "w")
    file.write(unwrapped_keys)
    file.close()

def call_bc(a, b, op):
    p = Popen(['bc'], stdout=PIPE, stdin=PIPE, stderr=PIPE)
    encode_a_b  = "{} {} {}\n".format(a,op,b).encode('utf-8')
    stdout_data = p.communicate(input=encode_a_b)[0]
    return stdout_data.decode('utf-8')[:-1]

if __name__ == "__main__":
    main()