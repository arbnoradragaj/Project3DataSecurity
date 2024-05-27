package Projekti3DS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.io.*;

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

            try (BufferedReader reader = new BufferedReader(
                    new FileReader(inputFileField.getText())
            )) {
                StringBuilder fileContent = new StringBuilder();
//              Accumulating the file's content
                String line;// hold each line
                while ((line = reader.readLine()) != null) {
                    //read line for line, unless if its null
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

        // Decrypt file button
        Button decryptFileButton = new Button("Decrypt File");
        decryptFileButton.setOnAction(e -> {
            String algorithm = algorithmComboBox.getValue();
            String key = keyField.getText();
            String decryptedContent = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFileField.getText()))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }

                if (algorithm.equals("Caesar")) {
                    int shift = Integer.parseInt(key);
                    decryptedContent = CaesarCipher.decrypt(fileContent.toString(), shift);
                } else if (algorithm.equals("Vigenere")) {
                    decryptedContent = VigenereCipher.decrypt(fileContent.toString(), key);
                }

                saveToFile(decryptedContent, outputFileField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                showAlert("File Error", "There was an error reading the input file or writing to the output file.");
            } catch (NumberFormatException nfe) {
                showAlert("Invalid Key", "The key for Caesar cipher must be an integer.");
            }
        });
        // Layout, qysh shfaqet n'output qeto qe i krijum
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
//      padding Inset(top, right, bottom, left);
        grid.setVgap(8);//vertical hapesira
        grid.setHgap(10);//horizontal hapesira

        GridPane.setConstraints(algorithmComboBox, 0, 0);
        GridPane.setConstraints(keyField, 1, 0);
        GridPane.setConstraints(inputFileField, 0, 1);
        GridPane.setConstraints(inputFileButton, 1, 1);
        GridPane.setConstraints(outputFileField, 0, 2);
        GridPane.setConstraints(outputFileButton, 1, 2);
        GridPane.setConstraints(messageField, 0, 3, 2, 1);
        GridPane.setConstraints(encryptButton, 0, 4);
        GridPane.setConstraints(encryptFileButton, 1, 4);
        GridPane.setConstraints(decryptButton, 0, 5);
        GridPane.setConstraints(decryptFileButton, 1, 5);

        grid.getChildren().addAll(algorithmComboBox, keyField, inputFileField, inputFileButton, outputFileField, outputFileButton, messageField, encryptButton, encryptFileButton, decryptButton, decryptFileButton);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void saveToFile(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
// Caesar Cipher: shifting shkronjat e tekstit plain prej nje shifre
// qe e thirrim key qe mund te jete prej 1 deri 26, ku 1 edhe 26 jane
// weak keys, dhe vlerat qe nuk jane karaktere,
// si numrat, shenjat, edhe hapesirat nuk ndryshohen
class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
//      Used for saving the result, used instead of String because
//      StringBuilder na lejon te modifikojme sekuencen e karaktereve
//      Pa nevojen e krijimit te karaktereve te reja.
        for(int i = 0; i < text.length(); i++){//loop all the way through
            char c = text.charAt(i);//per me rujt cdo karakter
            if(Character.isLetter(c)){// kqyr nese eshte shkronje
                char base = Character.isLowerCase(c) ? 'a' : 'A';
//              Base is set to 'a' for lowercase, 'A' per uppercase
                result.append((char) ((c - base + shift) % 26 + base));
//              For example, if c is 'e' and base is 'a', then
//              c - base is 4 (since 'e' is the 5th letter and
//              indices start at 0).
//              Tash nese eshe e(index 4) ja shtojm shift(psh. 3)
//              atehere e marrim vleren 7, edhe modulo 26, per rastin
//              nese e kalon z, psh. nese e bajm shift z(index 25),
//              per 3, i bjen 28 % 26, e kthen 2, a(index0), b(index1)
//              qe pi bjen osht c(index3). Tash nese e kemi
//              shifted index 7, tu ja shtu base 'a' or 'A', i bie qe
//              karakteri eshte h.
            }
            else{
                result.append(c);
                // shto c(ku ruhen krejt qka jep useri n'input)
                // te result.
            }
        }
        return result.toString();//return the encrypted as String
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
//      tash tekstin bone decrypt, tu ja hek qat shift qe osht applied
    }
}

//Per dallim nga Caesar, Vigenere nuk i bon shift per t'njejten vlere
//po i bon shift secilen shkronje e perdor keyword per me caktu
//shift per gjithsecilen.
// Psh nese keyword = 'KEY', text = 'HELLO'
// K(index 10), E(index 4), Y(index 24),
//H(index 0), (H - 'A' + 10) % 26 + 'A' = R
//E(index 1), ((E - 'A' + 4) % 26 + 'A') = I
//L(index 2), ((L - 'A' + 24) % 26 + 'A') = J.
// tash ska me gjatesi keyword, perqita tek shift e kemi ba
// '% key.length()' qe me kthy apet te K, nese text longer se keyword
//L(index 3), ((L - 'A' + 10) % 26 + 'A') = V.
//O(index 4), ((O - 'A' + 4) % 26 + 'A') = S
class VigenereCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();//gjithmone uppercase KEY te vigenere
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int shift = key.charAt( j % key.length()) - 'A';
                result.append((char) ((c - base + shift) % 26 + base));
                j++;
            } else {
                result.append(c);//nese isLetter() false shto pa changes
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
                int shift = (key.charAt(j % key.length()) - 'A');
                result.append((char) ((c - base - shift + 26) % 26 + base));
//              Arsyeja pse duhet '- base', eshte qe ktu shkojn me vlera
//              ASCII, ku A(97), B(98)..., edhe nese nuk e bon -base
//              nuk i kthen qysh duhet vlerat, psh:
//              ASCII value of 'h' is 104.
//              base is 'a' (ASCII 97).
//              Using the original formula:
//
//              Normalized index: c - base = 104 - 97 = 7
//              Apply reverse shift: 7 - 3 = 4
//              Adjust for negatives: 4 + 26 = 30
//              Wrap around: 30 % 26 = 4
//              Convert back to character: 4 + 97 = 101 (which is 'e')
//              ASCII value of 'h' is 104.
//              Using the incorrect formula:
//
//              Apply reverse shift: 104 - 3 = 101
//              Wrap around: 101 % 26 = 23
//              Convert back to character: 23
//              (which is '\u0017', a non-printable control character)
//              +26 siguron qe mos mu kthy vlera negative
                j++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

//Tash per decrypt qat fjalen RIJVS qe na kthej qysh dekriptohet:
// K(index 10), E(index 4), Y(index 24),
//R(index 0) ((R - 'A' - 10 + 26) % 26 + 'A') = H
//I(index 1) ((R - 'A' - 4 + 26) % 26 + 'A') = E
//J(index 2) ((R - 'A' - 24 + 26) % 26 + 'A') = L
//V(index 3) ((R - 'A' - 10 + 26) % 26 + 'A') = L
//S(index 4) ((R - 'A' - 4 + 26) % 26 + 'A') = O
