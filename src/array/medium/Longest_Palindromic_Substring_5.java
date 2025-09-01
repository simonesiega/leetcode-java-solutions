package array.medium;

/*
Problem: Longest Palindromic Substring (LeetCode #5)

Text:
Given a string s, return the longest palindromic substring in s.

Examples:
Input: s = "babad"
Output: "bab"  (or "aba", both are valid)

Input: s = "cbbd"
Output: "bb"

Constraints:
- 0 <= s.length <= 10^4
- s consist of only digits and English letters.

----------------------------------------------------
Ragionamento:
- Si utilizza l’algoritmo di Manacher per trovare il palindromo più lungo in O(n).
- L’input viene preprocessato inserendo sentinelle e separatori, in modo da uniformare la gestione di palindromi di lunghezza pari e dispari.
- Si mantengono:
  - `center` e `rightBoundary` del palindromo più a destra trovato fino a quel momento.
  - `radius[i]` = raggio del palindromo centrato in posizione i nella stringa trasformata.
- Early stopping (ottimizzazioni per fermare prima l’algoritmo):
  - Se il palindromo trovato raggiunge o supera la lunghezza della stringa originale, non serve proseguire.
  - Se le posizioni rimanenti, anche nel caso migliore, non possono battere la lunghezza massima attuale, si interrompe.

https://www.geeksforgeeks.org/dsa/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
----------------------------------------------------
Complessità:
- Tempo: O(n).
- Spazio: O(n), per la stringa trasformata e l’array dei raggi.
*/

public class Longest_Palindromic_Substring_5 {

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";

        // Step 1: Preprocessing della stringa con sentinelle e separatori
        // - Si aggiunge '@' come limite iniziale e '$' come limite finale
        // - Si inserisce '#' tra ogni carattere per uniformare i casi di palindromi pari e dispari
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        for (char c : s.toCharArray()) {
            sb.append('#').append(c);
        }
        sb.append("#$");

        char[] t = sb.toString().toCharArray();

        int n = t.length;
        int[] radius = new int[n]; // radius[i] = raggio del palindromo centrato in i
        int center = 0, rightBoundary = 0;
        int maxLength = 0; // lunghezza massima trovata
        int maxCenterIndex = 0; // centro del palindromo massimo

        // Step 2: Iterazione della stringa (esclusi @ e $, compresi #)
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;

            // Inizializzazione del raggio: si sfruttano palindromi già calcolati
            if (i < rightBoundary) {
                radius[i] = Math.min(rightBoundary - i, radius[mirror]);
            }

            // Espansione intorno al centro i
            while (t[i + radius[i] + 1] == t[i - (radius[i] + 1)]) {
                radius[i]++;
            }

            // Aggiornamento del centro e del limite destro
            if (i + radius[i] > rightBoundary) {
                center = i;
                rightBoundary = i + radius[i];
            }

            // Aggiornamento del palindromo massimo trovato
            if (radius[i] > maxLength) {
                maxLength = radius[i];
                maxCenterIndex = i;
            }

            // Early stop: se il palindromo trovato copre l’intera stringa originale
            if (maxLength == s.length()) {
                break;
            }

            // Early exit: se le posizioni rimanenti non possono battere il massimo attuale
            int potentialMax = Math.min(n - 1 - i, i);
            if (potentialMax <= maxLength) {
                break;
            }
        }

        // Step 3: Ricostruzione della substring palindroma
        int start = (maxCenterIndex - maxLength) / 2;
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abba"));
    }
}
