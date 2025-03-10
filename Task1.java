import java.util.*;
import java.util.Scanner;
public class Task1 {
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the encrypted message: ");
            String ciphertext= scanner.next();
            List<Character> alphabetList = new ArrayList<>();
            for (char c : ALPHABET.toCharArray()) {
                alphabetList.add(c);}
            int permutationCount = 0;
            List<List<Character>> permutations = generatePermutations(alphabetList);
            for (List<Character> perm : permutations) {
                permutationCount++;
                Map<Character, Character> decryptMap = new HashMap<>();
                for (int i = 0; i < ALPHABET.length(); i++) {
                    decryptMap.put(ALPHABET.charAt(i), perm.get(i));
                }
                String decryptedText = decryptText(ciphertext, decryptMap);
                System.out.println("Permutation #" + permutationCount + ": " + decryptedText);
            }
        }
        public static List<List<Character>> generatePermutations(List<Character> chars) {
            List<List<Character>> result = new ArrayList<>();
            permuteHelper(chars, 0, result);
            return result;
        }
        private static void permuteHelper(List<Character> chars, int index, List<List<Character>> result) {
            if (index == chars.size() - 1) {
                result.add(new ArrayList<>(chars));
                return;
            }
            for (int i = index; i < chars.size(); i++) {
                Collections.swap(chars, i, index);
                permuteHelper(chars, index + 1, result);
                Collections.swap(chars, i, index);
            }
        }
        public static String decryptText(String ciphertext, Map<Character, Character> decryptMap) {
            StringBuilder decryptedText = new StringBuilder();
            for (char c : ciphertext.toCharArray()) {
                char lowerChar = Character.toLowerCase(c);
                if (decryptMap.containsKey(lowerChar)) {
                    decryptedText.append(decryptMap.get(lowerChar));
                } else {
                    decryptedText.append(c);
                }
            }
            return decryptedText.toString();
        }
    }


