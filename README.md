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

# Caesar Cipher
The Caesar Cipher is a substitution cipher where each letter in the plaintext is shifted a certain number of places down or up the alphabet. Named after Julius Caesar, who reportedly used it in his private correspondence, this cipher is one of the simplest and most well-known encryption techniques. For example, with a shift of 1, 'A' would be replaced by 'B', 'B' would become 'C', and so on. This method is simple yet provides basic encryption, making it a popular choice for educational purposes.

# Vigenere Cipher
The Vigenere Cipher is a more complex polyalphabetic substitution cipher that uses a keyword to encrypt the text. Invented by Blaise de Vigenère in the 16th century, this cipher overcomes the weaknesses of the Caesar Cipher by using a series of different Caesar ciphers based on the letters of a keyword. Each letter of the plaintext is shifted along some number of places defined by the corresponding letter in the keyword. This creates a more secure encryption compared to the Caesar Cipher, as it varies the shift based on the keyword, making frequency analysis attacks much more difficult.

# Encryption and Decryption

# Caesar Cipher Encryption and Decryption

# Encryption: 
To encrypt a message using the Caesar Cipher, each letter in the plaintext is shifted by a fixed number (the key). For example, with a key of 3, 'A' becomes 'D', 'B' becomes 'E', and so forth. This process can be represented mathematically as:

# E(x)=(x+n)mod26

where E(x) is the encrypted letter, x is the plaintext letter, and n is the shift key.

# Decryption: 
Decryption reverses the process by shifting each letter in the ciphertext back by the same number. For example, with a key of 3, 'D' becomes 'A', 'E' becomes 'B', etc. The mathematical representation for decryption is:

# D(x)=(x−n+26)mod26

where D(x) is the decrypted letter, x is the ciphertext letter, and n is the shift key.

