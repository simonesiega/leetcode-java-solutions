package String.medium;

/*
Problem: Longest Substring Without Repeating Characters (LeetCode #3)

Text:
Given a string s, find the length of the longest substring
without repeating characters.

Examples:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:
- 0 <= s.length <= 5 * 10^4
- s consists of English letters, digits, symbols and spaces.

----------------------------------------------------
Ragionamento:
- Usare due puntatori (sliding window) e un array booleano [128] come flag per tracciare i caratteri presenti nella finestra corrente.
- Con `right` si espande la finestra, con `left` si riduce in caso di duplicati.
- Quando si incontra un duplicato, si rimuovono i caratteri da sinistra finché il duplicato non è eliminato.

Esempio passo-passo con s = "pwwkew":
- Inizio: left = 0, right = 0.
- Aggiunge 'p' → substring "p", maxLen = 1.
- Aggiunge 'w' → substring "pw", maxLen = 2.
- Aggiunge nuovamente 'w' → duplicato! toglie 'p' e poi 'w' da sinistra.
- Ora substring = "" e aggiunge il nuovo 'w'.
- Aggiunge 'k' → "wk", maxLen = 2.
- Aggiunge 'e' → "wke", maxLen = 3.
- Aggiunge 'w' → duplicato! toglie da sinistra finché valido → "kew", maxLen = 3.
- Risultato finale: 3.

----------------------------------------------------
Complexity:
- Time: O(n), ogni carattere viene elaborato al massimo due volte (una in ingresso e una in uscita).
- Space: O(1), poiché l’array di flag è di dimensione costante (128).
*/

public class Longest_Substring_Without_Repeating_Characters_3 {
    public static int lengthOfLongestSubstring(String s) {
        boolean[] flag = new boolean[128]; // flags ASCII - estendibile a n caratteri
        int l = 0, r = 0;
        int res = 0;

        while (r < s.length()) {
            char c = s.charAt(r);

            // Carattere non ancora incontrato - controlla se é la substring piú lunga e incrementa puntatore di destra
            if (!flag[c]) {
                flag[c] = true;
                res = Math.max(res, r - l + 1);
                r++;
            }
            // Carattere duplicato - incrementa puntatore di sinistra
            else {
                flag[s.charAt(l)] = false;
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
