import java.util.*;
public class Task2 {
    private static final String COMMON_LETTERS = "etaoinshrdlcumwfgypbvkjxqz";
    public static void main(String[] args) {
        String encryptedMessage = "QEB NRFZH YOLTK CLU GRJMP LSBO QEB IXWV ALD";
        String decodedMessage = decodeUsingFrequency(encryptedMessage);
        System.out.println("Decoded Message: " + decodedMessage);
    }
    public static String decodeUsingFrequency(String encryptedText) {
        String sortedCipherLetters = getLetterFrequency(encryptedText);
        Map<Character, Character> decryptionMap = new HashMap<>();
        for (int i = 0; i < sortedCipherLetters.length() && i < COMMON_LETTERS.length(); i++) {
            decryptionMap.put(sortedCipherLetters.charAt(i), COMMON_LETTERS.charAt(i));
        }
        StringBuilder decryptedOutput = new StringBuilder();
        for (char ch : encryptedText.toCharArray()) {
            char lowercaseChar = Character.toLowerCase(ch);
            if (decryptionMap.containsKey(lowercaseChar)) {
                decryptedOutput.append(decryptionMap.get(lowercaseChar));
            } else {
                decryptedOutput.append(ch);
            }
        }
        return decryptedOutput.toString();
    }
    public static String getLetterFrequency(String text) {
        Map<Character, Integer> letterCountMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            char lowercaseChar = Character.toLowerCase(ch);
            if (Character.isLetter(lowercaseChar)) {
                letterCountMap.put(lowercaseChar, letterCountMap.getOrDefault(lowercaseChar, 0) + 1);
            }
        }
        List<Character> sortedCharacters = new ArrayList<>(letterCountMap.keySet());
        sortedCharacters.sort((a, b) -> letterCountMap.get(b) - letterCountMap.get(a));
        StringBuilder frequencyOrder = new StringBuilder();
        for (char ch : sortedCharacters) {
            frequencyOrder.append(ch);
        }
        return frequencyOrder.toString();}}

