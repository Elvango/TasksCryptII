import java.util.*;
public class Task3 {
        private static final int SIZE = 5;
        private char[][] playfairMatrix = new char[SIZE][SIZE];
        private Map<Character, int[]> letterPositions = new HashMap<>();

        public Task3(String keyword) {
            generateMatrix(keyword);
        }
        private void generateMatrix(String keyword) {
            Set<Character> usedLetters = new HashSet<>();
            String keyString = (keyword + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toUpperCase().replace("J", "I");
            StringBuilder refinedKey = new StringBuilder();
            for (char c : keyString.toCharArray()) {
                if (!usedLetters.contains(c)) {
                    usedLetters.add(c);
                    refinedKey.append(c);
                }
            }
            int index = 0;
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    char letter = refinedKey.charAt(index++);
                    playfairMatrix[row][col] = letter;
                    letterPositions.put(letter, new int[]{row, col});
                }
            }
        }
        private String formatText(String text) {
            text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
            StringBuilder formattedText = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                if (i > 0 && text.charAt(i) == text.charAt(i - 1)) {
                    formattedText.append('X');
                }
                formattedText.append(text.charAt(i));
            }
            if (formattedText.length() % 2 != 0) {
                formattedText.append('X');
            }
            return formattedText.toString();
        }
        public String encrypt(String plaintext) {
            String formattedText = formatText(plaintext);
            return processText(formattedText, true);
        }
        public String decrypt(String ciphertext) {
            return processText(ciphertext, false);
        }
        private String processText(String text, boolean encrypt) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i += 2) {
                char a = text.charAt(i);
                char b = text.charAt(i + 1);
                int[] posA = letterPositions.get(a);
                int[] posB = letterPositions.get(b);
                if (posA[0] == posB[0]) {
                    result.append(playfairMatrix[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % SIZE]);
                    result.append(playfairMatrix[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % SIZE]);
                } else if (posA[1] == posB[1]) {
                    result.append(playfairMatrix[(posA[0] + (encrypt ? 1 : 4)) % SIZE][posA[1]]);
                    result.append(playfairMatrix[(posB[0] + (encrypt ? 1 : 4)) % SIZE][posB[1]]);
                } else {
                    result.append(playfairMatrix[posA[0]][posB[1]]);
                    result.append(playfairMatrix[posB[0]][posA[1]]);
                }
            }
            return result.toString();
        }
        public void printMatrix() {
            for (char[] row : playfairMatrix) {
                for (char c : row) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the keyword: ");
            String keyword = scanner.nextLine();
            Task3 playfair = new Task3(keyword);
            System.out.println("\nGenerated Playfair Matrix:");
            playfair.printMatrix();
            System.out.print("\nEnter text to encrypt: ");
            String plaintext = scanner.nextLine();
            String encryptedText = playfair.encrypt(plaintext);
            System.out.println("Encrypted Text: " + encryptedText);
            System.out.print("\nEnter text to decrypt: ");
            String ciphertext = scanner.nextLine();
            String decryptedText = playfair.decrypt(ciphertext);
            System.out.println("Decrypted Text: " + decryptedText);
            scanner.close();
        }
    }


