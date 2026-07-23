class Solution {
    // Boolean matrix to store cached results: null = unvisited
    private Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        // Return cached result if already calculated
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean ans;

        // Base case: pattern exhausted
        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            // Check if current characters match
            boolean firstMatch = (i < s.length() && 
                (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));

            // Check if next character in pattern is '*'
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }

        // Memoize and return result
        return memo[i][j] = ans;
    }
}