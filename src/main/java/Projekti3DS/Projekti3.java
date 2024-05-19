package Projekti3DS;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Projekti3 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Encrypt/Decrypt Application");

        // Algorithm selection
        ComboBox<String> algorithmComboBox = new ComboBox<>();
        algorithmComboBox.getItems().addAll("Caesar", "Vigenere");
        algorithmComboBox.setValue("Caesar");

        // Key input
        TextField keyField = new TextField();
        keyField.setPromptText("Enter key");

        // File selection
        TextField inputFileField = new TextField();
        inputFileField.setPromptText("Input file path");
        Button inputFileButton = new Button("Browse");
        inputFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                inputFileField.setText(selectedFile.getPath());
            }
        });

        TextField outputFileField = new TextField();
        outputFileField.setPromptText("Output file path");
        Button outputFileButton = new Button("Browse");
        outputFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showSaveDialog(primaryStage);
            if (selectedFile != null) {
                outputFileField.setText(selectedFile.getPath());
            }
        });

        // Message input
        TextArea messageField = new TextArea();
        messageField.setPromptText("Enter message to encrypt/decrypt");

        // Encrypt button
                Button encryptButton = new Button("Encrypt");
        encryptButton.setOnAction(e -> {
            String algorithm = algorithmComboBox.getValue();
            String key = keyField.getText();
            String message = messageField.getText();
            String encryptedMessage = "";

            try {
                if (algorithm.equals("Caesar")) {
                    int shift = Integer.parseInt(key);
                    encryptedMessage = CaesarCipher.encrypt(message, shift);
                } else if (algorithm.equals("Vigenere")) {
                    encryptedMessage = VigenereCipher.encrypt(message, key);
                }
                messageField.setText(encryptedMessage);
                saveToFile(encryptedMessage, outputFileField.getText());
            } catch (NumberFormatException nfe) {
                showAlert("Invalid Key", "The key for Caesar cipher must be an integer.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
                showAlert("File Error", "There was an error writing to the file.");
            }
        });

        // Encrypt file button
        Button encryptFileButton = new Button("Encrypt File");
        encryptFileButton.setOnAction(e -> {
            String algorithm = algorithmComboBox.getValue();
            String key = keyField.getText();
            String encryptedContent = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileField.getText()))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }

                if (algorithm.equals("Caesar")) {
                    int shift = Integer.parseInt(key);
                    encryptedContent = CaesarCipher.encrypt(fileContent.toString(), shift);
                } else if (algorithm.equals("Vigenere")) {
                    encryptedContent = VigenereCipher.encrypt(fileContent.toString(), key);
                }

                saveToFile(encryptedContent, outputFileField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                showAlert("File Error", "There was an error reading the input file or writing to the output file.");
            } catch (NumberFormatException nfe) {
                showAlert("Invalid Key", "The key for Caesar cipher must be an integer.");
            }
        });

        // Decrypt button
        Button decryptButton = new Button("Decrypt");
        decryptButton.setOnAction(e -> {
            String algorithm = algorithmComboBox.getValue();
            String key = keyField.getText();
            String encryptedMessage = messageField.getText();
            String decryptedMessage = "";

            try {
                if (algorithm.equals("Caesar")) {
                    int shift = Integer.parseInt(key);
                    decryptedMessage = CaesarCipher.decrypt(encryptedMessage, shift);
                } else if (algorithm.equals("Vigenere")) {
                    decryptedMessage = VigenereCipher.decrypt(encryptedMessage, key);
                }
                messageField.setText(decryptedMessage);
                saveToFile(decryptedMessage, outputFileField.getText());
            } catch (NumberFormatException nfe) {
                showAlert("Invalid Key", "The key for Caesar cipher must be an integer.");
            } catch (IOException ioException) {
                ioException.printStackTrace();
                showAlert("File Error", "There was an error writing to the file.");
            }
        });


    }
}

class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if(Character.isLetter(c)){
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                result.append((char) ((c - base + shift) % 26 + base));
            }
            else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
}

class VigenereCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                result.append((char) ((c - base + (key.charAt(j % key.length()) - 'A')) % 26 + base));
                j++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                result.append((char) ((c - base - (key.charAt(j % key.length()) - 'A') + 26) % 26 + base));
                j++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
