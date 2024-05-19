package Projekti3DS;

import javafx.application.Application;
import javafx.stage.Stage;

public class Projekti3 extends Application {
    @Override
    public void start(Stage stage) throws Exception {

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
