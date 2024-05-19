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
