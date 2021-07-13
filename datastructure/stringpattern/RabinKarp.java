package com.java.datastructure.stringpattern;

public class RabinKarp {

	private static final int D = 26;
	
	private static double calculatePow(int x, int y) {
		return Math.pow(x, y);
	}
	
	public static void search(String txt, String pattern, int q) {
		
		int m = pattern.length();
		int n = txt.length();
		double p = 0;
		double t = 0;
		int i, j;
		
		for (i = 0; i < m; i++) {
			
			p = (p + (pattern.charAt(i) * calculatePow(D,  m -i - 1))) % q;
			t = (t + (txt.charAt(i)) * calculatePow(D, m - i - 1)) % q;
		}

		for (i = 0; i <= n - m; i++) {
			
			if (p == t) {
				
				for (j = 0; j < m ; j++) {
					
					if (txt.charAt(i + j) != pattern.charAt(j))
						break;
				}
				
				if ( j == m) 
					System.out.println("Pattern is found at position: " + (i + 1));	// Result is 1 based, not zero based index.
			}
			
			if (i < n - m) {
				
				t = ((t - (txt.charAt(i) * calculatePow(D,  m - 1))) * D + txt.charAt(i + m)) % q;
				
				if (t < 0)
					t = (t + q);
			}
		}
	}
	
	public static void main(String[] args) {
		
		String txt = "appleaplap";
	    String pattern = "ap";
		int q = 13;
		
		search(txt, pattern, q);
	}
}
