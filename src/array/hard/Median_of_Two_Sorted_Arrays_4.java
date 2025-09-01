package array.hard;

/*
Problem: Median of Two Sorted Arrays (LeetCode #4)

Text:
Given two sorted arrays nums1 and nums2 of size m and n respectively,
return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Examples:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Spiegazione: merged array = [1,2,3] → median = 2

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Spiegazione: merged array = [1,2,3,4] → median = (2+3)/2 = 2.5

Constraints:
- nums1.length == m
- nums2.length == n
- 0 <= m, n <= 1000
- 1 <= m + n <= 2000
- -10^6 <= nums1[i], nums2[i] <= 10^6
- Both nums1 and nums2 are sorted in non-decreasing order.

----------------------------------------------------
Ragionamento:
- Idea: usare la ricerca binaria per "partizionare" correttamente i due array.
- L'obiettivo è dividere i due array in due metà tali che:
  - tutti gli elementi a sinistra <= tutti gli elementi a destra
  - le due metà abbiano dimensione quasi uguale (max differenza di 1)
- Da lì, la mediana è:
  - max(left_part) se la lunghezza totale è dispari
  - media tra max(left_part) e min(right_part) se la lunghezza è pari.

- Approccio sbagliato: unire i due array ordinati e calcolare la mediana.
  - Complessità: O(m+n) in tempo, O(m+n) in spazio.
  - Ma il problema richiede O(log(m+n)).
----------------------------------------------------
Complessità:
- Tempo: O(log(min(m,n))) perché facciamo ricerca binaria solo sull’array più corto.
- Spazio: O(1).
*/

public class Median_of_Two_Sorted_Arrays_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 deve essere l’array più corto, così la ricerca binaria è più veloce
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        boolean odd = (m + n) % 2 == 1;


        // Numero di elementi che devono stare nella "parte sinistra"
        int halfLength = (m + n + 1) / 2;

        // Limiti della ricerca binaria su nums1
        int low = 0, high = m;

        while (low <= high) {
            // i = quanti elementi prendere da nums1
            // j = quanti elementi prendere da nums2
            // condizione (i + j) == halfLength
            int i = (low + high) / 2;
            int j = halfLength - i;

            // Bordo sinistro e destro di nums1
            int left1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            // Bordo sinistro e destro di nums2
            int left2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Caso: partizione corretta trovata
            if (left1 <= right2 && left2 <= right1) {
                if (odd) {
                    // mediana è max della parte sinistra
                    return Math.max(left1, left2);
                }

                else {
                    // media dei due valori centrali
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
            }

            // Troppi elementi presi da nums1
            else if (left1 > right2) {
                high = i - 1;
            }
            // Troppi pochi elementi presi da nums1
            else {
                low = i + 1;
            }
        }

        // Secondo i vincoli non dovrebbe mai accadere
        return 0;

    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

}
