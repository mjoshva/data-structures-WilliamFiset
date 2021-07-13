public class SubString {
    
    public static int[] buildPattern(String pattern) {
        
        int[] lps = new int[pattern.length()];
        int i = 1;
        int j = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                j++;
                i++;
            } else if (j > 0) {
                j = lps[j - 1];                    
            } else {
                lps[i] = 0;
                i++;
            }
        } 
        return lps;
    }
    
    public static boolean doesMatch(String string, String pattern) {
        int[] lps = buildPattern(pattern);
        int i = 0;
        int j = 0;
        while (i < string.length() && j < pattern.length()) {                        
            
            if (string.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
            if (j == pattern.length()) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        System.out.println(doesMatch("appple", "p"));
    }
}