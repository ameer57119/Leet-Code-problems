import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] findWords(String[] words) {
        // Pre-calculated row index for each lowercase letter 'a' through 'z'
        int[] rowMap = {
            2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 
            3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3
        };

        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (word.isEmpty()) continue;
            
            // Get the row number for the first character
            int targetRow = rowMap[Character.toLowerCase(word.charAt(0)) - 'a'];
            boolean valid = true;

            for (int i = 1; i < word.length(); i++) {
                if (rowMap[Character.toLowerCase(word.charAt(i)) - 'a'] != targetRow) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}