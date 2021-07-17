import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node first;

    private Node last;

    private Integer size;

    public TransactionsLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void addTransaction(Transaction transaction) {
        if (first == null) {
            Node node = new Node(transaction, null, null);
            first = node;
            last = node;
        } else {
            Node node = new Node(transaction, last, null);
            last.next = node;
            last = node;
        }

        size++;
    }

    public void removeTransactionById(UUID id) throws TransactionNotFoundException {
        if (first != null) {
            Node tmp = first;

            if (tmp.isFind(id)) {
                if (tmp.next == null) {
                    tmp.transaction = null;
                    first = null;
                    last = null;
                    size = 0;
                    return;
                } else {
                    tmp.transaction = null;
                    first = tmp.next;
                    first.previous = null;
                    size--;
                    return;
                }
            }

            while (tmp.next != null) {
                tmp = tmp.next;
                if (tmp.isFind(id)) {
                    tmp.removeNode();
                    size--;
                    return;
                }
            }
        }

        throw new TransactionNotFoundException("Transaction not found!");
    }

    public Transaction[] transactionToArray() {
        Transaction[] array = new Transaction[size];

        if (first != null) {
            Node tmp = first;
            for (int i = 0; i < size; i++) {
                array[i] = tmp.transaction;
                tmp = tmp.next;
            }
        }

        return array;
    }

    private static class Node {
        Transaction transaction;

        Node next;

        Node previous;

        Node(Transaction transaction, Node previous, Node next) {
            this.transaction = transaction;
            this.previous = previous;
            this.next = next;
        }

        public boolean isFind(UUID id) {
            return transaction.getUuid().equals(id);
        }

        public void removeNode() {
            transaction = null;

            if (previous != null) {
                previous.next = next;
            }

            if (next != null) {
                next.previous = previous;
            }
        }
    }
}