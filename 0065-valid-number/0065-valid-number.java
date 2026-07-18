import java.util.Map;

class Solution {
    public boolean isNumber(String s) {
        // Define the state transitions using an array of Maps
        // States: 
        // 0: Start / Initial State
        // 1: Sign after start
        // 2: Digit before decimal point
        // 3: Decimal point with no digits before it
        // 4: Digit after decimal point
        // 5: Exponent character ('e'/'E')
        // 6: Sign after exponent
        // 7: Digit after exponent
        Map<String, Integer>[] dfa = new Map[] {
            Map.of("blank", 0, "sign", 1, "digit", 2, "dot", 3), // State 0
            Map.of("digit", 2, "dot", 3),                         // State 1
            Map.of("digit", 2, "dot", 4, "exponent", 5),          // State 2
            Map.of("digit", 4),                                   // State 3
            Map.of("digit", 4, "exponent", 5),                    // State 4
            Map.of("sign", 6, "digit", 7),                        // State 5
            Map.of("digit", 7),                                   // State 6
            Map.of("digit", 7)                                    // State 7
        };
        
        int currentState = 0;
        s = s.trim(); // Remove leading/trailing whitespaces if present
        
        for (char c : s.toCharArray()) {
            String inputType;
            if (Character.isDigit(c)) {
                inputType = "digit";
            } else if (c == '+' || c == '-') {
                inputType = "sign";
            } else if (c == '.' || c == ',') {
                inputType = "dot";
            } else if (c == 'e' || c == 'E') {
                inputType = "exponent";
            } else {
                return false; // Invalid character completely
            }
            
            // Check if there is a valid transition for the current inputType
            if (!dfa[currentState].containsKey(inputType)) {
                return false;
            }
            
            currentState = dfa[currentState].get(inputType);
        }
        
        // Valid end states where a number is successfully formed
        return currentState == 2 || currentState == 4 || currentState == 7;
    }
}