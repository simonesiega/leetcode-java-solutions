package math.easy;

/*
Problem: Palindrome Number (LeetCode #9)

Text:
Given an integer x, return true if x is a palindrome, and false otherwise.

Examples:
Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Constraints:
- -2^31 <= x <= 2^31 - 1

----------------------------------------------------
Ragionamento:
- I numeri negativi non possono essere palindromi.
- Anche i numeri che finiscono per 0 (escluso 0 stesso) non possono essere palindromi.
- Tutti i numeri compresi tra 0 e 9 sono trivialmente palindromi (leggono uguali in entrambi i versi).

- Per evitare overflow e uso inutile di spazio, ribaltare soltanto la metà destra del numero.
- Confronto:
  - Se x == reversedHalf → palindromo con numero pari di cifre.
  - Se x == reversedHalf / 10 → palindromo con numero dispari di cifre.

----------------------------------------------------
Complessità:
- Tempo: O(log10(n)), vengono elaborate metá le cifre di x.
- Spazio: O(1).
*/

public class Palindrome_Number_9 {

    public static boolean isPalindrome(int x) {
        // Caso 1: negativi → non sono palindromi
        // Caso 2: numeri che finiscono con 0, eccetto lo 0 stesso → non palindromi
        // Caso 3: 0 < numero < 10 → non gestito da riga 48 ma non entra nel while e passa diretto al return riga 60
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        // Ribaltamento → vedere problema 7 (array/medium)
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        // Palindromo se le due metà coincidono
        // - x == reversedHalf → numero con cifre pari
        // - x == reversedHalf/10 → numero con cifre dispari (cifra centrale ignorata)
        return (x == rev || x == rev / 10);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(12321));
    }
}
