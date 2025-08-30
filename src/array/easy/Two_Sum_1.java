package array.easy;

import java.util.*;

/*
Problema: Two Sum

Testo:
Given an array of integers nums and an integer target, return indices of the two numbers
such that they add up to target.

You may assume that each input would have exactly one solution,
and you may not use the same element twice.

You can return the answer in any order.

Esempi:
Input: nums = [2,7,11,15], target = 9    -> Output: [0,1]
Input: nums = [3,2,4], target = 6        -> Output: [1,2]
Input: nums = [3,3], target = 6          -> Output: [0,1]

Constraints:
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- Only one valid answer exists.

----------------------------------------------------
Ragionamento:
- Per ogni elemento nums[i], cerco il suo "complemento" (target - nums[i]).
- Se il complemento è già stato analizzato in precedenza, ho trovato la coppia.
- Uso una HashMap per memorizzare i valori già incontrati e il loro indice.

- Un approccio sbagliato è usare due cicli for annidati per confrontare tutte le coppie:
  - Vantaggio → usa solo O(1) spazio.
  - Svantaggio → richiede O(n^2) tempo, troppo lento per n fino a 10^4.

----------------------------------------------------
Complessità:
- Tempo: O(n), perché scorro l’array una sola volta.
- Spazio: O(n), per memorizzare gli elementi nella HashMap.
*/

public class Two_Sum_1 {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> already_found = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (already_found.containsKey(complement)) {
                return new int[]{ already_found.get(complement), i };
            }

            already_found.put(nums[i], i);
        }

        // Secondo i vincoli non dovrebbe mai accadere
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
