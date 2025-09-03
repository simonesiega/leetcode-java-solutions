package array.medium;

import java.util.ArrayList;
import java.util.List;

/*
Problem: Letter Combinations of a Phone Number (LeetCode #17)

Text:
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order.

Examples:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Input: digits = ""
Output: []

Input: digits = "2"
Output: ["a","b","c"]

Constraints:
- 0 <= digits.length <= 4
- digits[i] is a digit in the range ['2', '9'].

----------------------------------------------------
Ragionamento:
- Per generare tutte le combinazioni possibili:
  - Backtracking: costruisce ricorsivamente le stringhe aggiungendo una lettera per ogni cifra.
  - Dopo che tutte le cifre sono state elaborate, aggiunge la combinazione completa alla lista dei risultati.


----------------------------------------------------
Complessità:
- Time: O(3^N), dove:
    - N = numero di cifre che mappano a 3 lettere (2,3,4,5,6,7,8,9)
- Space: O(n) per la profondità della ricorsione + spazio per l’output.
*/

public class Letter_Combinations_of_a_Phone_Number_17 {

    // Mappa digit-to-letters
    // Il primo numero non é 0, pertanto DIGIT_TO_LETTERS[digits.charAt(index) - '2'] per risalire alla Stringa corretta e non - '0'
    private static final String[] DIGIT_TO_LETTERS = {
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        // Caso 1: digits = "" → []
        if (digits == null || digits.isEmpty()) return res;

        // Avvio del backtracking
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    // Funzione di backtracking
    private static void backtrack(
            List<String> res,
            StringBuilder partial,
            String digits,
            int index
    )
    {
        // Caso base: tutte le cifre sono state processate
        if (index == digits.length()) {
            res.add(partial.toString());
            return;
        }

        String letters = DIGIT_TO_LETTERS[digits.charAt(index) - '2'];

        // Per ogni carattere avvia il backtracking
        for (char c : letters.toCharArray()) {
            partial.append(c);
            backtrack(res, partial, digits, index + 1);
            partial.deleteCharAt(partial.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(letterCombinations(""));   // []
        System.out.println(letterCombinations("2"));  // [a, b, c]
    }
}
