package math.medium;

/*
Problem: Reverse Integer (LeetCode #7)

Text:
Given a signed 32-bit integer x, return x with its digits reversed.
If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Examples:
Input: x = 123
Output: 321

Input: x = -123
Output: -321

Input: x = 120
Output: 21

Constraints:
- -2^31 <= x <= 2^31 - 1

----------------------------------------------------
Ragionamento:
- Estrarre una cifra alla volta con x % 10.
- Prima di moltiplicare res per 10, controllare se causerebbe overflow.
- Se non c’è rischio, aggiornare res = res * 10 + ultima cifra.
- Dividere x per 10 per rimuovere la cifra già elaborata.

- In caso di overflow, restituire 0.

----------------------------------------------------
Complessità:
- Tempo: O(log10(n)), vengono elaborate tutte le cifre di x.
- Spazio: O(1).
*/

public class Reverse_Integer_7 {

    public static int reverse(int x) {
        int res = 0;

        while(x != 0){
            // Controllo overflow prima di moltiplicare per 10
            if (res > Integer.MAX_VALUE/10 || res < Integer.MIN_VALUE/10) return 0;

            // Aggiornamento res con ultima cifra e rimozione da x
            res = res * 10 + x % 10;
            x /= 10;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));

        // Overflow = 0
        System.out.println(reverse(1534236469));
    }
}
