# Encryption and decryption through Caesar's and Vigenere's algorithm

This is the third project created by the students of the second year of the Faculty of Electrical and Computer Engineering, as part of the exercises in the subject Data Security lectured by Ass. Mërgim Hoti.

# Authors

[Arbios Morina](https://github.com/arbiosmorina)

[Arbnora Dragaj](https://github.com/arbnoradragaj)

[Argjend Nimanaj](https://github.com/Argjend1of1)

[Arlinda Beqiraj](https://github.com/Argjend1of1)


# Introduction
This documentation provides an overview of an encryption/decryption application developed using Java and JavaFX. The application supports two encryption algorithms: Caesar and Vigenere. It allows users to encrypt and decrypt messages and files using either algorithm.


# Caesar and Vigenere Algorithms

## Caesar Cipher
The Caesar Cipher is a substitution cipher where each letter in the plaintext is shifted a certain number of places down or up the alphabet. Named after Julius Caesar, who reportedly used it in his private correspondence, this cipher is one of the simplest and most well-known encryption techniques. For example, with a shift of 1, 'A' would be replaced by 'B', 'B' would become 'C', and so on. This method is simple yet provides basic encryption, making it a popular choice for educational purposes.

## Vigenere Cipher
The Vigenere Cipher is a more complex polyalphabetic substitution cipher that uses a keyword to encrypt the text. Invented by Blaise de Vigenère in the 16th century, this cipher overcomes the weaknesses of the Caesar Cipher by using a series of different Caesar ciphers based on the letters of a keyword. Each letter of the plaintext is shifted along some number of places defined by the corresponding letter in the keyword. This creates a more secure encryption compared to the Caesar Cipher, as it varies the shift based on the keyword, making frequency analysis attacks much more difficult.


# Encryption and Decryption

## Caesar Cipher Encryption and Decryption

### Encryption: 
To encrypt a message using the Caesar Cipher, each letter in the plaintext is shifted by a fixed number (the key). For example, with a key of 3, 'A' becomes 'D', 'B' becomes 'E', and so forth. This process can be represented mathematically as:

      E(x)=(x+n)mod26

where E(x) is the encrypted letter, x is the plaintext letter, and n is the shift key.

### Decryption: 
Decryption reverses the process by shifting each letter in the ciphertext back by the same number. For example, with a key of 3, 'D' becomes 'A', 'E' becomes 'B', etc. The mathematical representation for decryption is:

      D(x)=(x−n+26)mod26

where D(x) is the decrypted letter, x is the ciphertext letter, and n is the shift key.

## Vigenere Cipher Encryption and Decryption

### Encryption:
The Vigenere Cipher uses a keyword where each letter of the plaintext is shifted by the value of the corresponding letter in the keyword. For example, with a keyword "KEY", the first letter of the plaintext is shifted by the value of 'K', the second by 'E', and the third by 'Y'. This can be represented as:

      𝐸(𝑥𝑖)=(𝑥𝑖+𝑘𝑖)mod26

where E(xi) is the encrypted letter, xi is the plaintext letter, and ki is the key letter.

### Decryption:
Decryption reverses the process using the same keyword. Each letter in the ciphertext is shifted back by the value of the corresponding letter in the keyword. The decryption process is:

        𝐷(𝑥𝑖)=(𝑥𝑖−𝑘𝑖+26)mod26

where D(xi) is the decrypted letter, xi is the ciphertext letter, and ki is the key letter.



# Implementation


## Algorithm Classes

### CaesarCipher
The CaesarCipher class contains methods for encrypting and decrypting text using the
Caesar Cipher. It uses a simple shift based on the provided key.

### VigenereCipher

The VigenereCipher class contains methods for encrypting and decrypting text using the
Vigenere Cipher. It uses a keyword to vary the shift for each letter in the text.



## Main Application
The Projekti3 class is the main application class that extends Application from JavaFX.
It provides a user interface to select an encryption algorithm, input keys, choose input
and output files, and perform encryption and decryption.

### Key Components

**-** Algorithm Selection: Users can choose between Caesar and Vigenere
algorithms using a combo box.

**-** Key Input: Users can enter the key for the selected algorithm.

**-** File Selection: Users can choose input and output files using file choosers.

**-** Message Input: Users can enter the message to be encrypted or decrypted in a
text area.

**-** Encrypt/Decrypt Buttons: Buttons to perform encryption and decryption on the
entered message or selected file.




# Results

## Current Results

The application provides a simple user interface to encrypt and decrypt messages and
files using Caesar and Vigenere ciphers. 
Users can:

**-** Select the encryption algorithm.

**-** Enter the encryption/decryption key.

**-** Choose input and output files.

**-** Input a message to encrypt or decrypt.

**-** Encrypt or decrypt the message or the selected file.

