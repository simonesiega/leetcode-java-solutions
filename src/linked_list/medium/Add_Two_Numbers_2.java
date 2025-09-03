package linked_list.medium;

/*
Problem: Add Two Numbers (LeetCode #2)

Text:
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Examples:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Spiegazione: 342 + 465 = 807

Input: l1 = [0], l2 = [0]
Output: [0]

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

Constraints:
- Each linked list is non-empty and contains digits in reverse order.
- 1 <= l1.length, l2.length <= 100
- 0 <= Node.val <= 9
- It is guaranteed that the list represents a number without leading zeros.

----------------------------------------------------
Ragionamento:
- Per simulare l’addizione come se fosse fatta in colonna bisogna:
- Sommare i nodi di l1 e l2 (se esistono) + eventuale riporto.
- Creare un nuovo nodo con il risultato % 10 e aggiornare carry = somma / 10.
- La lista risultante sarà in ordine inverso, come richiesto.
----------------------------------------------------
Complessità:
- Tempo: O(max(m, n)), dove m e n sono le lunghezze delle due liste.
- Spazio: O(max(m, n)) per la lista risultante.
*/

public class Add_Two_Numbers_2 {

    // Definizione di ListNode (tipica su LeetCode)
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;

        int sum = l1.val + l2.val;
        int carry = sum / 10;

        // Primo nodo (testa)
        ListNode head = new ListNode(sum % 10);
        // current attualmente punta al primo elemento
        ListNode current = head;

        // Iterazione dal nodo dopo la testa alla coda
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            sum = x + y + carry;
            carry = sum / 10;

            // current avanza a differenza di head
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Se é presente un riporto, creazione di un nodo extra
        if (carry > 0) current.next = new ListNode(carry);

        return head;
    }

    public static void main(String[] args) {

        // Numero 342 rappresentato come 2 -> 4 -> 3
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        // Numero 465 rappresentato come 5 -> 6 -> 4
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);

        while (result != null) {
            System.out.print(result.val + (result.next != null ? " -> " : ""));
            result = result.next;
        }
    }
}
