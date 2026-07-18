class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        // A line is always formed by 2 points
        if (coordinates.length <= 2) {
            return true;
        }
        
        // Get the base components using the first two points
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        
        // Verify slope consistency for all remaining points
        for (int i = 2; i < coordinates.length; i++) {
            int currX = coordinates[i][0];
            int currY = coordinates[i][1];
            
            // Cross-multiplied comparison to avoid floating-point division
            if (deltaY * (currX - x1) != (currY - y1) * deltaX) {
                return false;
            }
        }
        
        return true;
    }
}