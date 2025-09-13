package string.easy;

/*
Problem: Roman to Integer (LeetCode #13)

Text:
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example:
- 2 is written as II → just two ones added together.
- 12 is written as XII → X + II.
- 27 is written as XXVII → XX + V + II.

Roman numerals are usually written largest to smallest from left to right.
However, subtraction is used in six cases:
- I before V (5) and X (10) → 4, 9
- X before L (50) and C (100) → 40, 90
- C before D (500) and M (1000) → 400, 900

Given a roman numeral, convert it to an integer.

Examples:
Input: s = "III"
Output: 3
Explanation: III = 3.

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V = 5, III = 3.

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 e IV = 4.

Constraints:
- 1 <= s.length <= 15
- s contains only the characters ('I','V','X','L','C','D','M')
- It is guaranteed that s is a valid roman numeral in the range [1,3999]

----------------------------------------------------
Ragionamento:
- È necessario trasformare ogni simbolo romano nel suo valore numerico.
- Idea base: se il valore corrente < valore del simbolo successivo, allora va sottratto; altrimenti va sommato.
- Questa regola copre tutti i casi di notazione sottrattiva (IV, IX, XL, XC, CD, CM).

- Soluzione ricorsiva:
  - Funziona e rispetta la logica della sottrazione, ma ha problemi di efficienza:
    - Usa substring ad ogni chiamata → O(n²) in tempo e memoria.
    - Rischia stack overflow su input più grandi.
  - È comunque una soluzione valida e concettualmente elegante.

- Soluzione iterativa (più efficiente):
  - Scansione lineare della stringa da sinistra a destra.
  - Per ogni simbolo:
    - se < successivo → sottrarre
    - altrimenti → sommare
  - Una sola lettura della stringa, nessuna ricorsione, nessuna copia di stringa.

----------------------------------------------------
Complessità:
- Tempo: O(n), dove n è la lunghezza della stringa.
- Spazio: O(1).
*/


public class Roman_to_Integer_13 {

    public static int romanToInt(String s) {
        int total = 0;
        int l = s.length();

        for (int i = 0; i < l; i++) {
            int curr = convRom(s.charAt(i));
            int next = (i + 1 < l) ? convRom(s.charAt(i + 1)) : 0;

            // Se il numero corrente è minore del successivo, va sottratto altrimenti va sommato
            if (curr < next) total -= curr;
            else total += curr;
        }

        return total;
    }

    private static int convRom(char c) {
        return switch (c) {
            case 'M' -> 1000;
            case 'D' -> 500;
            case 'C' -> 100;
            case 'L' -> 50;
            case 'X' -> 10;
            case 'V' -> 5;
            case 'I' -> 1;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
