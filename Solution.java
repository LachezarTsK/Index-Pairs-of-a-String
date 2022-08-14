
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    private static final int ALPHABET_SIZE = 26;
    private final List<Integer>[] charToStartIndexes = new ArrayList[ALPHABET_SIZE];

    public int[][] indexPairs(String text, String[] words) {
        Arrays.fill(charToStartIndexes, new ArrayList<>());
        for (int i = 0; i < text.length(); ++i) {
            charToStartIndexes[text.charAt(i) - 'a'].add(i);
        }

        List<int[]> wordMatches = new ArrayList<>();
        findWordMatches(text, words, wordMatches);

        return listToSortedArray(wordMatches);
    }

    private void findWordMatches(String text, String[] words, List<int[]> wordMatches) {
        for (String word : words) {
            List<Integer> indexes = charToStartIndexes[word.charAt(0) - 'a'];
            for (int index : indexes) {
                if (text.startsWith(word, index)) {
                    wordMatches.add(new int[]{index, index + word.length() - 1});
                }
            }
        }
    }

    private int[][] listToSortedArray(List<int[]> wordMatches) {
        Collections.sort(wordMatches, (x, y) -> (x[0] == y[0]) ? (x[1] - y[1]) : (x[0] - y[0]));
        int[][] sortedWordMatches = new int[wordMatches.size()][2];
        for (int i = 0; i < sortedWordMatches.length; ++i) {
            sortedWordMatches[i] = wordMatches.get(i);
        }
        return sortedWordMatches;
    }
}
