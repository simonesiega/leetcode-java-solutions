package array.medium;

/*
Problem: ZigZag Conversion (LeetCode #6)

Text:
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Given a string s and an integer numRows, return the string formed after writing s in a zigzag pattern on numRows and reading line by line.

Examples:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

Input: s = "A", numRows = 1
Output: "A"

Constraints:
- 1 <= s.length <= 10^4
- s consists of English letters (lower-case and upper-case), ',' and '.'.
- 1 <= numRows <= 10^4

----------------------------------------------------
Ragionamento:
- La scrittura a zigzag è ripetitiva: ogni "ciclo" verticale+diagonale ha lunghezza
  cycleLen = 2 * numRows - 2 (per numRows > 1).
- Per la riga 0 e l'ultima riga i caratteri si trovano ad intervalli costanti pari a cycleLen.
- Per le righe centrali i caratteri si susseguono alternando due salti:
    saltoVerticale = cycleLen
    saltoDiagonale = cycleLen - 2 * row      dove row è l'indice della riga (0-based).
- Scorrendo riga per riga e calcolando le posizioni corrispondenti si ottiene la stringa risultante in O(n) tempo senza costruire l'intera matrice.
----------------------------------------------------
Complessità:
- Time: O(n), dove n = s.length().
- Space: O(n) per memorizzare la stringa risultato.
*/

public class Zigzag_Conversion_6 {

    public static String convert(String s, int numRows) {
        // no zigzag
        if (numRows == 1) return s;

        StringBuilder sb = new StringBuilder();
        int block = 2 * numRows - 2; // lunghezza di un ciclo completo (giù e su)
        int l = s.length();

        // Per ogni riga
        for (int i = 0; i < numRows; i++) {
            // Salta su ogni carattere in quella riga
            for (int j = i; j < l; j += block) {
                sb.append(s.charAt(j)); // carattere nella colonna

                // Righe centrali
                int second = j + block - 2 * i;
                if (i > 0 && i < numRows - 1 && second < l) {
                    sb.append(s.charAt(second)); // carattere singolo nella colonna
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
    }
}
