# Information

Text Files

- AlicePlainText.txt: Contains the plaintext that is to be encrypted by transposition-encr.py
- AliceCipherText.txt: Contains the cipher text that is the result of transposition-encr.py encrypting the plaintext from AlicePlainText.txt
- keys.txt: Contains the plaintext symmetric keys the user of transposition-encr.py has entered via input. 
- publicKey.txt: Contains the public key used by asymmetrickey_encr.py to wrap the symmetric keys in keys.txt via RSA encryption.
- privateKey.txt: Contains the private key used by asymmetrickey_decr.py to unwrap the symmetric keys in wrappedKeys.txt
- wrappedKeys.txt: Contains the wrapped version of the symmetric keys after being wrapped by asymmetrickey_encr.py
- unwrappedKeys.txt: Contains the unwrapped version of the symmetric keys after asymmetrickey_decr.py has unwrapped them.
- decryptedMessage.txt: Contains the plaintext message that transposition-decr.py has decrypted from AliceCiphterText.txt using the keys in unwrappedKeys.txt
- Information.md: This file.


Python Program Files

- transposition-encr.py
    * Takes the plaintext from AlicePlainText.txt (or other user specified file via input) as well as two symmetric keys from user input.
    * Uses the symmetric keys to encrypt the plaintext via a double transposition cipher.
    * Writes the symmetric keys from the user into keys.txt and the resulting cipher text to AliceCipherText.txt

- asymmetrickey_encr.py
    * Takes the public key from publicKey.txt and the plaintext symmetric keys in keys.txt
    * Uses the public key to wrap the symmetric keys via RSA encryption
    * Writes the wrapped keys to wrappedKeys.txt

- asymmetrickey_decr.py
    * Takes the private key from privateKey.txt and the wrapped symmetric keys in wrappedKeys.txt
    * Uses the private key to unwrap the wrapped symmetric keys via RSA decryption
    * Writes the unwrapped keys to unwrappedKeys.txt

- transposition-decr.py
    * Takes the cipher text from AliceCipherText.txt as well as the unwrapped symmetric keys in unwrappedKeys.txt
    * Uses the unwrapped keys to decrypt the cipher text via the reverse of the double transposition cipher.
    * Writes the final plaintext message to decryptedMessage.txt

Notes

- AlicePlainText.txt can be replaced for another plaintext file when transposition-encr.py asks for the name of the plaintext file
- Placing the names of different files in the commandline arguments won't make them be used by the programs.
- All files can be blank before executing the progam except for the plaintext file, publicKey.txt, privateKey.txt, and Information.md

- The sequence of commands to run the progams must be as follows:
    * python3 transposition-encr.py
    * python3 asymmetrickey_encr.py
    * python3 asymmetrickey_decr.py
    * python3 transposition-decr.py

- asymmetrickey_encr.py and asymmetrickey_decr.py will take time to wrap/unwrap the keys
- Some instructions are given in the programs as well as some output
- The file BobPlainText.txt was not included because decryptedMessage.txt performs the same function